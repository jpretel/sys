package vista.contenedores;

import entity.Clieprov;
import entity.Concepto;
import java.awt.GridBagLayout;

public class CntClieprov extends AbstractCntBuscar<Clieprov> {

	private static final long serialVersionUID = 1L;

	public CntClieprov() {
		super(new String[] { "C�digo", "Raz�n Social", "RUC" }, new int[] { 200,
				350, 200 });
		GridBagLayout gridBagLayout = (GridBagLayout) getLayout();
		gridBagLayout.columnWidths = new int[]{104, 0, 0};
	}

	@Override
	public void cargarDatos(Clieprov entity) {
		if (entity == null) {
			txtCodigo.setText("");
			txtDescripcion.setText("");
		} else {
			txtCodigo.setText(entity.getIdclieprov());
			txtDescripcion.setText(entity.getRazonSocial());
		}
	}

	@Override
	public boolean coincideBusqueda(Clieprov entity, String cadena) {
		String cad = cadena.toLowerCase();
		if (entity.getIdclieprov().toLowerCase().startsWith(cad)
				|| entity.getRazonSocial().toLowerCase().startsWith(cad))
			return true;
		return false;
	}

	@Override
	public Object[] entity2Object(Clieprov entity) {
		return new Object[] { entity.getIdclieprov(), entity.getRazonSocial(),
				entity.getRuc() };
	}

	@Override
	public String getEntityCode(Clieprov entity) {
		return entity.getIdclieprov();
	}
}