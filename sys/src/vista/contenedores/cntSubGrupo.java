package vista.contenedores;
import static controlador.VariablesGlobales.home;

import java.util.List;

import vista.utilitarios.busqueda;
import dao.SubgrupoDAO;
import entity.Grupo;
import entity.Subgrupo;

public class cntSubGrupo extends AbstractCntBuscar {
	private static final long serialVersionUID = 1L;	
	cntGrupo cntgrupo;
	Subgrupo sg = new Subgrupo();
	public Subgrupo getSg() {
		return sg;
	}

	public void setSg(Subgrupo sg) {
		this.sg = sg;
	}

	public cntSubGrupo(cntGrupo cntgrupo) {
		super();
		this.cntgrupo = cntgrupo;
	}

	@Override
	public void buscar() { 
		SubgrupoDAO sgDAO = new SubgrupoDAO(); 
		Grupo grupo1 = new Grupo();
		String idgrupo = cntgrupo.txtCodigo.getText();
		String descgrupo = cntgrupo.txtDescripcion.getText();
		grupo1.setIdgrupo(idgrupo);
		grupo1.setDescripcion(descgrupo);
		List<Subgrupo> grupoL = sgDAO.findAllbyGrupo1(grupo1);
		int nFilas = grupoL.size();
		String grupo[][] = new String[nFilas][2];		
		for (int i = 0; i < nFilas; i++) {
			Subgrupo subgrupo = new Subgrupo();
			subgrupo = grupoL.get(i);
			if (subgrupo.getId().getGrupoIdgrupo().equals(idgrupo)) {
				this.setSg(subgrupo);
				grupo[i][0] = subgrupo.getId().getIdsubgrupo();
				grupo[i][1] = subgrupo.getDescripcion();
			}
		}

		busqueda d = new busqueda(cntSubGrupo.this, grupo);
		d.setModal(true);
		d.setBounds(160, 180, 550, 450);
		home.getDesktopPane().add(d);
		d.setVisible(true);
	}

}
