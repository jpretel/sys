package vista.formularios;

import java.util.ArrayList;
import java.util.List;

import dao.ClieprovDAO;
import entity.Clieprov;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;

public class FrmClieprov extends AbstractMaestro {

	private static final long serialVersionUID = 1L;
	
	private ClieprovDAO cdao;
	public ClieprovDAO getCdao() {
		return cdao;
	}

	public void setCdao(ClieprovDAO cdao) {
		this.cdao = cdao;
	}

	private Clieprov clieprov = new Clieprov();	
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

	public List<Clieprov> getClieprovL() {
		return clieprovL;
	}

	public void setClieprovL(List<Clieprov> clieprovL) {
		this.clieprovL = clieprovL;
	}
	
	public FrmClieprov(String titulo) {
		super(titulo);
		
		GroupLayout groupLayout = new GroupLayout(pnlContenido);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGap(0, 403, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGap(0, 116, Short.MAX_VALUE)
		);
		int SpacioIzqLabel = 20;
		int SpacioIzqTexto = 80;
		int SpacioArribaTexto = 80;
		pnlContenido.setLayout(groupLayout);
		
		JLabel lblCodigo = new JLabel("idclieprov");
		lblCodigo.setBounds(SpacioIzqLabel, 10, 98, 14);
		pnlContenido.add(lblCodigo);		
		
		txtCodigo = new JTextField();
		txtCodigo.setBounds(SpacioIzqTexto, 10, 98, 14);
		txtCodigo.setColumns(10);
		pnlContenido.add(txtCodigo);
		
		JLabel lblRazon_Social = new JLabel("Razon Social");
		lblRazon_Social.setBounds(SpacioIzqLabel, 20, 98, 14);
		pnlContenido.add(lblRazon_Social);		
		
		txtRazon_Social = new JTextField();
		txtRazon_Social.setBounds(SpacioIzqTexto, 20, 98, 14);
		txtRazon_Social.setColumns(10);
		pnlContenido.add(txtRazon_Social);
		
		JLabel lblDireccion = new JLabel("Direccion");
		lblDireccion.setBounds(SpacioIzqLabel, 30, 98, 14);
		pnlContenido.add(lblDireccion);
		
		txtDireccion = new JTextField();
		txtDireccion.setBounds(SpacioIzqTexto, 30, 98, 14);
		txtDireccion.setColumns(10);
		pnlContenido.add(txtDireccion);
		
		JLabel lblRuc = new JLabel("RUC");
		lblRuc.setBounds(SpacioIzqLabel, 40, 98, 14);
		pnlContenido.add(lblRuc);
		
		txtRuc = new JTextField();
		txtRuc.setBounds(SpacioIzqTexto, 40, 98, 14);
		txtRuc.setColumns(10);
		pnlContenido.add(txtRuc);
		
		JButton button = new JButton("Consulta RUC");
		pnlContenido.add(button);
	
		iniciar();
	}
	

	@Override
	public void nuevo() {
		setEstado(NUEVO);
	}

	@Override
	public void anular() {
		vista_noedicion();
	}

	@Override
	public void grabar() {		
		getCdao().crear_editar(getClieprov());
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
	}

	@Override
	public void vista_noedicion() {
		txtCodigo.setEditable(false);
		txtRazon_Social.setEditable(false);
		txtDireccion.setEditable(true);
		txtRuc.setEditable(true);
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actualiza_objeto(Object entidad) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void llenarDesdeVista() {
		getClieprov().setIdclieprov(txtCodigo.getText());
		getClieprov().setRazonSocial(txtRazon_Social.getText());
		
	}

	@Override
	public boolean isValidaVista() {
		// TODO Auto-generated method stub
		return false;
	}
}
