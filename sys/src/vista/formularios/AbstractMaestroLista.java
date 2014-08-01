package vista.formularios;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.JXTable;

import vista.barras.BarraMaestro;
import vista.barras.PanelBarraMaestro;
import vista.barras.PanelBarraMaestroLista;

import java.awt.Dimension;

public abstract class AbstractMaestroLista extends AbstractMaestro{
	private static final long serialVersionUID = 1L;
	private JScrollPane scrollPane;
	public JXTable tblLista;
	public DefaultTableModel modeloLista = new DefaultTableModel();
	private AbstractMaestroLista frmGeneral;
	private int modo;		
	
	protected static final String EDICION = "EDICION";
	protected static final String VISTA = "VISTA";
	protected static final String NUEVO = "NUEVO";

	private PanelBarraMaestroLista barraLista;
	private PanelBarraMaestro barra = new PanelBarraMaestro();
	private int abierto = 0;
	public PanelBarraMaestro getBarra() {
		return barra;
	}

	public void setBarra(PanelBarraMaestro barra) {
		this.barra = barra;
	}

	protected JPanel pnlContenido;
	private String estado;
	public AbstractMaestroLista() {		
		super("aa");
		barraLista = new PanelBarraMaestroLista();
		setEstado(VISTA);
		setTitle("Lista");
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setVisible(true);
		setResizable(true);		
		barraLista.setFormMaestro(this);
		FlowLayout flowLayout = (FlowLayout) barraLista.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		getContentPane().add(barraLista, BorderLayout.NORTH);
		scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(530, 250));
		pnlContenido = new JPanel();
		getContentPane().add(pnlContenido, BorderLayout.CENTER);
		setBounds(100, 100, 555, 325);
		pnlContenido.add(scrollPane);
	}

	public void inicia_Lista(String columnas[], Object[] obj) {
		modeloLista.setColumnIdentifiers(columnas);
		tblLista = new JXTable(modeloLista) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int rowIndex, int vColIndex) {
				return false;
			}
		};
		tblLista.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					setModo(0);
					irFormulario();
				}
			}
		});
		scrollPane.setViewportView(tblLista);
		tblLista.setColumnControlVisible(true);
	}

	public void init(AbstractMaestro obj, int modo, Object entidad) {
		if (obj instanceof AbstractMaestro) {
			getDesktopPane().add(obj);
			if (modo == 1) {
				obj.DoNuevo();
				abierto = 1;
			} else {
				if (modo == 2) {					
					obj.actualiza_objeto(entidad);
					obj.editar();
					abierto = 1;
				} else {
					obj.actualiza_objeto(entidad);
				}
			}
			try {
				obj.setSelected(true);
			} catch (PropertyVetoException e) {
				e.printStackTrace();
			}
		}
	}
	
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public int getModo() {
		return modo;
	}

	public void setModo(int modo) {
		this.modo = modo;
	}

	public AbstractMaestroLista getFrmGeneral() {
		return frmGeneral;
	}

	public void setFrmGeneral(AbstractMaestroLista frmGeneral) {
		this.frmGeneral = frmGeneral;
	}

	public JXTable getTblLista() {
		return tblLista;
	}
	
	
	public Object RetornarPk(){
		Object id = null;
		if (tblLista.getSelectedRow() >= 0) {
			id = modeloLista.getValueAt(
					tblLista.getSelectedRow(), 0).toString();			
		}
		return id;
	}
	
	public void Nuevo() {
		if (abierto == 0){
			setModo(1);
			irFormulario();
		}
	}
	
	public abstract void  llenar_lista();

	public abstract void  irFormulario();
}
