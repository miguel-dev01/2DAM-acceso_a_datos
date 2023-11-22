package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import BD.Conexion;
import domain.Curso;
import static BD.Conexion.*;

public class CursoDAO {

    private static final String QUERY = "SELECT cod_curso, nombre, descripcion FROM curso";
    private static final String SQL_INSERT = "INSERT INTO curso(cod_curso, nombre, descripcion) VALUES (?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE curso SET nombre=?, descripcion=? WHERE cod_curso=?";
    private static final String SQL_DELETE = "DELETE FROM curso WHERE cod_curso=?";

    public List<Curso> consultar() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Curso curso = null;
        List<Curso> cursos = new ArrayList<>();

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(QUERY);
            rs = stmt.executeQuery();

            while (rs.next()) {
                String codCurso = rs.getString("cod_curso");
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");

                curso = new Curso(codCurso, nombre, descripcion);
                cursos.add(curso);
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

        return cursos;
    }

    public int insertar(Curso curso) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);

            stmt.setString(1, curso.getCodCurso());
            stmt.setString(2, curso.getNombre());
            stmt.setString(3, curso.getDescripcion());

            registros = stmt.executeUpdate();
            if (registros > 0) {
                System.out.println("Número de registros insertados: " + registros);
            } else {
                System.out.println("No se ha podido insertar el curso");
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

    public int actualizar(Curso curso) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);

            stmt.setString(1, curso.getNombre());
            stmt.setString(2, curso.getDescripcion());
            stmt.setString(3, curso.getCodCurso());

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

    public int eliminar(String codCurso) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setString(1, codCurso);
            registros = stmt.executeUpdate();

            if (registros > 0) {
                System.out.println("Número de registros eliminados: " + registros);
            } else {
                System.out.println("No se ha podido eliminar el curso");
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
