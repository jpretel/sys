package dao;

import java.util.List;

import entity.Asiento;
import entity.DAsiento;

public class DAsientoDAO extends AbstractDAO<DAsiento> {

	public DAsientoDAO() {
		super(DAsiento.class);
	}
	
	public List<DAsiento> getPorAsiento(Asiento asiento){
		return null;
	}

}
