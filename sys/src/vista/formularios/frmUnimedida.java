package vista.formularios;

import java.util.ArrayList;
import java.util.List;
import vista.utilitarios.MaestroTableModel;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import dao.UnimedidaDAO;
import entity.Unimedida;

import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class frmUnimedida extends AbstractMaestro {
	private static final long serialVersionUID = 1L;
	private JTextField txtCodigo;
	private JTextField txtDescripcion;
	private JTextField txtNomenclatura;
	private Unimedida unimedida;
	private List<Unimedida> unimedidaL = new ArrayList<Unimedida>();
	
	public List<Unimedida> getUnimedidaL() {
		return unimedidaL;
	}

	public void setUnimedidaL(List<Unimedida> unimedidaL) {
		this.unimedidaL = unimedidaL;
	}


	private UnimedidaDAO udao = new UnimedidaDAO();
	public UnimedidaDAO getUdao() {
		return udao;
	}

	public void setUdao(UnimedidaDAO udao) {
		this.udao = udao;
	}


	private JTable tblLista;

	public Unimedida getUnimedida() {		 
		return unimedida;
	}

	public void setUnimedida(Unimedida unimedida) {
		this.unimedida = unimedida;
	}

	public frmUnimedida() {
		super("Unidad de Medida");
		
		JLabel lblCodigo = new JLabel("Codigo");
		
		JLabel lblDescripcion = new JLabel("Descripcion");
		
		JLabel lblNomenclatura = new JLabel("Nomenclatura");
		
		txtCodigo = new JTextField();
		txtCodigo.setColumns(10);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setColumns(10);
		
		txtNomenclatura = new JTextField();
		txtNomenclatura.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setAlignmentY(0.0f);
		scrollPane.setAlignmentX(0.0f);
		// TODO Auto-generated constructor stub
		
		tblLista = new JTable(new MaestroTableModel());
		scrollPane.setViewportView(tblLista);
		tblLista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		GroupLayout groupLayout = new GroupLayout(pnlContenido);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblCodigo, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDescripcion, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNomenclatura, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE))
					.addGap(9)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(txtCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtDescripcion, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtNomenclatura, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(11)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(3)
							.addComponent(lblCodigo)
							.addGap(11)
							.addComponent(lblDescripcion)
							.addGap(11)
							.addComponent(lblNomenclatura))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(txtCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(5)
							.addComponent(txtDescripcion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(8)
							.addComponent(txtNomenclatura, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		pnlContenido.setLayout(groupLayout);
		tblLista.getSelectionModel().addListSelectionListener(
		new ListSelectionListener() {
			@Override					
			public void valueChanged(ListSelectionEvent arg0) {
				int selectedRow = tblLista.getSelectedRow();
				if (selectedRow >= 0)
					setUnimedida(getUnimedidaL().get(selectedRow));
				else
					setUnimedida(null);
				llenar_datos();						
			}
		});	
		iniciar();
	}

	public void grabar(){
		try {						
			getUdao().crear_editar(getUnimedida());		
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex);
		}
	}	
	@Override
	public void anular() {
		// TODO Auto-generated method stub

	}

	@Override
	public void llenar_datos() {
		if (getUnimedida() instanceof Unimedida) {		
			this.txtCodigo.setText(this.getUnimedida().getIdunimedida());
			this.txtDescripcion.setText(this.getUnimedida().getDescripcion());
			this.txtNomenclatura.setText(this.getUnimedida().getNomenclatura());
		} else {			
			this.txtCodigo.setText(null);
			this.txtDescripcion.setText(null);
			this.txtNomenclatura.setText(null);
		}

	}

	@Override
	public void llenar_lista() {
		tblLista.setFillsViewportHeight(true);
		MaestroTableModel model = (MaestroTableModel) tblLista.getModel();
		model.limpiar();
		for (Unimedida medida : getUnimedidaL()) {
			model.addRow(new Object[] { medida.getIdunimedida(), medida.getDescripcion() });
		}
		if (getUnimedidaL().size() > 0) {
			setUnimedida(getUnimedidaL().get(0));
			tblLista.setRowSelectionInterval(0, 0);
		}

	}

	@Override
	public void llenar_tablas() {
		setUnimedidaL(udao.findAll());

	}

	@Override
	public void vista_edicion() {
		if (getEstado().equals(NUEVO))
			txtCodigo.setEditable(true);
		txtDescripcion.setEditable(true);
		txtNomenclatura.setEditable(true);
	}

	@Override
	public void vista_noedicion() {
		txtCodigo.setEditable(false);
		txtDescripcion.setEditable(false);
		txtNomenclatura.setEditable(false);
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void nuevo() {
		setUnimedida(new Unimedida());			
	}
	

	@Override
	public void actualiza_objeto(Object entidad) {
		unimedida = (Unimedida)entidad;
		this.setUnimedida(unimedida);
		this.llenar_datos();
		this.vista_noedicion();	
	}

	@Override
	public void llenarDesdeVista() {
		getUnimedida().setIdunimedida(this.txtCodigo.getText());
		getUnimedida().setDescripcion(this.txtDescripcion.getText());
		getUnimedida().setNomenclatura(this.txtNomenclatura.getText());		
	}

	@Override
	public boolean isValidaVista() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void eliminar() {
		// TODO Auto-generated method stub
		
	}
}
