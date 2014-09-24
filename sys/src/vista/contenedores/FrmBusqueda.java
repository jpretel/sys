package vista.contenedores;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import vista.controles.ModalInternalFrame;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class FrmBusqueda<T> extends ModalInternalFrame {
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private List<T> data;
	private Object obj;

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public FrmBusqueda() {
		setClosable(true);
		setTitle("Busqueda");
		getContentPane().setLayout(null);

		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(10, 11, 87, 20);
		getContentPane().add(comboBox);

		comboBox.addItem("Codigo");
		comboBox.addItem("Descripcion");

		textField = new JTextField();
		textField.setBounds(107, 11, 189, 20);
		getContentPane().add(textField);
		textField.setColumns(10);

		JButton btnNewButton = new JButton("Buscar");
		
		btnNewButton.setBounds(360, 10, 69, 23);
		getContentPane().add(btnNewButton);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null, e.getClickCount());

			}
		});
		scrollPane.setBounds(10, 43, 414, 197);
		getContentPane().add(scrollPane);

		final DefaultTableModel modelo = new DefaultTableModel();
		final JTable tblLista = new JTable(modelo);
		
		scrollPane.setViewportView(tblLista);
		tblLista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JButton btnAceptar = new JButton("Aceptar");
		
		btnAceptar.setBounds(337, 251, 87, 23);
		getContentPane().add(btnAceptar);
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}
}
