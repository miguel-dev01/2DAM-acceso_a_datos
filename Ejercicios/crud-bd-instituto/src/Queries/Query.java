package Queries;

import static Snippets.Snippet.*;
import static User.Info.QUERIES_INFO;

import DAO.AlumnoDAO;
import DAO.AsignaturaDAO;
import DAO.ProfesorDAO;

import java.util.List;

import BD.Table;


public class Query {

	public static void entryQuery() {
		String query = "";
		int option = 0;
		do {
			option = getInt(QUERIES_INFO);
			switch (option) {
			case 1:
				String codCursoConsulta = getString("Introduce el codigo del curso para filtrar a los alumnos: ");
		        query = "SELECT DISTINCT a.* " +
                        "FROM alumno a " +
                        "INNER JOIN matricula m ON m.nre = a.nre " +
                        "INNER JOIN curso c ON c.cod_curso = m.cod_curso " +
                        "WHERE c.cod_curso = " + codCursoConsulta + ";";
				invocarConsultaPersAlumno(query);
				break;
			case 2:
				String codAsigConsulta = getString("Introduce el codigo de asignatura para filtrar a los alumnos: ");
				query = "SELECT a.* FROM alumno a " +
	                     "INNER JOIN matricula m ON m.nre = a.nre " +
	                     "INNER JOIN asignatura asig ON asig.cod_asignatura = m.cod_asig " +
	                     "WHERE asig.cod_asignatura = " + codAsigConsulta + ";";
				invocarConsultaPersAlumno(query);
				break;
			case 3:
				String nombreCompletoAlumno = getString("Introduce nombre y apellidos para "
						+ "realizar la busqueda de alumno/os: ");
				query = "select * from alumno a where concat(a.nombre, ' ', "
						+ "a.apellido1, ' ', a.apellido2) "
						+ "like '%" + nombreCompletoAlumno + "%';";
				System.out.println();
				invocarConsultaPersAlumno(query);
				break;
			case 4:
				String numAula = getString("Introduce numero de aula para la busqueda: ");
				query = "select distinct p.* from profesor p " +
		                  "inner join imparte i on p.nrp = i.nrp_profesor " +
		                  "inner join aula a on i.num_aula = a.num_aula " +
		                  "where a.num_aula = '" + numAula + "';";
				invocarConsultaPersProfesor(query);
				break;
			case 5:
				String codDpto = getString("Introduce codigo de departamento para la busqueda: ");
				query = "select distinct asig.* from asignatura asig " +
		                  "inner join imparte i on asig.cod_asignatura = i.cod_asig " +
		                  "inner join profesor p on i.nrp_profesor = p.nrp " +
		                  "inner join departamento d on d.cod_departamento = p.cod_departamento " +
		                  "where d.cod_departamento = '" + codDpto + "';";
				invocarConsultaPersAsignatura(query);
				break;
			case 0:
				System.out.println("\nVolviendo atras...");
				query = "";
				Table.showTables();
				System.out.println("14 - Consultas");
				break;
			default:
				System.out.println("Opcion no valida");
				break;
			}
			
		} while (option != 0);
	}
	
	public static void invocarConsultaPersAlumno(String query) {
		AlumnoDAO alumnoDao = new AlumnoDAO();
		List<?> registros = alumnoDao.consultasPersonalizadas(query);
		if (!registros.isEmpty()) {
			for (Object registro : registros) {
				System.out.println(registro);
			}
		}
		else {
			System.out.println("\nNo se han encontrado resultados");
		}
	}
	
	public static void invocarConsultaPersProfesor(String query) {
		ProfesorDAO profesorDao = new ProfesorDAO();
		List<?> registros = profesorDao.consultasPersonalizadas(query);
		if (!registros.isEmpty()) {
			for (Object registro : registros) {
				System.out.println(registro);
			}
		}
		else {
			System.out.println("\nNo se han encontrado resultados");
		}
	}
	
	public static void invocarConsultaPersAsignatura(String query) {
		AsignaturaDAO asigDao = new AsignaturaDAO();
		List<?> registros = asigDao.consultasPersonalizadas(query);
		if (!registros.isEmpty()) {
			for (Object registro : registros) {
				System.out.println(registro);
			}
		}
		else {
			System.out.println("\nNo se han encontrado resultados");
		}
	}
}
