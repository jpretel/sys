package vista.controles.celleditor;

import javax.swing.JTable;

import entity.Grupo;

public abstract class TxtGrupo extends JXTextFieldEntityAC<Grupo> {

	public TxtGrupo(JTable tabla,
			int ubicacion) {
		super(new String[] {"Código", "Descripción"}, new int [] {20, 60}, tabla, ubicacion);
	}

	@Override
	public boolean coincideBusqueda(Grupo entity, String cadena) {
		return false;
	}

	@Override
	public Object[] entity2Object(Grupo entity) {
		// TODO Auto-generated method stub
		return null;
	}

}
