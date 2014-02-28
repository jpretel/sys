package vista;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JToolBar;

import java.awt.Label;

import javax.swing.JSeparator;

import vista.barras.BarraMaestro;
import vista.formularios.AbstractMaestro;
import vista.utilitarios.menus;



import javax.swing.border.LineBorder;

import dao.MenuDAO;
import entity.Menu;

import java.awt.Color;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Home extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	private BarraMaestro barraMaestro;

	public BarraMaestro getBarraMaestro() {
		return barraMaestro;
	}

	public void setBarraMaestro(BarraMaestro barraMaestro) {
		this.barraMaestro = barraMaestro;
	}

	private JInternalFrame formularioActivo;	
	final JDesktopPane desktopPane = new JDesktopPane();
	private String vtOpciones[][];
	MenuDAO m = new MenuDAO();
	Menu menu = new Menu();

	
	public Home(String titulo) {
		super(titulo);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		barraMaestro = new BarraMaestro();
		barraMaestro.setVisible(false);

		desktopPane.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {
				
			}
		});
		
		desktopPane.setBorder(new LineBorder(new Color(0, 0, 0)));		
		probarMenus();
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(barraMaestro, BorderLayout.NORTH);	

		JToolBar tlbPie = new JToolBar();
		tlbPie.setRollover(true);
		tlbPie.setFloatable(false);
		contentPane.add(tlbPie, BorderLayout.SOUTH);

		Label label = new Label("Cod Usuario");
		tlbPie.add(label);

		JSeparator separator = new JSeparator();
		tlbPie.add(separator);
		
		//Vtr Fecha Sistema
		Date fecha=new Date(); 
        SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");       
		Label lblFecha = new Label();
		lblFecha.setText(sdf.format(fecha));
		lblFecha.setAlignment(Label.RIGHT);
		tlbPie.add(lblFecha);		
		contentPane.add(desktopPane, BorderLayout.CENTER);		

	}

	private void probarMenus() {				
		List<Menu> lista = new ArrayList<Menu>();		
		lista = m.findAll();	
		vtOpciones = new String[lista.size()][3];
		for(int i = 0; i < lista.size();i++){
			vtOpciones[i][0] = lista.get(i).getPosicion();
			vtOpciones[i][1] = lista.get(i).getDescripcion();					
		};					
		 menus mnPrin = new menus(vtOpciones, "AccionesMenu");
		 setJMenuBar(mnPrin);
		
	}
	
	 public void AccionesMenu(String Opc) {
		 JInternalFrame FormularioSeleccionado = new JInternalFrame();		 
		 menus objMenu = new menus(getBarraMaestro());
		 menu = m.RetornarIndex(Opc);		 
		 FormularioSeleccionado = objMenu.getFormulario(menu.getIndiceUbi());	
		 setFormularioActivo(FormularioSeleccionado);
	 }

	public void setFormularioActivo(JInternalFrame formularioActivo) {
		this.formularioActivo = formularioActivo;		
		if (formularioActivo instanceof AbstractMaestro) {			
			getDesktopPane().add(this.formularioActivo);
			try {
				this.formularioActivo.setSelected(true);
			} catch (PropertyVetoException e) {
				e.printStackTrace();
			}
		}		

	}
	
	public JDesktopPane getDesktopPane() {
		return desktopPane;
	}
}
