package dao;

import entity.Prueba;

public class MainPrueba {

	public static void main(String[] args) {
		PruebaDAO pdao = new PruebaDAO();
		Prueba p = new Prueba();
		
		//p.setIdprueba(1);
		
		p.setPruebacol("dato12222");
		p.setPruebacol2("dato2w");
		
		pdao.crear_editar(p);
		
		for (Prueba p1 : pdao.findAll()) {
			System.out.println(p1.getPruebacol() + " " + p1.getPruebacol2());
		}
		
	}

}
