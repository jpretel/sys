package vista.formularios.listas;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import vista.barras.PanelBarraDocumento;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.JComboBox;
import org.jdesktop.swingx.JXDatePicker;

public abstract class AbstractDocForm extends JInternalFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected JTextField txtPuntoEmision;
	protected JTextField txtAnio;
	protected JTextField txtMes;
	protected JTextField txtEstado;
	protected JTextField txtNumero;
	private PanelBarraDocumento barra;
	protected static final String EDICION = "EDICION";
	protected static final String VISTA = "VISTA";
	protected static final String NUEVO = "NUEVO";
	private String estado;	
	private JTextField textField;

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
		
		JLabel lblPeriodo = new JLabel("Periodo");
		lblPeriodo.setBounds(159, 8, 36, 14);
		pnlPrincipal.add(lblPeriodo);
		
		txtAnio = new JTextField();
		txtAnio.setEditable(false);
		txtAnio.setOpaque(false);
		txtAnio.setBounds(204, 5, 46, 20);
		pnlPrincipal.add(txtAnio);
		txtAnio.setColumns(5);
		
		txtMes = new JTextField();
		txtMes.setEditable(false);
		txtMes.setOpaque(false);
		txtMes.setBounds(249, 5, 62, 20);
		pnlPrincipal.add(txtMes);
		txtMes.setColumns(7);
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setBounds(439, 8, 33, 14);
		pnlPrincipal.add(lblEstado);
		
		txtEstado = new JTextField();
		txtEstado.setEditable(false);
		txtEstado.setOpaque(false);
		txtEstado.setBounds(486, 5, 102, 20);
		pnlPrincipal.add(txtEstado);
		txtEstado.setColumns(10);
		
		JLabel lblDocumento = new JLabel("Documento");
		lblDocumento.setBounds(5, 33, 66, 14);
		pnlPrincipal.add(lblDocumento);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(84, 30, 53, 20);
		pnlPrincipal.add(comboBox);
		
		JLabel lblSerie = new JLabel("Serie");
		lblSerie.setBounds(159, 33, 36, 14);
		pnlPrincipal.add(lblSerie);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(204, 30, 53, 20);
		pnlPrincipal.add(comboBox_1);
		
		JLabel lblNumero = new JLabel("Numero");
		lblNumero.setBounds(275, 33, 46, 14);
		pnlPrincipal.add(lblNumero);
		
		textField = new JTextField();
		textField.setBounds(331, 30, 86, 20);
		pnlPrincipal.add(textField);
		textField.setColumns(10);
		
		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setBounds(439, 33, 46, 14);
		pnlPrincipal.add(lblFecha);
		
		JXDatePicker datePicker = new JXDatePicker();
		datePicker.getEditor().setLocation(0, 27);
		datePicker.setBounds(486, 29, 91, 23);
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
