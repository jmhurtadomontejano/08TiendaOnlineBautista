package Dto.DOS;

import Dto.Categorias;
import Dto.Productos;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-02-28T20:54:35")
@StaticMetamodel(Categorias.class)
public class Categorias_ { 

    public static volatile ListAttribute<Categorias, Productos> productoList;
    public static volatile SingularAttribute<Categorias, String> imagen;
    public static volatile SingularAttribute<Categorias, Integer> idCategoria;
    public static volatile SingularAttribute<Categorias, String> nombre;

}