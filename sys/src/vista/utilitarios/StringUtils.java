package vista.utilitarios;

public class StringUtils {
	public static String _left(String cadena, int longitud){
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
		return cadena.substring(cadena.length()-longitud);
	}
	
	public static void main(String[] args) {
		String cadena = "Holaaabbb";
		System.out.println(_left(cadena, 3));
	}
}
