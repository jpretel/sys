package vista;

import static org.pushingpixels.flamingo.api.ribbon.RibbonElementPriority.MEDIUM;
import static org.pushingpixels.flamingo.api.ribbon.RibbonElementPriority.TOP;
import static org.pushingpixels.flamingo.api.ribbon.RibbonElementPriority.LOW;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JToolBar;

import org.pushingpixels.flamingo.api.common.JCommandButton;
import org.pushingpixels.flamingo.api.common.JCommandButton.CommandButtonKind;
import org.pushingpixels.flamingo.api.common.icon.ImageWrapperResizableIcon;
import org.pushingpixels.flamingo.api.common.icon.ResizableIcon;
import org.pushingpixels.flamingo.api.ribbon.JRibbonBand;
import org.pushingpixels.flamingo.api.ribbon.JRibbonFrame;
import org.pushingpixels.flamingo.api.ribbon.RibbonApplicationMenu;
import org.pushingpixels.flamingo.api.ribbon.RibbonApplicationMenuEntryFooter;
import org.pushingpixels.flamingo.api.ribbon.RibbonApplicationMenuEntryPrimary;
import org.pushingpixels.flamingo.api.ribbon.RibbonApplicationMenuEntrySecondary;
import org.pushingpixels.flamingo.api.ribbon.RibbonTask;
import org.pushingpixels.flamingo.api.ribbon.resize.CoreRibbonResizePolicies;
import org.pushingpixels.flamingo.api.ribbon.resize.IconRibbonBandResizePolicy;

import controlador.ControladorOpciones;
import dao.SysModuloDAO;
import core.entity.GrupoMenu;
import entity.SysGrupo;
import entity.SysModulo;
import entity.SysOpcion;
import entity.SysTitulo;
import core.entity.OpcionMenu;
import core.entity.TituloMenu;
import vista.utilitarios.MenuController;

public class MainFrame extends JRibbonFrame {
	static JDesktopPane desktopPane;
	static ControladorOpciones cOpciones;

	static SysModuloDAO moduloDAO = new SysModuloDAO();

	static {
		cOpciones = new ControladorOpciones();
	}

	public MainFrame() {
		desktopPane = new JDesktopPane();

		getContentPane().add(desktopPane, BorderLayout.CENTER);

		cOpciones.setDesktopPane(getDesktopPane());

		setTitle("BRIGHT GLOBAL CHANGE ERP");

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		CreaRibbonMenu();

		JToolBar tlbPie = new JToolBar();
		tlbPie.setRollover(true);
		tlbPie.setFloatable(false);
		getContentPane().add(tlbPie, BorderLayout.SOUTH);

		JLabel label = new JLabel(Sys.usuario.getIdusuario());
		tlbPie.add(label);

		tlbPie.add(new JSeparator());

		// Vtr Fecha Sistema
		Date fecha = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		JLabel lblFecha = new JLabel();
		lblFecha.setText(sdf.format(fecha));
		tlbPie.add(lblFecha);

		getContentPane().add(desktopPane, BorderLayout.CENTER);

		pack();
		setVisible(true);

	}

