package vista.contenedores;
import java.util.List;

import vista.MainFrame;
import vista.utilitarios.busqueda;
import dao.AreaDAO;
import entity.Area;

public class cntArea extends AbstractCntBuscar {
	private static final long serialVersionUID = 1L;
	Area area = new Area();
	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public cntArea() {
		super();
	}

	@Override
	public void buscar() {
		AreaDAO areaDAO = new	AreaDAO();
		List<Area> areaL = areaDAO.findAll();
		int nFilas = areaL.size();
		String area[][] = new String[nFilas][2];
		for(int i = 0;i<nFilas;i++){					
			area[i][0] = areaL.get(i).getIdarea();
			area[i][1] = areaL.get(i).getDescripcion();
		}
		
		busqueda d = new busqueda(cntArea.this,area);				
		d.setModal(true);
		d.setBounds(160, 180, 550, 450);		
		MainFrame.getDesktopPane().add(d);	
		d.setVisible(true);		
	}	
	

}
