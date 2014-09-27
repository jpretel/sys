package vista.formularios.reportes;

import vista.formularios.abstractforms.AbstractReporte;
import vista.contenedores.CntProducto;

import javax.swing.JLabel;

import vista.contenedores.cntAlmacen;
import vista.contenedores.cntSucursal;

import java.awt.Dimension;
import java.util.Calendar;

import org.jdesktop.swingx.JXDatePicker;

import entity.Almacen;
import entity.Producto;
import entity.Sucursal;
import vista.controles.DSGDatePicker;

public class FrmKardex extends AbstractReporte {
	public FrmKardex() {
		Calendar calendar = Calendar.getInstance();
		pnlFiltro.setPreferredSize(new Dimension(10, 90));
		
		this.cntProducto = new CntProducto();
		this.cntProducto.setBounds(10, 23, 192, 20);
		pnlFiltro.add(this.cntProducto);
		
		this.lblProducto = new JLabel("Producto");
		this.lblProducto.setBounds(10, 11, 86, 14);
		pnlFiltro.add(this.lblProducto);
		
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
		
		this.lblDesde = new JLabel("Desde");
		this.lblDesde.setBounds(10, 53, 46, 14);
		pnlFiltro.add(this.lblDesde);
		
		this.lblHasta = new JLabel("Hasta");
		this.lblHasta.setBounds(229, 53, 46, 14);
		pnlFiltro.add(this.lblHasta);
		
		this.dpDesde = new DSGDatePicker();
		this.dpDesde.setBounds(49, 54, 147, 22);
		pnlFiltro.add(this.dpDesde);
		
		this.dpHasta = new DSGDatePicker();
		this.dpHasta.setBounds(268, 54, 147, 22);
		pnlFiltro.add(this.dpHasta);
		setTitle("Kardex de Productos");
		
		
		dpDesde.setDate(calendar.getTime());
		dpHasta.setDate(calendar.getTime());
	}
	
	private static final long serialVersionUID = 1L;
	private CntProducto cntProducto;
	private JLabel lblProducto;
	private cntAlmacen cntAlmacen;
	private cntSucursal cntSucursal;
	private JLabel lblSucursal;
	private JLabel lblAlmacen;
	private JLabel lblDesde;
	private JLabel lblHasta;
	private DSGDatePicker dpDesde;
	private DSGDatePicker dpHasta;

	@Override
	public Object[][] getData() {
		//Stock Inicial
		Sucursal sucursal;
		Almacen almacen;
		Producto producto;
		
		sucursal = cntSucursal.getSeleccionado();
		almacen = cntAlmacen.getSeleccionado();
		producto = cntProducto.getSeleccionado();
		
		return null;
	}

	@Override
	public String[] getCabeceras() {
		return new String[] {"Sucursal", "Almacen", "Fecha", "Documento", "Entra", "Sale", "Stock", "P. U.", "Total"};
	}
}
