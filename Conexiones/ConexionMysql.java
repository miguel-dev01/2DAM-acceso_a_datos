package Conexiones;

import java.sql.*;

public class ConexionMysql {

	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/empleados";
	private static final String JDBC_USER = "root";
	private static final String JDBC_PASSWORD = "1234";

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
	}
	public static void close(ResultSet rs) throws SQLException {
		rs.close();
	}
	public static void close(Statement stmt) throws SQLException {
		stmt.close();
	}
	public static void close(Connection conn) throws SQLException {
		conn.close();
	}
}
