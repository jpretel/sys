package vista.contenedores;

import java.util.List;

import vista.MainFrame;
import vista.utilitarios.busqueda;
import dao.MarcaDAO;
import entity.Marca;
public class cntMarca extends AbstractCntBuscar {
	private static final long serialVersionUID = 1L;
	Marca marca = new Marca();
	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public cntMarca() {
		super();
	}

	@Override
	public void buscar() {
		MarcaDAO sgDAO = new	MarcaDAO();
		List<Marca> grupoL = sgDAO.findAll();
		int nFilas = grupoL.size();
		String grupo[][] = new String[nFilas][2];
		for(int i = 0;i<nFilas;i++){
			marca = (Marca)grupoL.get(i); 
			this.setMarca(marca);
			grupo[i][0] = grupoL.get(i).getIdmarca();
			grupo[i][1] = grupoL.get(i).getDescripcion();
		}
		
		busqueda d = new busqueda(cntMarca.this,grupo);				
		d.setModal(true);
		d.setBounds(160, 180, 550, 450);		
		MainFrame.getDesktopPane().add(d);	
		d.setVisible(true);		
	}	
	

}
