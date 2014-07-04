package vista.formularios;

import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import dao.UsuarioDAO;
import entity.Usuario;
import vista.barras.BarraMaestro;
import vista.utilitarios.MaestroTableModel;
import core.security.Encryption;

public class FrmUsuario extends AbstractMaestro{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	DefaultTableModel dtm= new DefaultTableModel();
	
	//private Encryption encryption = new Encryption();
	private Usuario usuario = new Usuario();
	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	
	private List<Usuario> usuarios = new ArrayList<Usuario>();
	
	private JTextField txtId = new JTextField(8);
	private JTextField txtUsuario = new JTextField(15);
	private JPasswordField txtClave = new JPasswordField(15);
	JLabel lblId = new JLabel("ID:");
	JLabel lblUsuario = new JLabel("Usuario:");
	JLabel lblCLave =  new JLabel("Clave:");
	
	private JTable tblLista = new JTable(dtm);
	JScrollPane scrollPane = new JScrollPane();
	
	public GroupLayout groupLayout = new GroupLayout(getContentPane());
	
	public FrmUsuario(BarraMaestro barra){
		super("..::Usuarios::..", barra);
		
		scrollPane.setViewportView(tblLista);
		tblLista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		getContentPane().setLayout(groupLayout);
		groupLayout.setAutoCreateGaps(true);
		groupLayout.setAutoCreateContainerGaps(true);
		
		groupLayout.setHorizontalGroup(groupLayout.createSequentialGroup()				
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)				
				.addGroup(groupLayout.createParallelGroup()						
						.addComponent(lblUsuario)
						.addComponent(lblId)
						.addComponent(lblCLave)
				)						
				.addGroup(groupLayout.createParallelGroup()	
						.addComponent(txtId)
						.addComponent(txtUsuario)
						.addComponent(txtClave)
				)			
		);		
		groupLayout.setVerticalGroup(groupLayout.createSequentialGroup()
				.addGroup(groupLayout.createParallelGroup()
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup()
										.addComponent(lblId)
										.addComponent(txtId, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
								)
								.addGroup(groupLayout.createParallelGroup()
										.addComponent(lblUsuario)
										.addComponent(txtUsuario, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
								)
								.addGroup(groupLayout.createParallelGroup()
										.addComponent(txtClave, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)								
										.addComponent(lblCLave)
								)								
						)						
				)						
		);
		
		tblLista.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
					@Override
					public void valueChanged(ListSelectionEvent e) {
						int selectedRow = tblLista.getSelectedRow();
						if (selectedRow >= 0)
							setusuario(getusuarios().get(selectedRow));
						else
							setusuario(null);
						llenar_datos();
					}
				});
		iniciar();
	}

	
	@Override
	public void nuevo() {
		setusuario(new Usuario());
		super.nuevo();
	}

	@Override
	public void anular() {
		vista_noedicion();
	}

	@SuppressWarnings("deprecation")
	@Override
	public void grabar() {	
		
		String usu, clave;
		usu = Encryption.encrypt(txtUsuario.getText());
		clave = Encryption.encrypt(txtClave.getText());
		
		getusuario().setId(Integer.parseInt(txtId.getText()));
		getusuario().setUsuario(txtUsuario.getText());
		getusuario().setClave(txtClave.getText());
		getusuario().setEncUsu(usu);
		getusuario().setEncClave(clave);;
		
		getusuarioDAO().crear_editar(getusuario());
		super.grabar();
	}

	@Override
	public void eliminar() {
		setEstado(VISTA);
		vista_noedicion();
	}

	public Usuario getusuario() {
		return usuario;
	}

	public void setusuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public UsuarioDAO getusuarioDAO() {
		return usuarioDAO;
	}

	public void setusuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}
		
	@Override
	public void llenar_datos() {
		if (getusuario() != null) {
			txtId.setText("" + getusuario().getId());
			txtUsuario.setText(getusuario().getUsuario());
			txtClave.setText(getusuario().getClave());
		} else {
			txtId.setText("");
			txtUsuario.setText("");
			txtClave.setText("");
		}
	}

	@Override
	public void llenar_lista() {
		tblLista.setFillsViewportHeight(true);
		dtm.addColumn("ID");
		dtm.addColumn("Usuario");
		dtm.addColumn("Contraseña");
		//MaestroTableModel model = (MaestroTableModel) tblLista.getModel();
		while (dtm.getRowCount() != 0) {
			dtm.removeRow(0);
		}
		for (Usuario usuario : getusuarios()) {
			dtm.addRow(new Object[] { usuario.getId(), usuario.getUsuario() , usuario.getClave()});
		}
		if (getusuarios().size() > 0) {
			setusuario(getusuarios().get(0));
			tblLista.setRowSelectionInterval(0, 0);
		}
	}

	
	@Override
	public void llenar_tablas() {
		setusuarios(getusuarioDAO().findAll());
	}

	public List<Usuario> getusuarios() {
		return usuarios;
	}

	public void setusuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	@Override
	public void vista_edicion() {
		if (getEstado().equals(NUEVO))
			txtId.setEditable(true);
			txtUsuario.setEditable(true);
			txtClave.setEditable(true);
		tblLista.setEnabled(false);
	}

	@Override
	public void vista_noedicion() {
		txtId.setEditable(false);
		txtUsuario.setEditable(false);
		txtClave.setEditable(false);
		tblLista.setEnabled(true);
	}

	@Override
	protected void actualizaBarra() {
		getBarra().setVisible(isSelected());
		if (isSelected()) {
			getBarra().setFormMaestro(this);
		}
		if (getEstado().equals(VISTA)) {
			getBarra().enVista();
		} else {
			getBarra().enEdicion();
		}
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actualiza_objeto(Object entidad) {
		// TODO Auto-generated method stub
	}

}
