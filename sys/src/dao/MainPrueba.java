package dao;

import entity.Prueba;

public class MainPrueba {

	public static void main(String[] args) {
		PruebaDAO pdao = new PruebaDAO();
		//Prueba p = new Prueba();
		
		//p.setIdprueba(1);
		
		
		
		
		for (Prueba p1 : pdao.findAll()) {
			
			if(p1.getIdprueba() == 1){
				p1.setPruebacol2("modficado");
				//pdao.remove(p1);
				pdao.crear_editar(p1);
				System.out.println("Pruebaa..");
			}
			//System.out.println(p1.getPruebacol() + " " + p1.getPruebacol2());
		}
		
	}

}
