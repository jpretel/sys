package vista.formularios.listas;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import dao.TCambioDAO;
import entity.TCambio;
import vista.barras.IFormDocumento;
import vista.barras.PanelBarraDocumento;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Date;

import vista.contenedores.CntMoneda;
import vista.controles.DSGDatePicker;
import vista.controles.DSGInternalFrame;
import vista.controles.DSGTextFieldCorrelativo;
import vista.controles.DSGTextFieldNumber;

public abstract class AbstractDocForm extends DSGInternalFrame implements
		IFormDocumento {
	private static final long serialVersionUID = 1L;
	protected JTextField txtNumero;
	protected PanelBarraDocumento barra;
	protected JPanel pnlPrincipal;
	private String estado;
	protected DSGTextFieldCorrelativo txtNumero_2;
	protected DSGTextFieldNumber txtTipoCambio;
	protected DSGTextFieldNumber txtTcMoneda;
	protected JLabel lblTcMoneda;
	protected JLabel lblTipoCambio;
	protected DSGDatePicker txtFecha;
	protected DSGTextFieldCorrelativo txtSerie;
	protected CntMoneda cntMoneda;
	private TCambioDAO tcambioDAO = null;
	
	public AbstractDocForm(String titulo) {
		setTitle(titulo);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setVisible(true);
		setResizable(true);
		initBarra();
		int AnchoCabecera = 850;
		
		pnlPrincipal = new JPanel();
		pnlPrincipal.setMinimumSize(new Dimension(AnchoCabecera, 500));
		pnlPrincipal.setPreferredSize(new Dimension(AnchoCabecera, 500));
		pnlPrincipal.setBounds(0, 40, AnchoCabecera, 70);

		getContentPane().add(pnlPrincipal);
		JLabel lblNumero = new JLabel("Correlativo");
		lblNumero.setBounds(10, 15, 53, 14);

		txtNumero_2 = new DSGTextFieldCorrelativo(8);
		this.txtNumero_2.setBounds(116, 12, 80, 20);
		txtNumero_2.setColumns(10);

		txtFecha = new DSGDatePicker();
		this.txtFecha.setBounds(245, 11, 101, 22);
		txtFecha.getEditor().setLocation(0, 8);
		txtFecha.setDate(new Date());
		cntMoneda = new CntMoneda();
		this.cntMoneda.setBounds(400, 12, 181, 20);
		
		setEstado(VISTA);
		setBounds(100, 100, 854, 465);

		this.pnlPrincipal.setLayout(null);
		this.pnlPrincipal.add(lblNumero);
								
										JLabel lblFecha = new JLabel("Fecha");
										lblFecha.setBounds(206, 15, 53, 14);
										this.pnlPrincipal.add(lblFecha);
								JLabel lblMoneda = new JLabel("Moneda");
								lblMoneda.setBounds(353, 15, 53, 14);
								this.pnlPrincipal.add(lblMoneda);
						
								lblTipoCambio = new JLabel("T. Cambio");
								this.lblTipoCambio.setBounds(591, 15, 53, 14);
								this.pnlPrincipal.add(this.lblTipoCambio);
				
						lblTcMoneda = new JLabel("T.C. Moneda");
						this.lblTcMoneda.setBounds(704, 15, 62, 14);
						this.pnlPrincipal.add(this.lblTcMoneda);
		
				txtSerie = new DSGTextFieldCorrelativo(4);
				this.txtSerie.setBounds(72, 12, 44, 20);
				txtSerie.setColumns(10);
				this.pnlPrincipal.add(this.txtSerie);
		this.pnlPrincipal.add(this.txtNumero_2);
		this.pnlPrincipal.add(this.txtFecha);
		this.pnlPrincipal.add(this.cntMoneda);
		
				txtTipoCambio = new DSGTextFieldNumber(4);
				this.txtTipoCambio.setBounds(641, 12, 55, 20);
				txtTipoCambio.setMinimumSize(new Dimension(30, 20));
				txtTipoCambio.setPreferredSize(new Dimension(30, 20));
				txtTipoCambio.setColumns(10);
				this.pnlPrincipal.add(this.txtTipoCambio);
		
				txtTcMoneda = new DSGTextFieldNumber(4);
				this.txtTcMoneda.setBounds(776, 12, 55, 20);
				txtTcMoneda.setColumns(10);
				txtTcMoneda.setText(String.valueOf(1.000));
				this.pnlPrincipal.add(this.txtTcMoneda);
				
				cntMoneda.txtCodigo.addFocusListener(new FocusAdapter() {
					@Override
					public void focusLost(FocusEvent arg0){
						actualizaTCambio();
					}
				});
				
				txtFecha.addFocusListener(new FocusAdapter() {
					@Override
					public void focusLost(FocusEvent arg0){
						actualizaTCambio();
					}
				});		
	}
	
	public void actualizaTCambio(){
		final int dia = this.txtFecha.getDate().getDate();
		final int mes = this.txtFecha.getDate().getMonth() + 1;
		final int anio = this.txtFecha.getDate().getYear() + 1900;
		if(cntMoneda.txtCodigo.getText().trim().length() > 0 && dia > 0 ){
			/*TCambio tcambio = tcambioDAO.getFechaMoneda(cntMoneda.getSeleccionado(), anio, mes, dia);
			if(tcambio != null)
				this.txtTipoCambio.setText(String.valueOf(tcambio.getCompra()));*/		
		}
	}
	
	public void initBarra() {
		int AnchoCabecera = 850;
		barra = new PanelBarraDocumento();
		barra.setMinimumSize(new Dimension(AnchoCabecera, 40));
		barra.setPreferredSize(new Dimension(AnchoCabecera, 40));
		barra.setBounds(0, 0, AnchoCabecera, 42);
		barra.setFormMaestro(this);
		FlowLayout flowLayout = (FlowLayout) barra.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		getContentPane().add(barra, BorderLayout.NORTH);
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

	public abstract void actualiza_objeto(Object id);

	public void cancelar() {
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
	
	public void doVerAsiento(){
		
	}

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
