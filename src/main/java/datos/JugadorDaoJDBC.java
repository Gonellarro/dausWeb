package datos;

import java.sql.*;
import java.util.*;
import model.Jugador;
import model.Partida;

public class JugadorDaoJDBC{

    private static final String SQL_SELECT = "SELECT nom, avatar, idPartida FROM jugador";
    private static final String SQL_SELECT_BY_ID = "SELECT nom, avatar FROM jugador WHERE idPartida = ?";
    private static final String SQL_INSERT = "INSERT INTO jugador (nom, avatar, idPartida) VALUES(?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE jugador SET nom=?, avatar=? WHERE idPartida=?";

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
                String idPartida = rs.getString("idPartida");

                jugador = new Jugador();
                jugador.setNom(nom);
                jugador.setAvatar(avatar);
                jugador.setIdPartida(idPartida);
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
            stmt.setString(3, jugador.getIdPartida());
            row = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
    }

    public Partida consultarIdSessio(String idSessio) {
        Partida partida = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setString(1, idSessio);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int punts = rs.getInt("punts");
                boolean enMarxa = rs.getBoolean("enMarxa");
                partida = new Partida(idSessio);
                partida.setEnMarxa(enMarxa);
                partida.setPunts(punts);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return partida;
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
            stmt.setString(3, jugador.getIdPartida());
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
