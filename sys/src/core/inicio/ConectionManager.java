package core.inicio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectionManager {

	public static int _mysql = 1;

	// private static int _postgres = 2;

	public static boolean isConexionOK(int db_manager, SysCfgInicio cfgInicio) {
		if (db_manager == _mysql) {
			return verificaMySql(cfgInicio);
		} else {
			return verificaPostgres(cfgInicio);
		}
	}

	private static boolean verificaMySql(SysCfgInicio cfgInicio) {
		Connection conexion = null;
		boolean isExito = false;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conexion = DriverManager.getConnection(cfgInicio.getURL(_mysql),
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
