package vista;

import static org.pushingpixels.flamingo.api.ribbon.RibbonElementPriority.MEDIUM;
import static org.pushingpixels.flamingo.api.ribbon.RibbonElementPriority.TOP;
import static org.pushingpixels.flamingo.api.ribbon.RibbonElementPriority.LOW;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Frame;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.skin.OfficeBlue2007Skin;
import org.jvnet.substance.skin.OfficeSilver2007Skin;
import org.jvnet.substance.skin.MagmaSkin;
import org.pushingpixels.flamingo.api.common.JCommandButton;
import org.pushingpixels.flamingo.api.common.icon.ImageWrapperResizableIcon;
import org.pushingpixels.flamingo.api.common.icon.ResizableIcon;
import org.pushingpixels.flamingo.api.ribbon.AbstractRibbonBand;
import org.pushingpixels.flamingo.api.ribbon.JRibbonBand;
import org.pushingpixels.flamingo.api.ribbon.JRibbonFrame;
import org.pushingpixels.flamingo.api.ribbon.RibbonApplicationMenu;
import org.pushingpixels.flamingo.api.ribbon.RibbonTask;
import org.pushingpixels.flamingo.api.ribbon.resize.CoreRibbonResizePolicies;
import org.pushingpixels.flamingo.api.ribbon.resize.IconRibbonBandResizePolicy;

import vista.barras.BarraMaestro;

import javax.swing.JDesktopPane;

import java.awt.BorderLayout;

/**
 * Main Frame to demonstrate ribbon use.
 * 
 * @author <a href="http://blog.frankel.ch/">Nicolas Frankel</a>
 * @date 26 juin 2010
 * @version 1.0
 * 
 */
public class MainFrame extends JRibbonFrame {
	public MainFrame() {

		JDesktopPane desktopPane = new JDesktopPane();
		getContentPane().add(desktopPane, BorderLayout.CENTER);
	}

	/** Serial version unique id. */
	private static final long serialVersionUID = 1L;

	public static ResizableIcon getResizableIconFromResource(String resource) {
		return ImageWrapperResizableIcon.getIcon(
				MainFrame.class.getResource(resource), new Dimension(48, 48));
	}

	/**
	 * Entry point method.
	 * 
	 * @param args
	 *            Application arguments
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public void run() {
				try {
					JFrame.setDefaultLookAndFeelDecorated(true);
					JDialog.setDefaultLookAndFeelDecorated(true);

					SubstanceLookAndFeel.setSkin(new OfficeSilver2007Skin());

					MainFrame frame = new MainFrame();

					frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
					frame.pack();
					frame.setVisible(true);

					JRibbonBand band1 = new JRibbonBand("Edición", null);

					JRibbonBand band2 = new JRibbonBand("world!", null);

					JRibbonBand band3 = new JRibbonBand("Otra!", null);

					JCommandButton button1 = new JCommandButton(
							"Nuevo",
							getResizableIconFromResource("/main/resources/iconos/nuevo.png"));
					JCommandButton button2 = new JCommandButton("Editar",
							getResizableIconFromResource("/main/resources/iconos/editar.png"));
					JCommandButton button3 = new JCommandButton("Cancelar",
							getResizableIconFromResource("/main/resources/iconos/cancelar.png"));
					JCommandButton button4 = new JCommandButton("Grabar",
							getResizableIconFromResource("/main/resources/iconos/grabar.png"));
					band1.addCommandButton(button1, TOP);
					band1.addCommandButton(button2, MEDIUM);
					band1.addCommandButton(button3, LOW);
					band1.addCommandButton(button4, TOP);

					band1.setResizePolicies((List) Arrays.asList(
							new CoreRibbonResizePolicies.None(band1
									.getControlPanel()),
							new CoreRibbonResizePolicies.Mirror(band1
									.getControlPanel()),
							new CoreRibbonResizePolicies.Mid2Low(band1
									.getControlPanel()),
							new CoreRibbonResizePolicies.High2Low(band1
									.getControlPanel()),
							new IconRibbonBandResizePolicy(band1
									.getControlPanel())));

					band2.setResizePolicies((List) Arrays.asList(
							new CoreRibbonResizePolicies.None(band1
									.getControlPanel()),
							new CoreRibbonResizePolicies.Mirror(band1
									.getControlPanel()),
							new CoreRibbonResizePolicies.Mid2Low(band1
									.getControlPanel()),
							new CoreRibbonResizePolicies.High2Low(band1
									.getControlPanel()),
							new IconRibbonBandResizePolicy(band1
									.getControlPanel())));

					band3.setResizePolicies((List) Arrays.asList(
							new CoreRibbonResizePolicies.None(band1
									.getControlPanel()),
							new CoreRibbonResizePolicies.Mirror(band1
									.getControlPanel()),
							new CoreRibbonResizePolicies.Mid2Low(band1
									.getControlPanel()),
							new CoreRibbonResizePolicies.High2Low(band1
									.getControlPanel()),
							new IconRibbonBandResizePolicy(band1
									.getControlPanel())));

					/*
					 * band2.setResizePolicies((List) Arrays .asList(new
					 * IconRibbonBandResizePolicy(band2 .getControlPanel())));
					 */
					JRibbonBand[] bandas = new JRibbonBand[2];

					bandas[0] = band1;
					bandas[1] = band2;

					RibbonTask task1 = new RibbonTask("Edición", bandas);

					RibbonTask task2 = new RibbonTask("Utilidades", band3);

					frame.getRibbon().addTask(task1);
					frame.getRibbon().addTask(task2);

					frame.getRibbon().setApplicationMenu(
							new RibbonApplicationMenu());

					frame.setBounds(0, 0, 400, 400);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
