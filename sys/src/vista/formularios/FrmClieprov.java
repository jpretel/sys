package vista.formularios;

import java.util.ArrayList;
import java.util.List;

import dao.ClieprovDAO;
import entity.Clieprov;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import vista.combobox.ComboBox;
import vista.controles.JTextFieldLimit;
import vista.utilitarios.ObjetoWeb;
import vista.utilitarios.UtilMensajes;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JCheckBox;

public class FrmClieprov extends AbstractMaestro {

	private static final long serialVersionUID = 1L;
	
	private ClieprovDAO cdao= new ClieprovDAO();
	public ClieprovDAO getCdao() {
		return cdao;
	}

	public void setCdao(ClieprovDAO cdao) {
		this.cdao = cdao;
	}

	private Clieprov clieprov; //= new Clieprov();	
	public Clieprov getClieprov() {
		return clieprov;
	}

	public void setClieprov(Clieprov clieprov) {
		this.clieprov = clieprov;
	}
	private List<Clieprov> clieprovL = new ArrayList<Clieprov>();
	private JTextField txtRazon_Social;
	private JTextField txtCodigo;
	private JTextField txtDireccion;
	private JTextField txtRuc;
	private List<String[]> optionList = new ArrayList<String[]>();
	
	private JCheckBox chkCliente;		
	private JCheckBox chkProveedor;
	
	public List<Clieprov> getClieprovL() {
		return clieprovL;
	}

	public void setClieprovL(List<Clieprov> clieprovL) {
		this.clieprovL = clieprovL;
	}
	
