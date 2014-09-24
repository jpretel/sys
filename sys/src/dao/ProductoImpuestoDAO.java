package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import entity.Producto;
import entity.ProductoImpuesto;

public class ProductoImpuestoDAO extends AbstractDAO<ProductoImpuesto> {

	public ProductoImpuestoDAO() {
		super(ProductoImpuesto.class);
	}

	public List<ProductoImpuesto> getPorProducto(Producto producto) {
		CriteriaQuery<ProductoImpuesto> q = cb
				.createQuery(ProductoImpuesto.class);
		Root<ProductoImpuesto> c = q.from(ProductoImpuesto.class);
		Predicate condicion = cb.equal(c.get("producto"), producto);
		q.select(c).where(condicion);
		return getEntityManager().createQuery(q).getResultList();
	}

	public List<ProductoImpuesto> aEliminar(Producto producto,
			List<ProductoImpuesto> impuestos) {
		List<ProductoImpuesto> eliminar = new ArrayList<ProductoImpuesto>();
		for (ProductoImpuesto o1 : getPorProducto(producto)) {
			boolean existe = false;
			salir: for (ProductoImpuesto o2 : impuestos) {
				if (o1.getId().equals(o2.getId())) {
					existe = true;
					break salir;
				}
			}
			if (!existe)
				eliminar.add(o1);
		}
		return eliminar;
	}
}
