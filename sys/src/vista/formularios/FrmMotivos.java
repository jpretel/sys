package vista.formularios;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import dao.MotivosDAO;
import entity.Motivo;
import vista.combobox.ComboBox;
import vista.utilitarios.MaestroTableModel;

import javax.swing.JCheckBox;
import javax.swing.JTabbedPane;
import javax.swing.border.BevelBorder;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class FrmMotivos extends AbstractMaestro {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtCodigo;
	private JTextField txtDescripcion;
	private JTextField txtNombreCorto;
	private JTable tblLista;
	private JCheckBox chkTransferencia;
	private JCheckBox chkCentraliza;
	private JCheckBox chkSolicitaCompra;
	private JCheckBox chckSolicitaRecepcion;
	private int EsTransferencia,centraliza,solicita_compra,solicita_recepcion;
	private Motivo motivo;
	public Motivo getMotivo() {
		return motivo;
	}

	public void setMotivo(Motivo motivo) {
		this.motivo = motivo;
	}

	private MotivosDAO mdao= new MotivosDAO();
	private List<Motivo> motivoL = mdao.findAll(); 
	public List<Motivo> getMotivoL() {
		return motivoL;
	}

	public void setMotivoL(List<Motivo> motivoL) {
		this.motivoL = motivoL;
	}

	private ComboBox cboTipo;
	private List<String[]> optionList = new ArrayList<String[]>();
	private JTabbedPane tabbedPane;
	
	public FrmMotivos() {
		super("Motivos");
		
		JLabel lblCodigo = new JLabel("Codigo");
		lblCodigo.setBounds(260, 11, 33, 46);
		
		JLabel lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setBounds(260, 69, 54, 14);
		
		JLabel lblNomenclatura = new JLabel("Nombre Corto");
		lblNomenclatura.setBounds(260, 104, 67, 14);
		
		txtCodigo = new JTextField();
		txtCodigo.setBounds(345, 24, 86, 20);
		txtCodigo.setColumns(10);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setColumns(10);
		txtDescripcion.setBounds(345, 63, 208, 20);
		
		txtNombreCorto = new JTextField();
		txtNombreCorto.setColumns(10);
		txtNombreCorto.setBounds(345, 101, 123, 20);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 230, 260);
		scrollPane.setAlignmentY(Component.TOP_ALIGNMENT);
		scrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
		tblLista = new JTable(new MaestroTableModel());
		scrollPane.setViewportView(tblLista);
		tblLista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setBounds(260, 142, 33, 14);
		
		optionList.add(new String[]{"I","Ingreso"});
		optionList.add(new String[]{"S","Salida"});
		
		cboTipo = new vista.combobox.ComboBox(optionList,1);
		cboTipo.setBounds(345, 139, 123, 20);
		pnlContenido.setLayout(null);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tabbedPane.setBounds(250, 170, 303, 101);
		pnlContenido.add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Configuraciones", null, panel, null);
		
		chkCentraliza = new JCheckBox("Centraliza\r\n");
		panel.add(chkCentraliza);
		
		chkTransferencia = new JCheckBox("Transferencia");
		chkTransferencia.setVerticalAlignment(SwingConstants.TOP);
		panel.add(chkTransferencia);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Documentos Requeridos", null, panel_1, null);
		
		chkSolicitaCompra = new JCheckBox("Solicita Orden Compra\r\n");
		panel_1.add(chkSolicitaCompra);
		
		chckSolicitaRecepcion = new JCheckBox("Solicita Recepcion\r\n\r\n");
		panel_1.add(chckSolicitaRecepcion);
		pnlContenido.add(scrollPane);
		pnlContenido.add(lblCodigo);
		pnlContenido.add(txtCodigo);
		pnlContenido.add(lblNomenclatura);
		pnlContenido.add(cboTipo);
		pnlContenido.add(txtNombreCorto);
		pnlContenido.add(lblDescripcion);
		pnlContenido.add(txtDescripcion);
		pnlContenido.add(lblTipo);
		tblLista.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
					@Override					
					public void valueChanged(ListSelectionEvent arg0) {
						int selectedRow = tblLista.getSelectedRow();
						if (selectedRow >= 0)
							setMotivo(getMotivoL().get(selectedRow));
						else
							setMotivo(null);
						llenar_datos();						
					}
				});
		iniciar();	
	}

	@Override
	public void nuevo() {
		setMotivo(new Motivo());
	}

	@Override
	public void anular() {
		// TODO Auto-generated method stub

	}

	@Override
	public void grabar() {
		mdao.crear_editar(getMotivo());
	}

	@Override
	public void eliminar() {
		// TODO Auto-generated method stub

	}

	@Override
	public void llenar_datos() {
		boolean band;
		if(getMotivo() instanceof Motivo){
			txtCodigo.setText(getMotivo().getIdmotivo());
			txtDescripcion.setText(getMotivo().getDescripcion());
			txtNombreCorto.setText(getMotivo().getNombreCorto());
			if (!getEstado().equals(NUEVO)){
				int indice = (getMotivo().getTipo().equals("I"))? 0: 1; 
				cboTipo.setSelectedIndex(indice);
			}
			band = (getMotivo().getEsTransferencia() == 1) ? true : false;
			chkTransferencia.setSelected(band);
			band = (getMotivo().getCentraliza() == 1) ? true : false;			
			chkCentraliza.setSelected(band);
			band = (getMotivo().getSolcitaCompra() == 1) ? true : false;			
			chkSolicitaCompra.setSelected(band);
			band = (getMotivo().getSolicitaRecepcion() == 1) ? true : false;			
			chckSolicitaRecepcion.setSelected(band);
		}else{
			txtCodigo.setText(null);
			txtDescripcion.setText(null);
			txtNombreCorto.setText(null);
		}
	}

	@Override
	public void llenar_lista() {
		tblLista.setFillsViewportHeight(true);
		MaestroTableModel modelo = (MaestroTableModel)tblLista.getModel();
		modelo.limpiar();
		for(Motivo motivo: getMotivoL()){
			modelo.addRow(new Object[] {motivo.getIdmotivo(),motivo.getDescripcion()});
		}
		if(getMotivoL().size() > 0){
			setMotivo(getMotivoL().get(0));
			tblLista.setRowSelectionInterval(0, 0);
		}

	}

	@Override
	public void llenar_tablas() {
		setMotivoL(getMdao().findAll());
	}

	@SuppressWarnings("deprecation")
	@Override
	public void vista_edicion() {
		if (getEstado().equals(NUEVO))
			txtCodigo.setEditable(true);
		txtDescripcion.setEditable(true);
		txtNombreCorto.setEditable(true);
		cboTipo.enable(true);
		chkTransferencia.enable(true);
		chkCentraliza.enable(true);
		chkSolicitaCompra.enable(true);
		chckSolicitaRecepcion.enable(true);
		tblLista.setEnabled(false);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void vista_noedicion() {
		txtCodigo.setEditable(false);
		txtDescripcion.setEditable(false);
		txtNombreCorto.setEditable(false);
		cboTipo.enable(false);
		chkTransferencia.enable(false);
		chkCentraliza.enable(false);
		chckSolicitaRecepcion.enable(false);
		tblLista.setEnabled(true);
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public void actualiza_objeto(Object entidad) {
		this.setMotivo(motivo);
		this.llenar_datos();
		this.vista_noedicion();
	}

	@Override
	public void llenarDesdeVista() {		 
		getMotivo().setIdmotivo(txtCodigo.getText());
		getMotivo().setDescripcion(txtDescripcion.getText());
		getMotivo().setNombreCorto(txtNombreCorto.getText());
		getMotivo().setTipo(((String[])cboTipo.getSelectedItem())[0]);
		EsTransferencia = (chkTransferencia.isSelected()) ? 1 : 0 ;
		centraliza = (chkCentraliza.isSelected()) ? 1 : 0;
		solicita_compra = (chkSolicitaCompra.isSelected())? 1:0;
		solicita_recepcion = (chckSolicitaRecepcion.isSelected())?1:0;
		getMotivo().setEsTransferencia(EsTransferencia);
		getMotivo().setCentraliza(centraliza);
		getMotivo().setSolcitaCompra(solicita_compra);
		getMotivo().setSolicitaRecepcion(solicita_recepcion);
	}

	@Override
	public boolean isValidaVista() {
		if(txtCodigo.getText().trim().isEmpty()){
			return false;
		}
		if(txtDescripcion.getText().trim().isEmpty()){
			return false;
		}
		if(txtNombreCorto.getText().trim().isEmpty()){
			return false;
		}
		if(cboTipo.getSelectedIndex() == -1){
			return false;
		}
		return true;
	}

	public MotivosDAO getMdao() {
		return mdao;
	}

	public void setMdao(MotivosDAO mdao) {
		this.mdao = mdao;
	}
}
