package core.dao;

import core.entity.Modulo;

public class Main {

	public static void main(String[] args) {
		ModuloDAO dao = new ModuloDAO();
		Modulo m = new Modulo();
		m.setDescripcion("Producción");
		m.setPosicion(4);
		m.setOpcion("produccion");
		
		//dao.create(m);
	}

}
