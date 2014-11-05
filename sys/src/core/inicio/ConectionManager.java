package core.inicio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectionManager {

	public static int _mysql = 1;
	public static int _sqlserver = 2;

	public static int _postgres = 3;
	
	public static String MYSQL = "MYSQL";
	public static String SQLSERVER = "SQLSERVER";
	
	public static boolean isConexionOK(SysCfgInicio cfgInicio) {
		if (cfgInicio.getGestor().equals(MYSQL)) {
			return verificaMySql(cfgInicio);
		}
		if (cfgInicio.getGestor().equals(SQLSERVER)) {
			return true;//verificaSQL(cfgInicio);
		}

		return verificaMySql(cfgInicio);
	}
	
	
	public static boolean isConexionOK(int db_manager, SysCfgInicio cfgInicio) {
		if (db_manager == _mysql) {
			return verificaMySql(cfgInicio);
		}
		if (db_manager == _sqlserver) {
			//return verificaSQL(cfgInicio);
			return true;
		}
		if (db_manager == _postgres) {
			return verificaPostgres(cfgInicio);
		}

		return verificaMySql(cfgInicio);
	}

	private static boolean verificaMySql(SysCfgInicio cfgInicio) {
		Connection conexion = null;
		boolean isExito = false;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conexion = DriverManager.getConnection(cfgInicio.getURL(),
					cfgInicio.getUsuario(), cfgInicio.getClave());
			isExito = true;
		} catch (ClassNotFoundException ex) {
			isExito = false;
		} catch (SQLException ex) {
			isExito = false;
		}

		try {
			conexion.close();
		} catch (Exception e) {
			isExito = false;
		}
		return isExito;
	}
	
	private static boolean verificaSQL(SysCfgInicio cfgInicio) {
		Connection conexion = null;
		boolean isExito = false;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conexion = DriverManager.getConnection(cfgInicio.getURL(),
					cfgInicio.getUsuario(), cfgInicio.getClave());
			isExito = true;
		} catch (ClassNotFoundException ex) {
			isExito = false;
		} catch (SQLException ex) {
			isExito = false;
		}

		try {
			conexion.close();
		} catch (Exception e) {
			isExito = false;
		}
		return isExito;
	}

	private static boolean verificaPostgres(SysCfgInicio cfgInicio) {
		return false;
	}
}
