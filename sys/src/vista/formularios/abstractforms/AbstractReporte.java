package vista.formularios.abstractforms;

import vista.controles.DSGInternalFrame;
import vista.controles.DSGTableModelReporte;

import java.awt.BorderLayout;

import vista.barras.PanelBarraReporte;

import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Dimension;

public abstract class AbstractReporte extends DSGInternalFrame {
	private static final long serialVersionUID = 1L;
	private PanelBarraReporte panelBarraReporte;
	protected JPanel pnlContenido;
	protected JPanel pnlFiltro;
	protected JPanel pnlDatos;
	protected JScrollPane srclDatos;
	protected JTable tblDatos;

	public AbstractReporte() {
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		this.panelBarraReporte = new PanelBarraReporte();
		FlowLayout flowLayout = (FlowLayout) this.panelBarraReporte.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		getContentPane().add(this.panelBarraReporte, BorderLayout.NORTH);
		
		this.pnlContenido = new JPanel();
		getContentPane().add(this.pnlContenido, BorderLayout.CENTER);
		this.pnlContenido.setLayout(new BorderLayout(0, 0));
		
		this.pnlFiltro = new JPanel();
		this.pnlFiltro.setPreferredSize(new Dimension(10, 60));
		this.pnlFiltro.setMinimumSize(new Dimension(10, 50));
		this.pnlContenido.add(this.pnlFiltro, BorderLayout.NORTH);
		this.pnlFiltro.setLayout(null);
		
		this.pnlDatos = new JPanel();
		this.pnlContenido.add(this.pnlDatos, BorderLayout.CENTER);
		this.pnlDatos.setLayout(new BorderLayout(0, 0));
		
		this.srclDatos = new JScrollPane();
		this.pnlDatos.add(this.srclDatos, BorderLayout.CENTER);
		
		this.tblDatos = new JTable(new DSGTableModelReporte());
		this.srclDatos.setViewportView(this.tblDatos);
	}
	
	public abstract Object[][] getData();
	
	public abstract String[] getCabeceras();
}
