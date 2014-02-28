package vista.formularios;

import java.beans.PropertyVetoException;

import javax.swing.JInternalFrame;

import vista.barras.BarraMaestro;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public abstract class AbstractMaestro extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	
	private String estado;
	
	protected static final String EDICION = "EDICION";
	protected static final String VISTA = "VISTA";
	protected static final String NUEVO = "NUEVO";
	
	private BarraMaestro barra;
		
	public AbstractMaestro(String titulo, BarraMaestro barra) {
		setBarra(barra);
		setEstado(VISTA);
		setTitle(titulo);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setVisible(true);
		setResizable(true);
		setBounds(100, 100, 555, 325);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGap(0, 539, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGap(0, 295, Short.MAX_VALUE)
		);
		getContentPane().setLayout(groupLayout);

	}
	
	public void iniciar() {
		llenar_tablas();
		llenar_lista();
		llenar_datos();
		getBarra().enVista();
		vista_noedicion();
	}

	public void nuevo() {
		setEstado(NUEVO);
		getBarra().enEdicion();
		llenar_datos();
		vista_edicion();
	}

	public void editar() {
		setEstado(EDICION);
		getBarra().enEdicion();
		vista_edicion();
	}

	public abstract void anular();

	public void grabar(){
		setEstado(VISTA);
		getBarra().enVista();
		vista_noedicion();
		llenar_tablas();
		llenar_lista();
		llenar_datos();
	}
	

	public void eliminar() {
		setEstado(VISTA);
		getBarra().enVista();
	}
	
	public abstract void llenar_datos();
	public abstract void llenar_lista();
	public abstract void llenar_tablas();
	public abstract void vista_edicion();
	public abstract void vista_noedicion();
	public abstract void nuevo_lista();
	
	public void cancelar () {
		llenar_tablas();
		llenar_lista();
		llenar_datos();
		setEstado(VISTA);
		vista_noedicion();
		getBarra().enVista();
	}	

	public void salir() {
		this.dispose();
	}
	
	@Override
	public void setSelected(boolean selected) throws PropertyVetoException {
		super.setSelected(selected);
		actualizaBarra();
	}
	
	protected abstract void actualizaBarra();
	

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public BarraMaestro getBarra() {
		return barra;
	}

	public void setBarra(BarraMaestro barra) {
		this.barra = barra;
	}	
}
