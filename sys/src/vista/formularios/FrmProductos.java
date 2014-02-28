package vista.formularios;

import vista.barras.BarraMaestro;
import vista.contenedores.cntGrupo;
import vista.contenedores.cntSubGrupo;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import dao.ProductoDAO;
import entity.Grupo;
import entity.Producto;
import entity.Subgrupo;

import java.beans.PropertyVetoException;

import javax.swing.JTabbedPane;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class FrmProductos extends AbstractMaestro {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtCodigo;
	private JTextField txtDescripcion;
	public final cntGrupo cntgrupo;
	public final cntSubGrupo cntSubGrupo;
	private Producto producto;// = new Producto()

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

		cntgrupo = new cntGrupo();
		cntgrupo.setBounds(149, 10, 261, 25);
		panel_1.add(cntgrupo);

		JLabel lblSubgrupoDeProductos = new JLabel("SubFamilia de Productos");
		lblSubgrupoDeProductos.setBounds(5, 40, 116, 14);
		panel_1.add(lblSubgrupoDeProductos);

		cntSubGrupo = new cntSubGrupo(cntgrupo);
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

	}

	public void grabar() {
		try {
			Producto p = new Producto();
			p.setIdproductos(this.txtCodigo.getText());
			p.setDescripcion(this.txtDescripcion.getText());
			p.setDescCorta(this.txtnomcorto.getText());
			p.setSubgrupo(this.cntSubGrupo.getSg());
			pdao.crear_editar(p);
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
	}

	@Override
	public void anular() {
		// TODO Auto-generated method stub

	}

	@Override
	public void llenar_datos() {
		if (getProducto() instanceof Producto) {
			Grupo grupo = new Grupo();
			Subgrupo subgrupo = new Subgrupo();
			grupo = this.getProducto().getSubgrupo().getGrupo();
			subgrupo = this.getProducto().getSubgrupo();
			this.cntgrupo.txtCodigo.setText(grupo.getIdgrupo());
			this.cntgrupo.txtDescripcion.setText(grupo.getDescripcion());
			this.cntSubGrupo.txtCodigo.setText(subgrupo.getId().getIdsubgrupo());
			this.cntSubGrupo.txtDescripcion.setText(subgrupo.getDescripcion());
			this.txtCodigo.setText(this.getProducto().getIdproductos());
			this.txtDescripcion.setText(this.getProducto().getDescripcion());
			this.txtnomcorto.setText(this.getProducto().getDescCorta());
		} else {
			this.cntgrupo.txtCodigo.setText(null);
			this.cntgrupo.txtDescripcion.setText(null);
			this.cntSubGrupo.txtCodigo.setText(null);
			this.cntSubGrupo.txtDescripcion.setText(null);
			this.txtCodigo.setText(null);
			this.txtDescripcion.setText(null);
			this.txtnomcorto.setText(null);
		}
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
	public void nuevo_lista() {

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

	public void metodo(Producto producto) {
		this.setProducto(producto);
		this.cntSubGrupo.setSg(producto.getSubgrupo());
		this.llenar_datos();
		this.vista_noedicion();
	}
}