	public static JDesktopPane getDesktopPane() {
		return desktopPane;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void CreaRibbonMenu() {
		MenuController menu_controller = new MenuController();

		for (TituloMenu titulo : menu_controller.getTitulos()) {

			if (titulo.getGrupos() == null) {
				titulo.setGrupos(new ArrayList<GrupoMenu>());
			}

			List<JRibbonBand> bandas_aux = new ArrayList<JRibbonBand>();

			for (GrupoMenu grupo : titulo.getGrupos()) {

				if (grupo.getOpciones() == null) {
					grupo.setOpciones(new ArrayList<OpcionMenu>());
				}
				// System.out.println(grupo.getOpciones().size());
				boolean dibujaGrupo = (grupo.getOpciones().size() > 0);

				// System.out.println(dibujaGrupo);
				JRibbonBand band = new JRibbonBand(grupo.getDescripcion(),
						getResizableIconFromResource(grupo.getImagen()));

				if (dibujaGrupo) {

					bandas_aux.add(band);

					for (OpcionMenu opcion : grupo.getOpciones()) {

						// System.out.println(opcion);

						JCommandButton button = new JCommandButton(
								opcion.getDescripcion(),
								getResizableIconFromResource(opcion.getImagen()));

						if (opcion.getPrioridad() == 1) {
							band.addCommandButton(button, TOP);
						} else if (opcion.getPrioridad() == 2) {
							band.addCommandButton(button, MEDIUM);
						} else {
							band.addCommandButton(button, LOW);
						}

						button.addActionListener(cOpciones.returnAction(opcion
								.getOpcion()));
					}

					band.setResizePolicies((List) Arrays.asList(
							new CoreRibbonResizePolicies.Mid2Mid(band
									.getControlPanel()),
							new CoreRibbonResizePolicies.Mid2Low(band
									.getControlPanel()),
							new IconRibbonBandResizePolicy(band
									.getControlPanel())));
				}
			}

			if (bandas_aux.size() > 0) {
				JRibbonBand[] bandas = new JRibbonBand[bandas_aux.size()];
				for (int i = 0; i < bandas_aux.size(); i++) {
					// System.out.println(bandas_aux.size());
					bandas[i] = bandas_aux.get(i);
				}

				RibbonTask task = new RibbonTask(titulo.getDescripcion(),
						bandas);

				this.getRibbon().addTask(task);
			}
		}

		// Comienzo del Contenido del Menu Desplegable

		RibbonApplicationMenu ribbon = new RibbonApplicationMenu();

		RibbonApplicationMenuEntryPrimary modulo_popup = new RibbonApplicationMenuEntryPrimary(
				getResizableIconFromResource("/main/resources/salir.png"),
				"Modulo", null, CommandButtonKind.POPUP_ONLY);

		List<SysModulo> modulos = moduloDAO.findAll();
		RibbonApplicationMenuEntrySecondary[] modulos_vec = new RibbonApplicationMenuEntrySecondary[modulos
				.size()];

		int i = -1;
		for (final SysModulo m : modulos) {
			i++;
			RibbonApplicationMenuEntrySecondary secondary = new RibbonApplicationMenuEntrySecondary(
					getResizableIconFromResource16x16("/main/resources/salir.png"),
					m.getDescripcion(), new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent arg0) {
							CreaRibbonMenu(MenuController
									.getTitulosPorModulo(m));
						}
					}, CommandButtonKind.ACTION_ONLY);
			modulos_vec[i] = secondary;
		}

		modulo_popup.addSecondaryMenuGroup("Lista de Módulos", modulos_vec);

		ribbon.addMenuEntry(modulo_popup);

		ribbon.addMenuSeparator();

