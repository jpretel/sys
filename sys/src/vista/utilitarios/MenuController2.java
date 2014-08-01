package vista.utilitarios;

import java.util.ArrayList;
import java.util.List;

import vista.Sys;
import core.entity.GrupoMenu;
import core.entity.OpcionMenu;
import core.entity.TituloMenu;
import dao.SysGrupoDAO;
import dao.SysOpcionDAO;
import dao.SysTituloDAO;
import entity.SysGrupo;
import entity.SysModulo;
import entity.SysTitulo;

public class MenuController2 {
	private List<TituloMenu> titulos;

	public MenuController2() {
		LlenarModulo();
	}

	public static List<SysTitulo> getTitulosPorModulo(SysModulo modulo) {
		List<SysTitulo> titulos = null;
		SysTituloDAO sysTituloDAO = new SysTituloDAO();
		SysGrupoDAO sysGrupoDAO = new SysGrupoDAO();
		SysOpcionDAO sysOpcionDAO = new SysOpcionDAO();

		if (Sys.usuario.getGrupoUsuario().getEsAdministrador() == 1) {
			titulos = sysTituloDAO.getPorModulo(modulo);
			if (titulos == null)
				titulos = new ArrayList<SysTitulo>();
			for (SysTitulo titulo : titulos) {
				titulo.setSysGrupos(sysGrupoDAO.getPorTitulo(titulo));
				if (titulo.getSysGrupos() == null) {
					titulo.setSysGrupos(new ArrayList<SysGrupo>());
				}
				for (SysGrupo grupo : titulo.getSysGrupos()) {
					grupo.setSysOpcions(sysOpcionDAO.getPorGrupo(grupo));
				}
			}
		} else {
			titulos = sysTituloDAO.getPorModuloUsuario(modulo, Sys.usuario);
			for (SysTitulo titulo : titulos) {
				titulo.setSysGrupos(sysGrupoDAO.getPorTitulo(titulo,
						Sys.usuario));
				if (titulo.getSysGrupos() == null) {
					titulo.setSysGrupos(new ArrayList<SysGrupo>());
				}
				for (SysGrupo grupo : titulo.getSysGrupos()) {
					grupo.setSysOpcions(sysOpcionDAO.getPorGrupo(grupo,
							Sys.usuario));
				}
			}
		}

		return titulos;
	}

