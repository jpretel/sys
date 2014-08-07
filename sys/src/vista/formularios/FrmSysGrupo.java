package vista.formularios;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import vista.contenedores.ctnModulo;
import vista.contenedores.ctnTitulo;
import vista.controles.DSGTableModel;
import vista.utilitarios.MaestroTableModel;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JButton;

import dao.SysGrupoDAO;
import dao.SysModuloDAO;
import dao.SysOpcionDAO;
import dao.SysTituloDAO;
import entity.SysGrupo;
import entity.SysGrupoPK;
import entity.SysModulo;
import entity.SysOpcion;
import entity.SysOpcionPK;
import entity.SysTitulo;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class FrmSysGrupo extends AbstractMaestro {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	ctnModulo ctnmodulo;
	ctnTitulo ctntitulo;	
	
	private JTable tblLista;

	private JTable tblOpciones;
	private JTextField txtCodigo;
	private JTextField txtDescripcion;

	private SysGrupo sysGrupo;
	private SysModulo sysModulo;
	private SysModuloDAO sysModuloDAO = new SysModuloDAO();
	private SysTituloDAO sysTituloDAO = new SysTituloDAO();
	
	private List<SysGrupo> sysGrupos;
	private List<SysOpcion> sysOpciones;

	private SysGrupoDAO sysGrupoDAO = new SysGrupoDAO();

	private SysOpcionDAO sysOpcionDAO = new SysOpcionDAO();

	private JButton btnILinea;
	private JButton btnBLinea;
	private JLabel lblCdModulo;
	private JLabel lblCdigo_1;

	public FrmSysGrupo() {
		super("Gestión de Opciones");

		JScrollPane scrollPane = new JScrollPane();

		tblLista = new JTable(new MaestroTableModel());
		scrollPane.setViewportView(tblLista);
		tblLista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JScrollPane scrollPaneNum = new JScrollPane();

		tblOpciones = new JTable(new DSGTableModel(new String[] { "Código",
				"Descripción", "Prioridad", "Imagen", "Opción" }) {

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

		JLabel lblDescripcin = new JLabel("Descripci\u00F3n");

		txtCodigo = new JTextField();
		txtCodigo.setColumns(10);

		txtDescripcion = new JTextField();
		txtDescripcion.setColumns(10);

		btnILinea = new JButton("I Linea");
		btnILinea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				final Object fila[] = { "", "", "", "", "" };
				getDetalleTM().addRow(fila);
			}
		});

		btnBLinea = new JButton("B Linea");
		btnBLinea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int ind = tblOpciones.getSelectedRow();
				if (ind >= 0)
					getDetalleTM().removeRow(ind);
			}
		});
		
		ctnmodulo = new ctnModulo();
		//txtidmodulo.setColumns(10);
		
		lblCdModulo = new JLabel("C\u00F3d. Modulo");
		
		ctntitulo = new ctnTitulo();
		//txtidtitulo.setColumns(10);
		
		lblCdigo_1 = new JLabel("C\u00F3d Titulo");
		GroupLayout groupLayout = new GroupLayout(pnlContenido);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPaneNum, GroupLayout.PREFERRED_SIZE, 314, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblCdModulo, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
									.addGap(13)
									.addComponent(ctnmodulo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblCdigo_1, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
									.addGap(13)
									.addComponent(ctntitulo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblDescripcin, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
									.addGap(13)
									.addComponent(txtDescripcion, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblCdigo, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
									.addGap(13)
									.addComponent(txtCodigo, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(btnILinea, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnBLinea, GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE))))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(11)
									.addComponent(lblCdModulo))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(9)
									.addComponent(ctnmodulo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(8)
									.addComponent(lblCdigo_1))
								.addGroup(groupLayout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(ctntitulo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblCdigo)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(4)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(txtCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(btnILinea, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))))
							.addGap(4)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(3)
									.addComponent(lblDescripcin))
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
									.addComponent(txtDescripcion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(btnBLinea, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)))
							.addGap(11)
							.addComponent(scrollPaneNum, GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)))
					.addContainerGap())
		);
		pnlContenido.setLayout(groupLayout);
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
		
		this.ctntitulo.txtCodigo.setText(null);
		this.ctntitulo.txtDescripcion.setText(null);
		this.ctnmodulo.txtCodigo.setText(null);
		this.ctnmodulo.txtDescripcion.setText(null);
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
		idmodulo = ctnmodulo.getSeleccionado().getIdmodulo();
		idtitulo = ctntitulo.getSeleccionado().getId().getIdtitulo();
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
			
			for(SysModulo sm : sysModuloDAO.findAll()){
				if(sm.getIdmodulo().equals(getSysGrupo().getId().getIdmodulo())){
					ctnmodulo.setSeleccionado(sm);
				}
			}			
			for(SysTitulo st : sysTituloDAO.findAll()){
				if(st.getId().getIdtitulo().equals(getSysGrupo().getId().getIdtitulo())){
					ctntitulo.setSeleccionado(st);
				}
			}			
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
			this.ctntitulo.txtCodigo.setText(null);
			this.ctntitulo.txtDescripcion.setText(null);
			this.ctnmodulo.txtCodigo.setText(null);
			this.ctnmodulo.txtDescripcion.setText(null);
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
			txtCodigo.setEditable(true);
		txtDescripcion.setEditable(true);
		getDetalleTM().setEditar(true);
		btnILinea.setEnabled(true);
		btnBLinea.setEnabled(true);
		this.ctntitulo.txtCodigo.setEditable(true);
		this.ctntitulo.txtDescripcion.setEditable(true);
		this.ctnmodulo.txtCodigo.setEditable(true);
		this.ctnmodulo.txtDescripcion.setEditable(true);

	}

	@Override
	public void vista_noedicion() {
		txtCodigo.setEditable(false);
		txtDescripcion.setEditable(false);
		getDetalleTM().setEditar(false);
		btnILinea.setEnabled(false);
		btnBLinea.setEnabled(false);
		this.ctntitulo.txtCodigo.setEditable(false);
		this.ctntitulo.txtDescripcion.setEditable(false);
		this.ctnmodulo.txtCodigo.setEditable(false);
		this.ctnmodulo.txtDescripcion.setEditable(false);
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

	public SysModulo getSysModulo() {
		return sysModulo;
	}

	public void setSysModulo(SysModulo sysModulo) {
		this.sysModulo = sysModulo;
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