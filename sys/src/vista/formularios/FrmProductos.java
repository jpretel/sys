package vista.formularios;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import vista.contenedores.cntGrupo;
import vista.contenedores.cntMarca;
import vista.contenedores.cntMedida;
import vista.contenedores.cntSubGrupo;
import vista.controles.JTextFieldLimit;
import vista.utilitarios.UtilMensajes;

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
import entity.Unimedida;

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
	public final cntMarca cntmarca;
	public final cntMedida cntmedida;
	private Producto producto;// = new Producto()
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

		JCheckBox chckbxEsDescrte = new JCheckBox("Es Descarte");
		panel.add(chckbxEsDescrte);

		JCheckBox chckbxEsProductoVenta = new JCheckBox("Es Producto Venta");
		panel.add(chckbxEsProductoVenta);

		JCheckBox chckbxEsProductoTerminado = new JCheckBox(
				"Es Producto Terminado");
		panel.add(chckbxEsProductoTerminado);
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
		try {
			if(pdao.find(getProducto().getIdproducto()) != null){
				Historial.validar("Modificar", bkEntidad , getProducto().historial(), getTitle() );
			}else{			
				Historial.validar("Nuevo", getProducto().historial(), getTitle());
			}	
			
			pdao.crear_editar(getProducto());
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
		if (!getEstado().equals(NUEVO)) {
			Subgrupo sg = getProducto().getSubgrupo();
			Grupo g = (sg == null)? null : sg.getGrupo();
			
			txtCodigo.setText(this.getProducto().getIdproducto());
			txtDescripcion.setText(this.getProducto().getDescripcion());
			txtnomcorto.setText(this.getProducto().getDescCorta());
			cntmarca.setSeleccionado(getProducto().getMarca());
			cntmedida.setSeleccionado(getProducto().getUnimedida());
			cntgrupo.setSeleccionado(g);
			cntSubGrupo.setSeleccionado(sg);
			
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
		llenar_tablas();
	}



	public void actualizaSubGrupo() {
		cntSubGrupo.setData(new SubgrupoDAO().findAllbyGrupo(cntgrupo.getSeleccionado()));
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
		if(pdao.find(txtCodigo.getText()) != null){
			bkEntidad = pdao.find(txtCodigo.getText()).historial();			
		}
		
		getProducto().setIdproducto(this.txtCodigo.getText());
		getProducto().setDescripcion(this.txtDescripcion.getText());
		getProducto().setDescCorta(this.txtnomcorto.getText());
		getProducto().setSubgrupo(this.cntSubGrupo.getSeleccionado());
		getProducto().setUnimedida(this.cntmedida.getSeleccionado());
		getProducto().setMarca(this.cntmarca.getSeleccionado());
	}

	@Override
	public boolean isValidaVista() {
		if (this.cntgrupo.txtCodigo.getText().trim().isEmpty()) {
			UtilMensajes.mensaje_alterta("DATO_REQUERIDO", "Familia de Productos");
			this.cntgrupo.txtCodigo.requestFocus();
			return false;
		}
		
		if (this.cntSubGrupo.txtCodigo.getText().trim().isEmpty()) {
			UtilMensajes.mensaje_alterta("DATO_REQUERIDO", "SubFamilia de Productos");
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

	@Override
	public void eliminar() {
		// TODO Auto-generated method stub

	}

}