package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import BD.Conexion;
import domain.Edificio;
import static BD.Conexion.*;

public class EdificioDAO {

    private static final String QUERY = "SELECT cod_edificio, nombre FROM edificio";
    private static final String SQL_INSERT = "INSERT INTO edificio(cod_edificio, nombre) VALUES (?, ?)";
    private static final String SQL_UPDATE = "UPDATE edificio SET nombre=? WHERE cod_edificio=?";
    private static final String SQL_DELETE = "DELETE FROM edificio WHERE cod_edificio=?";

    public List<Edificio> consultar() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Edificio edificio = null;
        List<Edificio> edificios = new ArrayList<>();

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(QUERY);
            rs = stmt.executeQuery();

            while (rs.next()) {
                String codEdificio = rs.getString("cod_edificio");
                String nombre = rs.getString("nombre");

                edificio = new Edificio(codEdificio, nombre);
                edificios.add(edificio);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            try {
                close(rs);
                close(stmt);
                close(conn);
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }
        }

        return edificios;
    }

    public int insertar(Edificio edificio) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);

            stmt.setString(1, edificio.getCodEdificio());
            stmt.setString(2, edificio.getNombre());

            registros = stmt.executeUpdate();
            if (registros > 0) {
                System.out.println("Número de registros insertados: " + registros);
            } else {
                System.out.println("No se ha podido insertar el edificio");
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            try {
                close(stmt);
                close(conn);
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }
        }
        return registros;
    }

    public int actualizar(Edificio edificio) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);

            stmt.setString(1, edificio.getNombre());
            stmt.setString(2, edificio.getCodEdificio());

            System.out.println("Ejecutando consulta " + SQL_UPDATE);
            registros = stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(System.out);

        } finally {
            try {
                close(stmt);
                close(conn);
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }
        }

        return registros;
    }

    public int eliminar(String codEdificio) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setString(1, codEdificio);
            registros = stmt.executeUpdate();

            if (registros > 0) {
                System.out.println("Número de registros eliminados: " + registros);
            } else {
                System.out.println("No se ha podido eliminar el edificio");
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            try {
                close(stmt);
                close(conn);
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }
        }
        return registros;
    }
}
