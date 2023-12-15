import java.sql.*;

public class Main {
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/empleadoss_departamentoss";

		try {
			Connection conexion = DriverManager.getConnection(url, "root", "1234");
			Statement instruccion = conexion.createStatement();
			String consulta = "SELECT codDepto, nombreDpto FROM departamentos;";
			ResultSet resultado = instruccion.executeQuery(consulta);
			while(resultado.next()) {
				System.out.print("Departamento: " + resultado.getString("codDepto"));
				System.out.println("");
			}
		} catch (SQLException ex) {
			ex.printStackTrace(System.out);
		}
	
	
	
	} // cierre main
}