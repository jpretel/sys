package vista.formularios;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import vista.contenedores.txtidformulario;
import vista.controles.DSGTableModel;
import vista.controles.JTextFieldLimit;
import vista.utilitarios.MaestroTableModel;
import vista.utilitarios.UtilMensajes;
import vista.utilitarios.editores.TableTextEditor;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import dao.DocFormularioDAO;
import dao.DocumentoDAO;
import dao.DocumentoNumeroDAO;
import dao.SysOpcionDAO;
import entity.DocFormulario;
import entity.Documento;
import entity.DocumentoNumero;
import entity.DocumentoNumeroPK;
import entity.SysOpcion;

import java.awt.ScrollPane;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTabbedPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class FrmDocumento extends AbstractMaestro {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTable tblLista;	
	private JTextField txtCodigo;
	private JTextField txtDescripcion;
	private Documento documento;
	private JTable tblnumeradores;
	private JScrollPane scrollPaneNum;
	private List<Documento> documentos;
	private List<DocumentoNumero> numeradores;
	private List<DocFormulario> formularios;
	private DocumentoDAO documentoDAO = new DocumentoDAO();
	private DefaultTableModel numerador;
	private DocumentoNumeroDAO docnumDAO = new DocumentoNumeroDAO();
	private DocFormularioDAO docFormDAO = new DocFormularioDAO(); 
	private JTextField txtCodigoSunat;
	private JTextField textField;
	private JTextField textField_1;
	
	public FrmDocumento() {
		super("Documentos");

		JScrollPane scrollPane = new JScrollPane();

		tblLista = new JTable(new MaestroTableModel());
		scrollPane.setViewportView(tblLista);
		tblLista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);	
		

		JLabel lblCdigo = new JLabel("Codigo");

		JLabel lblDescripcin = new JLabel("Descripci\u00F3n");

		txtCodigo = new JTextField();
		txtCodigo.setColumns(10);
		txtCodigo.setDocument(new JTextFieldLimit(3, true));
		
		txtDescripcion = new JTextField();
		txtDescripcion.setColumns(10);
		txtDescripcion.setDocument(new JTextFieldLimit(75, true));
		
		scrollPaneNum = new JScrollPane();
		numerador = new DefaultTableModel();		
		String columnNames[]=new String[] {"Serie" , "Numero"};
		numerador.setColumnIdentifiers(columnNames);
		tblnumeradores = new JTable(new DSGTableModel(new String[] {
				"Serie" , "Numero" }) {
			private static final long serialVersionUID = 1L;
			@Override
			public boolean evaluaEdicion(int row, int column) {
				return getEditar();
			}

			@Override
			public void addRow() {
				addRow(new Object[] { "", "" });
			}
		});
		
		scrollPaneNum.setViewportView(tblnumeradores);
		tblnumeradores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		getNumeradorTM().setNombre_detalle("Numeradores");
		getNumeradorTM().setObligatorios(0, 1, 2);
		getNumeradorTM().setRepetidos(0);
		TableColumnModel cModel = tblnumeradores.getColumnModel();
		cModel.getColumn(0).setCellEditor(new TableTextEditor(15, true));
		

				
		JLabel lblCodigoSunat = new JLabel("Codigo Sunat");
						
		txtCodigoSunat = new JTextField();
		txtCodigoSunat.setColumns(10);
		txtCodigoSunat.setDocument(new JTextFieldLimit(4, true));
						
										JLabel lblFormulario = new JLabel("Formulario");
						
						JLabel lblNomenclatura = new JLabel("Nomenclatura");
						
						textField = new JTextField();
						textField.setColumns(10);
						
						textField_1 = new JTextField();
						textField_1.setColumns(10);
						
						GroupLayout groupLayout = new GroupLayout(pnlContenido);
						groupLayout.setHorizontalGroup(
							groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addContainerGap()
									.addComponent(scrollPaneNum, GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblCdigo, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblDescripcin, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblFormulario, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNomenclatura, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblCodigoSunat, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(txtDescripcion, GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
										.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 241, GroupLayout.PREFERRED_SIZE)
										.addComponent(txtCodigoSunat, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
										.addComponent(txtCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(textField, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE))
									.addContainerGap())
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(198)
									.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE)
									.addGap(10))
						);
						groupLayout.setVerticalGroup(
							groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addGroup(groupLayout.createSequentialGroup()
													.addGap(18)
													.addComponent(lblCdigo))
												.addGroup(groupLayout.createSequentialGroup()
													.addGap(15)
													.addComponent(txtCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
											.addPreferredGap(ComponentPlacement.RELATED)
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addGroup(groupLayout.createSequentialGroup()
													.addGap(3)
													.addComponent(lblDescripcin))
												.addComponent(txtDescripcion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
												.addComponent(lblFormulario)
												.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
												.addComponent(lblNomenclatura)
												.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
												.addComponent(lblCodigoSunat)
												.addComponent(txtCodigoSunat, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
											.addGap(18)
											.addComponent(scrollPaneNum, GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE))
										.addGroup(groupLayout.createSequentialGroup()
											.addContainerGap()
											.addComponent(scrollPaneNum, GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE)))
									.addContainerGap())
						);
						pnlContenido.setLayout(groupLayout);
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
		docFormDAO.borrarPorDocumento(getDocumento());
		for (DocumentoNumero num : getNumeradores()) {
			docnumDAO.create(num);
		}		
	}

	@Override
	public void llenarDesdeVista() {
		getDocumento().setIddocumento(this.txtCodigo.getText().trim());
		getDocumento().setDescripcion(this.txtDescripcion.getText().trim());
		getDocumento().setCodSunat(this.txtCodigoSunat.getText().trim());
		setNumeradores(new ArrayList<DocumentoNumero>());		
		for (int i = 0; i < getNumeradorTM().getRowCount(); i++) {
			DocumentoNumeroPK id = new DocumentoNumeroPK();
			DocumentoNumero num = new DocumentoNumero();
			id.setIddocumento(getDocumento().getIddocumento());
			id.setIdptoemision(getNumeradorTM().getValueAt(i, 0).toString());
			id.setSerie(getNumeradorTM().getValueAt(i, 1).toString());
			num.setId(id);
			num.setNumero(getNumeradorTM().getValueAt(i, 2).toString());
			getNumeradores().add(num);
		}

	}

	@Override
	public void llenar_datos() {
		SysOpcionDAO sysopcionDAO = new SysOpcionDAO();
		SysOpcion sysopcion;
		getNumeradorTM().limpiar();
		setNumeradores(new ArrayList<DocumentoNumero>());
		if (getDocumento() != null) {
			txtCodigo.setText(getDocumento().getIddocumento());
			txtDescripcion.setText(getDocumento().getDescripcion());
			txtCodigoSunat.setText(getDocumento().getCodSunat());
			setNumeradores(docnumDAO.getPorDocumento(getDocumento()));
			for (DocumentoNumero num : getNumeradores()) {
				getNumeradorTM().addRow(new Object[] {
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
		if (this.txtCodigo.getText().trim().isEmpty()) {
			UtilMensajes.mensaje_alterta("DATO_REQUERIDO", "Código");
			this.txtCodigo.requestFocus();
			return false;
		}
		
		if (getEstado().equals(NUEVO)) {
			if (getDocumentoDAO().find(this.txtCodigo.getText().trim()) != null) {
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
		
		if (this.txtCodigoSunat.getText().trim().isEmpty()){
			UtilMensajes.mensaje_alterta("DATO_REQUERIDO", "Codigo Sunat");
			this.txtDescripcion.requestFocus();
			return false;
		}
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
		txtCodigoSunat.setEditable(true);
		getNumeradorTM().setEditar(true);
		tblnumeradores.setEnabled(true);		
		scrollPaneNum.setEnabled(true);

	}

	@Override
	public void vista_noedicion() {
		txtCodigo.setEditable(false);
		txtDescripcion.setEditable(false);
		txtCodigoSunat.setEditable(false);
		getNumeradorTM().setEditar(false);
		tblnumeradores.setEnabled(false);	
		scrollPaneNum.setEnabled(false);
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

	public List<DocumentoNumero> getNumeradores() {
		return numeradores;
	}

	public void setNumeradores(List<DocumentoNumero> numeradores) {
		this.numeradores = numeradores;
	}
	
	public DSGTableModel getNumeradorTM(){
		return ((DSGTableModel)tblnumeradores.getModel());
	}
	
	
	
	@Override
	public void eliminar() {
		if (getDocumento() != null) {
			int seleccion = UtilMensajes.msj_error("ELIMINAR_REG");
			
			if (seleccion == 0){
				getDocnumDAO().borrarPorDocumento(getDocumento());
				getDocumentoDAO().remove(getDocumento());
				iniciar();
			}			
		}
	}
}