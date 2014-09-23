package vista.formularios;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.GroupLayout;
import javax.swing.ListSelectionModel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import vista.controles.JTextFieldLimit;
import vista.utilitarios.FormValidador;
import vista.utilitarios.MaestroTableModel;
import vista.utilitarios.UtilMensajes;
import dao.TransportistaDAO;
import entity.SysOpcionPK;
import entity.Transportista;
import entity.Transportista;
import javax.swing.LayoutStyle.ComponentPlacement;

public class FrmTransportista extends AbstractMaestro{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable tblLista;
	private JTextField txtDNI;
	private JTextField txtNombre;
	private JTextField txtDireccion;
	
	private Transportista transportista = new Transportista();
	private TransportistaDAO tdao = new TransportistaDAO();
	private List<Transportista> TransportistaL = new ArrayList<Transportista>();
	private JTextField txtCategoria;
	private JTextField txtLicencia;
	private JTextField txtFechVenc;

	public FrmTransportista() {
		super("Transportista");
		
		tblLista = new JTable();
		
		JLabel lblNewLabel = new JLabel("DNI:");
		
		txtDNI = new JTextField();
		txtDNI.setColumns(10);
		this.txtDNI.setName("DNI");
		txtDNI.setDocument(new JTextFieldLimit(8, true));
		
		JLabel lblNombre = new JLabel("Nombre:");
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		this.txtNombre.setName("Nombre");
		txtNombre.setDocument(new JTextFieldLimit(50, true));
		
		JLabel lblDireccin = new JLabel("Direcci\u00F3n:");
		
		txtDireccion = new JTextField();
		txtDireccion.setColumns(10);
		this.txtDireccion.setName("Dirección");
		txtDireccion.setDocument(new JTextFieldLimit(100, true));
		
		JLabel lblBrevete = new JLabel("Brevete:");
		
		String[] tip_brev={"A1","A2"};
		
		JScrollPane scrollPane = new JScrollPane();
		
		tblLista = new JTable(new MaestroTableModel());
		scrollPane.setViewportView(tblLista);
		tblLista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JLabel lblCategoria = new JLabel("Categoria:");
		
		txtCategoria = new JTextField();
		txtCategoria.setName("Categoría");
		txtCategoria.setColumns(10);
		txtCategoria.setDocument(new JTextFieldLimit(10, true));
		
		JLabel lblNumLicencia = new JLabel("Num. Licen:");
		
		txtLicencia = new JTextField();
		txtLicencia.setName("Numero de Licencia");
		txtLicencia.setColumns(10);
		txtLicencia.setDocument(new JTextFieldLimit(20, true));
		
		JLabel lblFecVenc = new JLabel("Fec. Venc:");
		
		txtFechVenc = new JTextField();
		txtFechVenc.setName("Fecha de Vencimiento");
		txtFechVenc.setColumns(10);
		txtFechVenc.setDocument(new JTextFieldLimit(10, true));
		
		GroupLayout groupLayout = new GroupLayout(pnlContenido);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(10)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblCategoria, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
									.addContainerGap())
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
										.addGap(10)
										.addComponent(txtDNI, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
										.addContainerGap())
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(lblNombre, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
										.addGap(10)
										.addComponent(txtNombre, GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
										.addContainerGap())
									.addComponent(lblDireccin, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblBrevete, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
									.addGroup(groupLayout.createSequentialGroup()
										.addGap(56)
										.addComponent(txtDireccion, GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
										.addContainerGap()))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(10)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblFecVenc, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNumLicencia)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(56)
									.addComponent(txtFechVenc, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(56)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(txtCategoria, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
										.addComponent(txtLicencia, GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE))))
							.addContainerGap())))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(11)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(3)
											.addComponent(lblNewLabel))
										.addComponent(txtDNI, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(11)
											.addComponent(lblNombre))
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(8)
											.addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
									.addGap(11)
									.addComponent(lblDireccin)
									.addGap(14)
									.addComponent(lblBrevete)
									.addGap(9)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblCategoria)
										.addComponent(txtCategoria, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(txtLicencia, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(3)
											.addComponent(lblNumLicencia)))
									.addGap(14)
									.addComponent(lblFecVenc))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(67)
							.addComponent(txtDireccion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(93)
							.addComponent(txtFechVenc, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(11))
		);
		pnlContenido.setLayout(groupLayout);
		
		tblLista.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
					@Override
					public void valueChanged(ListSelectionEvent e) {
						int selectedRow = tblLista.getSelectedRow();
						if (selectedRow >= 0)
							setTransportista(getTransportistaL().get(selectedRow));
						else
							setTransportista(null);
						llenar_datos();
					}
				});
		iniciar();
	}

	@Override
	public void nuevo() {
		setTransportista(new Transportista());
	}

	@Override
	public void anular() {
		vista_noedicion();
	}

	@Override
	public void grabar() {
		
		getTdao().crear_editar(getTransportista());
	}

	@Override
	public void eliminar() {
		if (getTransportista() != null) {

			int seleccion = UtilMensajes.msj_error("ELIMINAR_REG");
			if (seleccion == 0) {				
				tdao.remove(getTransportista());
				iniciar();
			}
		}

		setEstado(VISTA);
		vista_noedicion();
	}

	@Override
	public void llenar_datos() {
		if (getTransportista() != null) {
			txtDNI.setText(getTransportista().getDni());
			txtNombre.setText(getTransportista().getNombre());
			txtDireccion.setText(getTransportista().getDireccion());
			txtCategoria.setText(getTransportista().getTipoBrevete());
			txtLicencia.setText(getTransportista().getNumLic());
			txtFechVenc.setText(getTransportista().getFechVenc());
		} else {
			txtDNI.setText("");
			txtNombre.setText("");
			txtDireccion.setText("");
			txtCategoria.setText("");
			txtLicencia.setText("");
			txtFechVenc.setText("");
		}
	}

	@Override
	public void llenar_lista() {
		tblLista.setFillsViewportHeight(true);

		MaestroTableModel model = (MaestroTableModel) tblLista.getModel();
		model.limpiar();
		for (Transportista transportista : getTransportistaL()) {
			model.addRow(new Object[] { transportista.getDni(), transportista.getNombre() });
		}
		if (getTransportistaL().size() > 0) {
			setTransportista(getTransportistaL().get(0));
			tblLista.setRowSelectionInterval(0, 0);
		}
	}

	@Override
	public void llenar_tablas() {
		setTransportistaL(getTdao().findAll());
	}

	@Override
	public void vista_edicion() {
		if (getEstado().equals(NUEVO))
			txtDNI.setEditable(true);
		txtNombre.setEditable(true);
		txtDireccion.setEditable(true);
		txtCategoria.setEditable(true);
		txtFechVenc.setEditable(true);
		txtLicencia.setEditable(true);
		tblLista.setEnabled(false);
	}

	@Override
	public void vista_noedicion() {
		txtDNI.setEditable(false);
		txtNombre.setEditable(false);
		txtDireccion.setEditable(false);
		txtCategoria.setEditable(false);
		txtFechVenc.setEditable(false);
		txtLicencia.setEditable(false);
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

		getTransportista().setDni(txtDNI.getText().trim());
		getTransportista().setDireccion(txtDireccion.getText());
		getTransportista().setNombre(txtNombre.getText());
		getTransportista().setTipoBrevete(txtCategoria.getText());
		getTransportista().setNumLic(txtLicencia.getText());
		getTransportista().setFechVenc(txtFechVenc.getText());
		
	}

	@Override
	public boolean isValidaVista() {

		if (!FormValidador.TextFieldObligatorios(txtDNI, txtNombre, txtDireccion, txtCategoria))
			return false;
		
		if (getEstado().equals(NUEVO)) {
			System.out.println("Probando: " + getTdao().find(txtDNI.getText()));
			if (getTdao().find(txtDNI.getText().trim()) != null) {
				UtilMensajes.mensaje_alterta("CODIGO_EXISTE");
				this.txtDNI.requestFocus();
				return false;
			}
		}
		
		if (tdao.find(this.txtDNI.getText().trim()) != null) {
			UtilMensajes.mensaje_alterta("CODIGO_EXISTE");
			this.txtDNI.requestFocus();
			return false;
		}
		
		return true;
	}

	public JTable getTblLista() {
		return tblLista;
	}

	public void setTblLista(JTable tblLista) {
		this.tblLista = tblLista;
	}

	public Transportista getTransportista() {
		return transportista;
	}

	public void setTransportista(Transportista transportista) {
		this.transportista = transportista;
	}

	public TransportistaDAO getTdao() {
		return tdao;
	}

	public void setTdao(TransportistaDAO tdao) {
		this.tdao = tdao;
	}

	public List<Transportista> getTransportistaL() {
		return TransportistaL;
	}

	public void setTransportistaL(List<Transportista> transportistaL) {
		TransportistaL = transportistaL;
	}
}
