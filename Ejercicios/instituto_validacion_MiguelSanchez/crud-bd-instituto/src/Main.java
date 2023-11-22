import static Snippets.Snippet.*;
import BD.Table;
import domain.*;


public class Main {
	public static void main(String[] args) {
		int table = 0;
		System.out.println("Estas son las tablas de la base de datos.");
		Table.showTables();
		do {
			table = getInt("Elige una tabla: ");
			switch (table) {
				case 1:
					// Tiene validacion
					Alumno.alumnoCRUD();
					break;
				case 2:
					// Tiene validacion
					Asignatura.asignaturaCRUD();
					break;
				case 3:
					Aula.aulaCRUD();
					break;
				case 4:
					System.out.println("\nProximamente...\n");
					break;
				case 5:
					Curso.cursoCRUD();
					break;
				case 6:
					Departamento.departamentoCRUD();
					break;
				case 7:
					Edificio.edificioCRUD();
					break;
				case 8:
					// Tiene validacion
					Grupo.grupoCRUD();
					break;
				case 9:
					System.out.println("\nProximamente...\n");
					break;
				case 10:
					System.out.println("\nProximamente...\n");
					break;
				case 11:
					// Tiene validacion
					Profesor.profesorCRUD();
					break;
				case 12:
					Turno.turnoCRUD();
					break;
				case 13:
					System.out.println("\nProximamente...\n");
					break;
				case 0:
					System.out.println("\nSaliendo del programa...");
					break;
				default:
					System.out.println("\nNo valido");
					break;
			}
		} while(table != 0);
		
	} // cierre main
}