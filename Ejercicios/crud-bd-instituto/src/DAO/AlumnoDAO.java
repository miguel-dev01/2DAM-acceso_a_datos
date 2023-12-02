package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import BD.Conexion;
import domain.Alumno;
import static BD.Conexion.getConnection;
import static BD.Conexion.close;

public class AlumnoDAO {

	private static final String QUERY = "SELECT nre, dni, nombre, apellido1, apellido2, tipo_via, nombre_via, numero, escalera, piso, puerta, cp, pais, tlfn_fijo, tlfn_movil, email, fecha_nac, tutor FROM alumno";
	private static final String SQL_INSERT = "INSERT INTO alumno(nre, dni, nombre, apellido1, apellido2, tipo_via, nombre_via, numero, escalera, piso, puerta, cp, pais, tlfn_fijo, tlfn_movil, email, fecha_nac, tutor) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
	private static final String SQL_UPDATE = "UPDATE alumno SET dni=?, nombre=?, apellido1=?, apellido2=?, tipo_via=?, nombre_via=?, numero=?, escalera=?, piso=?, puerta=?, cp=?, pais=?, tlfn_fijo=?, tlfn_movil=?, email=?, fecha_nac=?, tutor=? WHERE nre=?";
	private static final String SQL_DELETE = "DELETE FROM alumno WHERE nre=?";

	public List<Alumno> consultar() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Alumno alumno = null;
		List<Alumno> alumnos = new ArrayList<Alumno>();

		try {
			conn = getConnection();
			stmt = conn.prepareStatement(QUERY);
			rs = stmt.executeQuery();

			while (rs.next()) {
				String nre = rs.getString("nre");
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
				String tutor = rs.getString("tutor");

				alumno = new Alumno(nre, dni, nombre, apellido1, apellido2, tipoVia, nombreVia, 
						numero, escalera, piso, puerta, cp, pais, tlfnFijo, tlfnMovil, email, 
						fechaNac, tutor);
				alumnos.add(alumno);
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

		return alumnos;
	}
	
	public List<Alumno> consultasPersonalizadas(String query) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Alumno> alumnos = new ArrayList<Alumno>();

		try {
			conn = getConnection();
			stmt = conn.prepareStatement(query);
			rs = stmt.executeQuery();

			while (rs.next()) {
				Alumno alumno = new Alumno();
            	alumno.setNre(rs.getString("nre"));
            	alumno.setDni(rs.getString("dni"));
            	alumno.setNombre(rs.getString("nombre"));
            	alumno.setApellido1(rs.getString("apellido1"));
            	alumno.setApellido2(rs.getString("apellido2"));
                alumno.setTipoVia(rs.getString("tipo_via"));
                alumno.setNombreVia(rs.getString("nombre_via"));
                alumno.setNumero(rs.getString("numero"));
                alumno.setEscalera(rs.getString("escalera"));
                alumno.setPiso(rs.getString("piso"));
                alumno.setPuerta(rs.getString("puerta"));
                alumno.setCp(rs.getString("cp"));
                alumno.setPais(rs.getString("pais"));
            	alumno.setTlfnMovil(rs.getString("tlfn_movil"));
            	alumno.setTlfnFijo(rs.getString("tlfn_fijo"));
            	alumno.setFechaNac(rs.getDate("fecha_nac"));
            	alumno.setEmail(rs.getString("email"));
				alumno.setFechaNac(rs.getDate("fecha_nac"));
				alumno.setTutor(rs.getString("tutor"));

                alumnos.add(alumno);
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

		return alumnos;
	}

	public int insertar(Alumno alumno) {
	    Connection conn = null;
	    PreparedStatement stmt = null;
	    int registros = 0;
	    try {
	        conn = getConnection();
	        stmt = conn.prepareStatement(SQL_INSERT);

			stmt.setString(1, alumno.getNre());
			stmt.setString(2, alumno.getDni());
			stmt.setString(3, alumno.getNombre());
			stmt.setString(4, alumno.getApellido1());
			stmt.setString(5, alumno.getApellido2());
			stmt.setString(6, alumno.getTipoVia());
			stmt.setString(7, alumno.getNombreVia());
			stmt.setString(8, alumno.getNumero());
			stmt.setString(9, alumno.getEscalera());
			stmt.setString(10, alumno.getPiso());
			stmt.setString(11, alumno.getPuerta());
			stmt.setString(12, alumno.getCp());
			stmt.setString(13, alumno.getPais());
			stmt.setString(14, alumno.getTlfnFijo());
			stmt.setString(15, alumno.getTlfnMovil());
			stmt.setString(16, alumno.getEmail());
			stmt.setDate(17, alumno.getFechaNac());
			stmt.setString(18, alumno.getTutor());
	        registros = stmt.executeUpdate();
	        if (registros > 0) {
	            System.out.println("Numero de registros insertados: " + registros);
	        } else {
	            System.out.println("No se ha podido insertar el alumno");
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

	public int actualizar(Alumno alumno) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int registros = 0;

		try {
			conn = getConnection();
			stmt = conn.prepareStatement(SQL_UPDATE);

			stmt.setString(1, alumno.getDni());
			stmt.setString(2, alumno.getNombre());
			stmt.setString(3, alumno.getApellido1());
			stmt.setString(4, alumno.getApellido2());
			stmt.setString(5, alumno.getTipoVia());
			stmt.setString(6, alumno.getNombreVia());
			stmt.setString(7, alumno.getNumero());
			stmt.setString(8, alumno.getEscalera());
			stmt.setString(9, alumno.getPiso());
			stmt.setString(10, alumno.getPuerta());
			stmt.setString(11, alumno.getCp());
			stmt.setString(12, alumno.getPais());
			stmt.setString(13, alumno.getTlfnFijo());
			stmt.setString(14, alumno.getTlfnMovil());
			stmt.setString(15, alumno.getEmail());
			stmt.setDate(16, alumno.getFechaNac());
			stmt.setString(17, alumno.getTutor());
			stmt.setString(18, alumno.getNre());

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

	public int eliminar(String nre) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int registros = 0;

		try {
			conn = Conexion.getConnection();
			stmt = conn.prepareStatement(SQL_DELETE);
			stmt.setString(1, nre);
			registros = stmt.executeUpdate();

	        if (registros > 0) {
	            System.out.println("Numero de registros eliminados: " + registros);
	        } else {
	            System.out.println("No se ha podido eliminar el alumno");
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
