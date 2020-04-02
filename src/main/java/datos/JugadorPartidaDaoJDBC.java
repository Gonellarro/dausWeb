package datos;

import java.sql.*;
import java.util.*;
import model.Jugador;
import model.Partida;

public class JugadorPartidaDaoJDBC {

    private static final String SQL_SELECT = "SELECT * FROM jugadorpartida where hashJugador = ? AND hashPartida = ? AND ronda = ?";
    private static final String SQL_SELECT_BY_JUGADOR = "SELECT hashPartida FROM jugadorpartida where hashJugador = ?";
    private static final String SQL_SELECT_BY_PARTIDA = "SELECT hashJugador FROM jugadorpartida WHERE hashPartida = ?";
    private static final String SQL_INSERT = "INSERT INTO jugadorpartida (hashJugador, hashPartida, creador, punts, ronda) VALUES(?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE jugadorpartida SET punts=? WHERE hashPartida=? AND hashJugador=? AND  ronda=?";

    public int consultaPunts(Jugador jugador, Partida partida, int ronda) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int punts = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            stmt.setInt(1, jugador.getHashJugador());
            stmt.setInt(2, partida.getHashPartida());
            stmt.setInt(3, ronda);
            rs = stmt.executeQuery();

            while (rs.next()) {
                punts = rs.getInt("punts");
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return punts;
    }

    public List<Jugador> listarJugadors(Partida partida) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Jugador> jugadors = new ArrayList<>();
        Jugador jugador;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_PARTIDA);
            stmt.setInt(1, partida.getHashPartida());
            rs = stmt.executeQuery();

            while (rs.next()) {
                String hashJugador = rs.getString("hashJugador");

                jugador = new JugadorDaoJDBC().consultaJugador(hashJugador);
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

    public List<Partida> listarPartides(Jugador jugador) {
        //AQuest llistat només és de partides en marxa
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Partida> partides = new ArrayList<>();
        Partida partida;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_JUGADOR);
            stmt.setInt(1, jugador.getHashJugador());
            rs = stmt.executeQuery();
            while (rs.next()) {
                int hashPartida = rs.getInt("hashPartida");
                partida = new PartidaDaoJDBC().consultaPartida(hashPartida);
                partida.setHashPartida(hashPartida);
                partides.add(partida);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return partides;
    }

    public void insertar(Jugador jugador, Partida partida, int punts, int ronda) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int row = 0;
        System.out.println("INSERTAR JUGADORPARIDA - JUGADORPARTIDADAOJDBC");
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            System.out.println("Jugador: " + jugador);
            stmt.setInt(1, jugador.getHashJugador());
            stmt.setInt(2, partida.getHashPartida());
            stmt.setBoolean(3, jugador.isCreador());
            stmt.setInt(4, punts);
            stmt.setInt(5, ronda);

            row = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
         System.out.println("INSERTAT JUGADORPARIDA - JUGADORPARTIDADAOJDBC");
    }

    public void actualizar(Jugador jugador, Partida partida, int punts, int ronda) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setInt(1, punts);
            stmt.setInt(4, ronda);
            stmt.setInt(2, partida.getHashPartida());
            stmt.setInt(3, jugador.getHashJugador());
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
    }
}
