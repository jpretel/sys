package vista.formularios;

import java.awt.Window;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.util.List;

import dao.AreaDAO;
import dao.ResponsableDAO;
import entity.Area;
import entity.Responsable;

import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import vista.Sys;
import vista.contenedores.cntArea;
import vista.utilitarios.MaestroTableModel;

import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class FrmResponsable extends AbstractMaestro {

	private static final long serialVersionUID = 1L;

	private Responsable responsable;

	public Responsable getResponsable() {
		return responsable;
	}

	public void setResponsable(Responsable responsable) {
		this.responsable = responsable;
	}

	private ResponsableDAO responsableDAO = new ResponsableDAO();

	public ResponsableDAO getResponsableDAO() {
		return responsableDAO;
	}

	public void setResponsableDAO(ResponsableDAO responsableDAO) {
		this.responsableDAO = responsableDAO;
	}

	private List<Responsable> responsableL = new ArrayList<Responsable>();

	public List<Responsable> getResponsableL() {
		return responsableL;
	}

	public void setResponsableL(List<Responsable> responsableL) {
		this.responsableL = responsableL;
	}

	private JTable tblLista;
	private JTextField txtCodigo;
	private JTextField txtDescripcion;	
	public final cntArea cntarea;
	private AreaDAO adao;
	private Area area = new Area();
	private String datos[][];
	
	public FrmResponsable() {
		super("Responsables");		
		
		JLabel lblCdigo = new JLabel("Codigo");
		lblCdigo.setBounds(227, 11, 46, 14);

		txtCodigo = new JTextField();
		txtCodigo.setBounds(276, 8, 122, 20);
		txtCodigo.setColumns(10);

		JLabel lblDescripcin = new JLabel("Descripci\u00F3n");
		lblDescripcin.setBounds(227, 36, 75, 14);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 207, 273);

		tblLista = new JTable(new MaestroTableModel());
		scrollPane.setViewportView(tblLista);
		tblLista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		txtDescripcion = new JTextField();
		txtDescripcion.setColumns(10);
		txtDescripcion.setBounds(286, 33, 122, 20);
		
		JLabel lblArea = new JLabel("Area");
		lblArea.setBounds(286, 80, 75, 14);
		cntarea = new cntArea(){
			private static final long serialVersionUID = 1L;
			public Window getFormulario(){
			return (Window) Sys.mainF;
			}};
		cntarea.txtCodigo.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				if (!cntarea.txtCodigo.getText().equals("")) {					
					area = adao.find(cntarea.txtCodigo.getText());
					cntarea.setArea(area);
				}
			}
		});
		cntarea.setBounds(296, 80, 290, 25);
		getContentPane().add(cntarea);
		getContentPane().add(lblArea);
		GroupLayout groupLayout = new GroupLayout(pnlContenido);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(10)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(312)
											.addComponent(lblCdigo))
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(312)
											.addComponent(lblDescripcin)))
									.addGap(5)
									.addComponent(txtDescripcion, GroupLayout.PREFERRED_SIZE, 218, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 302, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(lblArea, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(cntarea, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(381)
							.addComponent(txtCodigo, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(11)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 274, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(15)
									.addComponent(lblCdigo))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(45)
									.addComponent(lblDescripcin)))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(cntarea, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblArea))
							.addGap(180))))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(52)
					.addComponent(txtDescripcion, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(26)
					.addComponent(txtCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		pnlContenido.setLayout(groupLayout);

		tblLista.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
					@Override
					public void valueChanged(ListSelectionEvent e) {
						int selectedRow = tblLista.getSelectedRow();
						if (selectedRow >= 0)
							setResponsable(getResponsableL().get(selectedRow));						
						else
							setResponsable(null);
						llenar_datos();
					}
				});
		llenar_cntarea();
		iniciar();
	}
	
	public void llenar_cntarea(){
		adao = new AreaDAO();
		List<Area> AreaL = adao.findAll();
		datos = new String[AreaL.size()][2];
		for (int i = 0; i < AreaL.size(); i++) {
			area = (Area) AreaL.get(i);
			datos[i][0] = area.getIdarea();
			datos[i][1] = area.getDescripcion();
		}
		cntarea.cargar_informacion(datos);
	}

	@Override
	public void nuevo() {
		setResponsable(new Responsable());
	}

	@Override
	public void anular() {
		vista_noedicion();
	}

	@Override
	public void grabar() {		
		getResponsableDAO().crear_editar(getResponsable());
	}

	@Override
	public void eliminar() {
		setEstado(VISTA);
		vista_noedicion();
	}

	@Override
	public void llenar_datos() {
		if (getResponsable() != null) {
			txtCodigo.setText(getResponsable().getIdresponsable());
			txtDescripcion.setText(getResponsable().getNombre());
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
		for (Responsable responsable : getResponsableL()) {
			model.addRow(new Object[] { responsable.getIdresponsable(), responsable.getNombre()});
		}
		if (getResponsableL().size() > 0) {
			setResponsable(getResponsableL().get(0));
			tblLista.setRowSelectionInterval(0, 0);
		}
	}

	@Override
	public void llenar_tablas() {
		setResponsableL(getResponsableDAO().findAll());
	}

	@Override
	public void vista_edicion() {
		if (getEstado().equals(NUEVO))
			txtCodigo.setEditable(true);
		txtDescripcion.setEditable(true);
		tblLista.setEnabled(false);
		cntarea.btnBuscar.setEnabled(true);
		cntarea.txtCodigo.setEditable(true);
		cntarea.txtDescripcion.setEditable(true);
	}

	@Override
	public void vista_noedicion() {
		txtCodigo.setEditable(false);
		txtDescripcion.setEditable(false);
		tblLista.setEnabled(true);
		cntarea.btnBuscar.setEnabled(false);
		cntarea.txtCodigo.setEditable(false);
		cntarea.txtDescripcion.setEditable(false);
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
		getResponsable().setIdresponsable(txtCodigo.getText());
		getResponsable().setNombre(txtDescripcion.getText());
		
	}

	@Override
	public boolean isValidaVista() {
		// TODO Auto-generated method stub
		return false;
	}

}
