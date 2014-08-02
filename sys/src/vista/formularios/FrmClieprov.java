package vista.formularios;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import dao.ClieprovDAO;
import entity.Clieprov;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import vista.combobox.ComboBox;
import vista.utilitarios.ObjetoWeb;

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
	private ComboBox cboTipo;
	private List<String[]> optionList = new ArrayList<String[]>();
	
	public List<Clieprov> getClieprovL() {
		return clieprovL;
	}

	public void setClieprovL(List<Clieprov> clieprovL) {
		this.clieprovL = clieprovL;
	}
	
	public FrmClieprov() {
		super("Edicion de Clientes y Proveedores");		
		JLabel lblCodigo = new JLabel("idclieprov");
		lblCodigo.setBounds(20, 10, 51, 14);
		pnlContenido.add(lblCodigo);		
		
		txtCodigo = new JTextField();
		txtCodigo.setBounds(80, 10, 98, 20);
		txtCodigo.setColumns(10);
		pnlContenido.add(txtCodigo);
		
		JLabel lblRazon_Social = new JLabel("Razon Social");
		lblRazon_Social.setBounds(5, 35, 66, 14);
		pnlContenido.add(lblRazon_Social);		
		
		txtRazon_Social = new JTextField();
		txtRazon_Social.setBounds(80, 35, 400, 20);
		txtRazon_Social.setColumns(10);
		pnlContenido.add(txtRazon_Social);
		
		JLabel lblDireccion = new JLabel("Direccion");
		lblDireccion.setBounds(20, 58, 50, 14);
		pnlContenido.add(lblDireccion);
		
		txtDireccion = new JTextField();
		txtDireccion.setBounds(80, 58, 400, 20);
		txtDireccion.setColumns(10);
		pnlContenido.add(txtDireccion);
		
		JLabel lblRuc = new JLabel("RUC");
		lblRuc.setBounds(41, 83, 30, 14);
		pnlContenido.add(lblRuc);
		
		txtRuc = new JTextField();
		txtRuc.setBounds(80, 83, 98, 20);
		txtRuc.setColumns(10);
		pnlContenido.add(txtRuc);
		
		JButton button = new JButton("Consulta RUC");
		button.setBounds(0, 0, 0, 0);
		pnlContenido.add(button);
		pnlContenido.setLayout(null);
		pnlContenido.add(lblCodigo);
		pnlContenido.add(txtCodigo);
		pnlContenido.add(lblRazon_Social);
		pnlContenido.add(txtRazon_Social);
		pnlContenido.add(lblDireccion);
		pnlContenido.add(txtDireccion);
		pnlContenido.add(lblRuc);
		pnlContenido.add(txtRuc);
		pnlContenido.add(button);
		
		JButton btnConsultaRuc = new JButton("Consultar RUC");
		btnConsultaRuc.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				consultar_ruc();			
			}	
		});
		btnConsultaRuc.setBounds(192, 79, 108, 23);
		pnlContenido.add(btnConsultaRuc);			
		
		optionList.add(new String[]{"C","Cliente"});
		optionList.add(new String[]{"P","Proveedor"});
		
		cboTipo = new vista.combobox.ComboBox(optionList,1);
		cboTipo.setBounds(211, 7, 92, 20);
		pnlContenido.add(cboTipo);
		
		JLabel lblTipo = new JLabel("tipo");
		lblTipo.setBounds(183, 10, 18, 14);
		pnlContenido.add(lblTipo);
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
		setEstado(VISTA);
		vista_noedicion();
	}
	
	@Override
	public void llenar_datos() {
		if (getClieprov() != null) {
			txtCodigo.setText(getClieprov().getIdclieprov());
			txtRazon_Social.setText(getClieprov().getRazonSocial());
			txtDireccion.setText(getClieprov().getDireccion());
			txtRuc.setText(getClieprov().getRuc());
		} else {
			txtCodigo.setText("");
			txtRazon_Social.setText("");
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
		cboTipo.setEditable(true);
	}

	@Override
	public void vista_noedicion() {
		txtCodigo.setEditable(false);
		txtRazon_Social.setEditable(false);
		txtDireccion.setEditable(false);
		txtRuc.setEditable(false);
		cboTipo.setEditable(false);
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actualiza_objeto(Object entidad) {

	}

	@Override
	public void llenarDesdeVista() {
		getClieprov().setIdclieprov(txtCodigo.getText());
		getClieprov().setRazonSocial(txtRazon_Social.getText());
		getClieprov().setDireccion(txtDireccion.getText());
		getClieprov().setRuc(txtRuc.getText());
		getClieprov().setTipo(((String[])cboTipo.getSelectedItem())[0]);		
	}

	@Override
	public boolean isValidaVista() {
		return true;
	}
	
	private void consultar_ruc() {
		String[] listaRUC;
		List<String> consulta = new ArrayList<String>();	
		int cont = 0;	
		consulta = ObjetoWeb.ConsultaRUC(txtRuc.getText().toString());		
		
		Iterator<String> nombreIterator = consulta.iterator();
		listaRUC = new String[consulta.size()];
		
		while(nombreIterator.hasNext()){					
			String elemento = nombreIterator.next();					
			listaRUC[cont] = elemento.toString();	
		}		
	}
}
