package vista.formularios.listas;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.EtchedBorder;

import entity.Documento;
import vista.barras.BarraMaestro;
import vista.controles.ComboBox;
import vista.controles.DSGDatePicker;
import vista.controles.IDocumentoDAO;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.beans.PropertyVetoException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.TitledBorder;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.LayoutStyle.ComponentPlacement;

public abstract class AbstractDocList extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected ComboBox<Documento> cboDocumento = new ComboBox<Documento>();
	protected JTextField txtSerie;
	protected JTextField txtNumero;
	private IDocumentoDAO documentoDAO;
	private DSGDatePicker txtDesde;
	private DSGDatePicker txtHasta;
	private JScrollPane pnlDocumentos = new JScrollPane();
	private JTable tblDocumentos;
	protected DefaultTableModel modelo_lista;
	private List<Object[]> datos;

	private static final int _ancho = 20;
	private static final int _alto = 20;

	protected String[] cabeceras = new String[] { "Documento", "Serie",
			"Numero" };
	
	protected JLabel lblDocumento;
	private JTextField textField;
	private JLabel label;

	/**
	 * Crea la lista del documento con los filtros por defecto.
	 */
	public AbstractDocList(String titulo) {
		setTitle(titulo);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setVisible(true);
		setResizable(true);
		//getContentPane().setLayout(new BorderLayout(0, 0));
		//calendar.set(Calendar.DATE, 1);
		getContentPane().add(pnlDocumentos, BorderLayout.CENTER);

		tblDocumentos = new JTable();
		tblDocumentos.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null,
				null));
		tblDocumentos.setModel(new DefaultTableModel(new Object[][] {},
				cabeceras));
		pnlDocumentos.setViewportView(tblDocumentos);

		JPanel pnlFiltros = new JPanel();

		pnlFiltros.setPreferredSize(new Dimension(0, 70));

		pnlFiltros
				.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		getContentPane().add(pnlFiltros, BorderLayout.NORTH);

		JLabel lblDesde = new JLabel("Desde");

		txtDesde = new DSGDatePicker();
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.set(Calendar.DAY_OF_MONTH, 1);
		txtDesde.setDate(c.getTime());// .setDate(Calendar.DAY_OF_MONTH);

		JLabel lblHasta = new JLabel("Hasta");

		txtHasta = new DSGDatePicker();
		txtHasta.setDate(new Date());
		//txtHasta.setDate(calendar.getTime());

		lblDocumento = new JLabel("Documento");

		JLabel lblNmero = new JLabel("Correlativo");

		txtSerie = new JTextField(10);
		
		txtNumero = new JTextField(10);
		
		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				llenarLista();
			}
		});
		
		this.textField = new JTextField(10);
		GroupLayout gl_pnlFiltros = new GroupLayout(pnlFiltros);
		gl_pnlFiltros.setHorizontalGroup(
			gl_pnlFiltros.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlFiltros.createSequentialGroup()
					.addGap(22)
					.addGroup(gl_pnlFiltros.createParallelGroup(Alignment.LEADING)
						.addComponent(txtDesde, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDesde, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))
					.addGap(26)
					.addGroup(gl_pnlFiltros.createParallelGroup(Alignment.LEADING)
						.addComponent(txtHasta, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblHasta, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
					.addGap(36)
					.addGroup(gl_pnlFiltros.createParallelGroup(Alignment.LEADING)
						.addComponent(lblDocumento)
						.addComponent(cboDocumento, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE))
					.addGap(12)
					.addGroup(gl_pnlFiltros.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNmero, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtNumero, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_pnlFiltros.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlFiltros.createSequentialGroup()
							.addGap(9)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE))
						.addComponent(getLabel(), GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
					.addGap(57)
					.addComponent(btnActualizar, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE))
		);
		gl_pnlFiltros.setVerticalGroup(
			gl_pnlFiltros.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlFiltros.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNmero)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_pnlFiltros.createParallelGroup(Alignment.LEADING)
						.addComponent(txtNumero, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
					.addGap(11))
				.addGroup(Alignment.TRAILING, gl_pnlFiltros.createSequentialGroup()
					.addContainerGap(31, Short.MAX_VALUE)
					.addComponent(btnActualizar)
					.addGap(13))
				.addGroup(gl_pnlFiltros.createSequentialGroup()
					.addGap(13)
					.addComponent(lblDesde)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtDesde, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
					.addGap(11))
				.addGroup(gl_pnlFiltros.createSequentialGroup()
					.addGap(11)
					.addGroup(gl_pnlFiltros.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_pnlFiltros.createSequentialGroup()
							.addComponent(lblHasta)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtHasta, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pnlFiltros.createSequentialGroup()
							.addComponent(lblDocumento)
							.addGap(7)
							.addComponent(cboDocumento, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)))
					.addGap(12))
				.addGroup(gl_pnlFiltros.createSequentialGroup()
					.addGap(35)
					.addComponent(getLabel())
					.addGap(18))
		);
		pnlFiltros.setLayout(gl_pnlFiltros);

		JPanel pnlOpciones = new JPanel();
		pnlOpciones.setPreferredSize(new Dimension(120, 0));
		pnlOpciones.setBorder(new TitledBorder(null, "Opciones",
				TitledBorder.CENTER, TitledBorder.TOP, null, null));
		getContentPane().add(pnlOpciones, BorderLayout.WEST);
		pnlOpciones
				.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("pref:grow"),},
			new RowSpec[] {
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.PREF_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.PREF_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));

		JButton btnCrear = new JButton("Crear");	
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				nuevo();
			}
		});
		pnlOpciones.add(btnCrear, "1, 2, fill, fill");

		btnCrear.setIcon(new ImageIcon(new ImageIcon(BarraMaestro.class
				.getResource("/main/resources/iconos/nuevo.png")).getImage()
				.getScaledInstance(_ancho, _alto, java.awt.Image.SCALE_DEFAULT)));
				
				JButton btnEditar = new JButton("Editar");
				btnEditar.setIcon(new ImageIcon(AbstractDocList.class.getResource("/main/resources/iconos/editar_lista3.png")));
				pnlOpciones.add(btnEditar, "1, 4");
								
								JButton btnImprimir = new JButton("Imprimir");
								btnImprimir.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent arg0) {
									}
								});
								pnlOpciones.add(btnImprimir, "1, 6");
										
												JButton btnVer = new JButton("Abrir");
												pnlOpciones.add(btnVer, "1, 8, fill, fill");
												
														btnVer.setIcon(new ImageIcon(new ImageIcon(BarraMaestro.class
																.getResource("/main/resources/iconos/abrir.png")).getImage()
																.getScaledInstance(_ancho, _alto, java.awt.Image.SCALE_DEFAULT)));
		setBounds(100, 100, 763, 325);
	}

	/*
	 * *
	 * Metodo que retorna cabeceras de la lista
	 * 
	 * @return Cabeceras que se mostrarán en la cabecera de la lista / public
	 * abstract String[] getCabeceras();
	 */
	/**
	 * Llena la lista del formulario con los datos de los documentos, usa un
	 * tipo de datos <b> List </b> que deberá ser llenado desde se tomará la
	 * posicion cero para el Objeto de tipo <b>ID</b> de la entidad, para poder
	 * llamarla en el formulario de edición una clase DAO que herede de
	 * <b>Abstract DAO</b> en caso de agregar más parametros sobreescribir esta
	 * clase.
	 */
	public void llenarLista() {
		int columnas;
		Calendar desde, hasta;
		String iddocumento, serie, numero;
		desde = Calendar.getInstance();
		hasta = Calendar.getInstance();
		desde.setTime(txtDesde.getDate());
		hasta.setTime(txtHasta.getDate());
		iddocumento = cboDocumento.getModel()
				.getElementAt(cboDocumento.getSelectedIndex()).getObject()
				.getIddocumento();
		//serie = txtSerie.getText();
		serie = "";
		numero = txtNumero.getText();

		columnas = cabeceras.length;

		datos = getDocumentoDAO().getListaDocumentos(
				desde.get(Calendar.DAY_OF_MONTH), desde.get(Calendar.MONTH),
				desde.get(Calendar.YEAR), hasta.get(Calendar.DAY_OF_MONTH),
				hasta.get(Calendar.MONTH), hasta.get(Calendar.YEAR),
				iddocumento, serie, numero);

		modelo_lista = new DefaultTableModel();

		for (String cabecera : cabeceras) {
			modelo_lista.addColumn(cabecera);
		}

		for (Object[] dato : datos) {
			Object[] row = new Object[columnas];
			for (int i = 0; i < columnas; i++) {
				row[i] = dato[i + 1];
			}
			modelo_lista.addRow(row);
		}
	}

	public IDocumentoDAO getDocumentoDAO() {
		return documentoDAO;
	}

	/**
	 * @param documentoDAO
	 *            the documentoDAO to set
	 */
	public void setDocumentoDAO(IDocumentoDAO documentoDAO) {
		this.documentoDAO = documentoDAO;
	}
	
	public void init(AbstractDocForm obj, String opcion, Object entidad) {
		if (obj instanceof AbstractDocForm) {
			getDesktopPane().add(obj);
			if (opcion.equals("NUEVO"))
				obj.DoNuevo();
			if (opcion.equals("VISTA")) {
				obj.actualiza_objeto(entidad);
			}
			if (opcion.equals("EDICION")) {
				obj.actualiza_objeto(entidad);
				obj.editar();
			}
			try {
				obj.setSelected(true);
			} catch (PropertyVetoException e) {
				e.printStackTrace();
			}
		}
	}
	
	public abstract void nuevo();
	private JLabel getLabel() {
		if (label == null) {
			label = new JLabel("-");
		}
		return label;
	}
}