		RibbonApplicationMenuEntryPrimary nn = new RibbonApplicationMenuEntryPrimary(
				getResizableIconFromResource("/main/resources/favoritos.png"),
				"DashBoard", new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						CreaRibbonMenu();
					}
				}, CommandButtonKind.ACTION_ONLY);

		ribbon.addMenuEntry(nn);

		for (int mas = 0; mas < 2; mas++) {

			nn = new RibbonApplicationMenuEntryPrimary(
					getResizableIconFromResource("/main/resources/salir.png"),
					"Desactivado", null, CommandButtonKind.ACTION_ONLY);
			nn.setEnabled(false);

			ribbon.addMenuEntry(nn);

		}
		for (int mas = 0; mas < 6; mas++) {

			nn = new RibbonApplicationMenuEntryPrimary(
					getResizableIconFromResource("/main/resources/salir.png"),
					"Productos Lista",
					cOpciones.returnAction("FrmListaProductos"),
					CommandButtonKind.ACTION_ONLY);

			ribbon.addMenuEntry(nn);

		}

		RibbonApplicationMenuEntryFooter footer = new RibbonApplicationMenuEntryFooter(
				getResizableIconFromResource("/main/resources/salir.png"),
				"Salir", null);

		ribbon.addFooterEntry(footer);

		footer = new RibbonApplicationMenuEntryFooter(
				getResizableIconFromResource("/main/resources/salir.png"),
				"Salir2", null);

		ribbon.addFooterEntry(footer);

		getRibbon().setApplicationMenu(ribbon);

		setIconImage(new ImageIcon(
				MainFrame.class.getResource("/main/resources/iconos/nuevo.png"))
				.getImage());
	}

	@SuppressWarnings("unchecked")
	public void CreaRibbonMenu(List<SysTitulo> titulos) {
		getRibbon().removeAllTasks();
		for (SysTitulo titulo : titulos) {

			if (titulo.getSysGrupos() == null) {
				titulo.setSysGrupos(new ArrayList<SysGrupo>());
			}

			List<JRibbonBand> bandas_aux = new ArrayList<JRibbonBand>();
			for (SysGrupo grupo : titulo.getSysGrupos()) {

				if (grupo.getSysOpcions() == null) {
					grupo.setSysOpcions(new ArrayList<SysOpcion>());
				}

				boolean dibujaGrupo = (grupo.getSysOpcions().size() > 0);

				// System.out.println(dibujaGrupo);
				JRibbonBand band = new JRibbonBand(
						grupo.getDescripcion(),
						getResizableIconFromResource("/main/resources/iconos/nuevo.png"));

				if (dibujaGrupo) {

					bandas_aux.add(band);

					for (SysOpcion opcion : grupo.getSysOpcions()) {

						opcion.setImagen("/main/resources/iconos/nuevo.png");

						JCommandButton button = new JCommandButton(
								opcion.getDescripcion(),
								getResizableIconFromResource(opcion.getImagen()));

						if (opcion.getPrioridad() == 1) {
							band.addCommandButton(button, TOP);
						} else if (opcion.getPrioridad() == 2) {
							band.addCommandButton(button, MEDIUM);
						} else {
							band.addCommandButton(button, LOW);
						}

						button.addActionListener(cOpciones.returnAction(opcion
								.getOpcion()));
					}

					band.setResizePolicies((List) Arrays.asList(
							new CoreRibbonResizePolicies.Mid2Mid(band
									.getControlPanel()),
							new CoreRibbonResizePolicies.Mid2Low(band
									.getControlPanel()),
							new IconRibbonBandResizePolicy(band
									.getControlPanel())));
				}
			}

			if (bandas_aux.size() > 0) {
				JRibbonBand[] bandas = new JRibbonBand[bandas_aux.size()];
				for (int i = 0; i < bandas_aux.size(); i++) {
					// System.out.println(bandas_aux.size());
					bandas[i] = bandas_aux.get(i);
				}

				RibbonTask task = new RibbonTask(titulo.getDescripcion(),
						bandas);

				this.getRibbon().addTask(task);
			}
		}
	}

	/** Serial version unique id. */
	private static final long serialVersionUID = 1L;

	public static ResizableIcon getResizableIconFromResource(String resource) {
		return ImageWrapperResizableIcon.getIcon(
				MainFrame.class.getResource(resource), new Dimension(48, 48));
	}

	public static ResizableIcon getResizableIconFromResource16x16(
			String resource) {
		return ImageWrapperResizableIcon.getIcon(
				MainFrame.class.getResource(resource), new Dimension(10, 10));
	}
}

/*
 * class MyDispatcher2 implements KeyEventDispatcher {
 * 
 * @Override public boolean dispatchKeyEvent(KeyEvent e) { if (e.getID() ==
 * KeyEvent.KEY_PRESSED) { System.out.println("tester"); } else if (e.getID() ==
 * KeyEvent.KEY_RELEASED) { System.out.println("2test2"); } else if (e.getID()
 * == KeyEvent.KEY_TYPED) { System.out.println("3test3"); } return false; } }
 */