	public FrmClieprov() {
		super("Edición de Clientes y Proveedores");		
		JLabel lblCodigo = new JLabel("idclieprov");
		
		txtCodigo = new JTextField();
		txtCodigo.setColumns(10);
		txtCodigo.setDocument(new JTextFieldLimit(11));
		
		JLabel lblRazon_Social = new JLabel("Razón Social");
		
		txtRazon_Social = new JTextField();
		txtRazon_Social.setColumns(10);
		
		JLabel lblDireccion = new JLabel("Dirección");
		
		txtDireccion = new JTextField();
		txtDireccion.setColumns(10);
		
		JLabel lblRuc = new JLabel("RUC");
		
		txtRuc = new JTextField();
		txtRuc.setColumns(10);
		txtRuc.setDocument(new JTextFieldLimit(11));

		
		JButton button = new JButton("Consulta RUC");
		
		JButton btnConsultaRuc = new JButton("Consultar RUC");
		btnConsultaRuc.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				consultar_ruc();			
			}	
		});
		
				
		JLabel lblTipo = new JLabel("tipo:");
		
		chkCliente = new JCheckBox("Cliente");			
		chkProveedor = new JCheckBox("Proveedor");
		
		GroupLayout groupLayout = new GroupLayout(pnlContenido);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(button, GroupLayout.PREFERRED_SIZE, 0, GroupLayout.PREFERRED_SIZE)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(20)
					.addComponent(lblCodigo, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
					.addGap(9)
					.addComponent(txtCodigo, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
					.addGap(5)
					.addComponent(lblTipo)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(chkCliente)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(chkProveedor)
					.addGap(198))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(5)
					.addComponent(lblRazon_Social, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
					.addGap(9)
					.addComponent(txtRazon_Social, GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE)
					.addGap(10))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(20)
					.addComponent(lblDireccion, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(txtDireccion, GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE)
					.addGap(10))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(45)
					.addComponent(lblRuc, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addGap(5)
					.addComponent(txtRuc, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnConsultaRuc, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(button, GroupLayout.PREFERRED_SIZE, 0, GroupLayout.PREFERRED_SIZE)
					.addGap(7)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(3)
							.addComponent(lblCodigo))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(3)
							.addComponent(txtCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(3)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblTipo)
								.addComponent(chkCliente)
								.addComponent(chkProveedor))))
					.addGap(5)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblRazon_Social)
						.addComponent(txtRazon_Social, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(6)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblDireccion)
						.addComponent(txtDireccion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(3)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblRuc)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(4)
							.addComponent(txtRuc, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(3)
							.addComponent(btnConsultaRuc))))
		);
		pnlContenido.setLayout(groupLayout);
		iniciar();
	}
	

	@Override
	public void nuevo() {
		clieprov = new Clieprov();
	}

	@Override
	public void anular() {
		vista_noedicion();
	}

	@Override
	public void grabar() {	
		try{
			if(getClieprov() instanceof Clieprov){
				getCdao().crear_editar(getClieprov());	
			}			
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
			
	}

	@Override
	public void eliminar() {
		if (getClieprov()!= null) {
			int seleccion = UtilMensajes.msj_error("ELIMINAR_REG");
			
			if (seleccion == 0){
				getCdao().remove(getClieprov());
				//iniciar();
			}			
		}
		setEstado(VISTA);
		vista_noedicion();
	}
	
	@Override
	public void llenar_datos() {
		int index = 0;
		if (getClieprov() != null && !getEstado().equals(NUEVO)) {
			txtCodigo.setText(getClieprov().getIdclieprov());
			txtRazon_Social.setText(getClieprov().getRazonSocial());
			txtDireccion.setText(getClieprov().getDireccion());
			txtRuc.setText(getClieprov().getRuc());
			//index = (getClieprov().getTipo().trim().equals("C"))?0:1;
			if(getClieprov().getTipo().trim().equals("1")){
				chkCliente.setSelected(true);
			}else if(getClieprov().getTipo().trim().equals("2")){
				chkProveedor.setSelected(true);
			}else{
				chkCliente.setSelected(true);
				chkProveedor.setSelected(true);
			}
			//cboTipo.setSelectedIndex(index);
		} else {
			txtCodigo.setText("");
			txtRazon_Social.setText("");
			txtDireccion.setText("");
			txtRuc.setText("");
		}
	}

	@Override
	public void llenar_lista() {
	}

	@Override
	public void llenar_tablas() {
		//setClieprovL(getCdao().findAll());
	}

	@Override
	public void vista_edicion() {
		if (getEstado().equals(NUEVO))
			txtCodigo.setEditable(true);
		txtRazon_Social.setEditable(true);
		txtDireccion.setEditable(true);
		txtRuc.setEditable(true);
		chkCliente.setEnabled(true);
		chkProveedor.setEnabled(true);
	}

	@Override
	public void vista_noedicion() {
		txtCodigo.setEditable(false);
		txtRazon_Social.setEditable(false);
		txtDireccion.setEditable(false);
		txtRuc.setEditable(false);
		chkCliente.setEnabled(false);
		chkProveedor.setEnabled(false);
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub		
	}

	@Override
	public void actualiza_objeto(Object entidad) {
		setClieprov((Clieprov) entidad);
		llenar_datos();
		vista_noedicion();
	}

	@Override
	public void llenarDesdeVista() {
		int a=0,b=0;
		getClieprov().setIdclieprov(txtCodigo.getText());
		getClieprov().setRazonSocial(txtRazon_Social.getText());
		getClieprov().setDireccion(txtDireccion.getText());
		getClieprov().setRuc(txtRuc.getText());
		//getClieprov().setTipo(((String[])cboTipo.getSelectedItem())[0]);
		if(chkCliente.isSelected()){
			a = 1;
		}
		if(chkProveedor.isSelected()){
			b = 2;
		}
		getClieprov().setTipo(""+(a+b));
	}

	@Override
	public boolean isValidaVista() {
		if (this.txtCodigo.getText().trim().isEmpty()) {
			UtilMensajes.mensaje_alterta("DATO_REQUERIDO", "Código");
			this.txtCodigo.requestFocus();
			return false;
		}
		
		if (getEstado().equals(NUEVO)) {
			if (getCdao().find(this.txtCodigo.getText().trim()) != null) {
				UtilMensajes.mensaje_alterta("CODIGO_EXISTE");
				this.txtCodigo.requestFocus();
				return false;
			}
		}
		
		if (this.txtDireccion.getText().trim().isEmpty()) {
			UtilMensajes.mensaje_alterta("DATO_REQUERIDO", "Dirección");
			this.txtDireccion.requestFocus();
			return false;
		}
		
		if (this.txtRazon_Social.getText().trim().isEmpty()) {
			UtilMensajes.mensaje_alterta("DATO_REQUERIDO", "Razón Social");
			this.txtRazon_Social.requestFocus();
			return false;
		}
		
		if (!chkCliente.isSelected() && !chkProveedor.isSelected()) {
			UtilMensajes.mensaje_alterta("DATO_REQUERIDO", "Tipo");
			this.chkCliente.requestFocus();
			return false;
		}
		return true;
	}
	
	private void consultar_ruc() {		
		clieprov = ObjetoWeb.ConsultaRUC(txtRuc.getText().toString());		
		setClieprov(clieprov);
		txtCodigo.setText(getClieprov().getIdclieprov());
		txtRazon_Social.setText(getClieprov().getRazonSocial());
		txtDireccion.setText(getClieprov().getDireccion());
		txtRuc.setText(getClieprov().getRuc());
	}
}
