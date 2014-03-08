package vista.formularios;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.JXTable;
import vista.barras.BarraMaestro;

public abstract class AbstractMaestroLista extends AbstractMaestro {
	private static final long serialVersionUID = 1L;
	private JScrollPane scrollPane;
	public JXTable tblLista;
	public DefaultTableModel modeloLista = new DefaultTableModel();
	private AbstractMaestro frmGeneral;
	private int modo;
	
	public int getModo() {
		return modo;
	}

	public void setModo(int modo) {
		this.modo = modo;
	}

	public AbstractMaestro getFrmGeneral() {
		return frmGeneral;
	}

	public void setFrmGeneral(AbstractMaestro frmGeneral) {
		this.frmGeneral = frmGeneral;
	}

	public JXTable getTblLista() {
		return tblLista;
	}

	public AbstractMaestroLista(String titulo, BarraMaestro barra) {
		super(titulo, barra);
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 40, 547, 280);
		getContentPane().add(scrollPane);
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
				obj.nuevo();
			} else {
				if (modo == 2) {
					obj.actualiza_objeto(entidad);
					obj.editar();
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
	
	public Object RetornarPk(){
		Object id = null;
		if (tblLista.getSelectedRow() >= 0) {
			id = modeloLista.getValueAt(
					tblLista.getSelectedRow(), 0).toString();			
		}
		return id;
	}
	
	public void nuevo(){
		setModo(1);
		irFormulario();
	}
	
	public void editar(){
		setModo(2);
		irFormulario();
	}

	public abstract void irFormulario();
}
