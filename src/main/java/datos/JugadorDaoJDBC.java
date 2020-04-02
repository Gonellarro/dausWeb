package datos;

import java.sql.*;
import java.util.*;
import model.Jugador;
import model.Partida;

public class JugadorDaoJDBC{

    private static final String SQL_SELECT = "SELECT nom, avatar, hashJugador FROM jugador";
    private static final String SQL_SELECT_BY_ID = "SELECT nom, avatar FROM jugador WHERE hashJugador = ?";
    private static final String SQL_INSERT = "INSERT INTO jugador (nom, avatar, hashJugador) VALUES(?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE jugador SET nom=?, avatar=? WHERE hashJugador=?";

    public List<Jugador> listar() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Jugador jugador = null;
        List<Jugador> jugadors = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                String nom = rs.getString("nom");
                String avatar = rs.getString("avatar");
                int hashJugador = rs.getInt("hashJugador");

                jugador = new Jugador();
                jugador.setNom(nom);
                jugador.setAvatar(avatar);
                jugador.setHashJugador(hashJugador);
                jugadors.add(jugador);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return jugadors;
    }
    
    public Jugador consultaJugador(int hashJugador){
        Jugador jugador = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setInt(1, hashJugador);
            rs = stmt.executeQuery();
            while (rs.next()) {
                jugador = new Jugador();
                jugador.setNom(rs.getString("nom"));
                jugador.setAvatar(rs.getString("avatar"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return jugador;        
    }

    public void insertar(Jugador jugador) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int row = 0;
        System.out.println("INSERTAR JUGADOR - JUGADORDAOJDBC");
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, jugador.getNom());
            stmt.setString(2, jugador.getAvatar());
            stmt.setInt(3, jugador.getHashJugador());
            row = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
    }

    public void actualizar(Jugador jugador) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, jugador.getNom());
            stmt.setString(2, jugador.getAvatar());
            stmt.setInt(3, jugador.getHashJugador());
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        //return rows;
    }
}
