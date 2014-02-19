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
import vista.utils.menus;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.border.LineBorder;

import java.awt.Color;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
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

	public Home(String titulo) {
		super(titulo);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 639, 439);

		barraMaestro = new BarraMaestro();
		barraMaestro.setVisible(false);

		desktopPane.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {

			}
		});

		desktopPane.setBorder(new LineBorder(new Color(0, 0, 0)));

		// Aqui va el menu
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

		// Vtr Fecha Sistema
		Date fecha = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Label lblFecha = new Label();
		lblFecha.setText(sdf.format(fecha));
		lblFecha.setAlignment(Label.RIGHT);
		tlbPie.add(lblFecha);
		contentPane.add(desktopPane, BorderLayout.CENTER);

	}

	private void probarMenus() {
		/*
		 * List<Menu> lista = new ArrayList<Menu>(); lista = m.findAll(); /*
		 * Definición e inicialización de la matriz n x 2 contentiva de las
		 * opciones que se desea dar a MiMenu. Se espera que esta matrix
		 * contenga sólo tipos String, y que cada elemento, en sentido vertical,
		 * esté compuesto por el par Clave, Opción, donde Clave es un número que
		 * mantiene la jerarquía del árbol de opciones, por ejemplo, 112 es
		 * subopción de 11, la cual es subopción de 1, y así en todos los demás
		 * casos. vtOpciones = new String[lista.size()][3]; for(int i = 0; i <
		 * lista.size();i++){ vtOpciones[i][0] = lista.get(i).getPosicion();
		 * vtOpciones[i][1] = lista.get(i).getDescripcion();
		 * menu.setIdmenu(lista.get(i).getIdmenu());
		 * menu.setIndiceUbi(lista.get(i).getIndiceUbi());
		 * menu.setDescripcion(lista.get(i).getDescripcion());
		 * menu.setPosicion(lista.get(i).getPosicion()); };
		 */

		String vtOpciones[][] = { { "1", "Tablas" },
				{ "11", "Sucursales y Almacenes" }, { "12", "Productos" },
				{ "121", "Cuentas" }, { "122", "Grupos de Productos" },
				{ "13", "Opción 1_3" }, { "2", "Opción _2" },
				{ "21", "Opción 2_1" }, { "22", "Opción 2_2" },
				{ "221", "Opción 22_1" }, { "3", "Opción _3" } };

		/*
		 * Llamada de MiMenu que envía la matriz de opciones y el Método de la
		 * clase que lo invoca que resuelve las acciones del menú dado
		 */
		menus mnPrin = new menus(vtOpciones, "AccionesMenu");

		setJMenuBar(mnPrin);

	}

	public void AccionesMenu(String Opc) {
		JInternalFrame FormularioSeleccionado = new JInternalFrame();
		menus objMenu = new menus(this.barraMaestro);
		if (Opc.equals("121")) {
			FormularioSeleccionado = objMenu.getFormulario(0);
		} else {
			FormularioSeleccionado = objMenu.getFormulario(1);
		}
		setFormularioActivo(FormularioSeleccionado);
	}

	/*
	 * public JInternalFrame getFormularioActivo() { return formularioActivo; }
	 */

	public void setFormularioActivo(JInternalFrame formularioActivo) {
		this.formularioActivo = formularioActivo;
		if (formularioActivo instanceof AbstractMaestro) {
			getDesktopPane().add(this.formularioActivo);
		}

	}

	public JDesktopPane getDesktopPane() {
		return desktopPane;
	}
}
