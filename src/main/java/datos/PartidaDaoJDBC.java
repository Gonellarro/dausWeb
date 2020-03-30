package datos;

import java.sql.*;
import java.util.*;
import model.Partida;

public class PartidaDaoJDBC{

    private static final String SQL_SELECT = "SELECT punts, idSession, enMarxa FROM partida";
    private static final String SQL_SELECT_BY_ID = "SELECT punts, enMarxa FROM partida WHERE idSession = ?";
    private static final String SQL_INSERT = "INSERT INTO partida (punts, idSession, enMarxa) VALUES(?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE partida SET punts=?, enMarxa=? WHERE idSession=?";

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
                String idSessio = rs.getString("idSession");
                int punts = rs.getInt("punts");
                boolean enMarxa = rs.getBoolean("enMarxa");

                partida = new Partida(idSessio);
                partida.setEnMarxa(enMarxa);
                partida.setPunts(punts);
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
            stmt.setInt(1, partida.getPunts());
            stmt.setString(2, partida.getIdSessio());
            stmt.setBoolean(3, partida.isEnMarxa());
            row = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
    }

    public Partida consultaPartida(String idSessio) {
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

    public void actualizar(Partida partida) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setInt(1, partida.getPunts());
            stmt.setBoolean(2, partida.isEnMarxa());
            stmt.setString(3, partida.getIdSessio());
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
