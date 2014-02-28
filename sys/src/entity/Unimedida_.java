package entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-02-27T14:45:44.664-0500")
@StaticMetamodel(Unimedida.class)
public class Unimedida_ {
	public static volatile SingularAttribute<Unimedida, String> idunimedida;
	public static volatile SingularAttribute<Unimedida, String> descripcion;
	public static volatile SingularAttribute<Unimedida, String> nomenclatura;
	public static volatile ListAttribute<Unimedida, Producto> productos;
}
