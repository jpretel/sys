package controlador;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Mensajes extends Properties {

	private static final long serialVersionUID = 1L;

	public Mensajes(String idioma) {
		super();
		InputStream input;
		try {
			if (idioma.equals("ESPANOL")) {
				input = new FileInputStream("Espanol.properties");
			} else if (idioma.equals("INGLES")) {
				input = new FileInputStream("Ingles.properties");
			} else {
				input = new FileInputStream("Espanol.properties");
			}
			load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
