package vista.formularios;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import vista.controles.DSGTableModel;
import vista.formularios.abstractforms.AbstractAsientoForm;
import vista.utilitarios.FormValidador;
import vista.utilitarios.UtilMensajes;
import dao.AsientoDAO;
import dao.DAsientoDAO;
import dao.MonedaDAO;
import dao.SubdiarioDAO;
import entity.Asiento;
import entity.DAsiento;
import entity.Producto;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class FrmAsientoDoc extends AbstractAsientoForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	AsientoDAO asientoDAO = new AsientoDAO();
	DAsientoDAO dasientoDAO = new DAsientoDAO();
	MonedaDAO monedaDAO = new MonedaDAO();
	SubdiarioDAO subdiarioDAO = new SubdiarioDAO();
	Calendar calendar = Calendar.getInstance();
	Asiento asiento = null;
	List<DAsiento> dasiento = null;

	private JTable tblasiento;
	protected JPanel pnlPrincipal;

	public FrmAsientoDoc() {
		super("Asiento Contable");
		getTxtFecha().setLocation(10, 31);
		setResizable(false);

		tblasiento = new JTable(new DSGTableModel(new String[] { "Cód Cuenta",
				"Cuenta", "Debe", "Haber", "Debe Of.", "Haber Of.", "Debe Ex.",
				"Haber Ex.", "Cod. Producto", "Producto" }) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean evaluaEdicion(int row, int column) {
				return getEditar();
			}

			@Override
			public void addRow() {
				addRow(new Object[] { "", "", "", 0.0F, 0.0F, 0.0F, 0.0F, 0.0F,
						0.0F, "", "", 0 });
			}
		});
		getScrlDetalle().setViewportView(tblasiento);

		tblasiento.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		tblasiento.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		getDAsientoTM().setNombre_detalle("Asiento");
		getDAsientoTM().setObligatorios(0, 1, 2);
		getDAsientoTM().setRepetidos(0);
		getDAsientoTM().setScrollAndTable(getScrlDetalle(), tblasiento);

	}

	public void DoLayouts() {
		this.pnlPrincipal.setLayout(null);
		GroupLayout gl_pnlPrincipal = new GroupLayout(this.pnlPrincipal);
		gl_pnlPrincipal.setHorizontalGroup(gl_pnlPrincipal
				.createParallelGroup(Alignment.LEADING)
				.addGroup(
						gl_pnlPrincipal.createSequentialGroup().addGap(7)
								.addComponent(this.lblFecha).addGap(94)
								.addComponent(this.lblSubDiario).addGap(161)
								.addComponent(this.lblNumero).addGap(4)
								.addComponent(this.lblMoneda).addGap(195)
								.addComponent(this.getLblTipoCambio())
								.addGap(8).addComponent(this.getLblTcMoneda()))
				.addGroup(
						gl_pnlPrincipal
								.createSequentialGroup()
								.addGap(7)
								.addComponent(this.getTxtFecha(),
										GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addGap(20)
								.addComponent(this.getCntSubdiario(),
										GroupLayout.PREFERRED_SIZE, 215,
										GroupLayout.PREFERRED_SIZE)
								.addGap(4)
								.addComponent(this.getTxtNumerador(),
										GroupLayout.PREFERRED_SIZE, 62,
										GroupLayout.PREFERRED_SIZE)
								.addGap(4)
								.addComponent(this.getCntMoneda(),
										GroupLayout.PREFERRED_SIZE, 236,
										GroupLayout.PREFERRED_SIZE)
								.addGap(4)
								.addComponent(this.getTxtTCambio(),
										GroupLayout.PREFERRED_SIZE, 56,
										GroupLayout.PREFERRED_SIZE)
								.addGap(8)
								.addComponent(this.getTxtTCMoneda(),
										GroupLayout.DEFAULT_SIZE, 69,
										Short.MAX_VALUE).addGap(7))
				.addGroup(
						gl_pnlPrincipal
								.createSequentialGroup()
								.addGap(384)
								.addComponent(this.lblGlosa)
								.addGap(4)
								.addComponent(this.scrollPane,
										GroupLayout.PREFERRED_SIZE, 373,
										GroupLayout.PREFERRED_SIZE))
				.addGroup(
						gl_pnlPrincipal
								.createSequentialGroup()
								.addGap(7)
								.addComponent(this.getScrlDetalle(),
										GroupLayout.PREFERRED_SIZE, 786,
										GroupLayout.PREFERRED_SIZE))
				.addGroup(
						gl_pnlPrincipal
								.createSequentialGroup()
								.addGap(13)
								.addComponent(this.lblDebe,
										GroupLayout.PREFERRED_SIZE, 116,
										GroupLayout.PREFERRED_SIZE)
								.addGap(6)
								.addComponent(this.lblHaber)
								.addGap(82)
								.addComponent(this.lblDebeOf,
										GroupLayout.PREFERRED_SIZE, 108,
										GroupLayout.PREFERRED_SIZE)
								.addGap(12)
								.addComponent(this.lblHaberOf)
								.addGap(77)
								.addComponent(this.lblDebeEx,
										GroupLayout.PREFERRED_SIZE, 77,
										GroupLayout.PREFERRED_SIZE)
								.addGap(41)
								.addComponent(this.lblHaberEx,
										GroupLayout.PREFERRED_SIZE, 97,
										GroupLayout.PREFERRED_SIZE))
				.addGroup(
						gl_pnlPrincipal
								.createSequentialGroup()
								.addGap(12)
								.addComponent(this.getTxtDebe(),
										GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addGap(9)
								.addComponent(this.getTxtHaber(),
										GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addGap(4)
								.addComponent(this.txtDebeOf,
										GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addGap(4)
								.addComponent(this.txtHaberOf,
										GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addGap(16)
								.addComponent(this.txtDebeEx,
										GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addGap(4)
								.addComponent(this.getTxtHaberEx(),
										GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)));
		gl_pnlPrincipal
				.setVerticalGroup(gl_pnlPrincipal
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_pnlPrincipal
										.createSequentialGroup()
										.addGap(6)
										.addGroup(
												gl_pnlPrincipal
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(
																this.lblFecha,
																GroupLayout.PREFERRED_SIZE,
																20,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																this.lblSubDiario,
																GroupLayout.PREFERRED_SIZE,
																20,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																this.lblNumero,
																GroupLayout.PREFERRED_SIZE,
																20,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																this.lblMoneda,
																GroupLayout.PREFERRED_SIZE,
																20,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																this.getLblTipoCambio(),
																GroupLayout.PREFERRED_SIZE,
																20,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																this.getLblTcMoneda(),
																GroupLayout.PREFERRED_SIZE,
																20,
																GroupLayout.PREFERRED_SIZE))
										.addGap(4)
										.addGroup(
												gl_pnlPrincipal
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(
																this.getTxtFecha(),
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addGroup(
																gl_pnlPrincipal
																		.createSequentialGroup()
																		.addGap(1)
																		.addComponent(
																				this.getCntSubdiario(),
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE))
														.addGroup(
																gl_pnlPrincipal
																		.createSequentialGroup()
																		.addGap(2)
																		.addComponent(
																				this.getTxtNumerador(),
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE))
														.addGroup(
																gl_pnlPrincipal
																		.createSequentialGroup()
																		.addGap(1)
																		.addComponent(
																				this.getCntMoneda(),
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE))
														.addGroup(
																gl_pnlPrincipal
																		.createSequentialGroup()
																		.addGap(2)
																		.addComponent(
																				this.getTxtTCambio(),
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE))
														.addGroup(
																gl_pnlPrincipal
																		.createSequentialGroup()
																		.addGap(2)
																		.addComponent(
																				this.getTxtTCMoneda(),
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE)))
										.addGap(10)
										.addGroup(
												gl_pnlPrincipal
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(
																this.lblGlosa)
														.addComponent(
																this.scrollPane,
																GroupLayout.PREFERRED_SIZE,
																47,
																GroupLayout.PREFERRED_SIZE))
										.addGap(8)
										.addComponent(this.getScrlDetalle(),
												GroupLayout.PREFERRED_SIZE,
												211, GroupLayout.PREFERRED_SIZE)
										.addGap(4)
										.addGroup(
												gl_pnlPrincipal
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(
																this.lblDebe)
														.addComponent(
																this.lblHaber)
														.addComponent(
																this.lblDebeOf)
														.addComponent(
																this.lblHaberOf)
														.addComponent(
																this.lblDebeEx)
														.addComponent(
																this.lblHaberEx))
										.addGap(6)
										.addGroup(
												gl_pnlPrincipal
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(
																this.getTxtDebe(),
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																this.getTxtHaber(),
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																this.txtDebeOf,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																this.txtHaberOf,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																this.txtDebeEx,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																this.getTxtHaberEx(),
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))));
		this.pnlPrincipal.setLayout(gl_pnlPrincipal);
	}

	public DSGTableModel getDAsientoTM() {
		return (DSGTableModel) tblasiento.getModel();
	}

	@Override
	public void nuevo() {
		setAsiento(new Asiento());
		Calendar c = Calendar.getInstance();
		getAsiento().setAnio(c.get(Calendar.YEAR));
		getAsiento().setMes(c.get(Calendar.MONTH) + 1);
		getAsiento().setDia(c.get(Calendar.DAY_OF_MONTH));
	}

	@Override
	public void anular() {
		// TODO Auto-generated method stub

	}

	@Override
	public void grabar() {

	}

	@Override
	public void eliminar() {
		// TODO Auto-generated method stub

	}

	@Override
	public void llenar_datos() {
		setDasiento(new ArrayList<DAsiento>());
		getDAsientoTM().limpiar();
		if (getAsiento() != null) {
			getCntSubdiario().txtCodigo
					.setText((getAsiento().getSubdiario() == null) ? ""
							: getAsiento().getSubdiario().getIdsubdiario());
			getCntSubdiario().llenar();

			getCntMoneda().txtCodigo
					.setText((getAsiento().getMoneda() == null) ? ""
							: getAsiento().getMoneda().getIdmoneda());
			getCntMoneda().llenar();

			getTxtNumerador().setValue(getAsiento().getNumerador());
			calendar.set(getAsiento().getAnio(), getAsiento().getMes() - 1,
					getAsiento().getDia(), 0, 0, 0);
			getTxtFecha().setDate(calendar.getTime());
			getTxtTCambio().setValue(getAsiento().getTcambio());
			getTxtTCMoneda().setValue(getAsiento().getTcmoneda());
			setDasiento(dasientoDAO.getPorAsiento(getAsiento()));
		} else {
			getCntSubdiario().txtCodigo.setText("");
			getCntSubdiario().llenar();
			getCntMoneda().txtCodigo.setText("");
			getCntMoneda().llenar();
			getTxtNumerador().setText("");
			getTxtFecha().setDate(null);
			getTxtTCambio().setValue(0);
			getTxtTCMoneda().setValue(0);
		}
		llenarDetalle();
	}

	public void llenarDetalle() {
		float debe = 0F, haber = 0F, debe_of = 0F, haber_of = 0F, debe_ex = 0F, haber_ex = 0F;
		for (DAsiento d : getDasiento()) {
			String idproducto = "", producto = "";
			debe += d.getDebe();
			haber += d.getHaber();
			debe_of += d.getDebe_of();
			haber_of += d.getHaber_of();
			debe_ex += d.getDebe_ex();
			haber_ex += d.getHaber_ex();
			Producto p = d.getProducto();
			if (p != null) {
				idproducto = p.getIdproducto();
				producto = p.getDescripcion();
			}
			getDAsientoTM().addRow(
					new Object[] { d.getCuenta().getIdcuenta(),
							d.getCuenta().getDescripcion(), d.getDebe(),
							d.getHaber(), d.getDebe_of(), d.getHaber_of(),
							d.getDebe_ex(), d.getHaber_of(), idproducto,
							producto, d.getId().getItem() });
		}
		getTxtDebe().setValue(debe);
		txtDebeOf.setValue(debe_of);
		txtDebeEx.setValue(debe_ex);

		getTxtHaber().setValue(haber);
		txtHaberOf.setValue(haber_of);
		getTxtHaberEx().setValue(haber_ex);
	}

	@Override
	public void cargarDatos(Object id) {
		this.id = id;
		setAsiento(asientoDAO.find(id));
		setDasiento(dasientoDAO.getPorAsiento(getAsiento()));
	}

	@Override
	public void vista_edicion() {
		FormValidador.TextFieldsEdicion(true, getCntMoneda().txtCodigo,
				getCntSubdiario().txtCodigo, getTxtNumerador(),
				getTxtTCambio(), getTxtTCMoneda(), getTxtDebe(), getTxtHaber(),
				txtDebeEx, getTxtHaberEx(), txtDebeOf, txtHaberOf);
		getTxtGlosa().setEditable(true);
		getTxtFecha().setEditable(true);
	}

	@Override
	public void vista_noedicion() {
		FormValidador.TextFieldsEdicion(false, getCntMoneda().txtCodigo,
				getCntSubdiario().txtCodigo, getTxtNumerador(),
				getTxtTCambio(), getTxtTCMoneda(), getTxtDebe(), getTxtHaber(),
				txtDebeEx, getTxtHaberEx(), txtDebeOf, txtHaberOf);
		getTxtFecha().setEditable(false);
		getTxtGlosa().setEditable(false);
	}

	@Override
	public void actualizar_tablas() {
		if (getCntMoneda() != null)
			getCntMoneda().setData(monedaDAO.findAll());

		if (getCntSubdiario() != null)
			getCntSubdiario().setData(subdiarioDAO.findAll());
	}

	@Override
	public void llenarDesdeVista() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isValidaVista() {
		return false;
	}

	public Asiento getAsiento() {
		return asiento;
	}

	public void setAsiento(Asiento asiento) {
		this.asiento = asiento;
	}

	public List<DAsiento> getDasiento() {
		return dasiento;
	}

	public void setDasiento(List<DAsiento> dasiento) {
		this.dasiento = dasiento;
	}

	@Override
	public boolean isValidaEdicion() {
		if (getAsiento() == null) {
			return false;
		}
		if (getAsiento().getTipo() == 'A') {
			UtilMensajes.mensaje_alterta("DOC_ES_DE_ORIGEN");
			return false;
		}
		return true;
	}

}
