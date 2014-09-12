package vista.formularios;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import vista.contenedores.ctnModulo;
import vista.contenedores.ctnTitulo;
import vista.controles.DSGTableModel;
import vista.controles.JTextFieldLimit;
import vista.utilitarios.MaestroTableModel;
import vista.utilitarios.UtilMensajes;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

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

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

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
	
	private JComboBox<String> combo1;

	private SysGrupo sysGrupo;
	private SysGrupo sysGrupo2;
	private SysGrupoPK sysGrupoPK;
	private SysTitulo sysTitulo;
	private SysModulo sysModulo;
	private SysModuloDAO sysModuloDAO = new SysModuloDAO();
	private SysTituloDAO sysTituloDAO = new SysTituloDAO();
	
	private List<SysGrupo> sysGrupos;
	private List<SysOpcion> sysOpciones;

	private SysGrupoDAO sysGrupoDAO = new SysGrupoDAO();
	private SysOpcionDAO sysOpcionDAO = new SysOpcionDAO();
	private JLabel lblCdModulo;
	private JLabel lblCdigo_1;

	public FrmSysGrupo() {
		super("Gestión de Opciones");

		JScrollPane scrollPane = new JScrollPane();

		combo1=new JComboBox<String>();
		combo1.addItem("Grande");
        combo1.addItem("Mediano");
        combo1.addItem("Pequeño");	
        
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

			@Override
			public void addRow() {
				addRow(new Object[] { idMas(), "", "", "", });
			}
			
			
		});
		scrollPaneNum.setViewportView(tblOpciones);
		tblOpciones.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			
		DefaultCellEditor defaultCellEditor=new DefaultCellEditor(combo1);
		tblOpciones.getColumn("Prioridad").setCellEditor(defaultCellEditor);
		
		getDetalleTM().setObligatorios(0,1);
		getDetalleTM().setRepetidos(0);
		getDetalleTM().setScrollAndTable(scrollPaneNum, tblOpciones);
		
		JLabel lblCdigo = new JLabel("C\u00F3d Grupo");

		JLabel lblDescripcin = new JLabel("Descripci\u00F3n");

		txtCodigo = new JTextField();
		txtCodigo.setColumns(10);
		txtCodigo.setDocument(new JTextFieldLimit(3, true));

		txtDescripcion = new JTextField();
		txtDescripcion.setColumns(10);
		txtDescripcion.setDocument(new JTextFieldLimit(75, true));
		
		
		
		lblCdModulo = new JLabel("C\u00F3d. Modulo");
		
		ctnmodulo = new ctnModulo();
		
		ctntitulo = new ctnTitulo();
		//txtidtitulo.setColumns(10);
		ctntitulo.txtCodigo.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				ctntitulo.setData(sysTituloDAO.getPorModulo(ctnmodulo.getSeleccionado()));
			}
		});
		
		lblCdigo_1 = new JLabel("C\u00F3d Titulo");
		GroupLayout groupLayout = new GroupLayout(pnlContenido);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
					.addGap(10)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPaneNum, GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblCdigo, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
									.addGap(13)
									.addComponent(this.txtCodigo, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
									.addGap(85))
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(this.lblCdigo_1, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
											.addGap(13)
											.addComponent(this.ctntitulo, GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE))
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(this.lblCdModulo, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
											.addGap(13)
											.addComponent(this.ctnmodulo, GroupLayout.PREFERRED_SIZE, 182, Short.MAX_VALUE))
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(lblDescripcin, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
											.addGap(13)
											.addComponent(this.txtDescripcion, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)))
									.addGap(8)))
							.addGap(83)))
					.addGap(6))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(11)
									.addComponent(this.lblCdModulo))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(9)
									.addComponent(this.ctnmodulo, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)))
							.addGap(6)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(8)
									.addComponent(this.lblCdigo_1))
								.addGroup(groupLayout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(this.ctntitulo, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblCdigo)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(4)
									.addComponent(this.txtCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addGap(4)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(3)
									.addComponent(lblDescripcin))
								.addComponent(this.txtDescripcion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(11)
							.addComponent(scrollPaneNum, GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)))
					.addGap(7))
		);
		pnlContenido.setLayout(groupLayout);
		tblLista.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
					@Override
					public void valueChanged(ListSelectionEvent e) {
						int selectedRow = tblLista.getSelectedRow();
						if (selectedRow >= 0){
							setSysGrupo(getSysGrupos().get(selectedRow));
							setSysGrupo2(getSysGrupos().get(selectedRow));
							setSysTitulo(getSysGrupos().get(selectedRow).getSysTitulo());
						}							
						else
							setSysGrupo(null);
						llenar_datos();
					}
				});
		iniciar();
	}
	
	public String idMas(){
		int tot;
		
		tot = tblOpciones.getRowCount() + 1;
		
		if(tot<10){
			return "00"+tot;
		}else if(tot < 100){
			return "0"+tot;
		}else{
			return ""+tot;
		}
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
		//iniciar();
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
			//System.out.println(getDetalleTM().getValueAt(i, 2));
			if(getDetalleTM().getValueAt(i, 2).equals("Grande")){
				obj.setPrioridad(1);
			}else if(getDetalleTM().getValueAt(i, 2).equals("Mediano")){
				obj.setPrioridad(2);
			}else if(getDetalleTM().getValueAt(i, 2).equals("Pequeño")){
				obj.setPrioridad(3);
			}
			
			obj.setImagen(getDetalleTM().getValueAt(i, 3).toString());
			obj.setOpcion(getDetalleTM().getValueAt(i, 4).toString());
			getSysOpciones().add(obj);
		}

	}

	@Override
	public void llenar_datos() {
		
		getDetalleTM().limpiar();
		//setSysOpciones(new ArrayList<SysOpcion>());
		
		//if (!getEstado().equals(NUEVO) && sysGrupoDAO.findAll().size()>0) {
		if (getSysGrupo() != null) {
			ctnmodulo.txtCodigo.setText(getSysGrupo().getId().getIdmodulo());// .setSeleccionado(getSysTitulo().getSysModulo());
			ctnmodulo.llenar();
			ctntitulo.setData(sysTituloDAO.getPorModulo(sysModuloDAO.find(getSysGrupo().getId().getIdmodulo())));
			ctntitulo.txtCodigo.setText(getSysGrupo().getId().getIdtitulo());
			ctntitulo.llenar();
			System.out.println("Mostrar: "+getSysGrupo2().getId().getIdgrupo());
			txtCodigo.setText(getSysGrupo2().getId().getIdgrupo().toString());
			txtDescripcion.setText(getSysGrupo2().getDescripcion());
			setSysOpciones(getSysOpcionDAO().getPorGrupo(getSysGrupo2()));

			for (SysOpcion obj : getSysOpciones()) {
				
				String op;
				if(obj.getPrioridad() == 1){
					op = "Grande";
				}else if(obj.getPrioridad() == 2){
					op = "Mediano";
				}else{
					op = "Pequeño";
				}
				
				getDetalleTM().addRow(
						new Object[] { obj.getId().getIdopcion(),
								obj.getDescripcion(), op, obj.getImagen(), obj.getOpcion() });
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
	public void actualiza_tablas() {
		if (ctnmodulo != null)
			ctnmodulo.setData(sysModuloDAO.findAll());
		if (ctntitulo != null)
			ctntitulo.setData(null);
	};
	
	@Override
	public void vista_edicion() {
		if (getEstado().equals(NUEVO))
			txtCodigo.setEditable(true);
		else
			txtCodigo.setEditable(true);
		txtDescripcion.setEditable(true);
		getDetalleTM().setEditar(true);
		this.ctntitulo.txtCodigo.setEditable(true);
		this.ctnmodulo.txtCodigo.setEditable(true);

	}

	@Override
	public void vista_noedicion() {
		txtCodigo.setEditable(false);
		txtDescripcion.setEditable(false);
		getDetalleTM().setEditar(false);
		this.ctntitulo.txtCodigo.setEditable(false);
		this.ctnmodulo.txtCodigo.setEditable(false);
	}

	@Override
	public void eliminar() {
		int seleccion = UtilMensajes.msj_error("ELIMINAR_REG");
		if (seleccion == 0){	
			if (getSysGrupo() != null) {
				getSysOpcionDAO().borrarPorGrupo(getSysGrupo());
				getSysGrupoDAO().remove(getSysGrupo());
				
				iniciar();
			}
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

	public SysGrupoPK getSysGrupoPK() {
		return sysGrupoPK;
	}

	public void setSysGrupoPK(SysGrupoPK sysGrupoPK) {
		this.sysGrupoPK = sysGrupoPK;
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

	public SysGrupo getSysGrupo2() {
		return sysGrupo2;
	}

	public void setSysGrupo2(SysGrupo sysGrupo2) {
		this.sysGrupo2 = sysGrupo2;
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

	public SysTitulo getSysTitulo() {
		return sysTitulo;
	}

	public void setSysTitulo(SysTitulo sysTitulo) {
		this.sysTitulo = sysTitulo;
	}
}