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

import vista.contenedores.CntGrupoCentralizacion;
import vista.contenedores.CntMoneda;

public abstract class AbstractDocForm extends JInternalFrame{
	private static final long serialVersionUID = 1L;
	protected JTextField txtNumero;
	private PanelBarraDocumento barra;
	protected JPanel pnlPrincipal;
	protected static final String EDICION = "EDICION";
	protected static final String VISTA = "VISTA";
	protected static final String NUEVO = "NUEVO";
	private String estado;	
	protected JTextField txtNumero_2;
	protected JTextField txtTipoCambio;
	protected JTextField txtTcMoneda;
	protected JLabel lblTcMoneda;
	protected JLabel lblTipoCambio;
	protected JXDatePicker txtFecha;
	protected JTextField txtSerie;
	protected CntMoneda cntMoneda;	
	protected CntGrupoCentralizacion cntGrupoCentralizacion;
	public AbstractDocForm(String titulo) {
		setTitle(titulo);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setVisible(true);
		setResizable(true);
		int AnchoCabecera = 850;
		barra = new PanelBarraDocumento();
		barra.setMinimumSize(new Dimension(AnchoCabecera, 30));
		barra.setPreferredSize(new Dimension(AnchoCabecera, 30));
		barra.setBounds(0, 0, AnchoCabecera, 42);
		barra.setFormMaestro(this);
		FlowLayout flowLayout = (FlowLayout) barra.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		getContentPane().add(barra, BorderLayout.NORTH);
		
		pnlPrincipal = new JPanel();
		pnlPrincipal.setMinimumSize(new Dimension(AnchoCabecera, 500));
		pnlPrincipal.setPreferredSize(new Dimension(AnchoCabecera, 500));
		pnlPrincipal.setBounds(0, 40, AnchoCabecera, 70);
		//pnlPrincipal.setLayout(getLayout());
		getContentPane().add(pnlPrincipal);
		JLabel lblNumero = new JLabel("Correlativo");
		lblNumero.setBounds(10, 39, 53, 14);
		
		txtNumero_2 = new JTextField();
		txtNumero_2.setBounds(116, 36, 80, 20);
		txtNumero_2.setEnabled(false);
		txtNumero_2.setColumns(10);
		
		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setBounds(206, 39, 53, 14);
		
		txtFecha = new JXDatePicker();
		txtFecha.setBounds(245, 34, 101, 22);
		txtFecha.getEditor().setLocation(0, 8);
		
		txtTipoCambio = new JTextField();
		txtTipoCambio.setBounds(786, 34, 44, 20);
		txtTipoCambio.setMinimumSize(new Dimension(30, 20));
		txtTipoCambio.setPreferredSize(new Dimension(30, 20));
		txtTipoCambio.setColumns(10);
		
		lblTipoCambio = new JLabel("Tipo de Cambio");
		lblTipoCambio.setBounds(699, 39, 73, 14);
		
		lblTcMoneda = new JLabel("T.C. Moneda");
		lblTcMoneda.setBounds(564, 39, 62, 14);
		
		txtTcMoneda = new JTextField();
		txtTcMoneda.setBounds(636, 34, 53, 20);
		txtTcMoneda.setColumns(10);
		
		txtSerie = new JTextField();
		txtSerie.setBounds(73, 36, 44, 20);
		txtSerie.setColumns(10);
		
		JLabel lblMoneda = new JLabel("Moneda");
		lblMoneda.setBounds(353, 39, 53, 14);
		pnlPrincipal.setLayout(null);
		pnlPrincipal.add(lblNumero);
		pnlPrincipal.add(txtNumero_2);
		pnlPrincipal.add(lblFecha);
		pnlPrincipal.add(txtFecha);
		pnlPrincipal.add(txtTipoCambio);
		pnlPrincipal.add(lblTipoCambio);
		pnlPrincipal.add(lblTcMoneda);
		pnlPrincipal.add(txtTcMoneda);
		pnlPrincipal.add(txtSerie);
		pnlPrincipal.add(lblMoneda);
		cntMoneda = new CntMoneda();
		cntMoneda.txtDescripcion.setSize(120, 20);
		cntMoneda.txtCodigo.setLocation(0, 0);
		cntMoneda.txtDescripcion.setLocation(29, 0);
		cntMoneda.setBounds(400, 33, 149, 20);
		pnlPrincipal.add(cntMoneda);
		
		JLabel lblGrupoDeCentralizacion = new JLabel("Operacion");
		lblGrupoDeCentralizacion.setBounds(10, 14, 53, 14);
		pnlPrincipal.add(lblGrupoDeCentralizacion);
		
		cntGrupoCentralizacion = new CntGrupoCentralizacion();
		cntGrupoCentralizacion.txtDescripcion.setBounds(46, 0, 219, 20);
		cntGrupoCentralizacion.btnBuscar.setLocation(275, 4);
		cntGrupoCentralizacion.setBounds(73, 8, 291, 20);
		pnlPrincipal.add(cntGrupoCentralizacion);
		setBounds(100, 100, 856, 465);		
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
