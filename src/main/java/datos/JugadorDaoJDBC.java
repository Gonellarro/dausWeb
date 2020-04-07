package datos;

import java.sql.*;
import java.util.*;
import model.Jugador;
import model.Partida;

public class JugadorDaoJDBC{

    private static final String SQL_SELECT = "SELECT * FROM jugador";
    private static final String SQL_SELECT_BY_ID = "SELECT nom, avatar, creador, guanyador FROM jugador WHERE hashJugador = ?";
    private static final String SQL_INSERT = "INSERT INTO jugador (nom, avatar, hashJugador, creador, guanyador) VALUES(?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE jugador SET nom=?, avatar=?, creador=?, guanyador=? WHERE hashJugador=?";

    public List<Jugador> listar() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Jugador jugador = null;
        List<Jugador> jugadors = new ArrayList<>();
        try {
            
            //SELECT * FROM jugador
            
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                String nom = rs.getString("nom");
                String avatar = rs.getString("avatar");
                int hashJugador = rs.getInt("hashJugador");
                boolean creador = rs.getBoolean("creador");
                boolean guanyador = rs.getBoolean("guanyador");

                jugador = new Jugador();
                jugador.setNom(nom);
                jugador.setAvatar(avatar);
                jugador.setHashJugador(hashJugador);
                jugador.setCreador(creador);
                jugador.setGuanyador(guanyador);
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
            
            //SELECT nom, avatar, creador, guanyador FROM jugador WHERE hashJugador = ?
            
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setInt(1, hashJugador);
            rs = stmt.executeQuery();
            while (rs.next()) {
                jugador = new Jugador();
                jugador.setNom(rs.getString("nom"));
                jugador.setAvatar(rs.getString("avatar"));
                jugador.setCreador(rs.getBoolean("creador"));
                jugador.setGuanyador(rs.getBoolean("guanyador"));
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
            
            //INSERT INTO jugador (nom, avatar, hashJugador, creador, guanyador) VALUES(?, ?, ?, ?, ?)
            
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, jugador.getNom());
            stmt.setString(2, jugador.getAvatar());
            stmt.setInt(3, jugador.getHashJugador());
            stmt.setBoolean(4, jugador.isCreador());
            stmt.setBoolean(5, jugador.isGuanyador());
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
            
            //UPDATE jugador SET nom=?, avatar=?, creador=?, guanyador=? WHERE hashJugador=?
            
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, jugador.getNom());
            stmt.setString(2, jugador.getAvatar());
            stmt.setInt(5, jugador.getHashJugador());
            stmt.setBoolean(3, jugador.isCreador());
            stmt.setBoolean(4, jugador.isGuanyador());
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
