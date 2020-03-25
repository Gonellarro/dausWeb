package datos;

import java.sql.*;
import java.util.*;
import model.Jugador;
import model.Partida;

public class JugadorPartidaDaoJDBC {

    private static final String SQL_SELECT_BY_JUGADOR = "SELECT idPartida FROM jugadorpartida where idJugador = ?";
    private static final String SQL_SELECT_BY_PARTIDA = "SELECT idJugador FROM jugadorpartida WHERE idPartida = ?";
    private static final String SQL_INSERT = "INSERT INTO jugadorpartida (idJugador, idPartida, creador) VALUES(?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE jugadorpartida SET idJugador=?, creador=? WHERE idPartida=?";

    public List<Jugador> listarJugadors(Partida partida) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Jugador> jugadors = new ArrayList<>();
        Jugador jugador;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_PARTIDA);
            stmt.setString(1, partida.getIdSessio());
            rs = stmt.executeQuery();

            DatabaseMetaData metaData = conn.getMetaData();

            // Retrieves the maximum number of concurrent
            // connections to this database that are possible.
            // A result of zero means that there is no limit or
            // the limit is not known.
            int max = metaData.getMaxConnections();
            System.out.println("Max concurrent connections: " + max);

            while (rs.next()) {
                String idJugador = rs.getString("idJugador");

                jugador = new JugadorDaoJDBC().consultaJugador(idJugador);
                //jugador.setIdSessio(idJugador);
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
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Partida> partides = new ArrayList<>();
        Partida partida;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_JUGADOR);
            stmt.setString(1, jugador.getIdSessio());
            rs = stmt.executeQuery();
            while (rs.next()) {
                String idPartida = rs.getString("idPartida");

                partida = new PartidaDaoJDBC().consultaPartida(idPartida);
                partida.setIdSessio(idPartida);
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

    public void insertar(Jugador jugador, Partida partida) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int row = 0;
        System.out.println("INSERTAR JUGADORPARIDA - JUGADORPARTIDADAOJDBC");
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, jugador.getIdSessio());
            stmt.setBoolean(3, jugador.isCreador());
            stmt.setString(2, partida.getIdSessio());

            row = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
    }
}
