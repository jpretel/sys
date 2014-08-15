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

public class FrmUsuario extends AbstractMaestro {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Usuario usuario = new Usuario();
	private UsuarioDAO usuarioDAO = new UsuarioDAO();

	private List<Usuario> usuarios = new ArrayList<Usuario>();

	private JTextField txtidUsuario = new JTextField(8);
	private JTextField txtNombres = new JTextField(15);
	private JPasswordField txtClave = new JPasswordField(15);

	JLabel lblId = new JLabel("Cod. Usuario");
	JLabel lblUsuario = new JLabel("Clave");
	JLabel lblCLave = new JLabel("Nombres");

	private JTable tblLista = new JTable(new MaestroTableModel());
	JScrollPane scrollPane = new JScrollPane();

	private JTextField txtidgrupo;

	public FrmUsuario() {
		super("Usuarios");
		getBarra().setBounds(0, 0, 0, 0);
		pnlContenido.setLayout(null);
		scrollPane.setBounds(10, 11, 274, 262);

		scrollPane.setViewportView(tblLista);
		tblLista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		pnlContenido.add(scrollPane);
		lblUsuario.setBounds(294, 43, 27, 14);
		pnlContenido.add(lblUsuario);
		lblId.setBounds(294, 12, 62, 14);
		pnlContenido.add(lblId);
		lblCLave.setBounds(294, 76, 42, 14);
		pnlContenido.add(lblCLave);
		txtidUsuario.setBounds(364, 11, 108, 22);
		pnlContenido.add(txtidUsuario);
		txtNombres.setBounds(346, 72, 175, 22);
		pnlContenido.add(txtNombres);
		txtClave.setBounds(331, 39, 95, 22);
		pnlContenido.add(txtClave);

		JLabel lblGrupoUsuario = new JLabel("Grupo Usuario");
		lblGrupoUsuario.setBounds(294, 117, 80, 14);
		pnlContenido.add(lblGrupoUsuario);

		txtidgrupo = new JTextField(8);
		txtidgrupo.setBounds(384, 113, 108, 22);
		pnlContenido.add(txtidgrupo);
		/*pnlContenido.setFocusTraversalPolicy(new FocusTraversalOnArray(
				new Component[] { scrollPane, tblLista, lblId, txtidUsuario,
						lblUsuario, txtClave, lblCLave, txtNombres,
						lblGrupoUsuario, txtidgrupo }));*/

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
			txtClave.setText(Encryption.pss_decrypt(getusuario().getClave()));
			txtidgrupo.setText(getusuario().getGrupoUsuario()
					.getIdgrupoUsuario());
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
		txtidgrupo.setEditable(true);
		tblLista.setEnabled(false);
	}

	@Override
	public void vista_noedicion() {
		txtidUsuario.setEditable(false);
		txtNombres.setEditable(false);
		txtClave.setEditable(false);
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
		getusuario().setClave(
				Encryption.pss_encrypt(new String(txtClave.getPassword())));
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
