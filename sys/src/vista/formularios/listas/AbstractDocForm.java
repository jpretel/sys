package vista.formularios.listas;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import vista.barras.PanelBarraDocumento;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Date;

import vista.contenedores.CntGrupoCentralizacion;
import vista.contenedores.CntMoneda;
import vista.controles.DSGDatePicker;
import vista.controles.DSGTextFieldCorrelativo;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public abstract class AbstractDocForm extends JInternalFrame{
	private static final long serialVersionUID = 1L;
	protected JTextField txtNumero;
	private PanelBarraDocumento barra;
	protected JPanel pnlPrincipal;
	protected static final String EDICION = "EDICION";
	protected static final String VISTA = "VISTA";
	protected static final String NUEVO = "NUEVO";
	private String estado;	
	protected DSGTextFieldCorrelativo txtNumero_2;
	protected JTextField txtTipoCambio;
	protected JTextField txtTcMoneda;
	protected JLabel lblTcMoneda;
	protected JLabel lblTipoCambio;
	protected DSGDatePicker txtFecha;
	protected JTextField txtSerie;
	protected CntMoneda cntMoneda;	
	protected CntGrupoCentralizacion cntGrupoCentralizacion;
	protected JLabel lblGrupoDeCentralizacion;
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
		
		getContentPane().add(pnlPrincipal);
		JLabel lblNumero = new JLabel("Correlativo");
		
		txtNumero_2 = new DSGTextFieldCorrelativo(8);
		txtNumero_2.setColumns(10);
		
		JLabel lblFecha = new JLabel("Fecha");
		
		txtFecha = new DSGDatePicker();
		txtFecha.getEditor().setLocation(0, 8);
		txtFecha.setDate(new Date());
		
		txtTipoCambio = new JTextField();
		txtTipoCambio.setMinimumSize(new Dimension(30, 20));
		txtTipoCambio.setPreferredSize(new Dimension(30, 20));
		txtTipoCambio.setColumns(10);
		
		lblTipoCambio = new JLabel("Tipo de Cambio");
		
		lblTcMoneda = new JLabel("T.C. Moneda");
		
		txtTcMoneda = new JTextField();
		txtTcMoneda.setColumns(10);
		
		txtSerie = new JTextField();
		txtSerie.setColumns(10);
		lblGrupoDeCentralizacion = new JLabel("Operacion");
		
		cntGrupoCentralizacion = new CntGrupoCentralizacion();
		JLabel lblMoneda = new JLabel("Moneda");
		cntMoneda = new CntMoneda();
		
		setBounds(100, 100, 854, 465);
		cntGrupoCentralizacion.txtCodigo.requestFocus();
		GroupLayout gl_cntGrupoCentralizacion = new GroupLayout(cntGrupoCentralizacion);
		gl_cntGrupoCentralizacion.setHorizontalGroup(
			gl_cntGrupoCentralizacion.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_cntGrupoCentralizacion.createSequentialGroup()
					.addComponent(cntGrupoCentralizacion.txtCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addComponent(cntGrupoCentralizacion.txtDescripcion, GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
					.addGap(10)
					.addComponent(cntGrupoCentralizacion.btnBuscar)
					.addGap(8))
		);
		gl_cntGrupoCentralizacion.setVerticalGroup(
			gl_cntGrupoCentralizacion.createParallelGroup(Alignment.LEADING)
				.addComponent(cntGrupoCentralizacion.txtCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addComponent(cntGrupoCentralizacion.txtDescripcion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addGroup(gl_cntGrupoCentralizacion.createSequentialGroup()
					.addGap(4)
					.addComponent(cntGrupoCentralizacion.btnBuscar))
		);
		cntGrupoCentralizacion.setLayout(gl_cntGrupoCentralizacion);
		GroupLayout gl_pnlPrincipal = new GroupLayout(pnlPrincipal);
		gl_pnlPrincipal.setHorizontalGroup(
			gl_pnlPrincipal.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlPrincipal.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_pnlPrincipal.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlPrincipal.createSequentialGroup()
							.addComponent(lblGrupoDeCentralizacion, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
							.addGap(3)
							.addComponent(cntGrupoCentralizacion, GroupLayout.PREFERRED_SIZE, 301, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pnlPrincipal.createSequentialGroup()
							.addComponent(lblNumero)
							.addGap(10)
							.addGroup(gl_pnlPrincipal.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_pnlPrincipal.createSequentialGroup()
									.addGap(43)
									.addComponent(txtNumero_2, GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE))
								.addGroup(gl_pnlPrincipal.createSequentialGroup()
									.addComponent(txtSerie, GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
									.addGap(79)))
							.addGap(10)
							.addGroup(gl_pnlPrincipal.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_pnlPrincipal.createSequentialGroup()
									.addGap(39)
									.addComponent(txtFecha, GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE))
								.addComponent(lblFecha, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE))
							.addGap(7)
							.addGroup(gl_pnlPrincipal.createParallelGroup(Alignment.LEADING)
								.addComponent(lblMoneda, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_pnlPrincipal.createSequentialGroup()
									.addGap(47)
									.addComponent(cntMoneda, GroupLayout.PREFERRED_SIZE, 181, Short.MAX_VALUE)))
							.addGap(10)
							.addComponent(lblTcMoneda)
							.addGap(10)
							.addComponent(txtTcMoneda, GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
							.addGap(10)
							.addComponent(lblTipoCambio)
							.addGap(10)
							.addComponent(txtTipoCambio, GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)))
					.addGap(8))
		);
		gl_pnlPrincipal.setVerticalGroup(
			gl_pnlPrincipal.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlPrincipal.createSequentialGroup()
					.addGap(8)
					.addGroup(gl_pnlPrincipal.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlPrincipal.createSequentialGroup()
							.addGap(6)
							.addComponent(lblGrupoDeCentralizacion))
						.addComponent(cntGrupoCentralizacion, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addGap(5)
					.addGroup(gl_pnlPrincipal.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlPrincipal.createSequentialGroup()
							.addGap(6)
							.addComponent(lblNumero))
						.addGroup(gl_pnlPrincipal.createSequentialGroup()
							.addGap(3)
							.addGroup(gl_pnlPrincipal.createParallelGroup(Alignment.LEADING)
								.addComponent(txtNumero_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtSerie, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_pnlPrincipal.createSequentialGroup()
							.addGap(1)
							.addGroup(gl_pnlPrincipal.createParallelGroup(Alignment.LEADING)
								.addComponent(txtFecha, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_pnlPrincipal.createSequentialGroup()
									.addGap(5)
									.addComponent(lblFecha))))
						.addGroup(gl_pnlPrincipal.createSequentialGroup()
							.addGap(6)
							.addComponent(lblMoneda))
						.addComponent(cntMoneda, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_pnlPrincipal.createSequentialGroup()
							.addGap(6)
							.addComponent(lblTcMoneda))
						.addGroup(gl_pnlPrincipal.createSequentialGroup()
							.addGap(3)
							.addComponent(txtTcMoneda, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pnlPrincipal.createSequentialGroup()
							.addGap(6)
							.addComponent(lblTipoCambio))
						.addGroup(gl_pnlPrincipal.createSequentialGroup()
							.addGap(1)
							.addComponent(txtTipoCambio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
		);
		pnlPrincipal.setLayout(gl_pnlPrincipal);
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
