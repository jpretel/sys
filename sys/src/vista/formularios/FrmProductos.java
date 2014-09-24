package vista.formularios;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.util.List;

import vista.contenedores.cntGrupo;
import vista.contenedores.cntMarca;
import vista.contenedores.cntMedida;
import vista.contenedores.cntSubGrupo;
import vista.controles.DSGTableModel;
import vista.controles.JTextFieldLimit;
import vista.controles.celleditor.TxtImpuesto;
import vista.utilitarios.FormValidador;
import vista.utilitarios.UtilMensajes;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import dao.GrupoDAO;
import dao.ImpuestoDAO;
import dao.MarcaDAO;
import dao.ProductoDAO;
import dao.ProductoImpuestoDAO;
import dao.SubgrupoDAO;
import dao.UnimedidaDAO;
import entity.Grupo;
import entity.Impuesto;
import entity.Marca;
import entity.Producto;
import entity.ProductoImpuesto;
import entity.ProductoImpuestoPK;
import entity.Subgrupo;
import entity.Unimedida;

import javax.swing.JTabbedPane;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;

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
	private ProductoDAO productoDAO = new ProductoDAO();
	private ImpuestoDAO impuestoDAO = new ImpuestoDAO();
	private TxtImpuesto txtimpuesto;
	private List<ProductoImpuesto> impuestos;
	private ProductoImpuestoDAO productoimpuestoDAO = new ProductoImpuestoDAO();
	Grupo grupo = new Grupo();
	Subgrupo subgrupo = new Subgrupo();
	Unimedida umedida = new Unimedida();
	Marca marca = new Marca();
	String bkEntidad = null;

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	protected ProductoDAO pdao = new ProductoDAO();
	private JTextField txtnomcorto;
	private JScrollPane scrlImpuestos;
	private JTable tblImpuestos;

	public FrmProductos() {
		super("Productos");
		JTabbedPane tabPanel = new JTabbedPane(JTabbedPane.TOP);

		JPanel panel_1 = new JPanel();
		tabPanel.addTab("Datos Generales del Producto", null, panel_1, null);
		panel_1.setLayout(null);
		JLabel lblGrupoDeProductos = new JLabel("Familia de Productos");
		lblGrupoDeProductos.setBounds(5, 10, 98, 14);
		panel_1.add(lblGrupoDeProductos);

		cntgrupo = new cntGrupo();

		cntgrupo.setBounds(149, 10, 290, 25);
		panel_1.add(cntgrupo);

		JLabel lblSubgrupoDeProductos = new JLabel("SubFamilia de Productos");
		lblSubgrupoDeProductos.setBounds(5, 40, 116, 14);
		panel_1.add(lblSubgrupoDeProductos);

		cntSubGrupo = new cntSubGrupo();

		cntSubGrupo.txtCodigo.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				actualizaSubGrupo();
			}
		});

		cntSubGrupo.setBounds(149, 40, 290, 25);
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
		txtCodigo.setDocument(new JTextFieldLimit(3, true));

		txtDescripcion = new JTextField();
		txtDescripcion.setBounds(149, 90, 290, 20);
		panel_1.add(txtDescripcion);
		txtDescripcion.setColumns(10);
		txtDescripcion.setDocument(new JTextFieldLimit(70, true));

		txtnomcorto = new JTextField();
		txtnomcorto.setBounds(149, 115, 86, 20);
		panel_1.add(txtnomcorto);
		txtnomcorto.setColumns(10);
		txtnomcorto.setDocument(new JTextFieldLimit(30, true));

		JLabel lblUnidadDeMedida = new JLabel("Unidad de Medida");
		lblUnidadDeMedida.setBounds(5, 147, 90, 14);
		panel_1.add(lblUnidadDeMedida);

		cntmedida = new cntMedida();

		cntmedida.setBounds(149, 147, 290, 25);
		panel_1.add(cntmedida);

		JLabel lblMarcas = new JLabel("Marca de Producto");
		lblMarcas.setBounds(5, 172, 90, 14);
		panel_1.add(lblMarcas);

		cntmarca = new cntMarca();

		cntmarca.setBounds(149, 172, 290, 25);
		panel_1.add(cntmarca);

		JPanel panel = new JPanel();
		tabPanel.addTab("Propiedades del Producto", null, panel, null);
		panel.setLayout(null);

		JCheckBox chckbxEsDescrte = new JCheckBox("Es Descarte");
		chckbxEsDescrte.setBounds(283, 33, 83, 23);
		panel.add(chckbxEsDescrte);

		JCheckBox chckbxEsProductoVenta = new JCheckBox("Es Producto Venta");
		chckbxEsProductoVenta.setBounds(283, 7, 113, 23);
		panel.add(chckbxEsProductoVenta);

		JCheckBox chckbxEsProductoTerminado = new JCheckBox(
				"Es Producto Terminado");
		chckbxEsProductoTerminado.setBounds(283, 59, 135, 23);
		panel.add(chckbxEsProductoTerminado);

		this.scrlImpuestos = new JScrollPane();
		this.scrlImpuestos.setBounds(10, 11, 267, 184);
		panel.add(this.scrlImpuestos);

		this.tblImpuestos = new JTable(new DSGTableModel(new String[] {
				"Cód Impuesto", "Impuesto" }) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean evaluaEdicion(int row, int column) {
				if (column == 1)
					return false;
				return getEditar();
			}

			@Override
			public void addRow() {
				addRow(new Object[] { "", "" });
			}
		}) {
			private static final long serialVersionUID = 1L;

			@Override
			public void changeSelection(int row, int column, boolean toggle,
					boolean extend) {
				super.changeSelection(row, column, toggle, extend);
				if (row > -1) {
					String idimpuesto = getImpuestoTM().getValueAt(row, 0)
							.toString();
					txtimpuesto.refresValue(idimpuesto);
				}
			}
		};

		getImpuestoTM().setNombre_detalle("Impuestos");
		getImpuestoTM().setScrollAndTable(scrlImpuestos, tblImpuestos);

		txtimpuesto = new TxtImpuesto(tblImpuestos, 0) {
			private static final long serialVersionUID = 1L;

			@Override
			public void cargaDatos(Impuesto entity) {
				int row = tblImpuestos.getSelectedRow();
				if (entity == null) {
					getImpuestoTM().setValueAt("", row, 0);
					getImpuestoTM().setValueAt("", row, 1);
				} else {
					setText(entity.getIdimpuesto());
					getImpuestoTM().setValueAt(entity.getIdimpuesto(), row, 0);
					getImpuestoTM().setValueAt(entity.getDescripcion(), row, 1);
				}
				setSeleccionado(null);
			}
		};
		txtimpuesto.updateCellEditor();
		this.scrlImpuestos.setViewportView(this.tblImpuestos);
		GroupLayout groupLayout = new GroupLayout(pnlContenido);
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
		pnlContenido.setLayout(groupLayout);
	}

	@Override
	public void actualiza_objeto(Object prod) {
		setProducto((Producto) prod);
		llenar_datos();
		vista_noedicion();
	}

	public void grabar() {
		pdao.crear_editar(getProducto());

		for (ProductoImpuesto pi : productoimpuestoDAO.aEliminar(getProducto(),
				impuestos)) {
			productoimpuestoDAO.remove(pi);
		}

		for (ProductoImpuesto pi : impuestos) {
			if (productoimpuestoDAO.find(pi.getId()) == null) {
				productoimpuestoDAO.create(pi);
			} else {
				productoimpuestoDAO.edit(pi);
			}
		}

		// try {
		// if (pdao.find(getProducto().getIdproducto()) != null) {
		// Historial.validar("Modificar", bkEntidad, getProducto()
		// .historial(), getTitle());
		// } else {
		// Historial.validar("Nuevo", getProducto().historial(),
		// getTitle());
		// }
		//
		// pdao.crear_editar(getProducto());
		// } catch (Exception ex) {
		// JOptionPane.showMessageDialog(null, ex);
		// }

	}

	@Override
	public void vista_edicion() {
		if (getEstado().equals(NUEVO))
			txtCodigo.setEditable(true);
		txtDescripcion.setEditable(true);
		txtnomcorto.setEditable(true);
		getImpuestoTM().setEditar(true);
		FormValidador.CntEdicion(true, cntgrupo, cntSubGrupo, cntmedida,
				cntmarca);
	}

	@Override
	public void vista_noedicion() {
		txtCodigo.setEditable(false);
		txtDescripcion.setEditable(false);
		txtnomcorto.setEditable(false);
		getImpuestoTM().setEditar(false);
		FormValidador.CntEdicion(false, cntgrupo, cntSubGrupo, cntmedida,
				cntmarca);
	}

	@Override
	public void llenar_datos() {
		getImpuestoTM().limpiar();
		if (!getEstado().equals(NUEVO)) {
			Subgrupo sg = getProducto().getSubgrupo();
			Grupo g = (sg == null) ? null : sg.getGrupo();

			txtCodigo.setText(this.getProducto().getIdproducto());
			txtDescripcion.setText(this.getProducto().getDescripcion());
			txtnomcorto.setText(this.getProducto().getDescCorta());
			cntmarca.setSeleccionado(getProducto().getMarca());
			cntmedida.setSeleccionado(getProducto().getUnimedida());
			cntgrupo.setSeleccionado(g);
			cntSubGrupo.setSeleccionado(sg);
			impuestos = productoimpuestoDAO.getPorProducto(getProducto());

			for (ProductoImpuesto i : impuestos) {
				Impuesto im = impuestoDAO.find(i.getId().getIdimpuesto());
				getImpuestoTM()
						.addRow(new Object[] { im.getIdimpuesto(),
								im.getDescripcion() });
			}

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
			impuestos = new ArrayList<ProductoImpuesto>();
		}
		llenar_tablas();
	}

	public void actualizaSubGrupo() {
		cntSubGrupo.setData(new SubgrupoDAO().findAllbyGrupo(cntgrupo
				.getSeleccionado()));
	}

	@Override
	public void llenar_lista() {
		// TODO Auto-generated method stub
	}

	@Override
	public void llenar_tablas() {
		cntgrupo.setData(new GrupoDAO().findAll());
		cntmarca.setData(new MarcaDAO().findAll());
		cntmedida.setData(new UnimedidaDAO().findAll());
		txtimpuesto.setData(new ImpuestoDAO().findAll());
	}

	@Override
	public void anular() {
		// TODO Auto-generated method stub
	}

	@Override
	public void init() {

	}

	@Override
	public void nuevo() {
		producto = new Producto();
	}

	@Override
	public void llenarDesdeVista() {
		if (pdao.find(txtCodigo.getText()) != null) {
			bkEntidad = pdao.find(txtCodigo.getText()).historial();
		}

		String idproducto;
		idproducto = this.txtCodigo.getText();
		getProducto().setIdproducto(idproducto);
		getProducto().setDescripcion(this.txtDescripcion.getText());
		getProducto().setDescCorta(this.txtnomcorto.getText());
		getProducto().setSubgrupo(this.cntSubGrupo.getSeleccionado());
		getProducto().setUnimedida(this.cntmedida.getSeleccionado());
		getProducto().setMarca(this.cntmarca.getSeleccionado());

		int rows = tblImpuestos.getRowCount();

		impuestos = new ArrayList<ProductoImpuesto>();

		for (int i = 0; i < rows; i++) {
			ProductoImpuesto pi = new ProductoImpuesto();
			ProductoImpuestoPK id = new ProductoImpuestoPK();
			id.setIdproducto(idproducto);
			id.setIdimpuesto(getImpuestoTM().getValueAt(i, 0).toString());
			pi.setId(id);
			impuestos.add(pi);
		}
	}

	@Override
	public boolean isValidaVista() {
		if (this.cntgrupo.txtCodigo.getText().trim().isEmpty()) {
			UtilMensajes.mensaje_alterta("DATO_REQUERIDO",
					"Familia de Productos");
			this.cntgrupo.txtCodigo.requestFocus();
			return false;
		}

		if (this.cntSubGrupo.txtCodigo.getText().trim().isEmpty()) {
			UtilMensajes.mensaje_alterta("DATO_REQUERIDO",
					"SubFamilia de Productos");
			this.cntSubGrupo.txtCodigo.requestFocus();
			return false;
		}

		if (this.txtCodigo.getText().trim().isEmpty()) {
			UtilMensajes.mensaje_alterta("DATO_REQUERIDO", "Código");
			this.txtCodigo.requestFocus();
			return false;
		}

		if (getEstado().equals(NUEVO)) {
			if (pdao.find(this.txtCodigo.getText().trim()) != null) {
				UtilMensajes.mensaje_alterta("CODIGO_EXISTE");
				this.txtCodigo.requestFocus();
				return false;
			}
		}
		if (this.txtDescripcion.getText().trim().isEmpty()) {
			UtilMensajes.mensaje_alterta("DATO_REQUERIDO", "Descripción");
			this.txtDescripcion.requestFocus();
			return false;
		}

		if (this.txtnomcorto.getText().trim().isEmpty()) {
			UtilMensajes.mensaje_alterta("DATO_REQUERIDO", "Nombre corto");
			this.txtnomcorto.requestFocus();
			return false;
		}

		if (this.cntmedida.txtCodigo.getText().trim().isEmpty()) {
			UtilMensajes.mensaje_alterta("DATO_REQUERIDO", "Unidad de Medida");
			this.cntmedida.txtCodigo.requestFocus();
			return false;
		}

		if (this.cntmarca.txtCodigo.getText().trim().isEmpty()) {
			UtilMensajes.mensaje_alterta("DATO_REQUERIDO", "Marca de Producto");
			this.cntmarca.txtCodigo.requestFocus();
			return false;
		}

		return true;
	}

	public DSGTableModel getImpuestoTM() {
		return (DSGTableModel) tblImpuestos.getModel();
	}

	@Override
	public void eliminar() {
		// TODO Auto-generated method stub

	}
}