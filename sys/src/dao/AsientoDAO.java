package dao;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import vista.utilitarios.StringUtils;
import entity.Asiento;
import entity.Consumidor;
import entity.Consumidor_;
import entity.Subdiario;

public class AsientoDAO extends AbstractDAO<Asiento> {
	private static final String _ceros = "0000000000";

	public AsientoDAO() {
		super(Asiento.class);
	}

	@SuppressWarnings("unchecked")
	public String getMaxNumerador(Subdiario subdiario, int anio, int mes) {
		CriteriaQuery<String> q = cb.createQuery(String.class);
		Root c = q.from(Asiento.class);
		q.select(cb.max(c.get("numerador"))).where(
				cb.and(cb.equal(c.get("subdiario"), subdiario),
						cb.equal(c.get("anio"), anio),
						cb.equal(c.get("mes"), mes)));
		String numerador = getEntityManager().createQuery(q).getSingleResult();
		int ult;
		if (numerador == null)
			ult = 0;
		else
			ult = Integer.parseInt(numerador);
		ult++;

		
		return StringUtils._right(_ceros + String.valueOf(ult).trim(), 10);
	}
}
