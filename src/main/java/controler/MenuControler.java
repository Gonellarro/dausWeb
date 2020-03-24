package controler;

import datos.JugadorDaoJDBC;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Jugador;
import model.Partida;
import datos.PartidaDaoJDBC;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@WebServlet("/MenuControler")
public class MenuControler extends HttpServlet {

    private Partida partida;
    private Jugador jugador;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Si hem creat una nova partida
        if (request.getParameterMap().containsKey("triaPartida")) {
            if (request.getParameter("triaPartida").equals("crear")) {
                System.out.println("CREAR - MenuControler");
                //Si hem de crear una partida            
                //Collim el nombre de la sessio i la posam com ID de la partida
                String idSessio = request.getSession().getId();
                this.partida = new Partida(idSessio);
                crearPartida(request);
                //Anam al JSP amb les dades insertades: 
                request.getRequestDispatcher("creaPartida.jsp").forward(request, response);
            } else {
                System.out.println("UNIR - MenuControler");
                //Si ens hem d'unir a una partida hem de demanar el idSession
                //Per tant, hi ha una passa més que se resol al controlador del joc
                request.getRequestDispatcher("uneixPartida.jsp").forward(request, response);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nom;
        String avatar;

        //Si estam en el menu de nom/avatar
        if (request.getParameterMap().containsKey("nom")) {
            System.out.println("NOM - MenuControler");
            //Hauríem de mirar si no està ja en sessió, però ho deixam per més envant
            nom = request.getParameter("nom");
            avatar = request.getParameter("avatar");
            //Cream el jugador
            this.jugador = new Jugador();
            this.jugador.setNom(nom);
            this.jugador.setAvatar(avatar);

            System.out.println("Jugador registrat: " + this.jugador);
            //Feim que el jugador sigui una variable d'abast sessio
            HttpSession session = request.getSession();
            session.setAttribute("jugador", this.jugador);
            request.getRequestDispatcher("triaPartida.jsp").forward(request, response);
        }
    }

    public void crearPartida(HttpServletRequest request) {
        //Afegim el jugador com a participant de la partida
        this.partida.afegeixJugador(this.jugador);
        //Insertam la partida a la BD
        new PartidaDaoJDBC().insertar(this.partida);
        //Feim que la partida sigui una variable d'abast de sessio
        HttpSession session = request.getSession();
        session.setAttribute("partida", this.partida);
        //Com ha sigut crear, posam a true el valor del creador
        this.jugador.setCreador(true);
        //I posam a quina partida pertany
        this.jugador.setIdPartida(this.partida.getIdSessio());
        //L'enmagatzemam a la BD
        new JugadorDaoJDBC().insertar(this.jugador);
    }


}
