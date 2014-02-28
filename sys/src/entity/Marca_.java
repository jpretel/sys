package entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-02-27T14:45:44.648-0500")
@StaticMetamodel(Marca.class)
public class Marca_ {
	public static volatile SingularAttribute<Marca, String> idmarca;
	public static volatile SingularAttribute<Marca, String> descripcion;
	public static volatile SingularAttribute<Marca, String> nomcorto;
	public static volatile ListAttribute<Marca, Producto> productos;
}
