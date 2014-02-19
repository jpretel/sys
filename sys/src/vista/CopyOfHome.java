package vista;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JMenuBar;
import javax.swing.JToolBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.Label;

import javax.swing.JSeparator;

import vista.barras.BarraMaestro;
import vista.formularios.AbstractMaestro;
import vista.formularios.FrmConsumidor;
import vista.formularios.FrmCuentas;
import vista.formularios.FrmDocumento;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.border.LineBorder;

import java.awt.Color;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;

public class CopyOfHome extends JFrame {

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

	
	public CopyOfHome(String titulo) {
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
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnMaestros = new JMenu("Maestros");
		menuBar.add(mnMaestros);

		JMenuItem mntmCuentas = new JMenuItem("Cuentas");
		mntmCuentas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FrmCuentas frmCuentas = new FrmCuentas(getBarraMaestro());
				getDesktopPane().add(frmCuentas);
				try {
					frmCuentas.setSelected(true);
				} catch (PropertyVetoException e) {
					e.printStackTrace();
				}
			}
		});

		mnMaestros.add(mntmCuentas);
		
		JMenuItem mntmCentrosDeCosto = new JMenuItem("Centros de Costo");
		mntmCentrosDeCosto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FrmConsumidor frmCons = new FrmConsumidor(getBarraMaestro());
				getDesktopPane().add(frmCons);
			}
		});
		mnMaestros.add(mntmCentrosDeCosto);
		
		JMenuItem mntmDocumentos = new JMenuItem("Documentos");
		mntmDocumentos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FrmDocumento frm = new FrmDocumento(getBarraMaestro());
				getDesktopPane().add(frm);
				try {
					frm.setSelected(true);
				} catch (PropertyVetoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		mnMaestros.add(mntmDocumentos);
		
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

		Label label_1 = new Label("10/01/2014");
		label_1.setAlignment(Label.RIGHT);
		tlbPie.add(label_1);

		
		contentPane.add(desktopPane, BorderLayout.CENTER);
		

	}

	public JInternalFrame getFormularioActivo() {
		return formularioActivo;
	}

	public void setFormularioActivo(JInternalFrame formularioActivo) {
		this.formularioActivo = formularioActivo;
		System.out.println("Formulario");
		if (formularioActivo instanceof AbstractMaestro) {
			System.out.println("Click en el maestro :D");
		}

	}
	
	public JDesktopPane getDesktopPane() {
		return desktopPane;
	}
}
