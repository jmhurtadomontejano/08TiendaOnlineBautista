package Dto.UNO;

import Dto.Detalleventas;
import Dto.Usuarios;
import Dto.Ventas;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-02-28T20:54:35")
@StaticMetamodel(Ventas.class)
public class Ventas_ { 

    public static volatile ListAttribute<Ventas, Detalleventas> detalleventasList;
    public static volatile SingularAttribute<Ventas, Date> fecha;
    public static volatile SingularAttribute<Ventas, Usuarios> cliente;
    public static volatile SingularAttribute<Ventas, String> estado;
    public static volatile SingularAttribute<Ventas, Integer> idVenta;

}