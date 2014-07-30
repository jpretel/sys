package vista.utilitarios;

import java.util.Calendar;
import java.util.UUID;

public class StringUtils {
	public static String _left(String cadena, int longitud) {
		cadena = cadena.trim();
		if (cadena.length() <= longitud)
			return cadena;
		return cadena.substring(0, longitud);
	}

	public static String _right(String cadena, int longitud) {
		cadena = cadena.trim();

		cadena = cadena.trim();
		if (cadena.length() <= longitud)
			return cadena;
		return cadena.substring(cadena.length() - longitud);
	}

	public static String prueba() {
		Calendar c;
		c = Calendar.getInstance();
		System.out.println(c.getTimeInMillis());
		// byte[] bytesOfMessage = yourString.getBytes("UTF-8");
		// MessageDigest md = MessageDigest.getInstance("MD5");
		// byte[] thedigest = md.digest(bytesOfMessage);
		return null;
	}

	public static void main(String[] args) {
		String cadena = "Holaaabbb";
		System.out.println(_left(cadena, 3));

		String input = "some input stringss";
		int hashCode = input.hashCode();
		System.out.println("input hash code = " + hashCode);

		UUID idOne = UUID.randomUUID();
		UUID idTwo = UUID.randomUUID();
		log("UUID One: " + idOne);
		log("UUID Two: " + idTwo);
	}

	private static void log(Object aObject) {
		System.out.println(String.valueOf(aObject));
	}
	
	
}
