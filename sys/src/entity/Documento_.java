package entity;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-02-17T07:17:10.011-0500")
@StaticMetamodel(Documento.class)
public class Documento_ {
	public static volatile SingularAttribute<Documento, String> id;
	public static volatile SingularAttribute<Documento, String> descripcion;
	public static volatile SingularAttribute<Documento, BigDecimal> factor;
	public static volatile SingularAttribute<Documento, String> origen;
	public static volatile SingularAttribute<Documento, String> tcAjuste;
}
