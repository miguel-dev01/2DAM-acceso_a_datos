package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import BD.Conexion;
import domain.Turno;
import static BD.Conexion.*;

public class TurnoDAO {

    private static final String QUERY = "SELECT cod_turno, horario FROM turno";
    private static final String SQL_INSERT = "INSERT INTO turno(cod_turno, horario) VALUES (?, ?)";
    private static final String SQL_UPDATE = "UPDATE turno SET horario=? WHERE cod_turno=?";
    private static final String SQL_DELETE = "DELETE FROM turno WHERE cod_turno=?";

    public List<Turno> consultar() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Turno turno = null;
        List<Turno> turnos = new ArrayList<>();

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(QUERY);
            rs = stmt.executeQuery();

            while (rs.next()) {
                String codTurno = rs.getString("cod_turno");
                String horario = rs.getString("horario");

                turno = new Turno(codTurno, horario);
                turnos.add(turno);
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

        return turnos;
    }

    public int insertar(Turno turno) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);

            stmt.setString(1, turno.getCodTurno());
            stmt.setString(2, turno.getHorario());

            registros = stmt.executeUpdate();
            if (registros > 0) {
                System.out.println("Número de registros insertados: " + registros);
            } else {
                System.out.println("No se ha podido insertar el turno");
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

    public int actualizar(Turno turno) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);

            stmt.setString(1, turno.getHorario());
            stmt.setString(2, turno.getCodTurno());

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

    public int eliminar(String codTurno) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setString(1, codTurno);
            registros = stmt.executeUpdate();

            if (registros > 0) {
                System.out.println("Número de registros eliminados: " + registros);
            } else {
                System.out.println("No se ha podido eliminar el turno");
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
