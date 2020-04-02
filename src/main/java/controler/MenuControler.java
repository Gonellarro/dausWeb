package controler;

import datos.JugadorDaoJDBC;
import datos.Utils;
import java.io.IOException;
import java.time.Clock;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Jugador;

@WebServlet("/MenuControler")
public class MenuControler extends HttpServlet {

    private Jugador jugador;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Si hem creat una nova partida
        if (request.getParameterMap().containsKey("triaPartida")) {
            if (request.getParameter("triaPartida").equals("crear")) {
                //Si hem de crear una partida  
                //Cream el hash de l'hora més el valor numèric de nom del jugador i del seu avatar
                Clock clock = Clock.systemDefaultZone();
                int hashPartida = clock.hashCode() + new Utils().S2I("crear" + this.jugador.getNom() + this.jugador.getAvatar());
                request.setAttribute("hashPartida", hashPartida);
                //Anam al JSP amb les dades insertades 
                request.getRequestDispatcher("creaPartida.jsp").forward(request, response);
            } else {
                //Si ens hem d'unir a una partida hem de demanar el hash de la partida
                //Per tant, hi ha una passa més que se resol al controlador del joc
                request.getRequestDispatcher("uneixPartida.jsp").forward(request, response);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Aquí començam amb la recepció de l'usuari i el seu avatar
        //Hem de crear el jugador com a Bean i l'insertam a la BD
        crearJugador(request);
        //Feim que el jugador sigui una variable d'abast sessio 
        HttpSession session = request.getSession();
        session.setAttribute("jugador", this.jugador);
        //Anam a triar la partida
        request.getRequestDispatcher("triaPartida.jsp").forward(request, response);

    }

    public void crearJugador(HttpServletRequest request) {
        String nom;
        String avatar;
        //Si estam en el menu de nom/avatar
        if (request.getParameterMap().containsKey("nom")) {
            //Hauríem de mirar si no està ja en sessió, però ho deixam per més envant
            nom = request.getParameter("nom");
            avatar = request.getParameter("avatar");
            //Cream el jugador a nivell de Bean
            this.jugador = new Jugador();
            this.jugador.setNom(nom);
            this.jugador.setAvatar(avatar);
            //Assignar hash al jugador
            //Primer cream un un obejcte Clock per demanar la data del moment
            Clock clock = Clock.systemDefaultZone();
            //Cream el hash de l'hora més el valor numèric de nom del jugador i del seu avatar
            int hashJugador = clock.hashCode() + new Utils().S2I(nom + avatar);
            //Assignam el hash al jugador
            this.jugador.setHashJugador(hashJugador);
            //Enmagatzemam el jugador a la BD
            new JugadorDaoJDBC().insertar(this.jugador);
            System.out.println("Jugador registrat: " + this.jugador);
        }
    }
}
