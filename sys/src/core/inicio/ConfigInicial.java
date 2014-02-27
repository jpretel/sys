package core.inicio;

import static core.security.Encryption.decrypt;
import static core.security.Encryption.encrypt;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;



public class ConfigInicial {
	
	public static String cfg_servidor;
	public static String cfg_basedatos;
	public static String cfg_url;
	public static String cfg_usuario;
	public static String cfg_clave;
	
	public static void LlenarConfig() {
		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream("config.properties");

			prop.load(input);

			String servidor = prop.getProperty("Servidor", "");
			String baseDatos = prop.getProperty("BaseDatos", "");
			String clave = prop.getProperty("Clave", "");
			String usuario = prop.getProperty("Usuario", "");

			if (servidor.equals("") || baseDatos.equals("")
					|| usuario.equals("") || clave.equals("")) {
				System.out.println("Error al obtener datos");
			} else {
				
				usuario = decrypt(usuario);
				clave = decrypt(clave);
				cfg_servidor = servidor;
				cfg_basedatos = baseDatos;
				cfg_usuario = usuario;
				cfg_clave = clave;
				cfg_url = "jdbc:mysql://" + servidor + "/" + baseDatos;
			}

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void CrearConfig() {
		Properties prop = new Properties();
		OutputStream output = null;

		try {
			String usuario = "toor";
			String clave = "toor";

			usuario = encrypt(usuario);
			clave = encrypt(clave);

			output = new FileOutputStream("config.properties");
			prop.setProperty("Servidor", "localhost:3306");
			prop.setProperty("BaseDatos", "bd");
			prop.setProperty("Usuario", usuario);
			prop.setProperty("Clave", clave);
			prop.store(output, null);

		} catch (IOException io) {
			io.printStackTrace();
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}

	public static void main(String[] args) {
		ConfigInicial.CrearConfig();
		ConfigInicial.LlenarConfig();
	}

}
