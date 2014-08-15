package vista.utilitarios;

import java.io.IOException;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.Connection.Method;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import entity.Clieprov;

public class ObjetoWeb {
	
	public ObjetoWeb(){
		
	}
	
	public static Clieprov ConsultaRUC(String ruc){
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
			int i = 0;
			Clieprov c = new Clieprov();
			c.setRuc(ruc);
			for (Element e : rows) {				
				if (e.children().size() > 1) {
					Element td = e.child(1);					
					if(i==0)
						c.setIdclieprov(td.text().substring(0, 11));
					if(i==2)
						c.setRazonSocial(td.text());
					if(i==6)
						c.setDireccion(td.text());
				}
				i +=1;
			}			
			return c;
			
		} catch (IOException e) {
			e.printStackTrace();

		}
		return null;
	}
}
