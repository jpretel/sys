package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import entity.Producto;
import entity.ProductoStockMinimo;

public class ProductoStockMinimoDAO extends AbstractDAO<ProductoStockMinimo> {

	public ProductoStockMinimoDAO() {
		super(ProductoStockMinimo.class);
	}
	
	public List<ProductoStockMinimo> getPorProducto(Producto producto) {
		CriteriaQuery<ProductoStockMinimo> q = cb
				.createQuery(ProductoStockMinimo.class);
		Root<ProductoStockMinimo> c = q.from(ProductoStockMinimo.class);
		Predicate condicion = cb.equal(c.get("producto"), producto);
		q.select(c).where(condicion);
		return getEntityManager().createQuery(q).getResultList();
	}

	public List<ProductoStockMinimo> aEliminar(Producto producto,
			List<ProductoStockMinimo> stockMinimo) {
		List<ProductoStockMinimo> eliminar = new ArrayList<ProductoStockMinimo>();
		for (ProductoStockMinimo o1 : getPorProducto(producto)) {
			boolean existe = false;
			salir: for (ProductoStockMinimo o2 : stockMinimo) {
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
