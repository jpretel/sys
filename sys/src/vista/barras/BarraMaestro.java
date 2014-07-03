package vista.barras;

import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;

import vista.formularios.AbstractMaestro;

import java.awt.Event;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class BarraMaestro extends JToolBar {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final int _ancho = 28;
	private static final int _alto = -1;
	private AbstractMaestro formMaestro;
	
	private JButton btnNuevo;
	private JButton btnEditar;
	private JButton btnAnular;
	private JButton btnGrabar;
	private JButton btnEliminar;
	private JButton btnSalir;
	private JButton btnCancelar;

	public JButton getBtnNuevo() {
		return btnNuevo;
	}

	public void setBtnNuevo(JButton btnNuevo) {
		this.btnNuevo = btnNuevo;
	}

	public JButton getBtnEditar() {
		return btnEditar;
	}

	public void setBtnEditar(JButton btnEditar) {
		this.btnEditar = btnEditar;
	}

	public JButton getBtnAnular() {
		return btnAnular;
	}

	public void setBtnAnular(JButton btnAnular) {
		this.btnAnular = btnAnular;
	}

	public JButton getBtnGrabar() {
		return btnGrabar;
	}

	public void setBtnGrabar(JButton btnGrabar) {
		this.btnGrabar = btnGrabar;
	}

	public JButton getBtnEliminar() {
		return btnEliminar;
	}

	public void setBtnEliminar(JButton btnEliminar) {
		this.btnEliminar = btnEliminar;
	}

	public JButton getBtnSalir() {
		return btnSalir;
	}

	public void setBtnSalir(JButton btnSalir) {
		this.btnSalir = btnSalir;
	}

	public BarraMaestro() {

		btnNuevo = new JButton("");
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getFormMaestro().nuevo();
			}
		});


		btnNuevo.setIcon(new ImageIcon(new ImageIcon(BarraMaestro.class
				.getResource("/main/resources/iconos/nuevo.png")).getImage()
				.getScaledInstance(_ancho, _alto, java.awt.Image.SCALE_DEFAULT)));
		btnNuevo.setToolTipText("Nuevo");
		add(btnNuevo);

		btnEditar = new JButton("");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getFormMaestro().editar();
			}
		});
		btnEditar
				.setIcon(new ImageIcon(new ImageIcon(BarraMaestro.class
						.getResource("/main/resources/iconos/editar.png"))
						.getImage().getScaledInstance(_ancho, _alto,
								java.awt.Image.SCALE_DEFAULT)));
		btnEditar.setToolTipText("Editar");
		add(btnEditar);

		btnAnular = new JButton("");
		btnAnular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getFormMaestro().anular();
			}
		});
		btnAnular
				.setIcon(new ImageIcon(new ImageIcon(BarraMaestro.class
						.getResource("/main/resources/iconos/anular.png"))
						.getImage().getScaledInstance(_ancho, _alto,
								java.awt.Image.SCALE_DEFAULT)));
		btnAnular.setToolTipText("Editar");
		add(btnAnular);

		btnGrabar = new JButton("");
		btnGrabar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getFormMaestro().grabar();
			}
		});

		btnCancelar = new JButton("");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getFormMaestro().cancelar();
			}
		});
		btnCancelar
				.setIcon(new ImageIcon(new ImageIcon(BarraMaestro.class
						.getResource("/main/resources/iconos/cancelar.png"))
						.getImage().getScaledInstance(_ancho, _alto,
								java.awt.Image.SCALE_DEFAULT)));
		btnCancelar.setToolTipText("Cancelar");
		add(btnCancelar);

		btnGrabar
				.setIcon(new ImageIcon(new ImageIcon(BarraMaestro.class
						.getResource("/main/resources/iconos/grabar.png"))
						.getImage().getScaledInstance(_ancho, _alto,
								java.awt.Image.SCALE_DEFAULT)));
		btnGrabar.setToolTipText("Grabar");
		add(btnGrabar);

		btnEliminar = new JButton("");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getFormMaestro().eliminar();
			}
		});

		btnEliminar
				.setIcon(new ImageIcon(new ImageIcon(BarraMaestro.class
						.getResource("/main/resources/iconos/eliminar.png"))
						.getImage().getScaledInstance(_ancho, _alto,
								java.awt.Image.SCALE_DEFAULT)));
		btnEliminar.setToolTipText("Eliminar");
		add(btnEliminar);

		btnSalir = new JButton("");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getFormMaestro().salir();
			}
		});
		btnSalir.setIcon(new ImageIcon(new ImageIcon(BarraMaestro.class
				.getResource("/main/resources/iconos/salir.png")).getImage()
				.getScaledInstance(_ancho, _alto, java.awt.Image.SCALE_DEFAULT)));
		btnSalir.setToolTipText("Salir");
		add(btnSalir);
	}

	public void enVista() {
		edicion(true);
	}

	public void enEdicion() {
		edicion(false);
	}

	private void edicion(boolean band) {
		getBtnAnular().setEnabled(band);
		getBtnEditar().setEnabled(band);
		getBtnGrabar().setEnabled(!band);
		getBtnEliminar().setEnabled(band);
		getBtnNuevo().setEnabled(band);
		getBtnCancelar().setEnabled(!band);
	}

	public JButton getBtnCancelar() {
		return btnCancelar;
	}

	public void setBtnCancelar(JButton btnCancelar) {
		this.btnCancelar = btnCancelar;
	}

	public AbstractMaestro getFormMaestro() {
		return formMaestro;
	}

	public void setFormMaestro(AbstractMaestro formMaestro) {
		this.formMaestro = formMaestro;
	}

}
