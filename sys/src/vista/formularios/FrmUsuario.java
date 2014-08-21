package vista.formularios;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import dao.UsuarioDAO;
import entity.GrupoUsuario;
import entity.Usuario;
import vista.utilitarios.MaestroTableModel;
import core.security.Encryption;
//import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class FrmUsuario extends AbstractMaestro {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Usuario usuario = new Usuario();
	private UsuarioDAO usuarioDAO = new UsuarioDAO();

	private List<Usuario> usuarios = new ArrayList<Usuario>();

	private JTextField txtidUsuario = new JTextField();
	private JTextField txtNombres = new JTextField();
	private JPasswordField txtClave = new JPasswordField();

	JLabel lblId = new JLabel("Cod. Usuario:");
	JLabel lblUsuario = new JLabel("Nombre:");
	JLabel lblCLave = new JLabel("Clave:");

	private JTable tblLista = new JTable(new MaestroTableModel());
	JScrollPane scrollPane = new JScrollPane();

	private JTextField txtidgrupo;
	private JPasswordField txtClaveR;

	public FrmUsuario() {
		super("Usuarios");
		getBarra().setBounds(0, 0, 0, 0);

		scrollPane.setViewportView(tblLista);
		tblLista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JLabel lblGrupoUsuario = new JLabel("Grupo Usuario");

		txtidgrupo = new JTextField();
		
		txtClaveR = new JPasswordField();
		
		JLabel lblRepitaLaClave = new JLabel("Repita Clave:");
		GroupLayout groupLayout = new GroupLayout(pnlContenido);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
					.addGap(10)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblId, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblUsuario, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCLave, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblRepitaLaClave, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblGrupoUsuario, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
					.addGap(3)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(txtNombres, GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
						.addComponent(txtClave, GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
						.addComponent(txtClaveR, GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
						.addComponent(txtidgrupo, GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
						.addComponent(txtidUsuario, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(11)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
					.addGap(11))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(15)
					.addComponent(lblId)
					.addGap(19)
					.addComponent(lblUsuario)
					.addGap(19)
					.addComponent(lblCLave)
					.addGap(19)
					.addComponent(lblRepitaLaClave)
					.addGap(19)
					.addComponent(lblGrupoUsuario))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(txtidUsuario, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addComponent(txtNombres, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addComponent(txtClave, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addComponent(txtClaveR, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addComponent(txtidgrupo, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
		);
		pnlContenido.setLayout(groupLayout);
		//pnlContenido.setFocusTraversalPolicy(new FocusTraversalOnArray(	
		//		new Component[] { scrollPane, tblLista, lblId, txtidUsuario, lblUsuario, txtClave, lblCLave, txtNombres,	lblGrupoUsuario, txtidgrupo }));

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
	}

	@Override
	public void anular() {

	}

	public void grabar() {
		getusuarioDAO().crear_editar(getusuario());
	}

	@Override
	public void llenar_datos() {
		if (getusuario() != null) {			
			txtidUsuario.setText(getusuario().getIdusuario());
			txtNombres.setText(getusuario().getNombres());
			txtidgrupo.setText(getusuario().getGrupoUsuario().getDescripcion());
			txtClave.setText(Encryption.decrypt(getusuario().getClave()));
			txtClaveR.setText(Encryption.decrypt(getusuario().getClave()));
			
		} else {
			txtidUsuario.setText("");
			txtNombres.setText("");
			txtClave.setText("");
			txtidgrupo.setText("");
		}
	}

	@Override
	public void llenar_lista() {

		tblLista.setFillsViewportHeight(true);

		MaestroTableModel model = (MaestroTableModel) tblLista.getModel();
		model.limpiar();
		for (Usuario obj : getusuarios()) {
			model.addRow(new Object[] { obj.getIdusuario(), obj.getNombres() });
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
			txtidUsuario.setEditable(true);
		txtNombres.setEditable(true);
		txtClave.setEditable(true);
		txtClaveR.setEditable(true);
		txtidgrupo.setEditable(true);
		tblLista.setEnabled(false);
	}

	@Override
	public void vista_noedicion() {
		txtidUsuario.setEditable(false);
		txtNombres.setEditable(false);
		txtClave.setEditable(false);
		txtClaveR.setEditable(false);
		txtidgrupo.setEditable(false);
		tblLista.setEnabled(true);
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
		GrupoUsuario u = new GrupoUsuario();
		u.setIdgrupoUsuario(txtidgrupo.getText());

		getusuario().setIdusuario(txtidUsuario.getText());
		getusuario().setClave(Encryption.encrypt(txtClave.getText()));
		getusuario().setNombres(txtNombres.getText());
		getusuario().setGrupoUsuario(u);
	}

	@Override
	public boolean isValidaVista() {
		if (txtidUsuario.getText().trim().isEmpty()) {
			return false;
		}

		if (new String(txtClave.getPassword()).isEmpty()) {
			return false;
		}
		return true;
	}

	@Override
	public void eliminar() {
		if (getusuario() != null) {
			getusuarioDAO().remove(getusuario());
		}
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
}
