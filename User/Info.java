package User;

public class Info {
	
	public final static String MAIN_MENU = "\nMENU\n" +
			"1.- Mostrar todos los empleados(desde MySQL)\n" +
			"2.- Copiar la tabla empleados de MySQL a SQLite\n" +
			"3.- Mostrar todos los empleados(desde SQLite)\n" +
			"4.- Eliminar empleado(en MySQL)\n" +
			"5.- Sincronizar datos de SQLite a MySQL\n" + 
			"0.- Salir\n";
	public static void showMainMenu() {
		System.out.println(MAIN_MENU);
	}
	
//	public static String menu() {
//		String menu = null;
//		try {
//			menu = Table.showTables() + 
//					"14 - Consultas monotabla\n" +
//					"15 - Consultas multitabla";
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return menu;
//	}
//	
//	public static void showMainMenu() {
//		System.out.println(menu());
//	}
}