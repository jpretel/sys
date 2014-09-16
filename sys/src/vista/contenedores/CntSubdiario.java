package vista.contenedores;

import entity.Subdiario;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class CntSubdiario extends AbstractCntBuscar<Subdiario> {
	public CntSubdiario() {
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(txtCodigo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(txtDescripcion, GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
					.addComponent(btnBuscar, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(txtCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addComponent(txtDescripcion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(2)
					.addComponent(btnBuscar))
		);
		setLayout(groupLayout);
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void cargarDatos(Subdiario entity) {
		txtCodigo.setText(entity.getIdsubdiario());
		txtDescripcion.setText(entity.getDescripcion());
	}

	@Override
	public boolean coincideBusqueda(Subdiario entity, String cadena) {
		String cad = cadena.toLowerCase();
		if (entity.getIdsubdiario().toLowerCase().startsWith(cad)
				|| entity.getDescripcion().toLowerCase().startsWith(cad))
			return true;
		return false;
	}

	@Override
	public Object[] entity2Object(Subdiario entity) {
		return new Object[] { entity.getIdsubdiario(), entity.getDescripcion() };
	}

	@Override
	public String getEntityCode(Subdiario entity) {
		return entity.getIdsubdiario();
	}
}
