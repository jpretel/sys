package vista.formularios;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import vista.Sys;
import vista.formularios.abstractforms.AbstractAsientoList;
import vista.utilitarios.StringUtils;
import dao.AsientoDAO;
import dao.SubdiarioDAO;
import entity.Asiento;
import entity.Subdiario;

public class FrmAsientoList extends AbstractAsientoList {
	AsientoDAO asientoDAO = new AsientoDAO();
	SubdiarioDAO subdiarioDAO = new SubdiarioDAO();
	List<Asiento> lista = new ArrayList<Asiento>();
	Object[][] data = null;
	/**
	 * 
	 */
	private static final long serialVersionUID = -282928445762533337L;

	public FrmAsientoList() {
		super("Asiento Contable");
	}

	@Override
	public Object[][] getData(int idesde, int ihasta, int numero,
			Subdiario subdiario) {
		lista = asientoDAO.getFiltro(idesde, ihasta, subdiario,
				numero);
		data = new Object[lista.size()][8];
		int i = 0;

		for (Asiento a : lista) {
			Calendar c = GregorianCalendar.getInstance();
			c.set(a.getAnio(), a.getMes() - 1, a.getDia());

			data[i] = new Object[] {String.valueOf(a.getAnio() * 100 + a.getMes()),
					c.getTime(), a.getSubdiario().getDescripcion(),
					StringUtils._padl(a.getNumerador(), 10, '0'),
					a.getMoneda().getDescripcion(), a.getDebe(), a.getHaber(),
					a.getEstado() == 1 ? "Activo" : "Anulado", a.getIdasiento() };
			i++;
		}
		return data;
	}

	@Override
	public void actualiza_tablas() {
		if (cntSubdiario != null)
			cntSubdiario.setData(new SubdiarioDAO().findAll());
	}

	@Override
	public void AbrirFormulario(int row, String opcion) {
		FrmAsientoDoc frm = new FrmAsientoDoc();
		frm.actualiza_objeto(data[row][data[0].length-1], opcion);
		Sys.desktoppane.add(frm);
		frm.moveToFront();
	}
	
	@Override
	public void nuevo() {
		FrmAsientoDoc frm = new FrmAsientoDoc();
		frm.DoNuevo();
		Sys.desktoppane.add(frm);
		frm.moveToFront();
	}
	
	@Override
	protected void editar(int row) {
		AbrirFormulario(row,"EDICION");
	}

	@Override
	protected void imprimir() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void ver(int row) {
		AbrirFormulario(row,"VISTA");
	}
}
