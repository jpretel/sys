package vista;

import static org.pushingpixels.flamingo.api.ribbon.RibbonElementPriority.MEDIUM;
import static org.pushingpixels.flamingo.api.ribbon.RibbonElementPriority.TOP;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.KeyEventDispatcher;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.util.Arrays;
import java.util.List;

import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.skin.OfficeSilver2007Skin;
import org.pushingpixels.flamingo.api.common.JCommandButton;
import org.pushingpixels.flamingo.api.common.icon.ImageWrapperResizableIcon;
import org.pushingpixels.flamingo.api.common.icon.ResizableIcon;
import org.pushingpixels.flamingo.api.ribbon.JRibbonBand;
import org.pushingpixels.flamingo.api.ribbon.JRibbonFrame;
import org.pushingpixels.flamingo.api.ribbon.RibbonApplicationMenu;
import org.pushingpixels.flamingo.api.ribbon.RibbonTask;
import org.pushingpixels.flamingo.api.ribbon.resize.CoreRibbonResizePolicies;
import org.pushingpixels.flamingo.api.ribbon.resize.IconRibbonBandResizePolicy;

import vista.barras.BarraMaestro;
import vista.formularios.FrmCuentas;
import vista.formularios.FrmDocumento;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MainFrame extends JRibbonFrame {
	static BarraMaestro barraMaestro;
	static JDesktopPane desktopPane;

	public MainFrame() {
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				System.out.println(arg0);
			}
		});
		
		desktopPane = new JDesktopPane();
		
		
		getContentPane().add(desktopPane, BorderLayout.CENTER);
		barraMaestro = new BarraMaestro();
		barraMaestro.setVisible(false);
		getContentPane().add(barraMaestro, BorderLayout.WEST);

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

		SwingUtilities.invokeLater(new Runnable() {

			@SuppressWarnings({ "unchecked", "rawtypes" })
			public void run() {

				JFrame.setDefaultLookAndFeelDecorated(true);
				JDialog.setDefaultLookAndFeelDecorated(true);

				SubstanceLookAndFeel.setSkin(new OfficeSilver2007Skin());
				MainFrame frame = new MainFrame();

				frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
				frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
				frame.pack();
				frame.setVisible(true);

				JRibbonBand band1 = new JRibbonBand(
						"Maestros",
						getResizableIconFromResource("/main/resources/iconos/editar.png"));

				JRibbonBand band2 = new JRibbonBand("world!", null);

				JCommandButton button1 = new JCommandButton(
						"Cuentas",
						getResizableIconFromResource("/main/resources/iconos/nuevo.png"));

				button1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						FrmCuentas frmGrupos = new FrmCuentas(barraMaestro);
						frmGrupos.setVisible(true);
						desktopPane.add(frmGrupos);
						try {
							frmGrupos.setSelected(true);
						} catch (PropertyVetoException e) {
							e.printStackTrace();
						}
					}
				});
				JCommandButton button2 = new JCommandButton(
						"Consumidores",
						getResizableIconFromResource("/main/resources/iconos/editar.png"));
				JCommandButton button3 = new JCommandButton(
						"Sub Diarios",
						getResizableIconFromResource("/main/resources/iconos/cancelar.png"));
				JCommandButton button4 = new JCommandButton(
						"Tipo de Documentos",
						getResizableIconFromResource("/main/resources/iconos/grabar.png"));
				
				button4.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						FrmDocumento frm = new FrmDocumento(barraMaestro);
						frm.setVisible(true);
						desktopPane.add(frm);
						try {
							frm.setSelected(true);
						} catch (PropertyVetoException e) {
							e.printStackTrace();
						}
					}
				});
				
				System.out.println();
				
				band1.addCommandButton(button1, TOP);
				band1.addCommandButton(button2, MEDIUM);
				band1.addCommandButton(button3, MEDIUM);
				band1.addCommandButton(button4, MEDIUM);

				band1.setResizePolicies((List) Arrays.asList(
						new CoreRibbonResizePolicies.Mid2Mid(band1
								.getControlPanel()),
						new IconRibbonBandResizePolicy(band1.getControlPanel())));

				band2.setResizePolicies((List) Arrays.asList(
						new CoreRibbonResizePolicies.Mid2Mid(band1
								.getControlPanel()),
						new IconRibbonBandResizePolicy(band2.getControlPanel())));

				RibbonTask task1 = new RibbonTask("Contabilidad", band1);
				RibbonTask task2 = new RibbonTask("Logística", band2);

				frame.getRibbon().addTask(task1);
				frame.getRibbon().addTask(task2);

				frame.getRibbon().setApplicationMenu(
						new RibbonApplicationMenu());
			}
		});
	}
}

class MyDispatcher implements KeyEventDispatcher {
    @Override
    public boolean dispatchKeyEvent(KeyEvent e) {
        if (e.getID() == KeyEvent.KEY_PRESSED) {
            System.out.println("tester");
        } else if (e.getID() == KeyEvent.KEY_RELEASED) {
            System.out.println("2test2");
        } else if (e.getID() == KeyEvent.KEY_TYPED) {
            System.out.println("3test3");
        }
        return false;
    }
}