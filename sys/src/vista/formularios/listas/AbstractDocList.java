package vista.formularios.listas;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.EtchedBorder;

import entity.Documento;
import vista.barras.BarraMaestro;
import vista.controles.ComboBox;
import vista.controles.IDocumentoDAO;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.jdesktop.swingx.JXDatePicker;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.GridBagLayout;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import org.jdesktop.swingx.JXCollapsiblePane;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import org.jdesktop.swingx.JXTaskPaneContainer;

import javax.swing.BoxLayout;
import javax.swing.border.TitledBorder;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JCheckBox;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.CompoundBorder;
import javax.swing.border.BevelBorder;

public abstract class AbstractDocList extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ComboBox<Documento> cboDocumento = new ComboBox<Documento>();
	private JTextField txtSerie;
	private JTextField txtNumero;
	private IDocumentoDAO documentoDAO;
	private JXDatePicker txtDesde;
	private JXDatePicker txtHasta;
	private JScrollPane pnlDocumentos = new JScrollPane();
	private JTable tblDocumentos;
	private Calendar calendar;

	private String datePattern = "dd/MM/yyyy";
	private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

	private DefaultTableModel modelo_lista;
	private List<Object[]> datos;

	private static final int _ancho = 20;
	private static final int _alto = 20;

	/**
	 * Crea la lista del documento con los filtros por defecto.
	 */
	public AbstractDocList() {
		setTitle("Lista de Documentos");
		setIconifiable(true);
		setMaximizable(true);
		setClosable(true);
		getContentPane().setLayout(new BorderLayout(0, 0));
		calendar.set(Calendar.DATE, 1);
		getContentPane().add(pnlDocumentos, BorderLayout.CENTER);

		tblDocumentos = new JTable();
		tblDocumentos.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null,
				null));
		tblDocumentos.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Documento", "New column", "New column",
						"New column" }));
		pnlDocumentos.setViewportView(tblDocumentos);

		JPanel pnlFiltros = new JPanel();

		pnlFiltros.setPreferredSize(new Dimension(0, 70));

		pnlFiltros
				.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		getContentPane().add(pnlFiltros, BorderLayout.NORTH);

		JLabel lblDesde = new JLabel("Desde");

		txtDesde = new JXDatePicker();
		txtDesde.setFormats(dateFormatter);

		txtDesde.setDate(calendar.getTime());

		JLabel lblHasta = new JLabel("Hasta");

		txtHasta = new JXDatePicker();
		txtHasta.setFormats(dateFormatter);
		txtHasta.setDate(calendar.getTime());

		JLabel lblDocumento = new JLabel("Documento");

		JLabel lblSerie = new JLabel("Serie");

		txtSerie = new JTextField();
		txtSerie.setColumns(10);

		JLabel lblNmero = new JLabel("N\u00FAmero");

		txtNumero = new JTextField();
		txtNumero.setColumns(10);

		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				llenarLista();
			}
		});
		GroupLayout gl_pnlFiltros = new GroupLayout(pnlFiltros);
		gl_pnlFiltros
				.setHorizontalGroup(gl_pnlFiltros
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_pnlFiltros
										.createSequentialGroup()
										.addContainerGap(
												GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addGroup(
												gl_pnlFiltros
														.createParallelGroup(
																Alignment.TRAILING,
																false)
														.addGroup(
																gl_pnlFiltros
																		.createSequentialGroup()
																		.addComponent(
																				lblDesde,
																				GroupLayout.PREFERRED_SIZE,
																				45,
																				GroupLayout.PREFERRED_SIZE)
																		.addGap(46))
														.addGroup(
																gl_pnlFiltros
																		.createSequentialGroup()
																		.addComponent(
																				txtDesde,
																				GroupLayout.PREFERRED_SIZE,
																				91,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)))
										.addGroup(
												gl_pnlFiltros
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_pnlFiltros
																		.createSequentialGroup()
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				lblHasta,
																				GroupLayout.PREFERRED_SIZE,
																				37,
																				GroupLayout.PREFERRED_SIZE))
														.addGroup(
																gl_pnlFiltros
																		.createSequentialGroup()
																		.addGap(6)
																		.addComponent(
																				txtHasta,
																				GroupLayout.PREFERRED_SIZE,
																				90,
																				GroupLayout.PREFERRED_SIZE)))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												gl_pnlFiltros
														.createParallelGroup(
																Alignment.TRAILING,
																false)
														.addGroup(
																gl_pnlFiltros
																		.createSequentialGroup()
																		.addComponent(
																				lblDocumento,
																				GroupLayout.PREFERRED_SIZE,
																				65,
																				GroupLayout.PREFERRED_SIZE)
																		.addGap(36))
														.addGroup(
																gl_pnlFiltros
																		.createSequentialGroup()
																		.addComponent(
																				cboDocumento,
																				GroupLayout.PREFERRED_SIZE,
																				101,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												gl_pnlFiltros
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(
																lblSerie,
																GroupLayout.PREFERRED_SIZE,
																42,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																txtSerie,
																GroupLayout.PREFERRED_SIZE,
																61,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												gl_pnlFiltros
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(
																lblNmero,
																GroupLayout.PREFERRED_SIZE,
																51,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																txtNumero,
																GroupLayout.PREFERRED_SIZE,
																66,
																GroupLayout.PREFERRED_SIZE))
										.addGap(169)
										.addComponent(btnActualizar,
												GroupLayout.DEFAULT_SIZE, 93,
												Short.MAX_VALUE)
										.addContainerGap()));
		gl_pnlFiltros
				.setVerticalGroup(gl_pnlFiltros
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_pnlFiltros
										.createSequentialGroup()
										.addGroup(
												gl_pnlFiltros
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_pnlFiltros
																		.createSequentialGroup()
																		.addGap(11)
																		.addGroup(
																				gl_pnlFiltros
																						.createParallelGroup(
																								Alignment.BASELINE)
																						.addComponent(
																								lblHasta)
																						.addComponent(
																								lblDesde))
																		.addGap(7)
																		.addGroup(
																				gl_pnlFiltros
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addComponent(
																								txtHasta,
																								GroupLayout.PREFERRED_SIZE,
																								23,
																								GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								txtDesde,
																								GroupLayout.PREFERRED_SIZE,
																								23,
																								GroupLayout.PREFERRED_SIZE)))
														.addGroup(
																gl_pnlFiltros
																		.createSequentialGroup()
																		.addGap(11)
																		.addGroup(
																				gl_pnlFiltros
																						.createParallelGroup(
																								Alignment.BASELINE)
																						.addComponent(
																								lblDocumento)
																						.addComponent(
																								lblSerie))
																		.addGap(7)
																		.addGroup(
																				gl_pnlFiltros
																						.createParallelGroup(
																								Alignment.BASELINE)
																						.addComponent(
																								cboDocumento,
																								GroupLayout.PREFERRED_SIZE,
																								23,
																								GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								txtSerie,
																								GroupLayout.PREFERRED_SIZE,
																								23,
																								GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								txtNumero,
																								GroupLayout.PREFERRED_SIZE,
																								23,
																								GroupLayout.PREFERRED_SIZE)))
														.addGroup(
																gl_pnlFiltros
																		.createSequentialGroup()
																		.addGap(11)
																		.addComponent(
																				lblNmero)))
										.addContainerGap())
						.addGroup(
								Alignment.TRAILING,
								gl_pnlFiltros.createSequentialGroup()
										.addContainerGap(24, Short.MAX_VALUE)
										.addComponent(btnActualizar).addGap(19)));
		pnlFiltros.setLayout(gl_pnlFiltros);

		JPanel pnlOpciones = new JPanel();
		pnlOpciones.setPreferredSize(new Dimension(120, 0));
		pnlOpciones.setBorder(new TitledBorder(null, "Opciones", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		getContentPane().add(pnlOpciones, BorderLayout.WEST);
		pnlOpciones
				.setLayout(new FormLayout(new ColumnSpec[] { ColumnSpec
						.decode("pref:grow"), }, new RowSpec[] {
						FormFactory.LINE_GAP_ROWSPEC, FormFactory.PREF_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC, FormFactory.PREF_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC, }));

		JButton btnCrear = new JButton("Crear");
		pnlOpciones.add(btnCrear, "1, 2, fill, fill");

		btnCrear.setIcon(new ImageIcon(new ImageIcon(BarraMaestro.class
				.getResource("/main/resources/iconos/nuevo.png")).getImage()
				.getScaledInstance(_ancho, _alto, java.awt.Image.SCALE_DEFAULT)));

		JButton btnVer = new JButton("Abrir");
		pnlOpciones.add(btnVer, "1, 4, fill, fill");

		btnVer.setIcon(new ImageIcon(new ImageIcon(BarraMaestro.class
				.getResource("/main/resources/iconos/abrir.png")).getImage()
				.getScaledInstance(_ancho, _alto, java.awt.Image.SCALE_DEFAULT)));
	}

	/**
	 * Metodo que retorna cabeceras de la lista
	 * 
	 * @return Cabeceras que se mostrarán en la cabecera de la lista
	 */
	public abstract String[] getCabeceras();

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
		serie = txtSerie.getText();
		numero = txtNumero.getText();

		String[] cabeceras = getCabeceras();

		columnas = cabeceras.length;

		datos = getDocumentoDAO().getListaDocumentos(
				desde.get(Calendar.DAY_OF_MONTH), desde.get(Calendar.MONTH),
				desde.get(Calendar.YEAR), hasta.get(Calendar.DAY_OF_MONTH),
				hasta.get(Calendar.MONTH), hasta.get(Calendar.YEAR),
				iddocumento, serie, numero);

		modelo_lista = new DefaultTableModel();

		for (String cabecera : getCabeceras()) {
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

	/**
	 * @return the documentoDAO
	 */
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

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
		}
	}
}
