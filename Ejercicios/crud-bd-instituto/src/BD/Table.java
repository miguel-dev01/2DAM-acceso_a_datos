package BD;

import static BD.Conexion.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Table {

	private final static String QUERY = "SHOW TABLES;";
	
	public static String showTables() {
		String opcionMenu = "";
    	Connection conn = null;
    	PreparedStatement stmt = null;
    	ResultSet rs = null;
        try {
			conn = getConnection();
			stmt = conn.prepareStatement(QUERY);
			rs = stmt.executeQuery();
            int i = 0;
            while (rs.next()) {
            	i++;
                String tableName = rs.getString(1);
                // System.out.println(i + " - " + tableName);
                opcionMenu += i + " - " + tableName + "\n";
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
			try {
				close(stmt);
				close(conn);
			} catch (SQLException e) {
				e.printStackTrace(System.out);
			}
        }
        return opcionMenu;
	}
}
