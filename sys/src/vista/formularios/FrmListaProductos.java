package vista.formularios;

import vista.barras.BarraMaestro;

import javax.swing.JLabel;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import dao.GrupoDAO;
import dao.ProductoDAO;
import dao.SubgrupoDAO;
import entity.Grupo;
import entity.Producto;
import entity.Subgrupo;

import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.beans.PropertyVetoException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FrmListaProductos extends AbstractMaestro {
	private static final long serialVersionUID = 1L;
	DefaultTableModel lista_productos;	
	private JTable tblProductos;
	private List<Grupo> grupoL = new ArrayList<Grupo>();
	private GrupoDAO gDAO = new GrupoDAO();
	private SubgrupoDAO sgDAO = new SubgrupoDAO();
	private Grupo grupoEnt = new Grupo(); 
	private BarraMaestro barra;
	private Producto producto = new Producto();
	protected ProductoDAO pdao = new ProductoDAO();
	
	
	@SuppressWarnings("rawtypes")	
	DefaultComboBoxModel grupo = new DefaultComboBoxModel();
	@SuppressWarnings("rawtypes")
	DefaultComboBoxModel subgrupo = new DefaultComboBoxModel();

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public FrmListaProductos(String titulo, BarraMaestro barra) {
		super("Lista de Productos",barra);
		this.barra = barra;
		setBounds(100, 100, 600, 353);
		getContentPane().setLayout(null);
		
		JLabel lblGrupoDeProductos = new JLabel("Grupo de Productos");
		lblGrupoDeProductos.setBounds(10, 11, 95, 14);
		getContentPane().add(lblGrupoDeProductos);
		
		/*Cargando Lista de Subgrupos*/			
		grupoL = gDAO.findAllbyGrupo();
		
		final JComboBox cboGrupo = new JComboBox();			
		cboGrupo.setBounds(128, 8, 160, 20);
		getContentPane().add(cboGrupo);
		
		JLabel lblSubgrupoDeProductos = new JLabel("SubGrupo de Productos");
		lblSubgrupoDeProductos.setBounds(298, 11, 113, 14);
		getContentPane().add(lblSubgrupoDeProductos);
		
		final JComboBox cboSubGrupo = new JComboBox();
		cboSubGrupo.setBounds(416, 8, 158, 20);
		getContentPane().add(cboSubGrupo);
		
		JScrollPane scrollPane_Productos = new JScrollPane();
		scrollPane_Productos.setBounds(10, 40, 547, 280);
		getContentPane().add(scrollPane_Productos);	
	
		int nFilas = grupoL.size();
		grupo.removeAllElements();
		cboGrupo.removeAllItems();
		cboGrupo.addItem("(Todos)");
		subgrupo.removeAllElements();
		cboSubGrupo.removeAllItems();
		cboSubGrupo.addItem("(Todos)");
		for(int i =0;i < nFilas ;i++){			 
			grupoEnt = grupoL.get(i);			
			grupo.addElement(grupoEnt);
			cboGrupo.addItem(grupoEnt.getDescripcion());
		}
		
		cboGrupo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Grupo Entidad = new Grupo();			
				Entidad = (Grupo)grupo.getElementAt(cboGrupo.getSelectedIndex() - 1);
				String lcCodigo = Entidad.getIdgrupo();
				subgrupo.removeAllElements();
				cboSubGrupo.removeAllItems();
				cboSubGrupo.addItem("(Todos)");
				llenar_subgrupos(lcCodigo);
			}

			private void llenar_subgrupos(String idgrupo) {				
				for(Subgrupo subgrupoEnt : sgDAO.findAllbyGrupo()){			
					if(idgrupo.equals(subgrupoEnt.getId().getGrupoIdgrupo())){
						subgrupo.addElement(subgrupoEnt);
						cboSubGrupo.addItem(subgrupoEnt.getDescripcion());
					}
				}
			}
		});
		
		//Inicializando la lista		
		lista_productos = new DefaultTableModel();
		String columnas[] ={"Codigo","Producto","Grupo de Producto","Subgrupo de Producto"};
		lista_productos.setColumnIdentifiers(columnas);
		//lista_productos.isCellEditable(arg0, arg1)
		
		tblProductos = new JTable(lista_productos){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int rowIndex, int vColIndex) {
	            return false;
	    }};
		tblProductos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2){ 
					String idproducto = lista_productos.getValueAt(tblProductos.getSelectedRow(), 0).toString();
					producto = pdao.RetornarxIdProducto(idproducto);
					vista_lista();
				}
			}
		});
		tblProductos.setBounds(20, 42, 421, 226);		
		scrollPane_Productos.setViewportView(tblProductos);
		tblProductos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		List<Producto> prodList= pdao.findAllbyProducto();
		
		int nFilasP = prodList.size();
		
		for(int i = 0;i<nFilasP;i++){
			Producto p = new Producto();
			Subgrupo sg = new Subgrupo();
			Grupo g = new Grupo();
			p = (Producto)prodList.get(i);
			sg = p.getSubgrupo();
			g = sg.getGrupo();
			lista_productos.addRow(new Object[] {p.getIdproductos(),p.getDescripcion(),g.getDescripcion(),sg.getDescripcion()});			
		}				
	}

	@Override
	public void anular() {
		// TODO Auto-generated method stub

	}

	@Override
	public void llenar_datos() {
		// TODO Auto-generated method stub

	}

	@Override
	public void llenar_lista() {
		// TODO Auto-generated method stub

	}

	@Override
	public void llenar_tablas() {
		// TODO Auto-generated method stub

	}

	@Override
	public void vista_edicion() {
		// TODO Auto-generated method stub

	}

	@Override
	public void vista_noedicion() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void actualizaBarra() {		
		getBarra().setVisible(isSelected());
		if (isSelected()) {
			getBarra().setFormMaestro(this);
		}
		if (getEstado().equals(VISTA)) {
			getBarra().enVista();
		} else {
			getBarra().enEdicion();
		}
	}

	@Override
	public void nuevo() {
		FrmProductos frmProductos= new FrmProductos("Edicion de Productos",this.barra);		
		frmProductos.nuevo();	
		if (frmProductos instanceof AbstractMaestro) {			
			getDesktopPane().add(frmProductos);
			try {
				frmProductos.setSelected(true);
			} catch (PropertyVetoException e) {
				e.printStackTrace();
			}
		}	
	}
	
	public void vista_lista(){
		FrmProductos frmProductos= new FrmProductos("Edicion de Productos",this.barra);		
		frmProductos.metodo(producto);
		if (frmProductos instanceof AbstractMaestro) {			
			getDesktopPane().add(frmProductos);
			try {
				frmProductos.setSelected(true);
			} catch (PropertyVetoException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void editar(){		
		if(tblProductos.getSelectedRow() >= 0){
			String idproducto = lista_productos.getValueAt(tblProductos.getSelectedRow(), 0).toString();
			producto = pdao.RetornarxIdProducto(idproducto);
			FrmProductos frmProductos= new FrmProductos("Edicion de Productos",this.barra);
			frmProductos.metodo(producto);
			frmProductos.editar();
			if (frmProductos instanceof AbstractMaestro) {			
				getDesktopPane().add(frmProductos);
				try {
					frmProductos.setSelected(true);
				} catch (PropertyVetoException e) {
					e.printStackTrace();
				}
			}
		}else{
			JOptionPane.showMessageDialog(null, "Seleccione un registro sobre la grilla");
		}
			
	}

	@Override
	public void nuevo_lista() {
		// TODO Auto-generated method stub
		
	}

}


