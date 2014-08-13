package vista.formularios;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import vista.contenedores.txtidformulario;
import vista.controles.DSGTableModel;
import vista.utilitarios.MaestroTableModel;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import dao.DocFormularioDAO;
import dao.DocumentoDAO;
import dao.DocumentoNumeroDAO;
import dao.SysOpcionDAO;
import entity.DocFormulario;
import entity.Documento;
import entity.DocumentoNumero;
import entity.DocumentoNumeroPK;
import entity.SysOpcion;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTabbedPane;
import javax.swing.ImageIcon;

import java.awt.Insets;

public class FrmDocumento extends AbstractMaestro {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTable tblLista;	

	private JTable tblnumeradores;
	private JTable tblFormularios;
	private JTextField txtCodigo;
	private JTextField txtDescripcion;
	private Documento documento;

	private List<Documento> documentos;
	private List<DocumentoNumero> numeradores;
	private List<DocFormulario> formularios;
	private DocumentoDAO documentoDAO = new DocumentoDAO();

	private DocumentoNumeroDAO docnumDAO = new DocumentoNumeroDAO();
	private DocFormularioDAO docFormDAO = new DocFormularioDAO(); 
	private JButton btnILinea;
	private JButton btnBLinea;
	private JTextField txtCodigoSunat;

	public FrmDocumento() {
		super("Documentos");

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 199, 288);

