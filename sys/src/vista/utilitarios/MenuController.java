package vista.utilitarios;

import java.util.ArrayList;
import java.util.List;

import core.entity.GrupoMenu;
import core.entity.OpcionMenu;
import core.entity.TituloMenu;

public class MenuController {
	private List<TituloMenu> titulos;
	
	public MenuController() {
		LlenarModulo();
	}
	
	private void LlenarModulo () {
		setTitulos(new ArrayList<TituloMenu>());
		
		TituloMenu m1 = new TituloMenu();
		m1.setPosicion(1);
		m1.setDescripcion("Logística");
		m1.setGrupos(new ArrayList<GrupoMenu>());
		
		getTitulos().add(m1);
		
		GrupoMenu g11 = new GrupoMenu();
		g11.setPosicion(1);
		g11.setDescripcion("Tablas");
		g11.setImagen("/main/resources/editar.png");
		
		
		GrupoMenu g12 = new GrupoMenu();
		g12.setPosicion(2);
		g12.setDescripcion("Documentos");
		g12.setImagen("/main/resources/eliminar.png");
		
		GrupoMenu g13 = new GrupoMenu();
		g13.setPosicion(3);
		g13.setDescripcion("Reportes");
		g13.setImagen("/main/resources/grabar.png");
		
		m1.getGrupos().add(g11);
		m1.getGrupos().add(g12);
		m1.getGrupos().add(g13);
		
		List<OpcionMenu> opciones = new ArrayList<OpcionMenu>();
		OpcionMenu om = new OpcionMenu();
		om.setPosicion(1);
		om.setDescripcion("Cuentas");
		om.setImagen("/main/resources/salir.png");
		om.setOpcion("Frm_Cuentas");
		om.setPrioridad(1); //1, 2 o 3
		
		opciones.add(om);
		
		
		om = new OpcionMenu();
		om.setPosicion(2);
		om.setDescripcion("Consumidores");
		om.setImagen("/main/resources/grabar.png");
		om.setOpcion("Frm_Cuentas");
		om.setPrioridad(2); //1, 2 o 3
		
		opciones.add(om);
		
		
		om = new OpcionMenu();
		om.setPosicion(3);
		om.setDescripcion("Documentos");
		om.setImagen("/main/resources/nuevo.png");
		om.setOpcion("Frm_Cuentas");
		om.setPrioridad(3); //1, 2 o 3
		
		opciones.add(om);
		
		om = new OpcionMenu();
		om.setPosicion(3);
		om.setDescripcion("Otra tabla");
		om.setImagen("/main/resources/editar.png");
		om.setOpcion("Frm_Cuentas");
		om.setPrioridad(3); //1, 2 o 3
		
		opciones.add(om);
		
		om = new OpcionMenu();
		om.setPosicion(3);
		om.setDescripcion("Otrass tabla");
		om.setImagen("/main/resources/editar.png");
		om.setOpcion("Frm_Cuentas");
		om.setPrioridad(3); //1, 2 o 3
		
		opciones.add(om);
		
		om = new OpcionMenu();
		om.setPosicion(3);
		om.setDescripcion("Otratabla");
		om.setImagen("/main/resources/editar.png");
		om.setOpcion("Frm_Cuentas");
		om.setPrioridad(3); //1, 2 o 3
		
		opciones.add(om);
		
		om = new OpcionMenu();
		om.setPosicion(3);
		om.setDescripcion("Otratabla");
		om.setImagen("/main/resources/editar.png");
		om.setOpcion("Frm_Cuentas");
		om.setPrioridad(3); //1, 2 o 3
		
		opciones.add(om);
		
		om = new OpcionMenu();
		om.setPosicion(3);
		om.setDescripcion("Otratabla");
		om.setImagen("/main/resources/editar.png");
		om.setOpcion("Frm_Cuentas");
		om.setPrioridad(3); //1, 2 o 3
		
		opciones.add(om);
		
		om = new OpcionMenu();
		om.setPosicion(3);
		om.setDescripcion("Otratabla");
		om.setImagen("/main/resources/editar.png");
		om.setOpcion("Frm_Cuentas");
		om.setPrioridad(3); //1, 2 o 3
		
		opciones.add(om);
		
		om = new OpcionMenu();
		om.setPosicion(3);
		om.setDescripcion("Otratabla");
		om.setImagen("/main/resources/editar.png");
		om.setOpcion("Frm_Cuentas");
		om.setPrioridad(3); //1, 2 o 3
		
		opciones.add(om);
		
		om = new OpcionMenu();
		om.setPosicion(3);
		om.setDescripcion("Otratabla");
		om.setImagen("/main/resources/editar.png");
		om.setOpcion("Frm_Cuentas");
		om.setPrioridad(3); //1, 2 o 3
		
		opciones.add(om);
		
		om = new OpcionMenu();
		om.setPosicion(3);
		om.setDescripcion("Otratabla");
		om.setImagen("/main/resources/editar.png");
		om.setOpcion("Frm_Cuentas");
		om.setPrioridad(3); //1, 2 o 3
		
		opciones.add(om);
		
		om = new OpcionMenu();
		om.setPosicion(3);
		om.setDescripcion("Otratabla");
		om.setImagen("/main/resources/editar.png");
		om.setOpcion("Frm_Cuentas");
		om.setPrioridad(3); //1, 2 o 3
		
		opciones.add(om);
		
		om = new OpcionMenu();
		om.setPosicion(3);
		om.setDescripcion("Otratabla");
		om.setImagen("/main/resources/editar.png");
		om.setOpcion("Frm_Cuentas");
		om.setPrioridad(3); //1, 2 o 3
		
		opciones.add(om);
		
		om = new OpcionMenu();
		om.setPosicion(3);
		om.setDescripcion("Otratabla");
		om.setImagen("/main/resources/editar.png");
		om.setOpcion("Frm_Cuentas");
		om.setPrioridad(3); //1, 2 o 3
		
		opciones.add(om);
		
		om = new OpcionMenu();
		om.setPosicion(3);
		om.setDescripcion("Otratabla");
		om.setImagen("/main/resources/editar.png");
		om.setOpcion("Frm_Cuentas");
		om.setPrioridad(3); //1, 2 o 3
		
		opciones.add(om);
		
		om = new OpcionMenu();
		om.setPosicion(3);
		om.setDescripcion("Otratabla");
		om.setImagen("/main/resources/editar.png");
		om.setOpcion("Frm_Cuentas");
		om.setPrioridad(3); //1, 2 o 3
		
		opciones.add(om);
		
		om = new OpcionMenu();
		om.setPosicion(3);
		om.setDescripcion("Otratabla");
		om.setImagen("/main/resources/editar.png");
		om.setOpcion("Frm_Cuentas");
		om.setPrioridad(3); //1, 2 o 3
		
		opciones.add(om);
		
		om = new OpcionMenu();
		om.setPosicion(3);
		om.setDescripcion("Otratabla");
		om.setImagen("/main/resources/editar.png");
		om.setOpcion("Frm_Cuentas");
		om.setPrioridad(3); //1, 2 o 3
		
		opciones.add(om);
		
		om = new OpcionMenu();
		om.setPosicion(3);
		om.setDescripcion("Otratabla");
		om.setImagen("/main/resources/editar.png");
		om.setOpcion("Frm_Cuentas");
		om.setPrioridad(3); //1, 2 o 3
		
		opciones.add(om);
		
		om = new OpcionMenu();
		om.setPosicion(3);
		om.setDescripcion("Otratabla");
		om.setImagen("/main/resources/editar.png");
		om.setOpcion("Frm_Cuentas");
		om.setPrioridad(3); //1, 2 o 3
		
		opciones.add(om);
		
		om = new OpcionMenu();
		om.setPosicion(3);
		om.setDescripcion("Otratabla");
		om.setImagen("/main/resources/editar.png");
		om.setOpcion("Frm_Cuentas");
		om.setPrioridad(3); //1, 2 o 3
		
		opciones.add(om);
		
		om = new OpcionMenu();
		om.setPosicion(3);
		om.setDescripcion("Otratabla");
		om.setImagen("/main/resources/editar.png");
		om.setOpcion("Frm_Cuentas");
		om.setPrioridad(3); //1, 2 o 3
		
		opciones.add(om);
		
		om = new OpcionMenu();
		om.setPosicion(3);
		om.setDescripcion("Otratabla");
		om.setImagen("/main/resources/editar.png");
		om.setOpcion("Frm_Cuentas");
		om.setPrioridad(3); //1, 2 o 3
		
		opciones.add(om);
		
		om = new OpcionMenu();
		om.setPosicion(3);
		om.setDescripcion("Otratabla");
		om.setImagen("/main/resources/editar.png");
		om.setOpcion("Frm_Cuentas");
		om.setPrioridad(3); //1, 2 o 3
		
		opciones.add(om);
		
		om = new OpcionMenu();
		om.setPosicion(3);
		om.setDescripcion("Otratabla");
		om.setImagen("/main/resources/editar.png");
		om.setOpcion("Frm_Cuentas");
		om.setPrioridad(3); //1, 2 o 3
		
		opciones.add(om);
		
		om = new OpcionMenu();
		om.setPosicion(3);
		om.setDescripcion("Otratabla");
		om.setImagen("/main/resources/editar.png");
		om.setOpcion("Frm_Cuentas");
		om.setPrioridad(3); //1, 2 o 3
		
		opciones.add(om);
		
		
		
		g11.setOpciones(opciones);
		
	}

	public List<TituloMenu> getTitulos() {
		return titulos;
	}

	public void setTitulos(List<TituloMenu> titulos) {
		this.titulos = titulos;
	}
	
}
