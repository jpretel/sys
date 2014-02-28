package vista.formularios;

import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import vista.barras.BarraMaestro;
import vista.combobox.DSGComboBox;
import vista.utilitarios.MaestroTableModel;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;

import dao.DocumentoDAO;
import entity.Documento;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class FrmDocumento extends AbstractMaestro {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTable tblLista;

	private JTable tblnumeradores;
	private JTextField txtCodigo;
	private JTextField txtDescripcion;

	private Documento documento;

	private List<Documento> documentos;

	private DocumentoDAO documentoDAO = new DocumentoDAO();

	private JComboBox<String[]> cboOrigen;

	private static List<String[]> origenes;

	static {
		origenes = new ArrayList<String[]>();
		origenes.add(new String[] { "C", "Cobrar" });
		origenes.add(new String[] { "P", "Pagar" });
		origenes.add(new String[] { "A", "Ambos" });
	}

	public FrmDocumento(BarraMaestro barra) {
		super("Documentos", barra);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 207, 273);

		tblLista = new JTable(new MaestroTableModel());
		scrollPane.setViewportView(tblLista);
		tblLista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JScrollPane scrollPaneNum = new JScrollPane();
		scrollPaneNum.setBounds(227, 128, 383, 156);

		tblnumeradores = new JTable(new NumeradorTableModel());
		scrollPaneNum.setViewportView(tblnumeradores);
		tblnumeradores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JLabel lblCdigo = new JLabel("C\u00F3digo");
		lblCdigo.setBounds(227, 12, 46, 14);

		JLabel lblDescripcin = new JLabel("Descripci\u00F3n");
		lblDescripcin.setBounds(227, 33, 61, 14);

		JLabel lblOrigen = new JLabel("Origen");
		lblOrigen.setBounds(227, 58, 38, 14);

		JLabel lblFactor = new JLabel("Factor");
		lblFactor.setBounds(227, 84, 38, 14);

		txtCodigo = new JTextField();
		txtCodigo.setBounds(271, 9, 86, 20);
		txtCodigo.setColumns(10);

		txtDescripcion = new JTextField();
		txtDescripcion.setBounds(295, 30, 86, 20);
		txtDescripcion.setColumns(10);

		JSpinner spFactor = new JSpinner();
		spFactor.setModel(new SpinnerNumberModel(0, -1, 1, 1));
		spFactor.setBounds(271, 81, 46, 20);
		
		cboOrigen = new DSGComboBox(origenes, 1);

		cboOrigen.setBounds(278, 55, 103, 20);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(4)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblFactor, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblOrigen, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblDescripcin, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblCdigo, GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE))
							.addGap(19)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(txtDescripcion, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
								.addComponent(spFactor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(cboOrigen, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtCodigo, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE))
							.addGap(102))
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scrollPaneNum, GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
							.addContainerGap())))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
									.addGroup(groupLayout.createSequentialGroup()
										.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
											.addComponent(lblCdigo)
											.addComponent(txtCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lblOrigen))
									.addGroup(groupLayout.createSequentialGroup()
										.addGap(59)
										.addComponent(cboOrigen, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(39)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblDescripcin)
								.addComponent(txtDescripcion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(47)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblFactor)
								.addComponent(spFactor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(scrollPaneNum, GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)))
					.addGap(11))
		);
		getContentPane().setLayout(groupLayout);
  
		iniciar();
	}

	@Override
	public void nuevo() {
		// TODO Auto-generated method stub
		super.nuevo();
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
		super.grabar();
	}

	@Override
	public void llenar_datos() {
		if (getDocumento() != null) {
			txtCodigo.setText(getDocumento().getId());
			txtDescripcion.setText(getDocumento().getDescripcion());
			cboOrigen.setSelectedIndex(-1);
			salir: for (int i = 0; i < cboOrigen.getItemCount(); i++) {
				String[] item = cboOrigen.getItemAt(i);
				if (item[0].equals(getDocumento().getOrigen())) {
					cboOrigen.setSelectedIndex(i);
					break salir;
				}
			}
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
		for (Documento cuenta : getDocumentos()) {
			model.addRow(new Object[] { cuenta.getId(), cuenta.getDescripcion() });
		}
		if (getDocumentos().size() > 0) {
			setDocumento(getDocumentos().get(0));
			tblLista.setRowSelectionInterval(0, 0);
		}
	}

	@Override
	public void llenar_tablas() {
		setDocumentos(getDocumentoDAO().findAll());
	}

	@Override
	public void vista_edicion() {
		if (getEstado().equals(NUEVO))
			txtCodigo.setEditable(true);
		else
			txtCodigo.setEditable(false);
		txtDescripcion.setEditable(true);
	}

	@Override
	public void vista_noedicion() {
		txtCodigo.setEditable(false);
		txtDescripcion.setEditable(false);
	}

	public Documento getDocumento() {
		return documento;
	}

	public void setDocumento(Documento documento) {
		this.documento = documento;
	}

	public List<Documento> getDocumentos() {
		return documentos;
	}

	public void setDocumentos(List<Documento> documentos) {
		this.documentos = documentos;
	}

	public DocumentoDAO getDocumentoDAO() {
		return documentoDAO;
	}

	public void setDocumentoDAO(DocumentoDAO documentoDAO) {
		this.documentoDAO = documentoDAO;
	}

	@Override
	public void setSelected(boolean selected) throws PropertyVetoException {
		controlador.VariablesGlobales.home.getBarraMaestro().setVisible(
				selected);
		if (selected) {
			controlador.VariablesGlobales.home.getBarraMaestro()
					.setFormMaestro(this);
		}
		super.setSelected(selected);
	}

	@Override
	protected void actualizaBarra() {
		getBarra().setVisible(isSelected());
		if (isSelected()) {
			getBarra().setFormMaestro(this);
		}
		if (getEstado().equals(VISTA)) {
			getBarra().enVista();
		} else {
			getBarra().enEdicion();
		}
	}

	@Override
	public void nuevo_lista() {
		// TODO Auto-generated method stub
		
	}
}

class NumeradorTableModel extends DefaultTableModel {

	private static final long serialVersionUID = 1L;

	public NumeradorTableModel() {
		addColumn("Serie");
		addColumn("Número");
	}

	public boolean isCellEditable(int row, int column) {
		return false;
	}

	public void limpiar() {
		while (getRowCount() != 0) {
			removeRow(0);
		}
	}
}
