package vista.formularios;

import java.util.ArrayList;
import java.util.List;

import vista.controles.JTextFieldLimit;
import vista.utilitarios.MaestroTableModel;
import vista.utilitarios.UtilMensajes;

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

public class FrmUnimedida extends AbstractMaestro {
	private static final long serialVersionUID = 1L;
	private JTextField txtCodigo;
	private JTextField txtDescripcion;
	private JTextField txtNomenclatura;
	private Unimedida unimedida;
	private List<Unimedida> unimedidaL = new ArrayList<Unimedida>();
	
	String bkEntidad = null;
	
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

	public FrmUnimedida() {
		super("Unidad de Medida");
		
		JLabel lblCodigo = new JLabel("Codigo");
		
		JLabel lblDescripcion = new JLabel("Descripcion");
		
		JLabel lblNomenclatura = new JLabel("Nomenclatura");
		
		txtCodigo = new JTextField();
		txtCodigo.setColumns(10);
		txtCodigo.setDocument(new JTextFieldLimit(3, true));
		
		txtDescripcion = new JTextField();
		txtDescripcion.setColumns(10);
		txtDescripcion.setDocument(new JTextFieldLimit(75, true));
		
		txtNomenclatura = new JTextField();
		txtNomenclatura.setColumns(10);
		txtNomenclatura.setDocument(new JTextFieldLimit(50, true));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setAlignmentY(0.0f);
		scrollPane.setAlignmentX(0.0f);
		// TODO Auto-generated constructor stub
		
		tblLista = new JTable(new MaestroTableModel());
		scrollPane.setViewportView(tblLista);
		tblLista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		GroupLayout groupLayout = new GroupLayout(pnlContenido);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblCodigo, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDescripcion, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNomenclatura, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE))
					.addGap(9)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(txtCodigo)
							.addGap(100))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(txtDescripcion, GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
							.addGap(5))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(txtNomenclatura)
							.addGap(103)))
					.addGap(0))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(11)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
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
			
			if(getUdao().find(getUnimedida().getIdunimedida()) != null){
				Historial.validar("Modificar", bkEntidad , getUnimedida().historial(), getTitle() );
			}else{			
				Historial.validar("Nuevo", getUnimedida().historial(), getTitle());
			}
			
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
		
		if(getUdao().find(txtCodigo.getText()) != null){
			bkEntidad = getUdao().find(txtCodigo.getText()).historial();			
		}
		
		getUnimedida().setIdunimedida(this.txtCodigo.getText());
		getUnimedida().setDescripcion(this.txtDescripcion.getText());
		getUnimedida().setNomenclatura(this.txtNomenclatura.getText());		
	}

	@Override
	public boolean isValidaVista() {
		if (this.txtCodigo.getText().trim().isEmpty()) {
			UtilMensajes.mensaje_alterta("DATO_REQUERIDO", "Código");
			this.txtCodigo.requestFocus();
			return false;
		}
		if (getEstado().equals(NUEVO)) {
			if (getUdao().find(this.txtCodigo.getText().trim()) != null) {
				UtilMensajes.mensaje_alterta("CODIGO_EXISTE");
				this.txtCodigo.requestFocus();
				return false;
			}
		}
		if (this.txtDescripcion.getText().trim().isEmpty()) {
			UtilMensajes.mensaje_alterta("DATO_REQUERIDO", "Descripción");
			this.txtDescripcion.requestFocus();
			return false;
		}
		if (this.txtNomenclatura.getText().trim().isEmpty()) {

			this.txtNomenclatura.requestFocus();
			return false;
		}
		
		return true;
	}

	@Override
	public void eliminar() {
		if (getUnimedida() != null) {
			int seleccion = UtilMensajes.msj_error("ELIMINAR_REG");
			
			if (seleccion == 0){
				Historial.validar("Eliminar", getUnimedida().historial(), getTitle() );
				getUdao().remove(getUnimedida());
				iniciar();
			}			
		}

	}
}
