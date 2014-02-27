package vista;

import static org.pushingpixels.flamingo.api.ribbon.RibbonElementPriority.LOW;
import static org.pushingpixels.flamingo.api.ribbon.RibbonElementPriority.TOP;

import java.awt.BorderLayout;
import java.awt.Dimension;

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
import vista.formularios.FrmGrupos;
import vista.utilitarios.menus;

import javax.swing.border.LineBorder;

import org.pushingpixels.flamingo.api.common.JCommandButton;
import org.pushingpixels.flamingo.api.common.icon.ImageWrapperResizableIcon;
import org.pushingpixels.flamingo.api.common.icon.ResizableIcon;
import org.pushingpixels.flamingo.api.ribbon.JRibbonBand;
import org.pushingpixels.flamingo.api.ribbon.JRibbonFrame;
import org.pushingpixels.flamingo.api.ribbon.RibbonApplicationMenu;
import org.pushingpixels.flamingo.api.ribbon.RibbonElementPriority;
import org.pushingpixels.flamingo.api.ribbon.RibbonTask;
import org.pushingpixels.flamingo.api.ribbon.resize.CoreRibbonResizePolicies;
import org.pushingpixels.flamingo.api.ribbon.resize.IconRibbonBandResizePolicy;

import dao.MenuDAO;
import entity.Menu;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Home extends JRibbonFrame {

	private static final long serialVersionUID = 1L;

	private JInternalFrame formularioActivo;

	final JDesktopPane desktopPane = new JDesktopPane();
	private String vtOpciones[][];
	MenuDAO m = new MenuDAO();
	Menu menu = new Menu();
	BarraMaestro barraMaestro;

	public Home(String titulo) {
		super(titulo);
		barraMaestro = new BarraMaestro();
		barraMaestro.setVisible(false);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		setBounds(0, 0, 605, 415);
		desktopPane.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {

			}
		});

		desktopPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		probarMenus();

		//getContentPane().add(barraMaestro, BorderLayout.EAST);

		/*
		 * contentPane = new JPanel(); contentPane.setBorder(new EmptyBorder(5,
		 * 5, 5, 5)); contentPane.setLayout(new BorderLayout(0, 0));
		 * setContentPane(contentPane);
		 */

		JToolBar tlbPie = new JToolBar();
		tlbPie.setRollover(true);
		tlbPie.setFloatable(false);
		getContentPane().add(tlbPie, BorderLayout.SOUTH);

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
		getContentPane().add(desktopPane, BorderLayout.CENTER);

	}

	private void probarMenus() {
		JRibbonBand band1 = new JRibbonBand("Maestros", null);

		JRibbonBand band2 = new JRibbonBand("Movimientos", null);

		JRibbonBand band3 = new JRibbonBand("Procesos", null);

		JCommandButton button1 = new JCommandButton(
				"Nuevo",
				getResizableIconFromResource("/main/resources/iconos/nuevo.png"));

		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FrmGrupos frmGrupos = new FrmGrupos(barraMaestro);
				frmGrupos.setVisible(true);
				getDesktopPane().add(frmGrupos);
				try {
					frmGrupos.setSelected(true);
				} catch (PropertyVetoException e) {
					e.printStackTrace();
				}
			}
		});

		JCommandButton button2 = new JCommandButton(
				"Editar",
				getResizableIconFromResource("/main/resources/iconos/editar.png"));
		JCommandButton button3 = new JCommandButton(
				"Cancelar",
				getResizableIconFromResource("/main/resources/iconos/cancelar.png"));
		JCommandButton button4 = new JCommandButton(
				"Grabar",
				getResizableIconFromResource("/main/resources/iconos/grabar.png"));
		band1.addCommandButton(button1, RibbonElementPriority.MEDIUM);
		band1.addCommandButton(button2, RibbonElementPriority.MEDIUM);
		band1.addCommandButton(button3, RibbonElementPriority.MEDIUM);
		band1.addCommandButton(button4, RibbonElementPriority.MEDIUM);

		band1.setResizePolicies((List) Arrays.asList(
				new CoreRibbonResizePolicies.None(band1.getControlPanel()),
				new CoreRibbonResizePolicies.Mirror(band1.getControlPanel()),
				new CoreRibbonResizePolicies.Mid2Low(band1.getControlPanel()),
				new CoreRibbonResizePolicies.High2Low(band1.getControlPanel()),
				new IconRibbonBandResizePolicy(band1.getControlPanel())));

		band2.setResizePolicies((List) Arrays.asList(
				new CoreRibbonResizePolicies.None(band1.getControlPanel()),
				new CoreRibbonResizePolicies.Mirror(band1.getControlPanel()),
				new CoreRibbonResizePolicies.Mid2Low(band1.getControlPanel()),
				new CoreRibbonResizePolicies.High2Low(band1.getControlPanel()),
				new IconRibbonBandResizePolicy(band1.getControlPanel())));

		band3.setResizePolicies((List) Arrays.asList(
				new CoreRibbonResizePolicies.None(band1.getControlPanel()),
				new CoreRibbonResizePolicies.Mirror(band1.getControlPanel()),
				new CoreRibbonResizePolicies.Mid2Low(band1.getControlPanel()),
				new CoreRibbonResizePolicies.High2Low(band1.getControlPanel()),
				new IconRibbonBandResizePolicy(band1.getControlPanel())));

		RibbonTask task1 = new RibbonTask("Contabilidad", new JRibbonBand[] {
				band1, band2 });

		RibbonTask task2 = new RibbonTask("Logística", band3);

		getRibbon().addTask(task1);
		getRibbon().addTask(task2);
		getRibbon().setApplicationMenu(new RibbonApplicationMenu());
	}

	public void AccionesMenu(String Opc) {
		/*
		 * JInternalFrame FormularioSeleccionado = new JInternalFrame(); menus
		 * objMenu = new menus(getBarraMaestro()); menu = m.RetornarIndex(Opc);
		 * FormularioSeleccionado = objMenu.getFormulario(menu.getIndiceUbi());
		 * setFormularioActivo(FormularioSeleccionado);
		 */
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

	public static ResizableIcon getResizableIconFromResource(String resource) {
		return ImageWrapperResizableIcon.getIcon(
				MainFrame.class.getResource(resource), new Dimension(48, 48));
	}
}
