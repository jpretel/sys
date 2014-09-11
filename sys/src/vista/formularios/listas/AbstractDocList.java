package vista.formularios.listas;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import entity.Documento;
import vista.barras.BarraMaestro;
import vista.controles.ComboBox;
import vista.controles.DSGDatePicker;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Calendar;
import java.util.Date;
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
	private DSGDatePicker txtDesde;
	private DSGDatePicker txtHasta;
	protected JScrollPane pnlDocumentos = new JScrollPane();
	protected DSGTableList tblDocumentos;
	protected DSGTableModelList modelo_lista;
	
	private static final int _ancho = 20;
	private static final int _alto = 20;

	protected String[] cabeceras;
	
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
		getContentPane().add(pnlDocumentos, BorderLayout.CENTER);
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
		txtDesde.setDate(c.getTime());

		JLabel lblHasta = new JLabel("Hasta");

		txtHasta = new DSGDatePicker();
		txtHasta.setDate(new Date());

		lblDocumento = new JLabel("Documento");

		JLabel lblNmero = new JLabel("Correlativo");

		txtSerie = new JTextField(10);
		
		txtNumero = new JTextField(10);
		
		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Object entity = new Object();
				llenarLista(entity);
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
				btnEditar.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						editar();						
					}
				});
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

	public void init(AbstractDocForm obj, String opcion, Object entidad) {
		if (obj instanceof AbstractDocForm) {
			getDesktopPane().add(obj);
			if (opcion.equals("NUEVO"))
				obj.DoNuevo();
			if (opcion.equals("VISTA")) {
				obj.setEstado(opcion);
				obj.actualiza_objeto(entidad);
			}
			if (opcion.equals("EDICION")) {
				obj.editar();
				obj.actualiza_objeto(entidad);
			}
			try {
				obj.setSelected(true);
			} catch (PropertyVetoException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void llenarLista(Object documento){
		modelo_lista.limpiar();
		for (Object [] data : getData(0, 0,
				0, documento)) {
			modelo_lista.addRow(data);
		}
	}
	
	
	public Object RetornarPk() {
		Object id = null;
		if (tblDocumentos.getSelectedRow() >= 0) {
			id = modelo_lista.getValueAt(tblDocumentos.getSelectedRow(), 0);
		}
		return id;
	}
	
	public abstract Object[][] getData(int idesde, int ihasta, int numero,
			Object ingreso);
	
	public abstract void nuevo();
	
	public abstract void editar();
	
	public abstract void abrirFormulario(String estado);
	
	private JLabel getLabel() {
		if (label == null) {
			label = new JLabel("-");
		}
		return label;
	}
}
