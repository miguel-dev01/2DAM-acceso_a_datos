package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import BD.Conexion;
import domain.Asignatura;
import static BD.Conexion.*;

public class AsignaturaDAO {

    private static final String QUERY = "SELECT cod_asignatura, cod_interno, descripcion, nHoras, cod_curso FROM asignatura";
    private static final String SQL_INSERT = "INSERT INTO asignatura(cod_asignatura, cod_interno, descripcion, nHoras, cod_curso) VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE asignatura SET cod_interno=?, descripcion=?, nHoras=?, cod_curso=? WHERE cod_asignatura=?";
    private static final String SQL_DELETE = "DELETE FROM asignatura WHERE cod_asignatura=?";

    public List<Asignatura> consultar() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Asignatura asignatura = null;
        List<Asignatura> asignaturas = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(QUERY);
            rs = stmt.executeQuery();

            while (rs.next()) {
                String codAsignatura = rs.getString("cod_asignatura");
                String codInterno = rs.getString("cod_interno");
                String descripcion = rs.getString("descripcion");
                int nHoras = rs.getInt("nHoras");
                String codCurso = rs.getString("cod_curso");

                asignatura = new Asignatura(codAsignatura, codInterno, descripcion, nHoras, codCurso);
                asignaturas.add(asignatura);
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

        return asignaturas;
    }

    public int insertar(Asignatura asignatura) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);

            stmt.setString(1, asignatura.getCodAsignatura());
            stmt.setString(2, asignatura.getCodInterno());
            stmt.setString(3, asignatura.getDescripcion());
            stmt.setInt(4, asignatura.getNHoras());
            stmt.setString(5, asignatura.getCodCurso());

            registros = stmt.executeUpdate();
            if (registros > 0) {
                System.out.println("Número de registros insertados: " + registros);
            } else {
                System.out.println("No se ha podido insertar la asignatura");
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

    public int actualizar(Asignatura asignatura) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);

            stmt.setString(1, asignatura.getCodInterno());
            stmt.setString(2, asignatura.getDescripcion());
            stmt.setInt(3, asignatura.getNHoras());
            stmt.setString(4, asignatura.getCodCurso());
            stmt.setString(5, asignatura.getCodAsignatura());

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

    public int eliminar(String codAsignatura) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setString(1, codAsignatura);
            registros = stmt.executeUpdate();

            if (registros > 0) {
                System.out.println("Número de registros eliminados: " + registros);
            } else {
                System.out.println("No se ha podido eliminar la asignatura");
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
