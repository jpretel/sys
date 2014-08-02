package vista.utilitarios;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.Connection.Method;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ObjetoWeb {
	
	private static List<String> consulta = new ArrayList<String>();	
	public ObjetoWeb(){
		
	}
	
	public static List<String> ConsultaRUC(String ruc){
		String captcha = "";
		
		try {

			Connection.Response res = Jsoup
					.connect(
							"http://www.sunat.gob.pe/cl-ti-itmrconsruc/captcha")
					.data("accion", "random")
					.method(Method.POST).execute();

			Map<String, String> cookie = res.cookies();

			captcha = res.parse().select("body").text();

			Document dRuc = Jsoup
					.connect(
							"http://www.sunat.gob.pe/cl-ti-itmrconsruc/jcrS00Alias")
					.data("accion", "consPorRuc").data("nroRuc", ruc)
					.data("actReturn", "1").data("numRnd", captcha)
					.cookies(cookie).get();

			Element table = dRuc.select("TABLE[class = form-table]").first();

			Elements rows = table.select("tr");
			
			String dato;
			for (Element e : rows) {
				Element te = e.child(0);
				if (e.children().size() > 1) {
					Element td = e.child(1);					
					dato = te.text() + " " + td.text();	
					consulta.add(dato);
				}
			}			
			return consulta;
			
		} catch (IOException e) {
			e.printStackTrace();

		}
		return null;
	}
}
