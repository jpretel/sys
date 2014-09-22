package vista.formularios;

import java.util.Calendar;

import vista.controles.DSGInternalFrame;
import vista.controles.DSGTableModel;
import vista.contenedores.CntMoneda;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.JButton;

public class FrmTCambioas extends DSGInternalFrame {
	Calendar calendar = Calendar.getInstance();
	public FrmTCambioas() {
		initGUI();
		setTitle("Tipo de Cambio");
	}

	private void initGUI() {
		getContentPane().setLayout(null);

		this.cntMoneda = new CntMoneda();
		this.cntMoneda.setBounds(54, 11, 202, 22);
		getContentPane().add(this.cntMoneda);

		this.lblMoneda = new JLabel("Moneda");
		this.lblMoneda.setBounds(10, 15, 46, 14);
		getContentPane().add(this.lblMoneda);

		this.lblAnio = new JLabel("A\u00F1o");
		this.lblAnio.setBounds(10, 44, 46, 14);
		getContentPane().add(this.lblAnio);

		this.lblMes = new JLabel("Mes");
		this.lblMes.setBounds(114, 44, 46, 14);
		getContentPane().add(this.lblMes);
		
		tblDetalle = new JTable(new DSGTableModel(new String[] {"Fecha", "Combra", "Venta"}) {
			private static final long serialVersionUID = 1L;
			@Override
			public boolean evaluaEdicion(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public void addRow() {
				// TODO Auto-generated method stub
				
			}
		});
		
		this.scrollPane = new JScrollPane(tblDetalle);
		this.scrollPane.setBounds(10, 84, 363, 233);
		getContentPane().add(this.scrollPane);

		this.spinner = new JSpinner();
		this.spinner.setModel(new SpinnerNumberModel(calendar.get(Calendar.YEAR), 1900, 3000, 1));
		this.spinner.setBounds(38, 41, 66, 20);
		getContentPane().add(this.spinner);

		this.comboBox = new JComboBox<String>();
		this.comboBox.setModel(new DefaultComboBoxModel<String>(new String[] { "Enero",
				"Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio",
				"Agosto", "Setiembre", "Octubre", "Noviembre", "Diciembre" }));
		this.comboBox.setBounds(143, 41, 124, 20);
		comboBox.setSelectedIndex(calendar.get(Calendar.MONTH));
		getContentPane().add(this.comboBox);
		
		this.btnActualizar = new JButton("Actualizar");
		this.btnActualizar.setBounds(284, 40, 89, 23);
		getContentPane().add(this.btnActualizar);
		
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected CntMoneda cntMoneda;
	protected JLabel lblMoneda;
	protected JLabel lblAnio;
	protected JLabel lblMes;
	protected JScrollPane scrollPane;
	protected JSpinner spinner;
	protected JComboBox<String> comboBox;
	protected JButton btnActualizar;
	protected JTable tblDetalle;
}
