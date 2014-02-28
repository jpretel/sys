package entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-02-26T08:46:38.705-0500")
@StaticMetamodel(Grupo.class)
public class Grupo_ {
	public static volatile SingularAttribute<Grupo, String> idgrupo;
	public static volatile SingularAttribute<Grupo, String> descCorta;
	public static volatile SingularAttribute<Grupo, String> descripcion;
	public static volatile ListAttribute<Grupo, Subgrupo> subgrupos;
}
