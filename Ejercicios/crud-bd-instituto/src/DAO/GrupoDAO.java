package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import BD.Conexion;
import domain.Grupo;
import static BD.Conexion.*;

public class GrupoDAO {

    private static final String QUERY = "SELECT cod_grupo, cod_curso, nombre, cod_turno, nMaxAlumnos FROM grupo";
    private static final String SQL_INSERT = "INSERT INTO grupo(cod_grupo, cod_curso, nombre, cod_turno, nMaxAlumnos) VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE grupo SET cod_curso=?, nombre=?, cod_turno=?, nMaxAlumnos=? WHERE cod_grupo=?";
    private static final String SQL_DELETE = "DELETE FROM grupo WHERE cod_grupo=?";

    public List<Grupo> consultar() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Grupo grupo = null;
        List<Grupo> grupos = new ArrayList<>();

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(QUERY);
            rs = stmt.executeQuery();

            while (rs.next()) {
                String codGrupo = rs.getString("cod_grupo");
                String codCurso = rs.getString("cod_curso");
                String nombre = rs.getString("nombre");
                String codTurno = rs.getString("cod_turno");
                int nMaxAlumnos = rs.getInt("nMaxAlumnos");

                grupo = new Grupo(codGrupo, codCurso, nombre, codTurno, nMaxAlumnos);
                grupos.add(grupo);
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

        return grupos;
    }

    public int insertar(Grupo grupo) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);

            stmt.setString(1, grupo.getCodGrupo());
            stmt.setString(2, grupo.getCodCurso());
            stmt.setString(3, grupo.getNombre());
            stmt.setString(4, grupo.getCodTurno());
            stmt.setInt(5, grupo.getNMaxAlumnos());

            registros = stmt.executeUpdate();
            if (registros > 0) {
                System.out.println("Número de registros insertados: " + registros);
            } else {
                System.out.println("No se ha podido insertar el grupo");
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

    public int actualizar(Grupo grupo) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);

            stmt.setString(1, grupo.getCodCurso());
            stmt.setString(2, grupo.getNombre());
            stmt.setString(3, grupo.getCodTurno());
            stmt.setInt(4, grupo.getNMaxAlumnos());
            stmt.setString(5, grupo.getCodGrupo());

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

    public int eliminar(String codGrupo) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setString(1, codGrupo);
            registros = stmt.executeUpdate();

            if (registros > 0) {
                System.out.println("Número de registros eliminados: " + registros);
            } else {
                System.out.println("No se ha podido eliminar el grupo");
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
