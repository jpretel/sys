package vista.formularios;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import vista.controles.JTextFieldLimit;
import vista.utilitarios.MaestroTableModel;
import vista.utilitarios.UtilMensajes;

import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JCheckBox;

import dao.GrupoCentralizacionDAO;
import dao.SubdiarioDAO;
import entity.GrupoCentralizacion;
import vista.contenedores.CntSubdiario;

public class FrmGrupoCentralizacion extends AbstractMaestro {

	private static final long serialVersionUID = 1L;

	private GrupoCentralizacion grupo;

	private GrupoCentralizacionDAO grupoDAO = new GrupoCentralizacionDAO();

	private List<GrupoCentralizacion> grupos = new ArrayList<GrupoCentralizacion>();
	private JTable tblLista;
	private JTextField txtCodigo;
	private JTextField txtDescripcion;
	CntSubdiario cntSubdiario;

	public FrmGrupoCentralizacion() {
		super("Cuentas Contables");
		initGUI();
	}

	private void initGUI() {

		JLabel lblCdigo = new JLabel("C\u00F3digo");
		lblCdigo.setBounds(287, 26, 39, 16);

		txtCodigo = new JTextField();
		txtCodigo.setBounds(360, 26, 129, 20);
		txtCodigo.setColumns(10);
		txtCodigo.setDocument(new JTextFieldLimit(10, true));

		JLabel lblDescripcin = new JLabel("Descripci\u00F3n");
		lblDescripcin.setBounds(287, 55, 68, 16);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 12, 257, 228);

		tblLista = new JTable(new MaestroTableModel());
		scrollPane.setViewportView(tblLista);
		tblLista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		txtDescripcion = new JTextField();
		txtDescripcion.setColumns(10);
		txtDescripcion.setBounds(360, 52, 173, 22);
		txtDescripcion.setDocument(new JTextFieldLimit(75, true));

		pnlContenido.setLayout(null);
		pnlContenido.add(scrollPane);
		pnlContenido.add(lblCdigo);
		pnlContenido.add(lblDescripcin);
		pnlContenido.add(this.txtDescripcion);
		pnlContenido.add(this.txtCodigo);

		JLabel lblTipoDeAnlisis = new JLabel("Sub Diario");
		lblTipoDeAnlisis.setBounds(287, 95, 99, 16);
		pnlContenido.add(lblTipoDeAnlisis);

		cntSubdiario = new CntSubdiario();
		cntSubdiario.setBounds(327, 118, 202, 20);
		pnlContenido.add(cntSubdiario);

		tblLista.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
					@Override
					public void valueChanged(ListSelectionEvent e) {
						int selectedRow = tblLista.getSelectedRow();
						if (selectedRow >= 0)
							setGrupo(getGrupos().get(selectedRow));
						else
							setGrupo(null);
						llenar_datos();
					}
				});
		iniciar();
	}

	@Override
	public void nuevo() {
		setGrupo(new GrupoCentralizacion());
		txtCodigo.requestFocus();
	}

	@Override
	public void anular() {
		vista_noedicion();
	}

	@Override
	public void grabar() {
		getGrupoDAO().crear_editar(getGrupo());
	}

	@Override
	public void llenarDesdeVista() {
		getGrupo().setIdgcentralizacion(txtCodigo.getText());
		getGrupo().setDescripcion(txtDescripcion.getText());

	};

	@Override
	public void eliminar() {
		if (getGrupo() != null) {
			int seleccion = UtilMensajes.msj_error("ELIMINAR_REG");

			if (seleccion == 0) {
				getGrupoDAO().remove(getGrupo());
				iniciar();
			}
		}
		setEstado(VISTA);
		vista_noedicion();
	}

	@Override
	public void llenar_datos() {
		if (getGrupo() != null) {
			txtCodigo.setText(getGrupo().getIdgcentralizacion());
			txtDescripcion.setText(getGrupo().getDescripcion());
		} else {
			txtCodigo.setText("");
			txtDescripcion.setText("");
		}
	}

	@Override
	public void llenar_lista() {
		tblLista.setFillsViewportHeight(true);

		MaestroTableModel model = (MaestroTableModel) tblLista.getModel();
		model.limpiar();
		for (GrupoCentralizacion obj : getGrupos()) {
			model.addRow(new Object[] { obj.getIdgcentralizacion(),
					obj.getDescripcion() });
		}
		if (getGrupos().size() > 0) {
			setGrupo(getGrupos().get(0));
			tblLista.setRowSelectionInterval(0, 0);
		}
	}

	@Override
	public void llenar_tablas() {
		setGrupos(getGrupoDAO().findAll());
		actualiza_tablas();
	}

	@Override
	public void vista_edicion() {
		if (getEstado().equals(NUEVO))
			txtCodigo.setEditable(true);

		tblLista.setEnabled(false);
		TextFieldsEdicion(true, txtDescripcion);
	}

	@Override
	public void vista_noedicion() {
		TextFieldsEdicion(false, txtCodigo, txtDescripcion);
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
	public boolean isValidaVista() {

		if (!TextFieldObligatorios(txtCodigo, txtDescripcion))
			return false;

		if (getEstado().equals(NUEVO)) {
			if (getGrupoDAO().find(this.txtCodigo.getText().trim()) != null) {
				UtilMensajes.mensaje_alterta("CODIGO_EXISTE");
				this.txtCodigo.requestFocus();
				return false;
			}
		}

		return true;
	}

	@Override
	public void actualiza_tablas() {
		if (cntSubdiario != null)
			cntSubdiario.setData(new SubdiarioDAO().findAll());
	}

	public GrupoCentralizacion getGrupo() {
		return grupo;
	}

	public void setGrupo(GrupoCentralizacion grupo) {
		this.grupo = grupo;
	}

	public GrupoCentralizacionDAO getGrupoDAO() {
		return grupoDAO;
	}

	public void setGrupoDAO(GrupoCentralizacionDAO grupoDAO) {
		this.grupoDAO = grupoDAO;
	}

	public List<GrupoCentralizacion> getGrupos() {
		return grupos;
	}

	public void setGrupos(List<GrupoCentralizacion> grupos) {
		this.grupos = grupos;
	}
}
