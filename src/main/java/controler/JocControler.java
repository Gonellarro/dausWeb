package controler;

import datos.JugadorDaoJDBC;
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
        request.getRequestDispatcher("esperar.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("POST - JOC CONTROLER");
        //Si s'ha creat una partida
        if (request.getParameterMap().containsKey("partida")) {
            System.out.println("partida" + request.getParameter("partida"));
            String partida = request.getParameter("partida");
            if (request.getParameter("partida").equals("0")) {
                System.out.println("Creador de partida");
            } else {
                String idSession = request.getParameter("partida");
                unirPartida(idSession, request);                
            }
            request.getRequestDispatcher("esperar.jsp").forward(request, response);
        }
    }

    public void unirPartida(String idSession, HttpServletRequest request) {
        HttpSession session = request.getSession(); 
        //Cercam la partida dins la BD on tengi l'ID que ens ha passat per formulari
        this.partida = new PartidaDaoJDBC().consultarIdSessio(idSession);   
        //Ara hem d'afegir l'usuari a la partida, però pensem que és una variable de sessio
        this.jugador = new Jugador();     
        this.jugador = (Jugador) session.getAttribute("jugador");
        this.jugador.setCreador(false);
        this.jugador.setIdPartida(idSession);
        //Afegim els jugadors que hi ha a la partida
        afegirJugadorsPartida(this.partida);
        //Enmagatzemam el jugador a la BD
        new JugadorDaoJDBC().insertar(this.jugador);
        //Actualitzam les variables de sessio          
        session.setAttribute("jugador", this.jugador);
        session.setAttribute("partida", this.partida);
    }

    public void afegirJugadorsPartida(Partida partida) {
        //Hem de revisar tots els jugadors de la BD a quina partida estan i afegir-los a la 
        //Classe partida que tenim en marxa
        List<Jugador> jugadors = new JugadorDaoJDBC().listar();
        for (Jugador jugador : jugadors) {
            String idSession = jugador.getIdPartida();
            if (partida.getIdSessio().equals(idSession)) {
                //Afegim aquest jugador a la partida
                partida.afegeixJugador(jugador);
                System.out.println("Jugador" + jugador);
            }
        }
        //Afegim també el jugador en curs
        partida.afegeixJugador(this.jugador);

    }

}
