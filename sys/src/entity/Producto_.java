package entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-02-28T09:19:18.812-0500")
@StaticMetamodel(Producto.class)
public class Producto_ {
	public static volatile SingularAttribute<Producto, String> idproductos;
	public static volatile SingularAttribute<Producto, String> descCorta;
	public static volatile SingularAttribute<Producto, String> descripcion;
	public static volatile SingularAttribute<Producto, Integer> esDescarte;
	public static volatile SingularAttribute<Producto, Integer> esTerminado;
	public static volatile SingularAttribute<Producto, Integer> esVenta;
	public static volatile SingularAttribute<Producto, Subgrupo> subgrupo;
	public static volatile SingularAttribute<Producto, Marca> marca;
	public static volatile SingularAttribute<Producto, Unimedida> unimedida;
}
