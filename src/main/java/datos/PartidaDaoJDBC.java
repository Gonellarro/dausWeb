package datos;

import java.sql.*;
import java.util.*;
import model.Partida;

public class PartidaDaoJDBC{

    private static final String SQL_SELECT = "SELECT hashPartida, enMarxa, tancada FROM partida";
    private static final String SQL_SELECT_BY_ID = "SELECT enMarxa, tancada FROM partida WHERE hashPartida = ?";
    private static final String SQL_INSERT = "INSERT INTO partida (hashPartida, enMarxa, tancada) VALUES(?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE partida SET enMarxa=?, tancada=? WHERE hashPartida=?";

    public List<Partida> listar() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Partida partida = null;
        List<Partida> partides = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int hashPartida = rs.getInt("hashPartida");
                boolean enMarxa = rs.getBoolean("enMarxa");
                boolean tancada = rs.getBoolean("tancada");

                partida = new Partida(hashPartida);
                partida.setEnMarxa(enMarxa);
                partida.setTancada(tancada);
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

    public void insertar(Partida partida) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int row = 0;
        System.out.println("INSERTAR PARTIDA - PARTIDADAOJDBC");
        System.out.println("Partida:" + partida);
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, partida.getHashPartida());
            stmt.setBoolean(2, partida.isEnMarxa());
            stmt.setBoolean(3, partida.isTancada());
            row = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
    }

    public Partida consultaPartida(int hashPartida) {
        Partida partida = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setInt(1, hashPartida);
            rs = stmt.executeQuery();
            while (rs.next()) {
                boolean enMarxa = rs.getBoolean("enMarxa");
                boolean tancada = rs.getBoolean("tancada");
                partida = new Partida(hashPartida);
                partida.setEnMarxa(enMarxa);
                partida.setTancada(tancada);
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

    public void actualizar(Partida partida) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setBoolean(1, partida.isEnMarxa());
            stmt.setBoolean(2, partida.isTancada());
            stmt.setInt(3, partida.getHashPartida());
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
    }

}
