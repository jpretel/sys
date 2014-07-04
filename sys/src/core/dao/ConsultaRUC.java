package core.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.Connection.Method;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import core.entity.Consultar;

public class ConsultaRUC {
	
	private static Consultar cons;
	private static List<Consultar> consulta = new ArrayList<Consultar>();
	
	public ConsultaRUC(){
		
	}
	
	public List<Consultar> Consulta(String ruc){
		String captcha = "";
		//String ruc = "10453688955";
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
			
			String lista[] = new String[rows.size()];
			int cont = 1;
			
			for (Element e : rows) {
				Element te = e.child(0);
				//System.out.println(te.text() + "\t");
				if (e.children().size() > 1) {
					Element td = e.child(1);
					//System.out.println(td.text());
					
					lista[cont] = te.text() + " " + td.text();
					cont++;
					cons = new Consultar(te.text().toString(), td.text());
					consulta.add(cons);
				}
			}
			
			//System.out.println("COntenido: " + lista[1]);
			//System.out.println("COntenido: " + cons);
			
			
			
			return consulta;
			
		} catch (IOException e) {
			e.printStackTrace();

		}
		return null;
	}

}
