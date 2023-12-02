package DAO;

import static BD.Conexion.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import domain.Alumno;
import domain.Asignatura;
import domain.Grupo;
import domain.Profesor;

public class MultitableDAO {

	public String consultaMultitablaAlumnoAsignatura(String query) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		StringBuilder registros = new StringBuilder();

		try {
			conn = getConnection();
			stmt = conn.prepareStatement(query);
			rs = stmt.executeQuery();
			
			// el metodo "" es un booleano que cualquier consulta exitosa que tenga datos, 
			// creara un ResultSet con dicho metodo devolviendo true.
			// Por tanto, si devuelve un false significa que la consulta no contiene datos a procesar,
			// es decir, el cursor de ResultSet no habra encontrado dichos datos.
			if (!rs.isBeforeFirst()) {
				registros.append("\nNo se encontraron resultados");
			} else {
				while (rs.next()) {
					Alumno alumno = new Alumno();
					Asignatura asig = new Asignatura();
					asig.setCodAsignatura(rs.getString("cod_asignatura"));
					asig.setDescripcion(rs.getString("descripcion"));
					asig.setCodInterno(rs.getString("cod_interno"));
					asig.setNHoras(rs.getInt("nHoras"));
					asig.setCodCurso(rs.getString("cod_curso"));
	
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
					
					registros.append(asig.toString()).append(" | ").append(alumno.toString())
								.append("\n");
				}
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
		return registros.toString();
	}
	
	public String consultaMultitablaProfesorAsignaturaDepartamento(String query) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		StringBuilder registros = new StringBuilder();

		try {
			conn = getConnection();
			stmt = conn.prepareStatement(query);
			rs = stmt.executeQuery();

			if (!rs.isBeforeFirst()) {
				registros.append("\nNo se encontraron resultados");
			} else {
				while (rs.next()) {
					Profesor profesor = new Profesor();
					Asignatura asig = new Asignatura();
					asig.setCodAsignatura(rs.getString("cod_asignatura"));
					asig.setDescripcion(rs.getString("descripcion"));
					asig.setCodInterno(rs.getString("cod_interno"));
					asig.setNHoras(rs.getInt("nHoras"));
					asig.setCodCurso(rs.getString("cod_curso"));
					
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
	
	                registros.append(asig.toString()).append(" | ").append(profesor.toString())
								.append("\n");
				}
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
		return registros.toString();
	}
	
	public String consultaMultitablaAlumnosGrupo(String query) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		StringBuilder registros = new StringBuilder();

		try {
			conn = getConnection();
			stmt = conn.prepareStatement(query);
			rs = stmt.executeQuery();
			
			if (!rs.isBeforeFirst()) {
				registros.append("\nNo se encontraron resultados");
			} else {
				while (rs.next()) {
					Alumno alumno = new Alumno();
					Grupo grupo = new Grupo();
	                String codGrupo = rs.getString("cod_grupo");
	                String codCurso = rs.getString("cod_curso");
	                String nombreGrupo = rs.getString("nombre");
	                String codTurno = rs.getString("cod_turno");
	                int nMaxAlumnos = rs.getInt("nMaxAlumnos");
	                grupo = new Grupo(codGrupo, codCurso, nombreGrupo, codTurno, nMaxAlumnos);
	
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
	
	                registros.append(grupo.toString()).append(" | ").append(alumno.toString())
								.append("\n");
				}
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
		return registros.toString();
	}

}
