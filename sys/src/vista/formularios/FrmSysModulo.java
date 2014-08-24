package vista.formularios;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import vista.Sys;
import vista.controles.DSGTableModel;
import vista.controles.JTextFieldLimit;
import vista.utilitarios.MaestroTableModel;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JButton;

import controlador.Mensajes;
import dao.SysGrupoDAO;
import dao.SysModuloDAO;
import dao.SysOpcionDAO;
import dao.SysTituloDAO;
import entity.SysGrupo;
import entity.SysModulo;
import entity.SysTitulo;
import entity.SysTituloPK;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import vista.utilitarios.UtilMensajes;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class FrmSysModulo extends AbstractMaestro {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTable tblLista;
	SysTituloDAO stdao = new SysTituloDAO();
	List<SysTitulo> lista;
	SysGrupoDAO sgdao = new SysGrupoDAO();
	SysOpcionDAO sodao = new SysOpcionDAO();
	
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

		tblLista = new JTable(new MaestroTableModel());
		scrollPane.setViewportView(tblLista);
		tblLista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JScrollPane scrollPaneNum = new JScrollPane();

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

		JLabel lblDescripcin = new JLabel("Descripci\u00F3n");

		txtCodigo = new JTextField();
		txtCodigo.setColumns(10);
		txtCodigo.setDocument(new JTextFieldLimit(3, true));
		
		txtDescripcion = new JTextField();
		txtDescripcion.setColumns(10);
		txtDescripcion.setDocument(new JTextFieldLimit(75, true));

		btnILinea = new JButton("I Linea");
		btnILinea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				final Object fila[] = { idMas(), "" };
				getTituloTM().addRow(fila);
			}
		});

		btnBLinea = new JButton("B Linea");
		btnBLinea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int ind = tblTitulo.getSelectedRow();
				if (ind >= 0)
					getTituloTM().removeRow(ind);
			}
		});
		GroupLayout groupLayout = new GroupLayout(pnlContenido);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
					.addGap(4)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblCdigo, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
							.addGap(19)
							.addComponent(txtCodigo, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(btnILinea)
							.addGap(6)
							.addComponent(btnBLinea, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblDescripcin, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
							.addGap(19)
							.addComponent(txtDescripcion, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(2)
							.addComponent(scrollPaneNum, GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)))
					.addGap(10))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(11)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
					.addGap(11))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(15)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(3)
							.addComponent(lblCdigo))
						.addComponent(txtCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnILinea, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnBLinea, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addGap(4)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(3)
							.addComponent(lblDescripcin))
						.addComponent(txtDescripcion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(6)
					.addComponent(scrollPaneNum, GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
					.addGap(11))
		);
		pnlContenido.setLayout(groupLayout);
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
	
	public String idMas(){
		int tot;
		
		tot = tblTitulo.getRowCount() + 1;
		
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
		setSysModulo(new SysModulo());
	}

	@Override
	public void editar() {
		super.editar();
	}

	@Override
	public void anular() {
		iniciar();
	}

	@Override
	public void grabar() {
		getSysModuloDAO().crear_editar(getSysModulo());
		getSysTituloDAO().borrarPorModulo(getSysModulo());
		for (SysTitulo obj : getSysTitulos()) {
			getSysTituloDAO().create(obj);
		}
		
	}

	public void cargar(){
		lista = new ArrayList<SysTitulo>();
		
		for( SysTitulo st : stdao.findAll()){
			lista.add(st);			
		}
		
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
		getTituloTM().limpiar();
		setSysTitulos(new ArrayList<SysTitulo>());
		
		if (!getEstado().equals(NUEVO) && getSysModulo() != null) {
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
			ArrayList<String> filas = new ArrayList<String>();
			filas.add("TABLAS");filas.add("DOCUMENTOS");filas.add("REPORTES");filas.add("PROCESOS");
			for(int i=0; i<filas.size();i++){
				final Object fila[] = {"00"+(i+1),filas.get(i)};
				getTituloTM().addRow(fila);
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
		boolean exisite = false;
		for(SysGrupo sysGrupo : sgdao.findAll()){
			if(sysGrupo.getId().getIdmodulo().equals(getSysModulo().getIdmodulo())){
				exisite = true;
				System.out.println("Probando 1");
				break;				
			}
		}
		
		if(!exisite){
			int seleccion = UtilMensajes.msj_error("ELIMINAR_REG");
			
			if (seleccion == 0){								
				System.out.println("Probando 2");
				if (getSysModulo() != null) {		
					getSysTituloDAO().borrarPorModulo(getSysModulo());
					getSysModuloDAO().remove(getSysModulo());
					
					iniciar();
				}			
			}			
		}else{
			UtilMensajes.mensaje_alterta("NO_ELIMINAR");
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