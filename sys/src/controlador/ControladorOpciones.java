package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDesktopPane;

import vista.barras.BarraMaestro;
import vista.formularios.FrmConsultarRUC;
import vista.formularios.FrmConsumidor;
import vista.formularios.FrmCuentas;
import vista.formularios.FrmDocumento;
import vista.formularios.FrmGrupos;
import vista.formularios.FrmListaProductos;
import vista.formularios.FrmProductos;
import vista.formularios.FrmPtoEmision;
import vista.formularios.FrmUsuario;
import vista.formularios.ModuloSpring;

public class ControladorOpciones {

	private BarraMaestro barraMaestro;

	private JDesktopPane desktopPane;

	public ActionListener returnAction(String opcion) {
		if (opcion.equalsIgnoreCase("FrmPtoEmision")) {
			return new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					FrmPtoEmision frm = new FrmPtoEmision();
					getDesktopPane().add(frm);

				}
			};
		}

		if (opcion.equalsIgnoreCase("FrmDocumento")) {
			return new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					FrmDocumento frm = new FrmDocumento(barraMaestro);
					getDesktopPane().add(frm);

				}
			};
		}

		if (opcion.equalsIgnoreCase("FrmCuentas")) {
			return new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					FrmCuentas frm = new FrmCuentas();
					getDesktopPane().add(frm);

				}
			};
		}

		if (opcion.equalsIgnoreCase("FrmUsuario")) {
			return new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					FrmUsuario frm = new FrmUsuario(barraMaestro);
					getDesktopPane().add(frm);

				}
			};
		}

		if (opcion.equalsIgnoreCase("FrmConsultarRUC")) {
			return new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					FrmConsultarRUC frm = new FrmConsultarRUC(barraMaestro);
					getDesktopPane().add(frm);
					System.out.println("Probando consulta RUC");
				}
			};
		}

		if (opcion.equalsIgnoreCase("FrmGrupos")) {
			return new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					FrmGrupos frm = new FrmGrupos(barraMaestro);
					getDesktopPane().add(frm);

				}
			};
		}

		if (opcion.equalsIgnoreCase("ModuloSpring")) {
			return new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					ModuloSpring frm = new ModuloSpring(barraMaestro);
					getDesktopPane().add(frm);

				}
			};
		}

		if (opcion.equalsIgnoreCase("FrmModulo")) {
			return new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					FrmConsultarRUC frm = new FrmConsultarRUC(barraMaestro);
					getDesktopPane().add(frm);
					System.out.println("Probando consulta RUC");
				}
			};
		}

		if (opcion.equalsIgnoreCase("FrmListaProductos")) {
			return new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					FrmListaProductos frm = new FrmListaProductos(
							"..::Lista de los Productos::..", barraMaestro);
					getDesktopPane().add(frm);

				}
			};
		}

		if (opcion.equalsIgnoreCase("FrmProductos")) {
			return new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					FrmProductos frm = new FrmProductos("..::Productos::..",
							barraMaestro);
					getDesktopPane().add(frm);

				}
			};
		}

		if (opcion.equalsIgnoreCase("FrmConsumidor")) {
			return new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					FrmConsumidor frm = new FrmConsumidor();
					getDesktopPane().add(frm);

				}
			};
		}
		return null;
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
