package core.dao;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.Connection.Method;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TipoCambio {

	public static void main(String[] args) {

		try {
			Map<String, String> data = new HashMap<String, String>();
			data.put("mesElegido", "03");
			data.put("mes", "03");
			data.put("anho", "2014");
			data.put("accion", "init");
			data.put("email", "");
			Connection.Response res = Jsoup
					.connect("http://www.sunat.gob.pe/cl-at-ittipcam/tcS01Alias")
					.data(data).method(Method.GET).execute();
			
			//System.out.println(res.parse());
			Element table = res.parse().select("TABLE").get(1);
			
			Elements rows = table.select("tr");
			
			for (int i =1; i<  rows.size(); i++) {
				Element e = rows.get(i);
				for (int j=0; j<e.children().size()/3; j++) {
					TCambio t = new TCambio();
					t.setDia(Integer.parseInt(e.child(j*3).select("td").text()));
					t.setTcompra(Float.parseFloat(e.child(j*3+1).select("td").text()));
					t.setTventa(Float.parseFloat(e.child(j*3+2).select("td").text()));
					System.out.println(t);
				}
				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}

class TCambio {
	private int dia;
	private float tcompra;
	private float tventa;
	public int getDia() {
		return dia;
	}
	public void setDia(int dia) {
		this.dia = dia;
	}
	public float getTcompra() {
		return tcompra;
	}
	public void setTcompra(float tcompra) {
		this.tcompra = tcompra;
	}
	public float getTventa() {
		return tventa;
	}
	public void setTventa(float tventa) {
		this.tventa = tventa;
	}
	
	@Override
	public String toString() {
		return  getDia() + " " + " T.C.: " + getTcompra() + " T.V.:" + getTventa();
	}
	
}
