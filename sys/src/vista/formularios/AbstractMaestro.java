package vista.formularios;

import javax.swing.AbstractAction;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import vista.barras.PanelBarraMaestro;
import vista.utilitarios.UtilMensajes;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public abstract class AbstractMaestro extends JInternalFrame {

	private static final long serialVersionUID = 1L;

	private String estado;

	protected static final String EDICION = "EDICION";
	protected static final String VISTA = "VISTA";
	protected static final String NUEVO = "NUEVO";

	private PanelBarraMaestro barra;
	protected JPanel pnlContenido;
	protected String bkEntidad = null;

	public AbstractMaestro(String titulo) {
		setEstado(VISTA);
		setTitle(titulo);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setVisible(true);
		setResizable(true);
		setMinimumSize(new Dimension(555, 325));

		barra = new PanelBarraMaestro();
		barra.setFormMaestro(this);
		FlowLayout flowLayout = (FlowLayout) barra.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		getContentPane().add(barra, BorderLayout.NORTH);

		pnlContenido = new JPanel();
		getContentPane().add(pnlContenido, BorderLayout.CENTER);
		setBounds(100, 100, 555, 325);

		InputMap inputMap = getRootPane().getInputMap(
				JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
		inputMap.put(editarKey, "editar");
		inputMap.put(nuevoKey, "nuevo");
		inputMap.put(grabarKey, "grabar");
		inputMap.put(cancelarKey, "cancelar");
		inputMap.put(elminarKey, "eliminar");

		getRootPane().getActionMap().put("editar", new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent evt) {

			}
		});
		getRootPane().getActionMap().put("nuevo", new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent evt) {
				DoNuevo();
			}
		});
		getRootPane().getActionMap().put("editar", new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent evt) {
				editar();
			}
		});
		getRootPane().getActionMap().put("grabar", new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent evt) {
				DoGrabar();
			}
		});
		getRootPane().getActionMap().put("cancelar", new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent evt) {
				cancelar();
			}
		});
		getRootPane().getActionMap().put("eliminar", new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent evt) {
				DoEliminar();
			}
		});
	}

	public void iniciar() {
		llenar_tablas();
		llenar_lista();
		llenar_datos();
		getBarra().enVista();
		vista_noedicion();
	}

	public abstract void nuevo();

	public void editar() {
		setEstado(EDICION);
		getBarra().enEdicion();
		vista_edicion();
	}

	public abstract void anular();

	public abstract void grabar();

	public abstract void eliminar();

	public abstract void llenar_datos();

	public abstract void llenar_lista();

	public  void limpiarDetalle() {}
	
	public abstract void llenar_tablas();

	public abstract void vista_edicion();

	public abstract void vista_noedicion();

	public abstract void init();

	public abstract void actualiza_objeto(Object entidad);

	public void cancelar() {
		llenar_tablas();
		llenar_lista();
		llenar_datos();
		setEstado(VISTA);
		vista_noedicion();
		getBarra().enVista();
		limpiarDetalle();
	}

	public void DoGrabar() {
		boolean esVistaValido;
		esVistaValido = isValidaVista();
		if (esVistaValido) {
			llenarDesdeVista();
			grabar();
			setEstado(VISTA);
			getBarra().enVista();
			vista_noedicion();
			llenar_tablas();
			llenar_lista();
			llenar_datos();
		}
	}

	public void DoNuevo() {
		nuevo();
		setEstado(NUEVO);
		getBarra().enEdicion();
		llenar_datos();
		vista_edicion();
	}

	public void DoEliminar() {
		eliminar();
		setEstado(VISTA);
		getBarra().enVista();
		vista_noedicion();
		llenar_tablas();
		llenar_lista();
		llenar_datos();
		iniciar();
	}

	public void logEliminar(EntidadLog log) {
		Historial.validar("Eliminar", log.historial(), getTitle());
	}

	public void logModificar(EntidadLog logBD, EntidadLog logActual) {
		if (logBD == null) {
			Historial.validar("Nuevo", logActual.historial(), getTitle());
		} else {
			Historial.validar("Modificar", logBD.historial(),
					logActual.historial(), getTitle());
		}
	}

	public abstract void llenarDesdeVista();

	public abstract boolean isValidaVista();
	
	public void TextFieldsEdicion(boolean band, JTextField... texts) {
		for (JTextField text : texts) {
			text.setEditable(band);
		}
	}
	
	public boolean TextFieldObligatorios (JTextField... texts) {
		for (JTextField text : texts) {
			if (text.getText().trim().isEmpty()) {
				UtilMensajes.mensaje_alterta("DATO_REQUERIDO", text.getName());
				text.requestFocus();
				return false;
			}
		}
		return true;
	}

	public void salir() {
		this.dispose();
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public PanelBarraMaestro getBarra() {
		return barra;
	}

	public void setBarra(PanelBarraMaestro barra) {
		this.barra = barra;
	}

	KeyStroke editarKey = KeyStroke.getKeyStroke(KeyEvent.VK_E,
			InputEvent.CTRL_MASK);
	KeyStroke nuevoKey = KeyStroke.getKeyStroke(KeyEvent.VK_N,
			InputEvent.CTRL_MASK);
	KeyStroke grabarKey = KeyStroke.getKeyStroke(KeyEvent.VK_G,
			InputEvent.CTRL_MASK);
	KeyStroke cancelarKey = KeyStroke.getKeyStroke(KeyEvent.VK_U,
			InputEvent.CTRL_MASK);
	KeyStroke elminarKey = KeyStroke.getKeyStroke(KeyEvent.VK_B,
			InputEvent.CTRL_MASK);
}
