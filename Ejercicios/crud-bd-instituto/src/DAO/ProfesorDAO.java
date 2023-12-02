package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import BD.Conexion;
import domain.Profesor;
import static BD.Conexion.*;

public class ProfesorDAO {

    private static final String QUERY = "SELECT nrp, dni, nombre, apellido1, apellido2, tipo_via, nombre_via, numero, escalera, piso, puerta, cp, pais, tlfn_fijo, tlfn_movil, email, fecha_nac, cod_departamento FROM profesor";
    private static final String SQL_INSERT = "INSERT INTO profesor(nrp, dni, nombre, apellido1, apellido2, tipo_via, nombre_via, numero, escalera, piso, puerta, cp, pais, tlfn_fijo, tlfn_movil, email, fecha_nac, cod_departamento) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE profesor SET dni=?, nombre=?, apellido1=?, apellido2=?, tipo_via=?, nombre_via=?, numero=?, escalera=?, piso=?, puerta=?, cp=?, pais=?, tlfn_fijo=?, tlfn_movil=?, email=?, fecha_nac=?, cod_departamento=? WHERE nrp=?";
    private static final String SQL_DELETE = "DELETE FROM profesor WHERE nrp=?";

    public List<Profesor> consultar() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Profesor profesor = null;
        List<Profesor> profesores = new ArrayList<>();

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(QUERY);
            rs = stmt.executeQuery();

            while (rs.next()) {
                String nrp = rs.getString("nrp");
                String dni = rs.getString("dni");
                String nombre = rs.getString("nombre");
                String apellido1 = rs.getString("apellido1");
                String apellido2 = rs.getString("apellido2");
                String tipoVia = rs.getString("tipo_via");
                String nombreVia = rs.getString("nombre_via");
                String numero = rs.getString("numero");
                String escalera = rs.getString("escalera");
                String piso = rs.getString("piso");
                String puerta = rs.getString("puerta");
                String cp = rs.getString("cp");
                String pais = rs.getString("pais");
                String tlfnFijo = rs.getString("tlfn_fijo");
                String tlfnMovil = rs.getString("tlfn_movil");
                String email = rs.getString("email");
                Date fechaNac = rs.getDate("fecha_nac");
                String codDepartamento = rs.getString("cod_departamento");

                profesor = new Profesor(nrp, dni, nombre, apellido1, apellido2, tipoVia, nombreVia, 
                        numero, escalera, piso, puerta, cp, pais, tlfnFijo, tlfnMovil, email, 
                        fechaNac, codDepartamento);
                profesores.add(profesor);
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

        return profesores;
    }

	public List<Profesor> consultasPersonalizadas(String query) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Profesor> profesores = new ArrayList<Profesor>();

		try {
			conn = getConnection();
			stmt = conn.prepareStatement(query);
			rs = stmt.executeQuery();

			while (rs.next()) {
				Profesor profesor = new Profesor();
                profesor.setNrp(rs.getString("nrp"));
                profesor.setNrp(rs.getString("nrp"));
                profesor.setDni(rs.getString("dni"));
                profesor.setNombre(rs.getString("nombre"));
                profesor.setApellido1(rs.getString("apellido1"));
                profesor.setApellido2(rs.getString("apellido2"));
                profesor.setTipoVia(rs.getString("tipo_via"));
                profesor.setNombreVia(rs.getString("nombre_via"));
                profesor.setNumero(rs.getString("numero"));
                profesor.setEscalera(rs.getString("escalera"));
                profesor.setPiso(rs.getString("piso"));
                profesor.setPuerta(rs.getString("puerta"));
                profesor.setCp(rs.getString("cp"));
                profesor.setPais(rs.getString("pais"));
                profesor.setTlfnFijo(rs.getString("tlfn_fijo"));
                profesor.setTlfnMovil(rs.getString("tlfn_movil"));
                profesor.setEmail(rs.getString("email"));
                profesor.setFechaNac(rs.getDate("fecha_nac"));
                profesor.setCodDepartamento(rs.getString("cod_departamento"));
                
                profesores.add(profesor);
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

		return profesores;
	}
    
    public int insertar(Profesor profesor) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);

            stmt.setString(1, profesor.getNrp());
            stmt.setString(2, profesor.getDni());
            stmt.setString(3, profesor.getNombre());
            stmt.setString(4, profesor.getApellido1());
            stmt.setString(5, profesor.getApellido2());
            stmt.setString(6, profesor.getTipoVia());
            stmt.setString(7, profesor.getNombreVia());
            stmt.setString(8, profesor.getNumero());
            stmt.setString(9, profesor.getEscalera());
            stmt.setString(10, profesor.getPiso());
            stmt.setString(11, profesor.getPuerta());
            stmt.setString(12, profesor.getCp());
            stmt.setString(13, profesor.getPais());
            stmt.setString(14, profesor.getTlfnFijo());
            stmt.setString(15, profesor.getTlfnMovil());
            stmt.setString(16, profesor.getEmail());
            stmt.setDate(17, profesor.getFechaNac());
            stmt.setString(18, profesor.getCodDepartamento());

            registros = stmt.executeUpdate();
            if (registros > 0) {
                System.out.println("Número de registros insertados: " + registros);
            } else {
                System.out.println("No se ha podido insertar el profesor");
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

    public int actualizar(Profesor profesor) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);

            stmt.setString(1, profesor.getDni());
            stmt.setString(2, profesor.getNombre());
            stmt.setString(3, profesor.getApellido1());
            stmt.setString(4, profesor.getApellido2());
            stmt.setString(5, profesor.getTipoVia());
            stmt.setString(6, profesor.getNombreVia());
            stmt.setString(7, profesor.getNumero());
            stmt.setString(8, profesor.getEscalera());
            stmt.setString(9, profesor.getPiso());
            stmt.setString(10, profesor.getPuerta());
            stmt.setString(11, profesor.getCp());
            stmt.setString(12, profesor.getPais());
            stmt.setString(13, profesor.getTlfnFijo());
            stmt.setString(14, profesor.getTlfnMovil());
            stmt.setString(15, profesor.getEmail());
            stmt.setDate(16, profesor.getFechaNac());
            stmt.setString(17, profesor.getCodDepartamento());
            stmt.setString(18, profesor.getNrp());

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

    public int eliminar(String nrp) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setString(1, nrp);
            registros = stmt.executeUpdate();

            if (registros > 0) {
                System.out.println("Número de registros eliminados: " + registros);
            } else {
                System.out.println("No se ha podido eliminar el profesor");
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
