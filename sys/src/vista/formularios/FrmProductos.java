package vista.formularios;

import vista.barras.BarraMaestro;
import vista.contenedores.cntGrupo;
import vista.contenedores.cntMarca;
import vista.contenedores.cntMedida;
import vista.contenedores.cntSubGrupo;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import dao.GrupoDAO;
import dao.MarcaDAO;
import dao.ProductoDAO;
import dao.SubgrupoDAO;
import dao.UnimedidaDAO;
import entity.Grupo;
import entity.Marca;
import entity.Producto;
import entity.Subgrupo;
import entity.SubgrupoPK;
import entity.Unimedida;

import java.beans.PropertyVetoException;
import java.util.List;

import javax.swing.JTabbedPane;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import controlador.VariablesGlobales;

import java.awt.Window;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class FrmProductos extends AbstractMaestro {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtCodigo;
	private JTextField txtDescripcion;
	public final cntGrupo cntgrupo;
	public final cntSubGrupo cntSubGrupo;
	public final cntMarca cntmarca;
	public final cntMedida cntmedida;
	private Producto producto;// = new Producto()
	private GrupoDAO gdao;
	private SubgrupoDAO sgdao;
	private UnimedidaDAO udao;
	private MarcaDAO mdao;
	Grupo grupo = new Grupo();
	Subgrupo subgrupo = new Subgrupo();
	Unimedida umedida = new Unimedida();
	Marca marca = new Marca();
	private String datos[][];

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	protected ProductoDAO pdao = new ProductoDAO();
	private JTextField txtnomcorto;

	public FrmProductos(String titulo, BarraMaestro barra) {
		super(titulo, barra);

		JTabbedPane tabPanel = new JTabbedPane(JTabbedPane.TOP);

		JPanel panel_1 = new JPanel();
		tabPanel.addTab("Datos Generales del Producto", null, panel_1, null);
		panel_1.setLayout(null);
		JLabel lblGrupoDeProductos = new JLabel("Familia de Productos");
		lblGrupoDeProductos.setBounds(5, 10, 98, 14);
		panel_1.add(lblGrupoDeProductos);

		cntgrupo = new cntGrupo(){
			private static final long serialVersionUID = 1L;
			public Window getFormulario(){
			return (Window) VariablesGlobales.home;
			}};
		cntgrupo.txtCodigo.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				if (!cntgrupo.txtCodigo.getText().equals("")) {
					sgdao = new SubgrupoDAO();
					Object id_grupo = cntgrupo.txtCodigo.getText();
					grupo = gdao.find(id_grupo);
					List<Subgrupo> subgrupoL = sgdao.findAllbyGrupo1(grupo);
					datos = new String[subgrupoL.size()][2];
					for (int i = 0; i < subgrupoL.size(); i++) {
						subgrupo = (Subgrupo) subgrupoL.get(i);
						datos[i][0] = subgrupo.getId().getIdsubgrupo();
						datos[i][1] = subgrupo.getDescripcion();
					}
					cntSubGrupo.cargar_informacion(datos);
				}
			}
		});
		cntgrupo.setBounds(149, 10, 261, 25);
		panel_1.add(cntgrupo);

		JLabel lblSubgrupoDeProductos = new JLabel("SubFamilia de Productos");
		lblSubgrupoDeProductos.setBounds(5, 40, 116, 14);
		panel_1.add(lblSubgrupoDeProductos);

		cntSubGrupo = new cntSubGrupo(cntgrupo){
			private static final long serialVersionUID = 1L;
			public Window getFormulario(){
			return (Window) VariablesGlobales.home;
			}};
		cntSubGrupo.txtCodigo.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (!cntSubGrupo.txtCodigo.getText().equals("") && !cntSubGrupo.txtDescripcion.getText().equals("")) {
					SubgrupoPK sgpk = new SubgrupoPK();
					sgpk.setGrupoIdgrupo(cntgrupo.txtCodigo.getText());
					sgpk.setIdsubgrupo(cntSubGrupo.txtCodigo.getText());
					subgrupo.setId(sgpk);
					sgdao = new SubgrupoDAO();
					subgrupo = sgdao.find(subgrupo.getId());					
					if (subgrupo instanceof Subgrupo) {						
						cntSubGrupo.setSg(subgrupo);
					}
				}
			}
		});
		cntSubGrupo.setBounds(149, 40, 261, 25);
		panel_1.add(cntSubGrupo);

		JLabel lblNewLabel = new JLabel("Codigo");
		lblNewLabel.setBounds(5, 65, 46, 14);
		panel_1.add(lblNewLabel);

		JLabel lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setBounds(5, 90, 61, 14);
		panel_1.add(lblDescripcion);

		JLabel lblNombreCorto = new JLabel("Nombre Corto");
		lblNombreCorto.setBounds(5, 115, 90, 14);
		panel_1.add(lblNombreCorto);

		txtCodigo = new JTextField();
		txtCodigo.setBounds(149, 65, 96, 20);
		panel_1.add(txtCodigo);
		txtCodigo.setColumns(10);

		txtDescripcion = new JTextField();
		txtDescripcion.setBounds(149, 90, 263, 20);
		panel_1.add(txtDescripcion);
		txtDescripcion.setColumns(10);

		txtnomcorto = new JTextField();
		txtnomcorto.setBounds(149, 115, 86, 20);
		panel_1.add(txtnomcorto);
		txtnomcorto.setColumns(10);

		JLabel lblUnidadDeMedida = new JLabel("Unidad de Medida");
		lblUnidadDeMedida.setBounds(5, 147, 90, 14);
		panel_1.add(lblUnidadDeMedida);

		cntmedida = new cntMedida(){
			private static final long serialVersionUID = 1L;
			public Window getFormulario(){
			return (Window) VariablesGlobales.home;
			}};
		cntmedida.txtCodigo.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (!cntmedida.txtCodigo.getText().equals("")) {
					umedida = udao.find(cntmedida.txtCodigo.getText());
					cntmedida.setUnimedida(umedida);
				}
			}
		});
		cntmedida.setBounds(149, 147, 261, 25);
		panel_1.add(cntmedida);

		JLabel lblMarcas = new JLabel("Marca de Producto");
		lblMarcas.setBounds(5, 172, 90, 14);
		panel_1.add(lblMarcas);

		cntmarca = new cntMarca(){
			private static final long serialVersionUID = 1L;
			public Window getFormulario(){
			return (Window) VariablesGlobales.home;
			}};
		cntmarca.txtCodigo.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (!cntmarca.txtCodigo.getText().equals("")) {
					marca = mdao.find(cntmarca.txtCodigo.getText());
					cntmarca.setMarca(marca);
				}
			}
		});
		cntmarca.setBounds(149, 172, 261, 25);
		panel_1.add(cntmarca);

		JPanel panel = new JPanel();
		tabPanel.addTab("Propiedades del Producto", null, panel, null);

		JCheckBox chckbxEsDescrte = new JCheckBox("Es Descrte");
		panel.add(chckbxEsDescrte);

		JCheckBox chckbxEsProductoVenta = new JCheckBox("Es Producto Venta");
		panel.add(chckbxEsProductoVenta);

		JCheckBox chckbxEsProductoTerminado = new JCheckBox(
				"Es Producto Terminado");
		panel.add(chckbxEsProductoTerminado);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addGroup(
				Alignment.TRAILING,
				groupLayout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(tabPanel, GroupLayout.DEFAULT_SIZE, 437,
								Short.MAX_VALUE).addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(
				Alignment.TRAILING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(tabPanel, GroupLayout.DEFAULT_SIZE, 312,
								Short.MAX_VALUE).addContainerGap()));
		getContentPane().setLayout(groupLayout);
		llenar_contenedores();
	}

	@Override
	public void actualiza_objeto(Object prod) {
		producto = (Producto) prod;
		this.setProducto(producto);
		this.cntSubGrupo.setSg(producto.getSubgrupo());
		this.cntmedida.setUnimedida(producto.getUnimedida());
		this.cntmarca.setMarca(producto.getMarca());
		this.llenar_datos();
		this.vista_noedicion();
	}

	public void grabar() {
		try {
			if (getEstado().equals(NUEVO)) {
				Producto prod = new Producto();
				setProducto(prod);
			}
			getProducto().setIdproductos(this.txtCodigo.getText());
			getProducto().setDescripcion(this.txtDescripcion.getText());
			getProducto().setDescCorta(this.txtnomcorto.getText());
			getProducto().setSubgrupo(this.cntSubGrupo.getSg());
			getProducto().setUnimedida(this.cntmedida.getUnimedida());
			getProducto().setMarca(this.cntmarca.getMarca());
			pdao.crear_editar(getProducto());
			super.grabar();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex);
		}

	}

	@Override
	public void vista_edicion() {
		if (getEstado().equals(NUEVO))
			txtCodigo.setEditable(true);
		txtDescripcion.setEditable(true);
		txtnomcorto.setEditable(true);
		this.cntgrupo.txtCodigo.setEditable(true);
		this.cntSubGrupo.txtCodigo.setEditable(true);
		this.cntgrupo.btnBuscar.setEnabled(true);
		this.cntSubGrupo.btnBuscar.setEnabled(true);
		this.cntmedida.txtCodigo.setEnabled(true);
		this.cntmedida.btnBuscar.setEnabled(true);
		this.cntmarca.txtCodigo.setEnabled(true);
		this.cntmarca.btnBuscar.setEnabled(true);
	}

	@Override
	public void vista_noedicion() {
		txtCodigo.setEditable(false);
		txtDescripcion.setEditable(false);
		txtnomcorto.setEditable(false);
		this.cntgrupo.txtCodigo.setEditable(false);
		this.cntSubGrupo.txtCodigo.setEditable(false);
		this.cntgrupo.btnBuscar.setEnabled(false);
		this.cntSubGrupo.btnBuscar.setEnabled(false);
		this.cntmedida.txtCodigo.setEnabled(false);
		this.cntmedida.btnBuscar.setEnabled(false);
		this.cntmarca.txtCodigo.setEnabled(false);
		this.cntmarca.btnBuscar.setEnabled(false);
	}

	@Override
	public void llenar_datos() {
		if (getProducto() instanceof Producto) {
			grupo = this.getProducto().getSubgrupo().getGrupo();
			subgrupo = this.getProducto().getSubgrupo();
			this.cntgrupo.txtCodigo.setText(grupo.getIdgrupo());
			this.cntgrupo.txtDescripcion.setText(grupo.getDescripcion());
			this.cntSubGrupo.txtCodigo
					.setText(subgrupo.getId().getIdsubgrupo());
			this.cntSubGrupo.txtDescripcion.setText(subgrupo.getDescripcion());
			this.txtCodigo.setText(this.getProducto().getIdproductos());
			this.txtDescripcion.setText(this.getProducto().getDescripcion());
			this.txtnomcorto.setText(this.getProducto().getDescCorta());
			this.cntmarca.txtCodigo.setText(this.getProducto().getMarca()
					.getIdmarca());
			this.cntmarca.txtDescripcion.setText(this.getProducto().getMarca()
					.getDescripcion());
			this.cntmedida.txtCodigo.setText(this.getProducto().getUnimedida()
					.getIdunimedida());
			this.cntmedida.txtDescripcion.setText(this.getProducto()
					.getUnimedida().getDescripcion());
		} else {
			this.cntgrupo.txtCodigo.setText(null);
			this.cntgrupo.txtDescripcion.setText(null);
			this.cntSubGrupo.txtCodigo.setText(null);
			this.cntSubGrupo.txtDescripcion.setText(null);
			this.cntmarca.txtCodigo.setText(null);
			this.cntmarca.txtDescripcion.setText(null);
			this.cntmedida.txtCodigo.setText(null);
			this.cntmedida.txtDescripcion.setText(null);
			this.txtCodigo.setText(null);
			this.txtDescripcion.setText(null);
			this.txtnomcorto.setText(null);
		}
	}

	public void llenar_contenedores() {
		gdao = new GrupoDAO();
		List<Grupo> grupoL = gdao.findAll();
		datos = new String[grupoL.size()][2];
		for (int i = 0; i < grupoL.size(); i++) {
			grupo = (Grupo) grupoL.get(i);
			datos[i][0] = grupo.getIdgrupo();
			datos[i][1] = grupo.getDescripcion();
		}
		cntgrupo.cargar_informacion(datos);
		udao = new UnimedidaDAO();
		List<Unimedida> umedidaL = udao.findAll();
		datos = new String[umedidaL.size()][2];
		for (int i = 0; i < umedidaL.size(); i++) {
			umedida = (Unimedida) umedidaL.get(i);
			datos[i][0] = umedida.getIdunimedida();
			datos[i][1] = umedida.getDescripcion();
		}
		cntmedida.cargar_informacion(datos);
		mdao = new MarcaDAO();
		List<Marca> marcaL = mdao.findAll();
		datos = new String[marcaL.size()][2];
		for (int i = 0; i < marcaL.size(); i++) {
			marca = (Marca) marcaL.get(i);
			datos[i][0] = marca.getIdmarca();
			datos[i][1] = marca.getDescripcion();
		}
		cntmarca.cargar_informacion(datos);
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
	public void anular() {
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
	public void setSelected(boolean selected) throws PropertyVetoException {
		controlador.VariablesGlobales.home.getBarraMaestro().setVisible(
				selected);
		if (selected) {
			controlador.VariablesGlobales.home.getBarraMaestro()
					.setFormMaestro(this);
		}
		super.setSelected(selected);
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

}
