package DAO;
import Conexiones.*;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Domain.Empleado;


public class EmpleadoDAO {
	
	private static final String QUERY_SELECT = "SELECT * FROM empleados";
	private static final String INSERT_VOLCADO_TABLA = "INSERT INTO empleados (num, nombre, edad, departamento, categoria, contrato) VALUES (?, ?, ?, ?, ?, ?)";
	private static final String CREAR_TABLA_EMPLEADOS = "CREATE TABLE IF NOT EXISTS empleados (\n"
			+ "    num INTEGER NOT NULL,\n"
			+ "    nombre TEXT NOT NULL,\n"
			+ "    edad INTEGER NOT NULL,\n"
			+ "    departamento INTEGER NOT NULL,\n"
			+ "    categoria INTEGER NOT NULL,\n"
			+ "    contrato DATE NOT NULL\n"
			+ ");";
	private static final String BORRAR_TABLA_EMPLEADOS = "DROP TABLE IF EXISTS empleados;";
	private static final String SQL_DELETE = "DELETE FROM empleados WHERE num=?;";

	public static List<Empleado> consultarEnMysql() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Empleado empleado = null;
		List<Empleado> empleados = new ArrayList<Empleado>();

		try {
			conn = ConexionMysql.getConnection();
			stmt = conn.prepareStatement(QUERY_SELECT);
			rs = stmt.executeQuery();

			while (rs.next()) {
				empleado = new Empleado();
				
				empleado.setNumero(rs.getString("num"));
				empleado.setNombre(rs.getString("nombre"));
				empleado.setEdad(rs.getInt("edad"));
				empleado.setDepartamento(rs.getString("departamento"));
				empleado.setCategoria(rs.getString("categoria"));
				empleado.setContrato(rs.getString("contrato"));

				empleados.add(empleado);
			}
		} catch (SQLException e) {
			e.printStackTrace(System.out);
		} finally {
			try {
				ConexionMysql.close(rs);
				ConexionMysql.close(stmt);
				ConexionMysql.close(conn);
			} catch (SQLException e) {
				e.printStackTrace(System.out);
			}
		}

