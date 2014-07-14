package vista.formularios;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import vista.barras.BarraMaestro;
import vista.utilitarios.MaestroTableModel;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import dao.DocumentoDAO;
import dao.DocumentoNumeroDAO;
import entity.Documento;
import entity.DocumentoNumero;
import entity.DocumentoNumeroPK;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmDocumento extends AbstractMaestro {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JTable tblLista;

	private JTable tblnumeradores;
	private JTextField txtCodigo;
	private JTextField txtDescripcion;
	private NumeradorTableModel numeradoresTM = new NumeradorTableModel();
	private Documento documento;

	private List<Documento> documentos;
	private List<DocumentoNumero> numeradores;

	private DocumentoDAO documentoDAO = new DocumentoDAO();

	private DocumentoNumeroDAO docnumDAO = new DocumentoNumeroDAO();

	private JButton btnILinea;
	private JButton btnBLinea;
	public FrmDocumento(BarraMaestro barra) {
		super("Documentos");
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 199, 273);

		tblLista = new JTable(new MaestroTableModel());
		scrollPane.setViewportView(tblLista);
		tblLista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JScrollPane scrollPaneNum = new JScrollPane();
		scrollPaneNum.setBounds(215, 65, 314, 219);

		tblnumeradores = new JTable(numeradoresTM);
		scrollPaneNum.setViewportView(tblnumeradores);
		tblnumeradores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JLabel lblCdigo = new JLabel("Cdigo");
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
				final Object fila[] = { "", "", "" };
				numeradoresTM.addRow(fila);
			}
		});

		btnBLinea = new JButton("B Linea");
		btnBLinea.setBounds(446, 15, 83, 20);
		btnBLinea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int ind = tblnumeradores.getSelectedRow();
				if (ind >= 0)
					numeradoresTM.removeRow(ind);
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
							setDocumento(getDocumentos().get(selectedRow));
						else
							setDocumento(null);
						llenar_datos();
					}
				});
		iniciar();
	}

	@Override
	public void nuevo() {
		setDocumento(new Documento());
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
		documentoDAO.crear_editar(getDocumento());
		docnumDAO.borrarPorDocumento(getDocumento());
		for (DocumentoNumero num : getNumeradores()) {
			docnumDAO.create(num);
		}
	}

	@Override
	public void llenarDesdeVista() {
		getDocumento().setIddocumento(this.txtCodigo.getText().trim());
		getDocumento().setDescripcion(this.txtDescripcion.getText().trim());

		setNumeradores(new ArrayList<DocumentoNumero>());

		for (int i = 0; i < numeradoresTM.getRowCount(); i++) {
			DocumentoNumeroPK id = new DocumentoNumeroPK();
			DocumentoNumero num = new DocumentoNumero();

			id.setIddocumento(getDocumento().getIddocumento());
			id.setIdptoemision(this.numeradoresTM.getValueAt(i, 0).toString());
			id.setSerie(this.numeradoresTM.getValueAt(i, 1).toString());

			num.setId(id);
			num.setNumero(this.numeradoresTM.getValueAt(i, 2).toString());
			getNumeradores().add(num);
		}

	}

	@Override
	public void llenar_datos() {
		numeradoresTM.limpiar();
		setNumeradores(new ArrayList<DocumentoNumero>());
		if (getDocumento() != null) {
			txtCodigo.setText(getDocumento().getIddocumento());
			txtDescripcion.setText(getDocumento().getDescripcion());
			setNumeradores(docnumDAO.getPorDocumento(getDocumento()));

			for (DocumentoNumero num : getNumeradores()) {
				numeradoresTM.addRow(new Object[] {
						num.getId().getIdptoemision(), num.getId().getSerie(),
						num.getNumero() });
			}
		} else {
			txtCodigo.setText("");
			txtDescripcion.setText("");
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
		for (Documento cuenta : getDocumentos()) {
			model.addRow(new Object[] { cuenta.getIddocumento(),
					cuenta.getDescripcion() });
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
		numeradoresTM.setEditar(true);
		btnILinea.setEnabled(true);
		btnBLinea.setEnabled(true);
		
	}

	@Override
	public void vista_noedicion() {
		txtCodigo.setEditable(false);
		txtDescripcion.setEditable(false);
		numeradoresTM.setEditar(false);
		btnILinea.setEnabled(false);
		btnBLinea.setEnabled(false);
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
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public void actualiza_objeto(Object entidad) {
		// TODO Auto-generated method stub

	}

	public DocumentoNumeroDAO getDocnumDAO() {
		return docnumDAO;
	}

	public void setDocnumDAO(DocumentoNumeroDAO docnumDAO) {
		this.docnumDAO = docnumDAO;
	}

	public NumeradorTableModel getNumeradoresTM() {
		return numeradoresTM;
	}

	public void setNumeradoresTM(NumeradorTableModel numeradoresTM) {
		this.numeradoresTM = numeradoresTM;
	}

	public List<DocumentoNumero> getNumeradores() {
		return numeradores;
	}

	public void setNumeradores(List<DocumentoNumero> numeradores) {
		this.numeradores = numeradores;
	}
}

class NumeradorTableModel extends DefaultTableModel {

	private static final long serialVersionUID = 1L;

	private boolean editar = false;

	public NumeradorTableModel() {
		addColumn("Pto. Emisión");
		addColumn("Serie");
		addColumn("Número");
	}

	public boolean isCellEditable(int row, int column) {
		return editar;
	}

	public void limpiar() {
		while (getRowCount() != 0) {
			removeRow(0);
		}
	}

	public void setEditar(boolean editar) {
		this.editar = editar;
	}

	public boolean getEditar() {
		return this.editar;
	}
}
