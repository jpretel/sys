package vista.formularios;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import vista.controles.DSGTableModel;
import vista.utilitarios.MaestroTableModel;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JButton;

import dao.SysGrupoDAO;
import dao.SysOpcionDAO;
import entity.SysGrupo;
import entity.SysGrupoPK;
import entity.SysOpcion;
import entity.SysOpcionPK;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmSysGrupo extends AbstractMaestro {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTable tblLista;

	private JTable tblOpciones;
	private JTextField txtCodigo;
	private JTextField txtDescripcion;

	private SysGrupo sysGrupo;

	private List<SysGrupo> sysGrupos;
	private List<SysOpcion> sysOpciones;

	private SysGrupoDAO sysGrupoDAO = new SysGrupoDAO();

	private SysOpcionDAO sysOpcionDAO = new SysOpcionDAO();

	private JButton btnILinea;
	private JButton btnBLinea;
	private JTextField txtidmodulo;
	private JLabel lblCdModulo;
	private JTextField txtidtitulo;
	private JLabel lblCdigo_1;

	public FrmSysGrupo() {
		super("Sucursal / Almacen");

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 199, 331);

		tblLista = new JTable(new MaestroTableModel());
		scrollPane.setViewportView(tblLista);
		tblLista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JScrollPane scrollPaneNum = new JScrollPane();
		scrollPaneNum.setBounds(219, 123, 314, 219);

		tblOpciones = new JTable(new DSGTableModel(new String[] { "Código",
				"Descripcion", "Prioridad", "Imagen", "Opción" }) {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean evaluaEdicion(int row, int column) {
				return getEditar();
			}
		});
		scrollPaneNum.setViewportView(tblOpciones);
		tblOpciones.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JLabel lblCdigo = new JLabel("C\u00F3d Grupo");
		lblCdigo.setBounds(219, 64, 66, 14);

		JLabel lblDescripcin = new JLabel("Descripci\u00F3n");
		lblDescripcin.setBounds(219, 95, 66, 14);

		txtCodigo = new JTextField();
		txtCodigo.setBounds(298, 68, 67, 20);
		txtCodigo.setColumns(10);

		txtDescripcion = new JTextField();
		txtDescripcion.setBounds(298, 92, 126, 20);
		txtDescripcion.setColumns(10);

		btnILinea = new JButton("I Linea");
		btnILinea.setBounds(375, 15, 65, 20);
		btnILinea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				final Object fila[] = { "", "", "", "", "" };
				getDetalleTM().addRow(fila);
			}
		});

		btnBLinea = new JButton("B Linea");
		btnBLinea.setBounds(446, 15, 83, 20);
		btnBLinea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int ind = tblOpciones.getSelectedRow();
				if (ind >= 0)
					getDetalleTM().removeRow(ind);
			}
		});
		pnlContenido.setLayout(null);
		pnlContenido.add(scrollPane);
		pnlContenido.add(lblDescripcin);
		pnlContenido.add(lblCdigo);
		pnlContenido.add(txtDescripcion);
		pnlContenido.add(txtCodigo);
		pnlContenido.add(btnILinea);
		pnlContenido.add(btnBLinea);
		pnlContenido.add(scrollPaneNum);
		
		txtidmodulo = new JTextField();
		txtidmodulo.setColumns(10);
		txtidmodulo.setBounds(298, 9, 67, 20);
		pnlContenido.add(txtidmodulo);
		
		lblCdModulo = new JLabel("C\u00F3d. Modulo");
		lblCdModulo.setBounds(219, 11, 66, 14);
		pnlContenido.add(lblCdModulo);
		
		txtidtitulo = new JTextField();
		txtidtitulo.setColumns(10);
		txtidtitulo.setBounds(298, 40, 67, 20);
		pnlContenido.add(txtidtitulo);
		
		lblCdigo_1 = new JLabel("C\u00F3d Titulo");
		lblCdigo_1.setBounds(219, 42, 66, 14);
		pnlContenido.add(lblCdigo_1);
		tblLista.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
					@Override
					public void valueChanged(ListSelectionEvent e) {
						int selectedRow = tblLista.getSelectedRow();
						if (selectedRow >= 0)
							setSysGrupo(getSysGrupos().get(selectedRow));
						else
							setSysGrupo(null);
						llenar_datos();
					}
				});
		iniciar();
	}

	@Override
	public void nuevo() {
		setSysGrupo(new SysGrupo());
		getSysGrupo().setId(new SysGrupoPK());
	}

	@Override
	public void editar() {
		super.editar();
	}

	@Override
	public void anular() {

	}

	@Override
	public void grabar() {
		getSysGrupoDAO().crear_editar(getSysGrupo());
		getSysOpcionDAO().borrarPorGrupo(getSysGrupo());
		for (SysOpcion obj : getSysOpciones()) {
			getSysOpcionDAO().create(obj);
		}
	}

	@Override
	public void llenarDesdeVista() {
		String idmodulo, idtitulo, idgrupo;
		idmodulo = txtidmodulo.getText().trim();
		idtitulo = txtidtitulo.getText().trim();
		idgrupo = this.txtCodigo.getText().trim();
		SysGrupoPK id = new SysGrupoPK();
		id.setIdmodulo(idmodulo);
		id.setIdtitulo(idtitulo);
		id.setIdgrupo(idgrupo);
		
		getSysGrupo().setId(id);
		getSysGrupo().setDescripcion(this.txtDescripcion.getText().trim());

		setSysOpciones(new ArrayList<SysOpcion>());

		for (int i = 0; i < getDetalleTM().getRowCount(); i++) {
			SysOpcionPK ido = new SysOpcionPK();
			SysOpcion obj = new SysOpcion();

			ido.setIdmodulo(idmodulo);
			ido.setIdtitulo(idtitulo);
			ido.setIdgrupo(idgrupo);
			ido.setIdopcion(getDetalleTM().getValueAt(i, 0).toString());

			obj.setId(ido);
			obj.setDescripcion(getDetalleTM().getValueAt(i, 1).toString());
			System.out.println(getDetalleTM().getValueAt(i, 2));
			obj.setPrioridad(Integer.parseInt(getDetalleTM().getValueAt(i, 2).toString()));
			obj.setImagen(getDetalleTM().getValueAt(i, 3).toString());
			obj.setOpcion(getDetalleTM().getValueAt(i, 4).toString());
			getSysOpciones().add(obj);
		}

	}

	@Override
	public void llenar_datos() {
		getDetalleTM().limpiar();
		setSysOpciones(new ArrayList<SysOpcion>());
		if (getSysGrupo() != null) {
			txtidmodulo.setText(getSysGrupo().getId().getIdmodulo());
			txtidtitulo.setText(getSysGrupo().getId().getIdtitulo());
			txtCodigo.setText(getSysGrupo().getId().getIdgrupo());
			txtDescripcion.setText(getSysGrupo().getDescripcion());
			setSysOpciones(getSysOpcionDAO().getPorGrupo(getSysGrupo()));

			for (SysOpcion obj : getSysOpciones()) {
				getDetalleTM().addRow(
						new Object[] { obj.getId().getIdopcion(),
								obj.getDescripcion(), obj.getPrioridad(), obj.getImagen(), obj.getOpcion() });
			}
		} else {
			txtCodigo.setText("");
			txtDescripcion.setText("");
			txtidtitulo.setText("");
			txtidmodulo.setText("");
		}
	}

	@Override
	public boolean isValidaVista() {
		if (this.txtCodigo.getText().trim().isEmpty())
			return false;
		if (this.txtDescripcion.getText().trim().isEmpty())
			return false;
		return true;
	}

	@Override
	public void llenar_lista() {
		tblLista.setFillsViewportHeight(true);

		MaestroTableModel model = (MaestroTableModel) tblLista.getModel();
		model.limpiar();
		for (SysGrupo obj : getSysGrupos()) {
			model.addRow(new Object[] {
					obj.getId().getIdmodulo() + " " + obj.getId().getIdtitulo()
							+ " " + obj.getId().getIdgrupo(),
					obj.getDescripcion() });
		}
		if (getSysGrupos().size() > 0) {
			setSysGrupo(getSysGrupos().get(0));
			tblLista.setRowSelectionInterval(0, 0);
		}
	}

	@Override
	public void llenar_tablas() {
		setSysGrupos(getSysGrupoDAO().findAll());
	}

	@Override
	public void vista_edicion() {
		if (getEstado().equals(NUEVO))
			txtCodigo.setEditable(true);
		else
			txtCodigo.setEditable(false);
		txtDescripcion.setEditable(true);
		getDetalleTM().setEditar(true);
		btnILinea.setEnabled(true);
		btnBLinea.setEnabled(true);

	}

	@Override
	public void vista_noedicion() {
		txtCodigo.setEditable(false);
		txtDescripcion.setEditable(false);
		getDetalleTM().setEditar(false);
		btnILinea.setEnabled(false);
		btnBLinea.setEnabled(false);
	}

	@Override
	public void eliminar() {
		if (getSysGrupo() != null) {
			// getAlmacenDAO().borrarPorSucursal(getSucursal());
			getSysGrupoDAO().remove(getSysGrupo());
		}

	}

	private DSGTableModel getDetalleTM() {
		return (DSGTableModel) tblOpciones.getModel();
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public void actualiza_objeto(Object entidad) {
		// TODO Auto-generated method stub

	}

	public SysGrupo getSysGrupo() {
		return sysGrupo;
	}

	public void setSysGrupo(SysGrupo sysGrupo) {
		this.sysGrupo = sysGrupo;
	}

	public List<SysGrupo> getSysGrupos() {
		return sysGrupos;
	}

	public void setSysGrupos(List<SysGrupo> sysGrupos) {
		this.sysGrupos = sysGrupos;
	}

	public List<SysOpcion> getSysOpciones() {
		return sysOpciones;
	}

	public void setSysOpciones(List<SysOpcion> sysOpciones) {
		this.sysOpciones = sysOpciones;
	}

	public SysGrupoDAO getSysGrupoDAO() {
		return sysGrupoDAO;
	}

	public void setSysGrupoDAO(SysGrupoDAO sysGrupoDAO) {
		this.sysGrupoDAO = sysGrupoDAO;
	}

	public SysOpcionDAO getSysOpcionDAO() {
		return sysOpcionDAO;
	}

	public void setSysOpcionDAO(SysOpcionDAO sysOpcionDAO) {
		this.sysOpcionDAO = sysOpcionDAO;
	}
}