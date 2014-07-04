package vista.formularios;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import dao.UsuarioDAO;
import entity.Usuario;
import vista.MainFrame;
import core.security.Encryption;

public class login extends JPanel implements MouseListener{	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtUsuario = new JTextField(15);
	private JPasswordField txtClave = new JPasswordField(15);
	
	private JLabel lblUsuario = new JLabel("Usuario:");
	private JLabel lblClave =  new JLabel("Clave:");
	
	private JButton btnAceptar = new JButton("Aceptar");
	private JButton btnCancelar = new JButton("Cancelar");

	public GroupLayout groupLayout = new GroupLayout(this);
	
	public boolean cargar = true;
	
	public boolean isCargar() {
		return cargar;
	}

	public void setCargar(boolean cargar) {
		this.cargar = cargar;
	}

	public login(){	
		
		setLayout(groupLayout);
		
		groupLayout.setAutoCreateContainerGaps(true);
		groupLayout.setAutoCreateGaps(true);
		
		System.out.println("Probando Login");
		
		groupLayout.setHorizontalGroup(groupLayout.createSequentialGroup()
				.addGroup(groupLayout.createParallelGroup()
						.addComponent(lblUsuario)
						.addComponent(lblClave)
				)
				.addGroup(groupLayout.createParallelGroup()
						.addComponent(txtUsuario)
						.addComponent(txtClave)
						.addGroup(groupLayout.createSequentialGroup()
								.addComponent(btnAceptar)
								.addComponent(btnCancelar)								
						)											
				)
		);
		
		groupLayout.setVerticalGroup(groupLayout.createSequentialGroup()
				.addGroup(groupLayout.createParallelGroup()
						.addComponent(lblUsuario)
						.addComponent(txtUsuario)
				)
				.addGroup(groupLayout.createParallelGroup()
						.addComponent(lblClave)
						.addComponent(txtClave)
				)
				.addGroup(groupLayout.createParallelGroup()						
						.addComponent(btnAceptar)
						.addComponent(btnCancelar)	
				)
		);
		
		btnAceptar.addMouseListener(this);
		
		
	}
	
	@SuppressWarnings("deprecation")
	public void mouseClicked(MouseEvent evento) {
		
		UsuarioDAO uado = new UsuarioDAO();		
		String usu, clave;

		usu = Encryption.encrypt(txtUsuario.getText());
		clave = Encryption.encrypt(txtClave.getText());
		
		for(Usuario u : uado.findAll()){
			if(u.getEncUsu().equals(usu)){
				System.out.println("Usuario correcto");
				if(u.getEncClave().equals(clave)){
					System.out.println("Contraseña correcto");
					new MainFrame();
					break;
				}
			}else{
				System.out.println("Ususario o contra Invalida: ");
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	
}