		return empleados;
	}
	
	public static int volcarDatosTabla() {
        Connection conn = null;
        PreparedStatement stmt1 = null;
        PreparedStatement stmt2 = null;
        PreparedStatement stmt3 = null;
        List<Empleado> empleados = consultarEnMysql();
        int registros = 0;
		
        try {
            // Conectar a la base de datos de destino
            conn = ConexionSqlite.getConnection();
            
			stmt3 = conn.prepareStatement(BORRAR_TABLA_EMPLEADOS);
			stmt3.executeUpdate();
			stmt3.close();
            
            // Creamos la tabla si no existe.
            stmt1 = conn.prepareStatement(CREAR_TABLA_EMPLEADOS);
            stmt1.executeUpdate();
            stmt1.close();
            
            stmt2 = conn.prepareStatement(INSERT_VOLCADO_TABLA);
            // Insertar datos en SQLite de los empleados que vienen de MySQL
            for (Empleado empleado : empleados) {
            	stmt2.setString(1, empleado.getNumero());
            	stmt2.setString(2, empleado.getNombre());
                stmt2.setInt(3, empleado.getEdad());
                stmt2.setString(4, empleado.getDepartamento());
                stmt2.setString(5, empleado.getCategoria());
                stmt2.setString(6, empleado.getContrato());

                registros += stmt2.executeUpdate();
            }
            stmt2.close();
	        if (registros > 0) {
	            System.out.println("Numero de registros insertados: " + registros);
	        } else {
	            System.err.println("Ha habido error en el volcado de datos.");
	        }
	        
	        conn.close();
        } catch(SQLException e) {
        	e.printStackTrace(System.out);
        }
        
		return registros;
	}
	
	public static List<Empleado> consultarEnSqlite() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Empleado empleado = null;
		List<Empleado> empleados = new ArrayList<Empleado>();

		try {
			conn = ConexionSqlite.getConnection();
			
	        // Verificar si la tabla "empleados" existe
	        if (!tablaExiste(conn, "empleados")) {
	            System.out.println("\nLa tabla 'empleados' no existe desde SQLite.");
	            return empleados;
	        }
	        
			stmt = conn.prepareStatement(QUERY_SELECT);
			rs = stmt.executeQuery();

			while (rs.next()) {
				empleado = new Empleado();
				
				empleado.setNumero(rs.getString("num"));
				empleado.setNombre(rs.getString("nombre"));
				empleado.setEdad(rs.getInt("edad"));
				empleado.setDepartamento(rs.getString("departamento"));
				empleado.setCategoria(rs.getString("categoria"));
				empleado.setContrato(rs.getString("contrato"));

				empleados.add(empleado);
			}

		} catch (SQLException e) {
			e.printStackTrace(System.out);
		} finally {
			try {
				ConexionSqlite.close(rs);
				ConexionSqlite.close(stmt);
				ConexionSqlite.close(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return empleados;
	}
	
	// Metodo para verificar la existencia de una tabla en la base de datos
	private static boolean tablaExiste(Connection conn, String nombreTabla) throws SQLException {
	    DatabaseMetaData meta = conn.getMetaData();
	    ResultSet resultSet = meta.getTables(null, null, nombreTabla, new String[]{"TABLE"});
	    return resultSet.next();
	}
	
    public static int eliminar(String numEmpleado) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;

        try {
            conn = ConexionMysql.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setString(1, numEmpleado);
            registros = stmt.executeUpdate();

            if (registros > 0) {
                System.out.println("\nRegistros eliminados: " + registros);
            } else {
                System.out.println("\nNo se ha eliminado el empleado. \nEs posible que no exista.");
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            try {
            	ConexionMysql.close(stmt);
            	ConexionMysql.close(conn);
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }
        }
        return registros;
    }
    
    public static void sincronizarDatos() {
        List<Empleado> empleadosMySQL = EmpleadoDAO.consultarEnMysql();
        List<Empleado> empleadosSQLite = EmpleadoDAO.consultarEnSqlite();

        // Identificas empleados que estan en SQLite pero no en MySQL
        List<Empleado> empleadosFaltantes = encontrarEmpleadosFaltantes(empleadosMySQL, empleadosSQLite);

        // Insertar los empleados que faltan en MySQL
        insertarEmpleadosEnMySQL(empleadosFaltantes);
    }

    private static List<Empleado> encontrarEmpleadosFaltantes(List<Empleado> empleadosMySQL, List<Empleado> empleadosSQLite) {
    	List<String> numerosMySQL = new ArrayList<>();
        for (Empleado empleadoMySQL : empleadosMySQL) {
            numerosMySQL.add(empleadoMySQL.getNumero());
        }

        List<Empleado> empleadosFaltantes = new ArrayList<>();
        // Identificar empleados que están en SQLite pero no en MySQL
        for (Empleado empleadoSQLite : empleadosSQLite) {
            if (!numerosMySQL.contains(empleadoSQLite.getNumero())) {
                empleadosFaltantes.add(empleadoSQLite);
            }
        }
        return empleadosFaltantes;
    }

    private static void insertarEmpleadosEnMySQL(List<Empleado> empleados) {
    	Connection conn = null;
    	PreparedStatement stmt = null;
        try {
        	conn = ConexionMysql.getConnection();
        	
            stmt = conn.prepareStatement(INSERT_VOLCADO_TABLA);

            for (Empleado empleado : empleados) {
                stmt.setString(1, empleado.getNumero());
                stmt.setString(2, empleado.getNombre());
                stmt.setInt(3, empleado.getEdad());
                stmt.setString(4, empleado.getDepartamento());
                stmt.setString(5, empleado.getCategoria());
                stmt.setString(6, empleado.getContrato());

                stmt.executeUpdate();
            }

            System.out.println("Sincronizacion completada");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
} // cierre Clase
