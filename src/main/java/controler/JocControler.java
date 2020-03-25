package controler;

import datos.JugadorDaoJDBC;
import datos.JugadorPartidaDaoJDBC;
import datos.PartidaDaoJDBC;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Jugador;
import model.Partida;

@WebServlet("/JocControler")
public class JocControler extends HttpServlet {

    private Partida partida;
    private Jugador jugador;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Si hem creat una nova partida
        System.out.println("GET - JOC CONTROLER");
        String accio = request.getParameter("accio");
        switch (accio) {
            case "refrescar":

                actualitzarJugadorsPartida(this.partida);
                //Collim s'estat de sa partida de la BD
                Partida partidaAux = new PartidaDaoJDBC().consultaPartida(this.partida.getIdSessio());
                HttpSession session = request.getSession();
                if (partidaAux.isEnMarxa()) {
                    this.partida.setEnMarxa(true);
                    //Actualitzam les variables de sessio   
                    session.setAttribute("partida", this.partida);
                    request.getRequestDispatcher("joc.jsp").forward(request, response);
                } else {
                    //Actualitzam les variables de sessio   
                    session.setAttribute("partida", this.partida);
                    request.getRequestDispatcher("esperar.jsp").forward(request, response);
                }

                break;
            case "comencar":

                this.partida.setEnMarxa(true);
                new PartidaDaoJDBC().actualizar(this.partida);
                request.getRequestDispatcher("joc.jsp").forward(request, response);

                break;
            default:
                break;

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("POST - JOC CONTROLER");

        //if (request.getParameterMap().containsKey("idSessio")) {
        String idSessio = request.getParameter("idSessio");
        String crear = request.getParameter("crear");
        System.out.println("IDSESSION - JOC CONTROLER:" + idSessio);
        System.out.println("IDSESSION - JOC CONTROLER:" + crear);
        //Si s'ha creat una partida
        if (crear.equals("true")) {
            System.out.println("POST - JOC CONTROLER - Creador de partida - " + idSessio);
            gestioPartida(idSessio, request, true);
        } else {
            System.out.println("POST - JOC CONTROLER - Unir-se a partida - " + idSessio);
            gestioPartida(idSessio, request, false);
        }
        request.getRequestDispatcher("esperar.jsp").forward(request, response);
    }

    public void gestioPartida(String idSessio, HttpServletRequest request, boolean creador) {
        String idSessioJug = "";
        if (creador) {
            this.partida = new Partida(idSessio);
            new PartidaDaoJDBC().insertar(this.partida);
        } else {
            //Cercam la partida dins la BD on tengi l'ID que ens ha passat per formulari
            this.partida = new PartidaDaoJDBC().consultaPartida(idSessio);
            idSessioJug = request.getSession().getId();
        }
        //Ara hem d'afegir l'usuari a la partida, però pensem que és una variable de sessio
        //Per tant, capturam les seves dades introduides i les tractam
        HttpSession session = request.getSession();
        this.jugador = new Jugador();
        this.jugador = (Jugador) session.getAttribute("jugador");
        //Indicam qui es el creador
        this.jugador.setCreador(creador);
        //Indicam a quina partida pertany
        if (creador) {
            this.jugador.setIdSessio(idSessio);
        } else {
            this.jugador.setIdSessio(idSessioJug);
        }
        //Afegim els jugadors que hi ha a la partida
        this.partida.afegeixJugador(jugador);
        //Enmagatzemam el jugador a la BD
        new JugadorDaoJDBC().insertar(this.jugador);
        //Hem d'actualitzar la taula jugador-partida
        new JugadorPartidaDaoJDBC().insertar(this.jugador, this.partida);
        //Actualitzam les variables de sessio          
        session.setAttribute("jugador", this.jugador);
        session.setAttribute("partida", this.partida);
    }

    public void actualitzarJugadorsPartida(Partida partida) {
        //Hem de revisar tots els jugadors de la BD a quina partida estan i afegir-los a la 
        //Classe partida que tenim en marxa
        List<Jugador> jugadors = new JugadorPartidaDaoJDBC().listarJugadors(partida);
        System.out.println("Jugadors: " + jugadors);
        partida.actualitzaJugadors(jugadors);
    }

}
