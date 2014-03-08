package vista.contenedores;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Window;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

import vista.controles.IBusqueda;
import vista.utilitarios.JXTextFieldAC;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractCntBuscar extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public  JTextField txtCodigo;
	public JTextField txtDescripcion;
	public JButton btnBuscar;
	List<IBusqueda> data = new ArrayList<IBusqueda>();

	public AbstractCntBuscar() {
		this.setForeground(Color.LIGHT_GRAY);
		FlowLayout flowLayout = (FlowLayout) this.getLayout();
		flowLayout.setVgap(0);
		flowLayout.setHgap(0);
		flowLayout.setAlignment(FlowLayout.LEFT);
		this.setBounds(152, 11, 262, 23);

		String[] cabeceras = new String[] { "Codigo", "Descripcion" };
		int[] anchos = new int[] { 90, 200 };
		
		/**
		 * Constructor para TextField this(debería ser el internalframe), 
		 * cabceras y ancho de columnas; y la data en un List<T>
		 */
		
		txtCodigo = new JXTextFieldAC(getFormulario(), cabeceras, anchos, data) {
			private static final long serialVersionUID = 1L;
			/**
			 * Sobreescribir este motodo en caso de que se quiere buscar
			 * a partir del dígito N, por defecto es 3.
			 * En este caso va a mostrar el popup a partir del 2do dígito
			 */
			@Override
			public int getMinimoBusqueda() {
				return 2;
			}
			/**
			 * Sobreescribir este motodo para recuperar el valor seleccionado
			 */
			@Override
			public void cargaDatos() {
				if (getSeleccionado() != null) {
					if (getSeleccionado() instanceof Object) {
						Obj obj = (Obj) getSeleccionado();
						txtCodigo.setText(obj.getCodigo());
						txtDescripcion.setText(obj.getDescripcion());						
					}
				}
			}
		};

		txtCodigo.setColumns(5);
		add(txtCodigo);
		
		txtDescripcion = new JTextField();		
		txtDescripcion.setEnabled(false);
		txtDescripcion.setColumns(20);
		add(txtDescripcion);
		
		btnBuscar = new JButton("");	
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				buscar();				
			}
		});
		btnBuscar.setIcon(new ImageIcon(AbstractCntBuscar.class.getResource("/main/resources/iconos/vistaprevia.png")));
		btnBuscar.setVerticalAlignment(SwingConstants.BOTTOM);
		btnBuscar.setHorizontalAlignment(SwingConstants.LEFT);
		btnBuscar.setAlignmentY(0.0f);
		add(btnBuscar);		
	}
	public abstract void buscar();
	
	public void cargar_informacion(String[][] datos){
		for(int i = 0 ; i < datos.length; i++){
			data.add(new Obj(datos[i][0], datos[i][1]));
		}
	}
	public Window getFormulario(){
		return null;
	}
	
}


class Obj implements IBusqueda {
	private String codigo;
	private String descripcion;

	public Obj(String codigo, String descripcion) {
		this.codigo = codigo;
		this.descripcion = descripcion;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String[] datoBusqueda() {
		return new String[] { this.codigo, this.descripcion };
	}

	@Override
	public boolean coincideBusqueda(String busqueda) {
		busqueda = busqueda.trim().toLowerCase();
		if (busqueda.isEmpty()) {
			return false;
		}
		if (getCodigo().toLowerCase().startsWith(busqueda)
				|| getDescripcion().toLowerCase().startsWith(busqueda)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		return this.getCodigo() + " - " + getDescripcion();
	}

}