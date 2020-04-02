package controler;

import datos.JugadorDaoJDBC;
import datos.JugadorPartidaDaoJDBC;
import datos.PartidaDaoJDBC;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Joc;
import model.Jugador;
import model.Partida;

@WebServlet("/JocControler")
public class JocControler extends HttpServlet {

    private Partida partida;
    private Jugador jugador;
    private Joc joc;
    private static int ronda;
    private static int punts;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("GET - JOC CONTROLER");
        HttpSession session = request.getSession();
        this.jugador = (Jugador) session.getAttribute("jugador");
        this.partida = (Partida) session.getAttribute("partida");

        System.out.println("Partida: " + this.partida.getHashPartida());
        System.out.println("Jugador: " + this.jugador.getNom());

        boolean llancat = false;
        String accio = request.getParameter("accio");
        switch (accio) {
            case "refrescar":
                //Primer actualitzam tots els jugadors que hi ha a la partida
                actualitzarJugadorsPartida(this.partida);
                //Si el creador ha començat i ha tancat la partida
                Partida partidaAux = new PartidaDaoJDBC().consultaPartida(this.partida.getHashPartida());
                if (partidaAux.isTancada()) {
                    this.partida.setTancada(true);
                    this.partida.setEnMarxa(true);
                    session.setAttribute("partida", this.partida);
                    request.getRequestDispatcher("joc.jsp").forward(request, response);
                } else {
                    //Si no està encara tancada
                    this.partida.setTancada(false);
                    this.partida.setEnMarxa(true);
                    session.setAttribute("partida", this.partida);
                    request.getRequestDispatcher("esperar.jsp").forward(request, response);
                }
                break;
            case "comencar":
                //Se comença la partida per part del creador
                //La tancam i l'actualitzam a la BD
                this.partida.setTancada(true);
                new PartidaDaoJDBC().actualizar(this.partida);
                //Indicam que encara no hem llançat
                llancat = false;
                session.setAttribute("partida", this.partida);
                request.setAttribute("llancat", llancat);
                request.getRequestDispatcher("joc.jsp").forward(request, response);
                break;
            case "llancar":
                //Hem de llancar, per això feim un joc nou
                this.punts = jocNou();
                this.jugador.setValorDau(this.punts);
                llancat = true;
                //Actualitzam el valors del jugador a la partida
                new JugadorPartidaDaoJDBC().actualizar(this.jugador, this.partida, this.punts, this.ronda);
                //Ho enviam al jsp
                request.setAttribute("llancat", llancat);
                request.setAttribute("valorDau", this.punts);
                session.setAttribute("jugador", this.jugador);
                request.getRequestDispatcher("joc.jsp").forward(request, response);
                break;
            case "continuar":
                //Hem de collir la partida/ronda actual
                //Crear un objecte amb tot i passar-ho al jsp
                System.out.println("--Continuar--");
                List<Jugador> jugadors = new JugadorPartidaDaoJDBC().listarJugadors(this.partida, this.ronda);
                System.out.println("Jugadors:" +  jugadors);
                request.setAttribute("ronda", this.ronda);
                request.setAttribute("jugadors", jugadors);
                request.getRequestDispatcher("estadistiques.jsp").forward(request, response);
                break;
            default:
                break;
        }
        session.setAttribute("partida", this.partida);
        session.setAttribute("jugador", this.jugador);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("POST - JOC CONTROLER");
        HttpSession session = request.getSession();
        this.jugador = (Jugador) session.getAttribute("jugador");
        String jsp = creaUneix(request, session);
        request.getRequestDispatcher(jsp).forward(request, response);
    }

    public String creaUneix(HttpServletRequest request, HttpSession session) {
        //Miram si hi hem passat la partida
        String jsp = "";
        //Si hem rebut la variable partida
        if (request.getParameterMap().containsKey("partida")) {
            int hashPartida = Integer.parseInt(request.getParameter("partida"));
            System.out.println("Partida: " + hashPartida);
            //Miram si hem rebut la variable crear
            if (request.getParameterMap().containsKey("crear")) {
                String crear = request.getParameter("crear");
                //Si s'ha de creat una partida
                if (crear.equals("true")) {
                    this.partida = new Partida(hashPartida);
                    gestioPartida(request, session, true);
                    jsp = "esperar.jsp";
                } else {
                    //Hem de revisar que no estigui tancada
                    //Collim les dades de la partida de la BD
                    this.partida = new PartidaDaoJDBC().consultaPartida(hashPartida);
                    //Si no està tancada
                    if (!this.partida.isTancada()) {
                        //També hem de revisar que estigui en marxa
                        if (this.partida.isEnMarxa()) {
                            gestioPartida(request, session, false);
                            jsp = "esperar.jsp";;
                        } else {
                            //Ho hem d'enviar a un jsp de partida encara no en marxa
                            jsp = "error.jsp";
                        }
                    } else {
                        //Ho hem d'enviar a un jsp de partida tancada
                        System.out.println("Partida tancada");
                        jsp = "error.jsp";
                    }
                }
                //O ens hem d'unir
            } else {
                System.out.println("POST - JOC CONTROLER - No s'ha passat CREAR");
            }
            System.out.println("POST - JOC CONTROLER - No s'ha passat IDSESSIO");
        }
        return jsp;
    }

    public void gestioPartida(HttpServletRequest request, HttpSession session, boolean creador) {

        this.jugador.setCreador(creador);
        if (creador) {
            //El creador ha de posar la partida com no tancada i en marxa
            this.partida.setEnMarxa(true);
            this.partida.setTancada(false);
            new PartidaDaoJDBC().insertar(this.partida);
            //Hem d'actualitzar la taula jugador-partida punts=0, ronda=1
            this.ronda = 1;
            this.punts = 0;
            System.out.println("*Jugador: " + this.jugador);
        }
        //Per cada jugador, sigui creador o no, l'hem d'afegir a la taula jugadorpartida
        new JugadorPartidaDaoJDBC().insertar(this.jugador, this.partida, this.punts, this.ronda);
        //Actualitzam els jugadors a la variable de sessio partida
        actualitzarJugadorsPartida(this.partida);
        //Feim partida com a variable de sessio   
        session.setAttribute("partida", this.partida);
    }

    public void actualitzarJugadorsPartida(Partida partida) {
        //Hem de revisar tots els jugadors de la BD a quina partida estan i afegir-los a la 
        //Classe partida que tenim en marxa
        List<Jugador> jugadors = new JugadorPartidaDaoJDBC().listarJugadors(partida, this.ronda);
        partida.actualitzaJugadors(jugadors);
    }

    public int jocNou() {
        this.joc = new Joc();
        this.joc.setPartida(this.partida);
        this.joc.llancarDaus(this.jugador);
        return this.jugador.getValorDau();
    }
    
}
