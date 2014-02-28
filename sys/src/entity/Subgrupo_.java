package entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-02-26T15:17:59.226-0500")
@StaticMetamodel(Subgrupo.class)
public class Subgrupo_ {
	public static volatile SingularAttribute<Subgrupo, SubgrupoPK> id;
	public static volatile SingularAttribute<Subgrupo, String> descripcion;
	public static volatile ListAttribute<Subgrupo, Producto> productos;
	public static volatile SingularAttribute<Subgrupo, Grupo> grupo;
}
