package vista.formularios.listas;

import java.util.Calendar;
import java.util.List;

import vista.utilitarios.FormValidador;
import dao.AsientoDAO;
import dao.DAsientoDAO;
import dao.MonedaDAO;
import dao.SubdiarioDAO;
import entity.Asiento;
import entity.DAsiento;

public class FrmAsientoDoc extends AbstractAsientoForm{
	
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
	public FrmAsientoDoc() {
		super("Asiento Contable");
	}

	@Override
	public void nuevo() {
		setAsiento(new Asiento());
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
		if (getAsiento() != null) {
			cntSubdiario.txtCodigo.setText(getAsiento().getSubdiario().getIdsubdiario());
			cntSubdiario.txtDescripcion.setText(getAsiento().getSubdiario().getDescripcion());
			cntMoneda.txtCodigo.setText(getAsiento().getMoneda().getIdmoneda());
			cntMoneda.txtDescripcion.setText(getAsiento().getMoneda().getDescripcion());
			txtNumerador.setValue(getAsiento().getNumerador());
			calendar.set(getAsiento().getAnio(), getAsiento().getMes() - 1, getAsiento().getDia(),0,0,0);
			txtFecha.setDate(calendar.getTime());
			txtTCambio.setValue(getAsiento().getTcambio());
			txtTCMoneda.setValue(getAsiento().getTcmoneda());
		} else {
			cntSubdiario.txtCodigo.setText("");
			cntSubdiario.txtDescripcion.setText("");
			cntMoneda.txtCodigo.setText("");
			cntMoneda.txtDescripcion.setText("");
			txtNumerador.setText("");
			txtFecha.setDate(null);
			txtTCambio.setValue(0);
			txtTCMoneda.setValue(0);
		}
	}

	@Override
	public void cargarDatos(Object id) {
		setAsiento(asientoDAO.find(id));
		setDasiento(dasientoDAO.getPorAsiento(getAsiento()));
	}

	@Override
	public void vista_edicion() {
		FormValidador.TextFieldsEdicion(true, cntMoneda.txtCodigo, cntSubdiario.txtCodigo, txtNumerador,txtTCMoneda,txtTCambio);
		
	}

	@Override
	public void vista_noedicion() {
		FormValidador.TextFieldsEdicion(false, cntMoneda.txtCodigo, cntSubdiario.txtCodigo, txtNumerador,txtTCMoneda,txtTCambio);
		txtFecha.setEditable(false);
	}

	@Override
	public void actualizar_tablas() {
		if (cntMoneda != null)
			cntMoneda.setData(monedaDAO.findAll());
		
		if (cntSubdiario != null)
			cntSubdiario.setData(subdiarioDAO.findAll());
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

}
