package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import BD.Conexion;
import domain.Departamento;
import static BD.Conexion.*;

public class DepartamentoDAO {

    private static final String QUERY = "SELECT cod_departamento, nombre, descripcion FROM departamento";
    private static final String SQL_INSERT = "INSERT INTO departamento(cod_departamento, nombre, descripcion) VALUES (?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE departamento SET nombre=?, descripcion=? WHERE cod_departamento=?";
    private static final String SQL_DELETE = "DELETE FROM departamento WHERE cod_departamento=?";

    public List<Departamento> consultar() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Departamento departamento = null;
        List<Departamento> departamentos = new ArrayList<>();

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(QUERY);
            rs = stmt.executeQuery();

            while (rs.next()) {
                String codDepartamento = rs.getString("cod_departamento");
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");

                departamento = new Departamento(codDepartamento, nombre, descripcion);
                departamentos.add(departamento);
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

        return departamentos;
    }

    public int insertar(Departamento departamento) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);

            stmt.setString(1, departamento.getCodDepartamento());
            stmt.setString(2, departamento.getNombre());
            stmt.setString(3, departamento.getDescripcion());

            registros = stmt.executeUpdate();
            if (registros > 0) {
                System.out.println("Número de registros insertados: " + registros);
            } else {
                System.out.println("No se ha podido insertar el departamento");
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

    public int actualizar(Departamento departamento) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);

            stmt.setString(1, departamento.getNombre());
            stmt.setString(2, departamento.getDescripcion());
            stmt.setString(3, departamento.getCodDepartamento());

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

    public int eliminar(String codDepartamento) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setString(1, codDepartamento);
            registros = stmt.executeUpdate();

            if (registros > 0) {
                System.out.println("Número de registros eliminados: " + registros);
            } else {
                System.out.println("No se ha podido eliminar el departamento");
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

