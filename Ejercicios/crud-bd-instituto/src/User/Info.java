package User;

import BD.Table;

public class Info {
	
	public final static String CRUD_MENU = "\nMENU\n" +
			"1.- Añadir\n" +
			"2.- Borrar\n" +
			"3.- Actualizar datos\n" +
			"4.- Mostrar datos\n" +
			"0.- Atras\n" + 
			"Introduce una opcion: ";
	public final static String QUERIES_INFO = "\n"
			+ "1 - Obtener datos de alumnos de un curso solicitado \n"
			+ "2 - Obtener datos de alumnos de una asignatura solicitada \n"
			+ "3 - Buscador de alumnos \n"
			+ "4 - Profesores que imparten clase en una aula solicitada \n"
			+ "5 - Obtener las asignaturas que se imparten en un departamento solicitado \n"
			+ "0 - Volver atras \n"
			+ "Introduce una opcion: ";
	public final static String QUERIES_TABLE_DIFFERENT_INFO = "\n"
			+ "1 - Mostrar un listado de alumnos y para cada alumno indicar las asignaturas de las que está matriculado. \n"
			+ "2 - Mostrar las asignaturas que imparte cada uno de los profesores de un departamento previamente seleccionado. \n"
			+ "3 - Mostrar un listado de alumnos por grupo. \n"
			+ "Introduce una opcion: ";
	
	public static String menu() {
		String menu = null;
		try {
			menu = Table.showTables() + 
					"14 - Consultas monotabla\n" +
					"15 - Consultas multitabla";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return menu;
	}
	
	public static void showMainMenu() {
		System.out.println(menu());
	}
}