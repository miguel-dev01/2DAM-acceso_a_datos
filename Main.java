import User.Info;
import static User.Snippet.*;

import java.util.List;

import DAO.EmpleadoDAO;
import Domain.Empleado;

public class Main {

	public static void main(String[] args) {
		int option = 0;
		System.out.println("Base de datos de empleados. Elija una opcion: ");
		Info.showMainMenu();
		do {
			option = getInt("Introduce una opcion: ");
			switch (option) {
				case 1:
					for (Empleado empleado : EmpleadoDAO.consultarEnMysql()) {
						System.out.println(empleado);
					}
					Info.showMainMenu();
					break;
				case 2:
					System.out.println();
					EmpleadoDAO.volcarDatosTabla();
					Info.showMainMenu();
					break;
				case 3:
					try {
					    List<Empleado> empleados = EmpleadoDAO.consultarEnSqlite();
					    if (empleados.isEmpty()) {
					        System.out.println("\nSin resultados.");
					        break;
					    }
					    for (Empleado empleado : empleados) {
					        System.out.println(empleado);
					    }
					} catch (NullPointerException e) { }
					Info.showMainMenu();
					break;
				case 4:
					Integer numEmpleado = getInt("Introduce codigo del empleado para eliminarlo: ");
					EmpleadoDAO.eliminar(numEmpleado.toString());
					Info.showMainMenu();
					break;
				case 5:
					System.out.println();
					try {
						EmpleadoDAO.sincronizarDatos();
					} catch (NullPointerException e) { }
					Info.showMainMenu();
					break;
				case 0:
					System.out.println("\nSaliendo del programa...");
					break;
				default:
					System.out.println("\nNo valido");
					break;
			}
		} while (option != 0);
		
	} // cierre main

}
