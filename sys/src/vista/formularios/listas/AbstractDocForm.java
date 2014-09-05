package vista.formularios.listas;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import vista.barras.PanelBarraDocumento;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import org.jdesktop.swingx.JXDatePicker;

public abstract class AbstractDocForm extends JInternalFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected JTextField txtNumero;
	private PanelBarraDocumento barra;
	protected static final String EDICION = "EDICION";
	protected static final String VISTA = "VISTA";
	protected static final String NUEVO = "NUEVO";
	private String estado;	
	protected JTextField txtNumero_2;
	protected JTextField txtTipoCambio;
	protected JTextField txtTcMoneda;
	protected JLabel lblTcMoneda;
	protected JLabel lblTipoCambio;
	protected JXDatePicker datePicker;
	protected JTextField txtSerie;
	public AbstractDocForm(String titulo) {
		setTitle(titulo);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setVisible(true);
		setResizable(true);
		barra = new PanelBarraDocumento();
		barra.setMinimumSize(new Dimension(411, 30));
		barra.setPreferredSize(new Dimension(411, 30));
		barra.setBounds(0, 0, 598, 42);
		barra.setFormMaestro(this);
		FlowLayout flowLayout = (FlowLayout) barra.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		getContentPane().add(barra, BorderLayout.NORTH);
		
		JPanel pnlPrincipal = new JPanel();
		pnlPrincipal.setBounds(0, 44, 598, 33);
		pnlPrincipal.setPreferredSize(new Dimension(10, 50));
		getContentPane().add(pnlPrincipal);
		pnlPrincipal.setLayout(null);
		
		JLabel lblNumero = new JLabel("Correlativo");
		lblNumero.setBounds(10, 11, 59, 14);
		pnlPrincipal.add(lblNumero);
		
		txtNumero_2 = new JTextField();
		txtNumero_2.setEnabled(false);
		txtNumero_2.setBounds(122, 8, 71, 20);
		pnlPrincipal.add(txtNumero_2);
		txtNumero_2.setColumns(10);
		
		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setBounds(203, 11, 29, 14);
		pnlPrincipal.add(lblFecha);
		
		datePicker = new JXDatePicker();
		datePicker.setBounds(242, 8, 91, 23);
		datePicker.getEditor().setLocation(0, 8);
		pnlPrincipal.add(datePicker);
		
		txtTipoCambio = new JTextField();
		txtTipoCambio.setBounds(431, 8, 43, 20);
		txtTipoCambio.setColumns(10);
		pnlPrincipal.add(txtTipoCambio);
		
		lblTipoCambio = new JLabel("Tipo de Cambio");
		lblTipoCambio.setBounds(343, 11, 78, 14);
		pnlPrincipal.add(lblTipoCambio);
		
		lblTcMoneda = new JLabel("T.C. Moneda");
		lblTcMoneda.setBounds(484, 11, 62, 14);
		pnlPrincipal.add(lblTcMoneda);
		
		txtTcMoneda = new JTextField();
		txtTcMoneda.setBounds(558, 8, 43, 20);
		txtTcMoneda.setColumns(10);
		pnlPrincipal.add(txtTcMoneda);
		
		txtSerie = new JTextField();
		txtSerie.setColumns(10);
		txtSerie.setBounds(74, 8, 50, 20);
		pnlPrincipal.add(txtSerie);
		setBounds(100, 100, 627, 325);
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
	public PanelBarraDocumento getBarra() {
		return barra;
	}
	public void setBarra(PanelBarraDocumento barra) {
		this.barra = barra;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
}
