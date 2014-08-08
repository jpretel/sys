package vista.formularios;

import static vista.utilitarios.UtilMensajes.mensaje_alterta;

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

import dao.SysModuloDAO;
import dao.SysTituloDAO;
import entity.SysModulo;
import entity.SysTitulo;
import entity.SysTituloPK;

import java.awt.SystemTray;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmSysModulo extends AbstractMaestro {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTable tblLista;
	SysTituloDAO stdao = new SysTituloDAO();
	List<SysTitulo> lista;
	
	private JTable tblTitulo;
	private JTextField txtCodigo;
	private JTextField txtDescripcion;
	
	private SysModulo sysModulo;

	private List<SysModulo> sysModulos;
	private List<SysTitulo> sysTitulos;

	private SysModuloDAO sysModuloDAO = new SysModuloDAO();

	private SysTituloDAO sysTituloDAO = new SysTituloDAO();

	private JButton btnILinea;
	private JButton btnBLinea;

	public FrmSysModulo() {
		super("Gestión de Módulos");

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 199, 273);

		tblLista = new JTable(new MaestroTableModel());
		scrollPane.setViewportView(tblLista);
		tblLista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JScrollPane scrollPaneNum = new JScrollPane();
		scrollPaneNum.setBounds(215, 65, 314, 219);

		tblTitulo = new JTable(new DSGTableModel(new String [] {"Cod. Título", "Descripción"}) {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean evaluaEdicion(int row, int column) {
				return getEditar();
			}
		});
		scrollPaneNum.setViewportView(tblTitulo);
		tblTitulo.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JLabel lblCdigo = new JLabel("Codigo");
		lblCdigo.setBounds(213, 18, 66, 14);

		JLabel lblDescripcin = new JLabel("Descripci\u00F3n");
		lblDescripcin.setBounds(213, 42, 66, 14);

		txtCodigo = new JTextField();
		txtCodigo.setBounds(298, 15, 67, 20);
		txtCodigo.setColumns(10);

		txtDescripcion = new JTextField();
		txtDescripcion.setBounds(298, 39, 126, 20);
		txtDescripcion.setColumns(10);

		btnILinea = new JButton("I Linea");
		btnILinea.setBounds(375, 15, 65, 20);
		btnILinea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				final Object fila[] = { "", "" };
				getTituloTM().addRow(fila);
			}
		});

		btnBLinea = new JButton("B Linea");
		btnBLinea.setBounds(446, 15, 83, 20);
		btnBLinea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int ind = tblTitulo.getSelectedRow();
				if (ind >= 0)
					getTituloTM().removeRow(ind);
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
		tblLista.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
					@Override
					public void valueChanged(ListSelectionEvent e) {
						int selectedRow = tblLista.getSelectedRow();
						if (selectedRow >= 0)
							setSysModulo(getSysModulos().get(selectedRow));
						else
							setSysModulo(null);
						llenar_datos();
					}
				});
		iniciar();
		cargar();
	}

	@Override
	public void nuevo() {
		setSysModulo(new SysModulo());
		
		final Object fila[] = { "001", "TABLA" };
		getTituloTM().addRow(fila);
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
		getSysModuloDAO().crear_editar(getSysModulo());
		getSysTituloDAO().borrarPorModulo(getSysModulo());
		for (SysTitulo obj : getSysTitulos()) {
			getSysTituloDAO().create(obj);
		}
			
		
		if(crear()){
			//mensaje_alterta("CLAVEUSUARIO_INC");
			crearTitulos();
		}
	}

	public void cargar(){
		lista = new ArrayList<SysTitulo>();
		
		for( SysTitulo st : stdao.findAll()){
			lista.add(st);			
		}
		
	}
	public boolean crear(){
		boolean crear = true;
		System.out.println("Probando: " + lista.size() + " Probando el IDMod: "+ txtCodigo.getText().trim());
		 
		int con=1;
		for( SysTitulo st : lista){
			System.out.println(con + " "+st.getSysModulo().getDescripcion());
			con++;
		}
		
		for( SysTitulo st :lista){
			//System.out.println("Modulo: "+ st.getSysModulo().getDescripcion());
			if(st.getSysModulo().getIdmodulo().equals(txtCodigo.getText().trim())){
				
				crear = false;
				break;
			}
		}
		
		return crear;
	}
 	public void crearTitulos(){
		SysTituloPK id = new SysTituloPK();
		SysTitulo st = new SysTitulo();
		//SysTituloDAO stdao = new SysTituloDAO();
		
		id.setIdmodulo(txtCodigo.getText().trim());
		id.setIdtitulo("001");		
		st.setId(id);
		st.setDescripcion("TABLAS");
		stdao.crear_editar(st);
		lista.add(st);	
		
		id = new SysTituloPK();
		st = new SysTitulo();
		id.setIdmodulo(txtCodigo.getText().trim());
		id.setIdtitulo("002");		
		st.setId(id);
		st.setDescripcion("DOCUMENTOS");
		stdao.crear_editar(st);
		lista.add(st);	
		
		
		id = new SysTituloPK();
		st = new SysTitulo();
		id.setIdmodulo(txtCodigo.getText().trim());
		id.setIdtitulo("003");		
		st.setId(id);
		st.setDescripcion("REPORTES");
		stdao.crear_editar(st);
		lista.add(st);	
		
		id = new SysTituloPK();
		st = new SysTitulo();
		id.setIdmodulo(txtCodigo.getText().trim());
		id.setIdtitulo("004");		
		st.setId(id);
		st.setDescripcion("PROCESOS");
		stdao.crear_editar(st);
		lista.add(st);	
	}
	
	@Override
	public void llenarDesdeVista() {
		getSysModulo().setIdmodulo(this.txtCodigo.getText().trim());
		getSysModulo().setDescripcion(this.txtDescripcion.getText().trim());

		setSysTitulos(new ArrayList<SysTitulo>());

		for (int i = 0; i < getTituloTM().getRowCount(); i++) {
			SysTituloPK id = new SysTituloPK();
			SysTitulo obj = new SysTitulo();

			id.setIdmodulo(getSysModulo().getIdmodulo());
			id.setIdtitulo(getTituloTM().getValueAt(i, 0).toString());

			obj.setId(id);
			obj.setDescripcion(getTituloTM().getValueAt(i, 1).toString());
			getSysTitulos().add(obj);
		}

	}

	@Override
	public void llenar_datos() {
		if(!getEstado().equals(NUEVO)){		

		getTituloTM().limpiar();
		setSysTitulos(new ArrayList<SysTitulo>());
		if (getSysModulo() != null) {
			txtCodigo.setText(getSysModulo().getIdmodulo());
			txtDescripcion.setText(getSysModulo().getDescripcion());
			setSysTitulos(getSysTituloDAO().getPorModulo(getSysModulo()));

			for (SysTitulo obj : getSysTitulos()) {
				getTituloTM().addRow(new Object[] { obj.getId().getIdtitulo(),
						obj.getDescripcion() });
			}
		} else {
			txtCodigo.setText("");
			txtDescripcion.setText("");
		}
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
		for (SysModulo obj : getSysModulos()) {
			model.addRow(new Object[] { obj.getIdmodulo(),
					obj.getDescripcion() });
		}
		if (getSysModulos().size() > 0) {
			setSysModulo(getSysModulos().get(0));
			tblLista.setRowSelectionInterval(0, 0);
		}
	}

	@Override
	public void llenar_tablas() {
		setSysModulos(getSysModuloDAO().findAll());
	}

	@Override
	public void vista_edicion() {
		if (getEstado().equals(NUEVO))
			txtCodigo.setEditable(true);
		else
			txtCodigo.setEditable(false);
		txtDescripcion.setEditable(true);
		getTituloTM().setEditar(true);
		btnILinea.setEnabled(true);
		btnBLinea.setEnabled(true);

	}

	@Override
	public void vista_noedicion() {
		txtCodigo.setEditable(false);
		txtDescripcion.setEditable(false);
		getTituloTM().setEditar(false);
		btnILinea.setEnabled(false);
		btnBLinea.setEnabled(false);
	}

	@Override
	public void eliminar() {
		if (getSysModulo() != null) {
			//getAlmacenDAO().borrarPorSucursal(getSucursal());
			getSysModuloDAO().remove(getSysModulo());
		}

	}
	
	private DSGTableModel getTituloTM(){
		return (DSGTableModel) tblTitulo.getModel();
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actualiza_objeto(Object entidad) {
		// TODO Auto-generated method stub
		
	}

	public SysModulo getSysModulo() {
		return sysModulo;
	}

	public void setSysModulo(SysModulo sysModulo) {
		this.sysModulo = sysModulo;
	}

	public List<SysModulo> getSysModulos() {
		return sysModulos;
	}

	public void setSysModulos(List<SysModulo> sysModulos) {
		this.sysModulos = sysModulos;
	}

	public SysModuloDAO getSysModuloDAO() {
		return sysModuloDAO;
	}

	public void setSysModuloDAO(SysModuloDAO sysModuloDAO) {
		this.sysModuloDAO = sysModuloDAO;
	}

	public SysTituloDAO getSysTituloDAO() {
		return sysTituloDAO;
	}

	public void setSysTituloDAO(SysTituloDAO sysTituloDAO) {
		this.sysTituloDAO = sysTituloDAO;
	}

	public List<SysTitulo> getSysTitulos() {
		return sysTitulos;
	}

	public void setSysTitulos(List<SysTitulo> sysTitulos) {
		this.sysTitulos = sysTitulos;
	}
}