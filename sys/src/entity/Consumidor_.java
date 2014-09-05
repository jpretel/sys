package entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "Dali", date = "2014-02-16T15:12:27.278-0500")
@StaticMetamodel(Consumidor.class)
public class Consumidor_ {
	public static volatile SingularAttribute<Consumidor, String> idconsumidor;
	public static volatile SingularAttribute<Consumidor, String> descripcion;
	public static volatile SingularAttribute<Consumidor, String> jerarquia;
	public static volatile SingularAttribute<Consumidor, String> tipo;
	public static volatile SingularAttribute<Consumidor, Consumidor> consumidor;
	public static volatile ListAttribute<Consumidor, Consumidor> consumidors;
}