		tblLista = new JTable(new MaestroTableModel());
		scrollPane.setViewportView(tblLista);
		tblLista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);	
		

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
		btnILinea = new JButton("I LINEA");
		btnILinea.setMargin(new Insets(0, 0, 0, 0));
		btnILinea.setAlignmentY(0.0f);
		btnILinea.setIcon(new ImageIcon(FrmDocumento.class.getResource("/main/resources/iconos/table_row_insert.png")));
		btnILinea.setBounds(375, 65, 83, 37);
		btnBLinea = new JButton("B Linea");
		btnBLinea.setBounds(446, 15, 83, 20);
		pnlContenido.setLayout(null);
		pnlContenido.add(scrollPane);
		pnlContenido.add(lblDescripcin);
		pnlContenido.add(lblCdigo);
		pnlContenido.add(txtDescripcion);
		pnlContenido.add(txtCodigo);
		pnlContenido.add(btnILinea);
		pnlContenido.add(btnBLinea);
		final JTabbedPane tabPanel = new JTabbedPane(JTabbedPane.TOP);
		tabPanel.setBounds(215, 106, 314, 193);

		pnlContenido.add(tabPanel);
		
				JScrollPane scrollPaneNum = new JScrollPane();
				tabPanel.addTab("Detalle", null, scrollPaneNum, null);
				
						tblnumeradores = new JTable(new DSGTableModel(new String[] {"Pto. Emisi�n", "Serie", "N�mero"}) {
							private static final long serialVersionUID = 1L;
							@Override
							public boolean evaluaEdicion(int row, int column) {
								return getEditar();
							}
						});
						scrollPaneNum.setViewportView(tblnumeradores);
						tblnumeradores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
						
				JScrollPane scrollPaneFormularios = new JScrollPane();
				tabPanel.addTab("Formularios Asociados", null, scrollPaneFormularios, null);
						
						tblFormularios = new JTable(new DSGTableModel(new String[]{"idFormulario","Formulario","Activo"}){
							private static final long serialVersionUID = 1L;
							@Override
							public boolean evaluaEdicion(int row, int column) {
								return getEditar();
							}
						});
						
						scrollPaneFormularios.setViewportView(tblFormularios);
						tblFormularios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
						
						final txtidformulario txtidform = new txtidformulario();		
						DefaultCellEditor editor = new DefaultCellEditor(txtidform);
						tblFormularios.getColumn("idFormulario").setCellEditor(editor);
						tblFormularios.getColumn("idFormulario").setPreferredWidth(120);
						
						txtidform.addFocusListener(new FocusAdapter() {
							@Override
							public void focusLost(FocusEvent arg0) {
								getFormularioTM().setValueAt(txtidform.getDescripcion(), tblFormularios.getSelectedRow(), 1);
							}
						});
						
						
						txtidform.addKeyListener(new KeyAdapter() {
							@Override
							public void keyPressed(KeyEvent ev) {	
								if(ev.getKeyCode() != 9)
									txtidform.mostrar(txtidform.getText());
							}
						});
						
						JLabel lblCodigoSunat = new JLabel("Codigo Sunat");
						lblCodigoSunat.setBounds(213, 68, 66, 14);
						pnlContenido.add(lblCodigoSunat);
						
						txtCodigoSunat = new JTextField();
						txtCodigoSunat.setColumns(10);
						txtCodigoSunat.setBounds(298, 65, 67, 20);
						pnlContenido.add(txtCodigoSunat);
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
		btnILinea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(isValidaVista()){
					final Object fila[] = { "", "", "" };
					if (tabPanel.getSelectedIndex() == 0)
						getNumeradorTM().addRow(fila);
					else 
						getFormularioTM().addRow(fila);
				}else{
					JOptionPane.showMessageDialog(null, "Faltan datos en la Cabecera");
				}
			}
		});
		
		btnBLinea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int ind = tblnumeradores.getSelectedRow();
				if (ind >= 0)
					getNumeradorTM().removeRow(ind);
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
	
		for(DocFormulario form : getFormularios()){
			docFormDAO.create(form);
		}
	}

	@Override
	public void llenarDesdeVista() {
		getDocumento().setIddocumento(this.txtCodigo.getText().trim());
		getDocumento().setDescripcion(this.txtDescripcion.getText().trim());
		getDocumento().setCodSunat(this.txtCodigoSunat.getText().trim());
		setNumeradores(new ArrayList<DocumentoNumero>());
		setFormularios(new ArrayList<DocFormulario>());

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
		
		for(int i = 0;i < getFormularioTM().getRowCount();i++){
			DocFormulario docFormulario = new DocFormulario();
			docFormulario.setDocumento(getDocumento());
			docFormulario.setIddocumento(getDocumento().getIddocumento());
			docFormulario.setEstado(Integer.parseInt(getFormularioTM().getValueAt(i, 2).toString()));
			docFormulario.setOpcion(getFormularioTM().getValueAt(i, 0).toString());
			getFormularios().add(docFormulario);
		}

	}

	@Override
	public void llenar_datos() {
		SysOpcionDAO sysopcionDAO = new SysOpcionDAO();
		SysOpcion sysopcion;
		getNumeradorTM().limpiar();
		getFormularioTM().limpiar();
		setNumeradores(new ArrayList<DocumentoNumero>());
		setFormularios(new ArrayList<DocFormulario>());
		if (getDocumento() != null) {
			txtCodigo.setText(getDocumento().getIddocumento());
			txtDescripcion.setText(getDocumento().getDescripcion());
			txtCodigoSunat.setText(getDocumento().getCodSunat());
			setNumeradores(docnumDAO.getPorDocumento(getDocumento()));
			setFormularios(docFormDAO.getPorDocumento(getDocumento()));
			for (DocumentoNumero num : getNumeradores()) {
				getNumeradorTM().addRow(new Object[] {
						num.getId().getIdptoemision(), num.getId().getSerie(),
						num.getNumero() });
			}
			for (DocFormulario form: getFormularios()){
				sysopcion = sysopcionDAO.getPorOpcion(form.getOpcion().trim());
				getFormularioTM().addRow(new Object[]{
						form.getOpcion(),sysopcion.getDescripcion(),form.getEstado()
				});
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
		if (this.txtCodigoSunat.getText().trim().isEmpty())
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
		txtCodigoSunat.setEditable(true);
		getNumeradorTM().setEditar(true);
		getFormularioTM().setEditar(true);
		btnILinea.setEnabled(true);
		btnBLinea.setEnabled(true);

	}

	@Override
	public void vista_noedicion() {
		txtCodigo.setEditable(false);
		txtDescripcion.setEditable(false);
		txtCodigoSunat.setEditable(false);
		getNumeradorTM().setEditar(false);
		getFormularioTM().setEditar(false);
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
	
	public List<DocFormulario> getFormularios() {
		return formularios;
	}

	public void setFormularios(List<DocFormulario> formularios) {
		this.formularios = formularios;
	}
	
	public DSGTableModel getNumeradorTM(){
		return ((DSGTableModel) tblnumeradores.getModel());
	}
	
	public DSGTableModel getFormularioTM(){
		return ((DSGTableModel) tblFormularios.getModel());
	}
	@Override
	public void eliminar() {
		if (getDocumento() != null) {
			getDocnumDAO().borrarPorDocumento(getDocumento());
			getDocumentoDAO().remove(getDocumento());
		}
	}

}