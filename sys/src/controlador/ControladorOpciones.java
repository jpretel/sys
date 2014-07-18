package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

import vista.barras.BarraMaestro;
import vista.formularios.FrmConsultarRUC;
import vista.formularios.FrmConsumidor;
import vista.formularios.FrmCuentas;
import vista.formularios.FrmDocumento;
import vista.formularios.FrmGrupos;
import vista.formularios.FrmListaProductos;
import vista.formularios.FrmProductos;
import vista.formularios.FrmPtoEmision;
import vista.formularios.FrmSubdiario;
import vista.formularios.FrmSucursal;
import vista.formularios.FrmUsuario;
import vista.formularios.ModuloSpring;

public class ControladorOpciones {

	private BarraMaestro barraMaestro;

	private JDesktopPane desktopPane;

	public ActionListener returnAction(String opcion) {
		
		if (opcion.equalsIgnoreCase("FrmSubdiario")) {
			return new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					FrmSubdiario frm = new FrmSubdiario();
					agregarFormulario(frm);
				}
			};
		}
		
		if (opcion.equalsIgnoreCase("FrmSucursal")) {
			return new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					FrmSucursal frm = new FrmSucursal();
					agregarFormulario(frm);
				}
			};
		}
		
		if (opcion.equalsIgnoreCase("FrmPtoEmision")) {
			return new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					FrmPtoEmision frm = new FrmPtoEmision();
					agregarFormulario(frm);
				}
			};
		}

		if (opcion.equalsIgnoreCase("FrmDocumento")) {
			return new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					FrmDocumento frm = new FrmDocumento();
					agregarFormulario(frm);
				}
			};
		}

		if (opcion.equalsIgnoreCase("FrmCuentas")) {
			return new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					FrmCuentas frm = new FrmCuentas();
					agregarFormulario(frm);
				}
			};
		}

		if (opcion.equalsIgnoreCase("FrmUsuario")) {
			return new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					FrmUsuario frm = new FrmUsuario(barraMaestro);
					agregarFormulario(frm);
				}
			};
		}

		if (opcion.equalsIgnoreCase("FrmConsultarRUC")) {
			return new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					FrmConsultarRUC frm = new FrmConsultarRUC(barraMaestro);
					agregarFormulario(frm);
				}
			};
		}

		if (opcion.equalsIgnoreCase("FrmGrupos")) {
			return new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					FrmGrupos frm = new FrmGrupos(barraMaestro);
					agregarFormulario(frm);

				}
			};
		}

		if (opcion.equalsIgnoreCase("ModuloSpring")) {
			return new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					ModuloSpring frm = new ModuloSpring(barraMaestro);
					agregarFormulario(frm);

				}
			};
		}

		if (opcion.equalsIgnoreCase("FrmModulo")) {
			return new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					FrmConsultarRUC frm = new FrmConsultarRUC(barraMaestro);
					agregarFormulario(frm);
				}
			};
		}

		if (opcion.equalsIgnoreCase("FrmListaProductos")) {
			return new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					FrmListaProductos frm = new FrmListaProductos(
							"..::Lista de los Productos::..", barraMaestro);
					agregarFormulario(frm);

				}
			};
		}

		if (opcion.equalsIgnoreCase("FrmProductos")) {
			return new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					FrmProductos frm = new FrmProductos("..::Productos::..",
							barraMaestro);
					agregarFormulario(frm);
				}
			};
		}

		if (opcion.equalsIgnoreCase("FrmConsumidor")) {
			return new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					FrmConsumidor frm = new FrmConsumidor();
					agregarFormulario(frm);
				}
			};
		}
		return null;
	}

	private void agregarFormulario(JInternalFrame form) {
		getDesktopPane().add(form);
		try {
			form.setSelected(true);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
	}

	public BarraMaestro getBarraMaestro() {
		return barraMaestro;
	}

	public void setBarraMaestro(BarraMaestro barraMaestro) {
		this.barraMaestro = barraMaestro;
	}

	public JDesktopPane getDesktopPane() {
		return desktopPane;
	}

	public void setDesktopPane(JDesktopPane desktopPane) {
		this.desktopPane = desktopPane;
	}
}
