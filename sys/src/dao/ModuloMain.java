	package dao;

import entity.Modulo;

public class ModuloMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ModuloDAO mdao = new ModuloDAO();
		Modulo m = new Modulo();
		
		m.setId(2);
		m.setDescripcion("Prueba1");
		m.setOpcion("frm_cuentas");
		
		mdao.crear_editar(m);
		
		m.setId(5);
		m.setDescripcion("Prueba2");
		m.setOpcion("frm_cuentas");
		
		mdao.crear_editar(m);
		
		m.setId(6);
		m.setDescripcion("Prueba3");
		m.setOpcion("frm_cuentas");
		
		mdao.crear_editar(m);
		
		m.setId(15);
		m.setDescripcion("Prueba4");
		m.setOpcion("frm_cuentas");;
		
		mdao.crear_editar(m);
		
		for(Modulo mod : mdao.findAll()){
			System.out.println("ID: " + mod.getId() +" Desc.: "+ mod.getDescripcion() + " Prosicion: " + mod.getPosicion() + mod.getOpcion());
		}

	}

}
