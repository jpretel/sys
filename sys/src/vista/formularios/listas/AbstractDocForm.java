package vista.formularios.listas;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import vista.barras.PanelBarraDocumento;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.border.MatteBorder;

import java.awt.Color;

import javax.swing.JComboBox;

import org.jdesktop.swingx.JXDatePicker;
import dao.PtoEmisionDAO;
import entity.PtoEmision;

public abstract class AbstractDocForm extends JInternalFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected JTextField txtPuntoEmision;
	protected JTextField txtNumero;
	private PanelBarraDocumento barra;
	protected static final String EDICION = "EDICION";
	protected static final String VISTA = "VISTA";
	protected static final String NUEVO = "NUEVO";
	private String estado;	
	private JTextField textField;
	private PtoEmisionDAO ptoemisionDAO = new PtoEmisionDAO();
	private PtoEmision ptoemision = ptoemisionDAO.find("001");

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
		pnlPrincipal.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		pnlPrincipal.setBounds(0, 44, 598, 33);
		pnlPrincipal.setPreferredSize(new Dimension(10, 50));
		getContentPane().add(pnlPrincipal);
		pnlPrincipal.setLayout(null);
		
		JLabel lblPuntoEmision = new JLabel("Punto Emision");
		lblPuntoEmision.setBounds(5, 8, 66, 14);
		pnlPrincipal.add(lblPuntoEmision);
		
		txtPuntoEmision = new JTextField();
		txtPuntoEmision.setEditable(false);
		txtPuntoEmision.setOpaque(false);
		txtPuntoEmision.setBounds(84, 5, 70, 20);
		pnlPrincipal.add(txtPuntoEmision);
		txtPuntoEmision.setColumns(8);
		txtPuntoEmision.setText(ptoemision.getDescripcion());
		
		JLabel lblDocumento = new JLabel("Documento");
		lblDocumento.setBounds(164, 8, 66, 14);
		pnlPrincipal.add(lblDocumento);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(243, 5, 53, 20);
		pnlPrincipal.add(comboBox);
		
		JLabel lblNumero = new JLabel("Correlativo");
		lblNumero.setBounds(306, 8, 59, 14);
		pnlPrincipal.add(lblNumero);
		
		textField = new JTextField();
		textField.setBounds(363, 5, 86, 20);
		pnlPrincipal.add(textField);
		textField.setColumns(10);
		
		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setBounds(455, 8, 29, 14);
		pnlPrincipal.add(lblFecha);
		
		JXDatePicker datePicker = new JXDatePicker();
		datePicker.getEditor().setLocation(0, 27);
		datePicker.setBounds(486, 5, 91, 23);
		pnlPrincipal.add(datePicker);
		setBounds(100, 100, 610, 325);
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
