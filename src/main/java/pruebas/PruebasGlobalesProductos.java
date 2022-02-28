/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas;

import daos.CategoriasDao;
import Dto.Productos;
import daos.VentasDao;
import Dto.Categorias;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import daos.ProductosDao;

/**
 *
 * @author jmhur
 */
public class PruebasGlobalesProductos {

    public static void main(String[] args) {
        /*Creamos una venta y lo igualamos al VentaDaoImpl con la persistencia a Unidad1*/
        VentasDao ventDao = new VentasDao(/*"unidad1"*/);
        ProductosDao prodDao = new ProductosDao(/*"unidad1"*/);

        /*0. Insertar una nueva Categoria*/
 /*Creamos un usuario y lo igualamos al CategoriaDaoImpl con la persistencia a Unidad1*/
        CategoriasDao catDao = new CategoriasDao(/*"unidad1"*/);
        /*Insertamos una Categoria a la fuerza*/
        System.out.println("0. Insertamos una nueva Categoria");
        Categorias u = new Categorias(61, "CategoriaPruebaJuanmi", "rutaImagen.jpg");
     //   catDao.insertar(u);
        System.out.println("   * Se ha añadido la Categoria: " + u.toString());

        /*1. Mostrar todos los productos*/
        System.out.println("1. LISTADO DE PRODUCTOS:");
     /*   List<Producto> listaProductos = prodDao.getProductos();
        for (Object o : listaProductos) {
            Producto prod = (Producto) o;
            System.out.println("   * " + prod.getIdProducto() + " " + prod.getNombre() + " " + prod.getDescripcion() + " " + prod.getImagen());
        }

        //*2. Insertamos producto
        System.out.println("2. INSERTAR 1 PRODUCTO");
        double precioDouble = 18.28;
        BigDecimal precioBigDecimal = new BigDecimal(precioDouble);
        Categorias c = new Categorias();
        c.setIdCategoria(2);
        //*Insertamos un producto a la fuerza
        Producto p = new Producto(26, "PruebaBautista", "Descripcionbautista", precioBigDecimal, "imagen.jpg", c);
        System.out.println("   * Vamos a insertar el producto: " + p.toString());
        prodDao.insertar(p);

        //*3. Mostrar el Producto insertado
        System.out.println("3. MOSTRAR PRODUCTO INSERTADO ");
        System.out.println("   * El ultimo articulo insertado es: " + prodDao.buscarPorId(p.getIdProducto()));
/*
        /*4. Modificar un atributo de un producto*/
        Productos updateProduct = new Productos(24);//Cambiamos aqui el numero del producto a cambiar en el resto del metodo
        System.out.println("4. MODIFICAR EL PRODUCTO: "+updateProduct.getIdProducto());
    /*    precioBigDecimal = BigDecimal.valueOf(28.16);
        System.out.println("   * El producto" + updateProduct.getIdProducto() + " contiene la siguiente información y va a ser modificado: " + updateProduct.toString());
        updateProduct = new Producto(updateProduct.getIdProducto(), "ProductoModificado", "DescripcionModificada producto prueba Juanmy", precioBigDecimal, "prueba.jpg", c);
        prodDao.editar(updateProduct);
        System.out.println("   * Se ha modificado el producto" + updateProduct.getIdProducto() + " y ahora contiene la siguiente información: " + updateProduct.toString());
*/
        /*5. Mostrar todos los productos*/
   /*     System.out.println("5. MOSTRAR TODOS LOS PRODUCTOS");
        List<Producto> listaProductoslistar = prodDao.getProductos();
        for (Object o : listaProductoslistar) {
            Producto prod = (Producto) o;
            System.out.println("  * " + prod.getIdProducto() + " " + prod.getNombre() + " " + prod.getDescripcion() + " " + prod.getImagen());
        }
*/
        /*6. Eliminar el producto*/
        System.out.println("6.BORRAR UN PRODUCTO POR ID");
        int idProductoBorrarPorID = 18;//iguala aqui el id para borrarlo
        if (prodDao.getProductoId(idProductoBorrarPorID) != null) {
            System.out.println("   * Se va a borrar el producto:" + idProductoBorrarPorID + ", en el próximo listado no aparecerá");
            System.out.println(prodDao.getProductoId(idProductoBorrarPorID).toString());
        /*    prodDao.eliminar(idProductoBorrarPorID);*/
        } else {
            System.out.println("   * El Producto que intentas borrar por ID no existe.");
        }

        /*7. Mostrar todos los productos otra vez*/
        System.out.println("7. LISTADO DE PRODUCTOS:");
      /*  List<Producto> listaProductosComprobar = prodDao.getProductos();
        for (Object o : listaProductosComprobar) {
            Producto prod = (Producto) o;
            System.out.println("   * " + prod.getIdProducto() + " " + prod.getNombre() + " " + prod.getDescripcion() + " " + prod.getImagen());
        }
*/
    }
}
