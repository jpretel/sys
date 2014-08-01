package vista.contenedores;

import java.util.List;

import vista.utilitarios.busqueda;
import dao.UnimedidaDAO;
import entity.Unimedida;
import vista.MainFrame;

public class cntMedida extends AbstractCntBuscar {
	private static final long serialVersionUID = 1L;
	Unimedida unimedida = new Unimedida();
	public Unimedida getUnimedida() {
		return unimedida;
	}

	public void setUnimedida(Unimedida unimedida) {
		this.unimedida = unimedida;
	}

	public cntMedida() {
		super();
	}

	@Override
	public void buscar() {
		UnimedidaDAO sgDAO = new	UnimedidaDAO();
		List<Unimedida> grupoL = sgDAO.findAll();
		int nFilas = grupoL.size();
		String grupo[][] = new String[nFilas][2];
		for(int i = 0;i<nFilas;i++){
			unimedida = (Unimedida)grupoL.get(i);
			setUnimedida(unimedida);
			grupo[i][0] = grupoL.get(i).getIdunimedida();
			grupo[i][1] = grupoL.get(i).getDescripcion();
		}
		
		busqueda d = new busqueda(cntMedida.this,grupo);				
		d.setModal(true);
		d.setBounds(160, 180, 550, 450);		
		MainFrame.getDesktopPane().add(d);	
		d.setVisible(true);		
	}	
	

}
