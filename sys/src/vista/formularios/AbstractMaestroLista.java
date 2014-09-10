package vista.formularios;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.JXTable;

import vista.barras.PanelBarraMaestroLista;

import java.awt.Dimension;

public abstract class AbstractMaestroLista extends AbstractMaestro {
	private static final long serialVersionUID = 1L;
	private JScrollPane scrollPane;
	public JXTable tblLista;
	public DefaultTableModel modeloLista = new DefaultTableModel();

	private PanelBarraMaestroLista barraLista;

	protected JPanel pnlContenido;

	public AbstractMaestroLista() {
		super("");
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
					irFormulario(VISTA);
				}
			}
		});		
		scrollPane.setViewportView(tblLista);
		tblLista.setColumnControlVisible(true);
	}

	public void init(AbstractMaestro obj, String opcion, Object entidad) {
		if (obj instanceof AbstractMaestro) {
			getDesktopPane().add(obj);
			if (opcion.equals(NUEVO))
				obj.DoNuevo();
			if (opcion.equals(VISTA)) {
				obj.actualiza_objeto(entidad);
			}
			if (opcion.equals(EDICION)) {
				obj.actualiza_objeto(entidad);
				obj.editar();
			}
			try {
				obj.setSelected(true);
			} catch (PropertyVetoException e) {
				e.printStackTrace();
			}
		}
	}

	public JXTable getTblLista() {
		return tblLista;
	}

	public Object RetornarPk() {
		Object id = null;
		if (tblLista.getSelectedRow() >= 0) {
			id = modeloLista.getValueAt(tblLista.getSelectedRow(), 0);
		}
		return id;
	}
	
	@Override
	public void nuevo() {
		System.out.println("hace nuevo");
		irFormulario(NUEVO);
		iniciar();
	}
	
	@Override
	public void editar() {
		irFormulario(EDICION);
	}

	public abstract void llenar_lista();

	public abstract void irFormulario(String opcion);
}