	private void LlenarModulo() {
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
		om.setOpcion("FrmCuentas");
		om.setPrioridad(1); // 1, 2 o 3

		opciones.add(om);

		om = new OpcionMenu();
		om.setPosicion(3);
		om.setDescripcion("Grupos de Productos");
		om.setImagen("/main/resources/nuevo.png");
		om.setOpcion("FrmGrupos");		
		om.setPrioridad(3); 
	
		
		opciones.add(om);
		OpcionMenu om1 = new OpcionMenu();
		om1 = new OpcionMenu();
		om1.setPosicion(3);
		om1.setDescripcion("Marcas de Productos");
		om1.setImagen("/main/resources/editar.png");
		om1.setOpcion("FrmMarca");
		om1.setPrioridad(3); //1, 2 o 3
		
		opciones.add(om1);
		
		om = new OpcionMenu();
		om.setPosicion(3);
		om.setDescripcion("Medidas");
		om.setImagen("/main/resources/editar.png");
		om.setOpcion("frmUnimedida");
		om.setPrioridad(3); //1, 2 o 3
		
		opciones.add(om);
		
		om = new OpcionMenu();
		om.setPosicion(3);
		om.setDescripcion("Areas");
		om.setImagen("/main/resources/editar.png");
		om.setOpcion("FrmAreas");
		om.setPrioridad(3); //1, 2 o 3
		
		opciones.add(om);
		
		om = new OpcionMenu();
		om.setPosicion(3);
		om.setDescripcion("Responsable");
		om.setImagen("/main/resources/editar.png");
		om.setOpcion("FrmResponsable");
		om.setPrioridad(3); //1, 2 o 3
		
		opciones.add(om);
		
		om = new OpcionMenu();
		om.setPosicion(3);
		om.setDescripcion("Lista de Productos");
		om.setImagen("/main/resources/editar.png");
		om.setOpcion("FrmListaProductos");
		om.setPrioridad(3); //1, 2 o 3
		
		opciones.add(om);
		
		om = new OpcionMenu();
		om.setPosicion(3);
		om.setDescripcion("Lista de Clientes y Proveedores");
		om.setImagen("/main/resources/editar.png");
		om.setOpcion("FrmListaClieprov");
		om.setPrioridad(3); //1, 2 o 3
		
		opciones.add(om);
	
		om = new OpcionMenu();
		om.setPosicion(3);
		om.setDescripcion("Cdf Form");
		om.setImagen("/main/resources/editar.png");
		om.setOpcion("FrmCfgDocumento");
		om.setPrioridad(3); // 1, 2 o 3

		opciones.add(om);

		om = new OpcionMenu();
		om.setPosicion(3);
		om.setDescripcion("Grupo de Usuarios");
		om.setImagen("/main/resources/editar.png");
		om.setOpcion("FrmGrupoUsuario");
		om.setPrioridad(3); // 1, 2 o 3

		opciones.add(om);

		om = new OpcionMenu();
		om.setPosicion(3);
		om.setDescripcion("Usuario");
		om.setImagen("/main/resources/editar.png");
		om.setOpcion("FrmUsuario");
		om.setPrioridad(3); // 1, 2 o 3

		opciones.add(om);

		om = new OpcionMenu();
		om.setPosicion(3);
		om.setDescripcion("FrmSysModulo");
		om.setImagen("/main/resources/editar.png");
		om.setOpcion("FrmSysModulo");
		om.setPrioridad(3); // 1, 2 o 3

		opciones.add(om);

		om = new OpcionMenu();
		om.setPosicion(3);
		om.setDescripcion("FrmSysGrupo");
		om.setImagen("/main/resources/editar.png");
		om.setOpcion("FrmSysGrupo");
		om.setPrioridad(3); // 1, 2 o 3

		opciones.add(om);

		om = new OpcionMenu();
		om.setPosicion(3);
		om.setDescripcion("Otratabla");
		om.setImagen("/main/resources/editar.png");
		om.setOpcion("Frm_Cuentas");
		om.setPrioridad(3); // 1, 2 o 3

		opciones.add(om);

		om = new OpcionMenu();
		om.setPosicion(3);
		om.setDescripcion("Otratabla");
		om.setImagen("/main/resources/editar.png");
		om.setOpcion("Frm_Cuentas");
		om.setPrioridad(3); // 1, 2 o 3

		opciones.add(om);

		om = new OpcionMenu();
		om.setPosicion(3);
		om.setDescripcion("Otratabla");
		om.setImagen("/main/resources/editar.png");
		om.setOpcion("Frm_Cuentas");
		om.setPrioridad(3); // 1, 2 o 3

		opciones.add(om);

		om = new OpcionMenu();
		om.setPosicion(3);
		om.setDescripcion("Otratabla");
		om.setImagen("/main/resources/editar.png");
		om.setOpcion("Frm_Cuentas");
		om.setPrioridad(3); // 1, 2 o 3

		opciones.add(om);

		om = new OpcionMenu();
		om.setPosicion(3);
		om.setDescripcion("Otratabla");
		om.setImagen("/main/resources/editar.png");
		om.setOpcion("Frm_Cuentas");
		om.setPrioridad(3); // 1, 2 o 3

		opciones.add(om);

		om = new OpcionMenu();
		om.setPosicion(3);
		om.setDescripcion("Otratabla");
		om.setImagen("/main/resources/editar.png");
		om.setOpcion("Frm_Cuentas");
		om.setPrioridad(3); // 1, 2 o 3

		opciones.add(om);

		om = new OpcionMenu();
		om.setPosicion(3);
		om.setDescripcion("Otratabla");
		om.setImagen("/main/resources/editar.png");
		om.setOpcion("Frm_Cuentas");
		om.setPrioridad(3); // 1, 2 o 3

		opciones.add(om);

		om = new OpcionMenu();
		om.setPosicion(3);
		om.setDescripcion("Otratabla");
		om.setImagen("/main/resources/editar.png");
		om.setOpcion("Frm_Cuentas");
		om.setPrioridad(3); // 1, 2 o 3

		opciones.add(om);

		om = new OpcionMenu();
		om.setPosicion(3);
		om.setDescripcion("Otratabla");
		om.setImagen("/main/resources/editar.png");
		om.setOpcion("Frm_Cuentas");
		om.setPrioridad(3); // 1, 2 o 3

		opciones.add(om);

		om = new OpcionMenu();
		om.setPosicion(3);
		om.setDescripcion("Otratabla");
		om.setImagen("/main/resources/editar.png");
		om.setOpcion("Frm_Cuentas");
		om.setPrioridad(3); // 1, 2 o 3

		opciones.add(om);

		om = new OpcionMenu();
		om.setPosicion(3);
		om.setDescripcion("Otratabla");
		om.setImagen("/main/resources/editar.png");
		om.setOpcion("Frm_Cuentas");
		om.setPrioridad(3); // 1, 2 o 3

		opciones.add(om);

		om = new OpcionMenu();
		om.setPosicion(3);
		om.setDescripcion("Otratabla");
		om.setImagen("/main/resources/editar.png");
		om.setOpcion("Frm_Cuentas");
		om.setPrioridad(3); // 1, 2 o 3

		opciones.add(om);

		om = new OpcionMenu();
		om.setPosicion(3);
		om.setDescripcion("Otratabla");
		om.setImagen("/main/resources/editar.png");
		om.setOpcion("Frm_Cuentas");
		om.setPrioridad(3); // 1, 2 o 3

		opciones.add(om);

		om = new OpcionMenu();
		om.setPosicion(3);
		om.setDescripcion("Otratabla");
		om.setImagen("/main/resources/editar.png");
		om.setOpcion("Frm_Cuentas");
		om.setPrioridad(3); // 1, 2 o 3

		opciones.add(om);

		om = new OpcionMenu();
		om.setPosicion(3);
		om.setDescripcion("Otratabla");
		om.setImagen("/main/resources/editar.png");
		om.setOpcion("Frm_Cuentas");
		om.setPrioridad(3); // 1, 2 o 3

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
