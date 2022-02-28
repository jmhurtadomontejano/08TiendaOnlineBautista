package Dto.DOS;

import Dto.Categorias;
import Dto.Productos;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-02-28T20:54:35")
@StaticMetamodel(Productos.class)
public class Productos_ { 

    public static volatile SingularAttribute<Productos, String> descripcion;
    public static volatile SingularAttribute<Productos, BigDecimal> precio;
    public static volatile SingularAttribute<Productos, Categorias> categoria;
    public static volatile SingularAttribute<Productos, String> imagen;
    public static volatile SingularAttribute<Productos, Integer> idProducto;
    public static volatile SingularAttribute<Productos, String> nombre;

}