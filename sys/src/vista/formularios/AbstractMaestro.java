package vista.formularios;

import javax.swing.JInternalFrame;

import vista.barras.PanelBarraMaestro;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

public abstract class AbstractMaestro extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	
	private String estado;
	
	protected static final String EDICION = "EDICION";
	protected static final String VISTA = "VISTA";
	protected static final String NUEVO = "NUEVO";
	
	private PanelBarraMaestro barra;
	protected JPanel pnlContenido;
	public AbstractMaestro(String titulo) {
		setEstado(VISTA);
		setTitle(titulo);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setVisible(true);
		setResizable(true);
		barra = new PanelBarraMaestro();
		barra.setFormMaestro(this);
		FlowLayout flowLayout = (FlowLayout) barra.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		getContentPane().add(barra, BorderLayout.NORTH);
		
		pnlContenido = new JPanel();
		getContentPane().add(pnlContenido, BorderLayout.CENTER);
		setBounds(100, 100, 555, 325);
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
	public abstract void llenar_tablas();
	public abstract void vista_edicion();
	public abstract void vista_noedicion();
	public abstract void init();
	public abstract void actualiza_objeto(Object entidad);
	
	public void cancelar () {
		llenar_tablas();
		llenar_lista();
		llenar_datos();
		setEstado(VISTA);
		vista_noedicion();
		getBarra().enVista();
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
	}
	
	public abstract void llenarDesdeVista();
	
	public abstract boolean isValidaVista();

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
}
