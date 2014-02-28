package vista.contenedores;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public abstract class AbstractCntBuscar extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public  JTextField txtCodigo;
	public JTextField txtDescripcion;
	public JButton btnBuscar;

	public AbstractCntBuscar() {
		this.setForeground(Color.LIGHT_GRAY);
		FlowLayout flowLayout = (FlowLayout) this.getLayout();
		flowLayout.setVgap(0);
		flowLayout.setHgap(0);
		flowLayout.setAlignment(FlowLayout.LEFT);
		this.setBounds(152, 11, 262, 23);
		
		txtCodigo = new JTextField();
		txtCodigo.setColumns(5);
		add(txtCodigo);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setEnabled(false);
		txtDescripcion.setColumns(20);
		add(txtDescripcion);
		
		btnBuscar = new JButton("");	
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				buscar();				
			}
		});
		btnBuscar.setIcon(new ImageIcon(AbstractCntBuscar.class.getResource("/main/resources/iconos/vistaprevia.png")));
		btnBuscar.setVerticalAlignment(SwingConstants.BOTTOM);
		btnBuscar.setHorizontalAlignment(SwingConstants.LEFT);
		btnBuscar.setAlignmentY(0.0f);
		add(btnBuscar);
	}
	public abstract void buscar();


}
