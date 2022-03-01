package Dto.UNO;

import Dto.Detalleventas;
import Dto.DetalleventasPK;
import Dto.Productos;
import Dto.Ventas;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-03-02T00:22:18")
@StaticMetamodel(Detalleventas.class)
public class Detalleventas_ { 

    public static volatile SingularAttribute<Detalleventas, BigDecimal> pctDescuento;
    public static volatile SingularAttribute<Detalleventas, DetalleventasPK> detalleventasPK;
    public static volatile SingularAttribute<Detalleventas, Ventas> ventas;
    public static volatile SingularAttribute<Detalleventas, BigDecimal> pvp;
    public static volatile SingularAttribute<Detalleventas, Integer> cantidad;
    public static volatile SingularAttribute<Detalleventas, Productos> productos;

}