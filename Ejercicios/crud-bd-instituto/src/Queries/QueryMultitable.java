package Queries;

import static Snippets.Snippet.*;
import static User.Info.QUERIES_TABLE_DIFFERENT_INFO;

import static Validate.Validate.obtenerDato;
import DAO.MultitableDAO;

// Class for data from different tables
public class QueryMultitable {

	public static void dataTables () {
		String query = "";
		MultitableDAO daoMultitabla = new MultitableDAO();
		int option = 0;
		do {
			option = getInt(QUERIES_TABLE_DIFFERENT_INFO);
			switch (option) {
				case 1:
					Integer anioMatricula = obtenerDato("Año de matricula", "validarAnio", Integer.class);
					if (anioMatricula == null) break;
					query = "SELECT asig.*, al.* " +
					               "FROM alumno al " +
					               "INNER JOIN matricula m ON al.nre = m.nre " +
					               "INNER JOIN asignatura asig ON asig.cod_asignatura = m.cod_asig " +
					               "WHERE m.anyo = '" + anioMatricula + "'";
					System.out.println(daoMultitabla.consultaMultitablaAlumnoAsignatura(query));
					break;
				case 2:
					Integer anio = obtenerDato("Año", "validarAnio", Integer.class);
					if (anio == null) break;
					String codDepart = obtenerDato("Codigo departamento", "validateCodigo", String.class);
					if (codDepart == null) break;
					query = "SELECT asig.*, p.* " +
				               "FROM asignatura asig " +
				               "INNER JOIN imparte im ON asig.cod_asignatura = im.cod_asig " +
				               "INNER JOIN profesor p ON im.nrp_profesor = p.nrp " +
				               "INNER JOIN departamento d ON p.cod_departamento = d.cod_departamento " +
				               "WHERE d.cod_departamento = '" + codDepart + "' AND im.anyo = '" + anio + "';";
					System.out.println(daoMultitabla.consultaMultitablaProfesorAsignaturaDepartamento(query));
					break;
				case 3:
					anio = obtenerDato("Año", "validarAnio", Integer.class);
					if (anio == null) break;
					query = "SELECT g.*, al.* " +
					                "FROM alumno al " +
					                "INNER JOIN matricula m ON m.nre = al.nre " +
					                "INNER JOIN curso cur ON cur.cod_curso = m.cod_curso " +
					                "INNER JOIN grupo g ON g.cod_curso = cur.cod_curso " +
					                "WHERE m.anyo = '" + anio + "' " +
					                "GROUP BY g.cod_grupo, al.nre;";
					System.out.println(daoMultitabla.consultaMultitablaAlumnosGrupo(query));
					break;
				default:
					System.out.println("\nNo valido");
					query = "";
					break;
			}
		} while (option != 0);
		
	}

}
