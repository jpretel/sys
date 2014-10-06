package vista.controles;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JWindow;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import vista.Sys;
import vista.controles.FindButton;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.ScrollPane;
import java.awt.Window;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CntReferenciaDoc extends JPanel implements FocusListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public DSGTextFieldCorrelativo txtSerie;
	public DSGTextFieldCorrelativo txtNumero;
	public FindButton btnBuscar;
	public ViewButton btnVer;

	private JWindow refWindow;
	private JScrollPane scrollPane;
	private JTable table;
	private String[] cabeceras;
	private Object[][] data;

	/**
	 * @wbp.parser.constructor
	 */
	public CntReferenciaDoc(String[] cabeceras, int[] anchos) {
		this.cabeceras = cabeceras;
		this.data = data;
		setForeground(Color.LIGHT_GRAY);
		this.setBounds(152, 11, 180, 20);
		GridBagLayout gridBagLayout = new GridBagLayout();

		gridBagLayout.columnWidths = new int[] { 46, 94, 20, 20, 0 };
		gridBagLayout.rowHeights = new int[] { 20, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 1.0, 0.0, 0.0,
				Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		txtSerie = new DSGTextFieldCorrelativo(4);

		GridBagConstraints gbc_txtCodigo = new GridBagConstraints();

		gbc_txtCodigo.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCodigo.gridx = 0;
		gbc_txtCodigo.gridy = 0;
		add(txtSerie, gbc_txtCodigo);

		txtNumero = new DSGTextFieldCorrelativo(8);
		GridBagConstraints gbc_txtDescripcion = new GridBagConstraints();
		gbc_txtDescripcion.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtDescripcion.gridx = 1;
		gbc_txtDescripcion.gridy = 0;
		add(txtNumero, gbc_txtDescripcion);

		btnBuscar = new FindButton();
		this.btnBuscar.setFocusable(false);

		GridBagConstraints gbc_btnBuscar = new GridBagConstraints();
		gbc_btnBuscar.anchor = GridBagConstraints.EAST;
		gbc_btnBuscar.gridx = 2;
		gbc_btnBuscar.gridy = 0;
		add(btnBuscar, gbc_btnBuscar);

		btnVer = new ViewButton();
		this.btnVer.setFocusable(false);

		GridBagConstraints gbc_btnVer = new GridBagConstraints();
		gbc_btnVer.anchor = GridBagConstraints.EAST;
		gbc_btnBuscar.gridx = 3;
		gbc_btnBuscar.gridy = 0;
		add(btnVer, gbc_btnVer);
		
		refWindow = new JWindow((Window) Sys.mainF);
		refWindow.setOpacity(0.95f);

		scrollPane = new JScrollPane();
		refWindow.getContentPane().add(scrollPane);
		table = new JTable();
		scrollPane.setViewportView(table);

		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		table.addFocusListener(this);
		txtNumero.addFocusListener(this);
		txtSerie.addFocusListener(this);
		
		btnBuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (isEnabled()) {
					buscarReferencia();
				}
			}
		});
		
		btnVer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (isEnabled()) {
					if (refWindow.isVisible())
						refWindow.setVisible(false);
					else
						mostrarReferencias();
				}
			}
		});
	}

	public CntReferenciaDoc() {
		this(new String[] { "Doc.", "Correlativo", "Fecha" }, new int[] { 90,
				200, 200 });
		setBorder(null);
	}

	public void mostrarReferencias() {

		DefaultTableModel model = new DefaultTableModel(data, cabeceras) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int rowIndex, int vColIndex) {
				return false;
			}
		};

		table.setModel(model);

		int windowX = getLocationOnScreen().x;
		int windowY = getLocationOnScreen().y + getHeight();

		refWindow.setLocation(windowX, windowY);
		refWindow.setMinimumSize(new Dimension(getWidth(), 170));
		refWindow.setAutoRequestFocus(false);
		refWindow.setVisible(true);
	}

	@Override
	public void focusGained(FocusEvent e) {
		boolean band = false;
		if (e.getComponent() == table) {
			band = true;
		}
		if (!band) {
			refWindow.setVisible(false);
		}
	}

	@Override
	public void focusLost(FocusEvent e) {

		boolean band = false;
		if ((e.getComponent() == table && (e.getOppositeComponent() == this.txtNumero || e.getOppositeComponent() == this.txtSerie))
				|| (e.getComponent() == this && e.getOppositeComponent() == table)) {
			band = true;
		}
		if (!band) {
			refWindow.setVisible(false);
		}
	}
	
	public void buscarReferencia() {
		System.out.println("Sobreescribir el método: buscarReferencia()");
	}
}