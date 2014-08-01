package vista.contenedores;

import java.util.List;

import vista.MainFrame;
import vista.utilitarios.busqueda;
import dao.GrupoDAO;
import entity.Grupo;
public class cntGrupo extends AbstractCntBuscar {
	private static final long serialVersionUID = 1L;
	
	public cntGrupo() {
		super();
	}

	@Override
	public void buscar() {
		GrupoDAO sgDAO = new	GrupoDAO();
		List<Grupo> grupoL = sgDAO.findAllbyGrupo();
		int nFilas = grupoL.size();
		String grupo[][] = new String[nFilas][2];
		for(int i = 0;i<nFilas;i++){					
			grupo[i][0] = grupoL.get(i).getIdgrupo();
			grupo[i][1] = grupoL.get(i).getDescripcion();
		}
		
		busqueda d = new busqueda(cntGrupo.this,grupo);				
		d.setModal(true);
		d.setBounds(160, 180, 550, 450);		
		MainFrame.getDesktopPane().add(d);	
		d.setVisible(true);		
	}	
	

}
