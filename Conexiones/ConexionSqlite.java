package Conexiones;

import java.sql.*;

public class ConexionSqlite {

	private static final String JDBC_URL = "jdbc:sqlite:database/empleados.db";

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(JDBC_URL);
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
