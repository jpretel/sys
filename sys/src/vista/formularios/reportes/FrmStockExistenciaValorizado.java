package vista.formularios.reportes;

//prueba
import vista.formularios.abstractforms.AbstractReporte;
import vista.utilitarios.FormValidador;
import vista.utilitarios.StringUtils;
import vista.barras.PanelBarraReporte;
import vista.contenedores.CntProducto;

import javax.persistence.Tuple;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import vista.contenedores.cntAlmacen;
import vista.contenedores.cntSucursal;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;

import dao.AlmacenDAO;
import dao.DocingresoDAO;
import dao.DocsalidaDAO;
import dao.KardexDAO;
import dao.ProductoDAO;
import dao.SucursalDAO;
import entity.Almacen;
import entity.Docingreso;
import entity.Docsalida;
import entity.Kardex;
import entity.Producto;
import entity.Sucursal;
import entity.StockExistenciasValorizadoEnt;
import dao.StockExistenciasValorizadoDAO;
import vista.controles.DSGDatePicker;

public class FrmStockExistenciaValorizado extends AbstractReporte {

	private SucursalDAO sucursalDAO = new SucursalDAO();
	private AlmacenDAO almacenDAO = new AlmacenDAO();

	public FrmStockExistenciaValorizado() {

		Calendar calendar = Calendar.getInstance();

		this.cntAlmacen = new cntAlmacen();
		this.cntAlmacen.setBounds(431, 23, 192, 20);
		pnlFiltro.add(this.cntAlmacen);

		this.cntSucursal = new cntSucursal();
		this.cntSucursal.setBounds(229, 23, 192, 20);
		pnlFiltro.add(this.cntSucursal);

		this.lblSucursal = new JLabel("Sucursal");
		this.lblSucursal.setBounds(229, 11, 86, 14);
		pnlFiltro.add(this.lblSucursal);

		this.lblAlmacen = new JLabel("Almacen");
		this.lblAlmacen.setBounds(430, 11, 86, 14);
		pnlFiltro.add(this.lblAlmacen);

		this.lblHasta = new JLabel("Hasta");
		this.lblHasta.setBounds(10, 11, 46, 14);
		pnlFiltro.add(this.lblHasta);

		this.dpHasta = new DSGDatePicker();
		this.dpHasta.setBounds(10, 22, 147, 22);
		pnlFiltro.add(this.dpHasta);
		setTitle("Saldo por Almacen");
		cntSucursal.setData(sucursalDAO.findAll());

		cntAlmacen.txtCodigo.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				super.focusLost(e);
				cntAlmacen.setData(almacenDAO.getPorSucursal(cntSucursal
						.getSeleccionado()));
			}
		});
		dpHasta.setDate(calendar.getTime());
	}

	private static final long serialVersionUID = 1L;
	private cntAlmacen cntAlmacen;
	private cntSucursal cntSucursal;
	private JLabel lblSucursal;
	private JLabel lblAlmacen;
	private JLabel lblHasta;
	private DSGDatePicker dpHasta;

	@Override
	public Object[][] getData() {
		// StockExistenciasValorizadoDAO dao=new
		// StockExistenciasValorizadoDAO();
		Sucursal sucursal;
		Almacen almacen;

		sucursal = cntSucursal.getSeleccionado();
		almacen = cntAlmacen.getSeleccionado();
		// Integer.parseInt(sucursal.getIdsucursal())
		// Integer.parseInt(almacen.getId().getIdalmacen())
		List<StockExistenciasValorizadoEnt> ListSto = null;
		try {

			ListSto = new StockExistenciasValorizadoDAO()
					.ReporteStockExistenciaValorizado(
							cntSucursal.txtCodigo.getText(),
							cntAlmacen.txtCodigo.getText());

			/*
			 * Object[] nombres = {"Codigo", "Descripcion", "Marca", "Costo",
			 * "Stock", "Total"}; DefaultTableModel dtm = new
			 * DefaultTableModel(nombres, 10); tblDatos = new JTable(dtm);
			 */
			this.tblDatos.setModel(new javax.swing.table.DefaultTableModel(
					new Object[][] {}, new String[] { "Codigo", "Descripcion",
							"Marca", "Costo", "Stock", "Total" }));

			for (StockExistenciasValorizadoEnt s : ListSto) {
				Object datos[] = { s.getCodigo(), s.getDescripcion(),
						s.getMarca(), s.getCosto(), s.getStock(),
						(s.getCosto().multiply(s.getStock())) };
				((DefaultTableModel) tblDatos.getModel()).addRow(datos);
			}

			//

		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}

		//

		return null;
	}

	@Override
	public String[] getCabeceras() {
		return new String[] { "Codigo", "Descripcion", "Marca", "Costo",
				"Stock", "Total" };

	}

	@Override
	public boolean isFiltrosValidos() {
		// TODO Auto-generated method stub
		return true;
	}
}
