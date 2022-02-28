/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas;

import Dto.Categorias;
import Dto.Productos;
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
public class PruebaProducto {

    public static void main(String[] args) {
        /*Creamos un producto y lo igualamos al ProductosDao con la persistencia a Unidad1*/
        ProductosDao prodDao = new ProductosDao(/*"unidad1"*/);
        /*Insertamos un producto a la fuerza*/
        double precioDouble = 18.28000;
        BigDecimal precioBigDecimal = new BigDecimal(precioDouble);
        Categorias c = new Categorias();
        c.setIdCategoria(2);
 //       Productos p = new Productos(19, "Productos19", "Descripcion19", precioBigDecimal, "imagen.jpg", c);
//        prodDao.insertar(p);
/*
        //*Obtenemos un listado de productos llamando a buscarTodos de ProductosDAO para comprobar que se ha añadido el nuevo
        System.out.println("LISTADO DE PRODUCTOS:");
        List<Productos> listaProductoss = prodDao.getProductoss();
        for (Object o : listaProductoss) {
            Productos prod = (Productos) o;
            System.out.println("* " + prod.getIdProductos() + " " + prod.getNombre() + " "+ prod.getDescripcion()+ " " + prod.getImagen());
        }
*/
        /*Buscar producto Por ID*/
        System.out.println("BUSCAR PRODUCTO POR ID: ");
      //  System.out.println("*" + prodDao.buscarPorId(3).toString());
        /*Buscar producto Por nombre*/
        System.out.println("BUSCAR PRODUCTO POR NOMBRE: ");
      //  System.out.println("*" + prodDao.buscarPorNombre("Vino tinto Rioja").toString());


        /*Modificar un producto*/
  /*      System.out.println("MODIFICAR UN PRODUCTO");
        precioBigDecimal = BigDecimal.valueOf(28.16);
        Productos updateProduct = new Productos(15);//Cambiamos aqui el numero del producto a cambiar en el resto del metodo
        System.out.println("*El producto" + updateProduct.get() + " contiene la siguiente información y va a ser modificado: " + updateProduct.toString());
        updateProduct = new Productos(updateProduct.getIdProductos(), "Productos", "MODIFICADO descripcion producto prueba Juanmy", precioBigDecimal, "prueba.jpg", c);
        prodDao.editar(updateProduct);
        System.out.println("*Se ha modificado el producto" + updateProduct.getIdProductos() + " y ahora contiene la siguiente información: " + updateProduct.toString());
*/
        /*borrar producto por ID en el parentesis introducimos el ID a borrar*/
  /*      System.out.println("BORRAR UN PRODUCTO POR ID");
        int idProductosBorrarPorID = 16;//iguala aqui el id para borrarlo
        if (prodDao.buscarPorId(idProductosBorrarPorID) != null) {
            System.out.println("*Se va a borrar el producto:" + idProductosBorrarPorID + ", en el próximo listado no aparecerá");
            System.out.println(prodDao.buscarPorId(idProductosBorrarPorID).toString());
            prodDao.eliminar(idProductosBorrarPorID);
        } else {
            System.out.println("*El Productos que intentas borrar por ID no existe.");
        }
        //borrar producto por Productos en el parentesis introducimos el Productos a borrar
        System.out.println("BORRAR UN PRODUCTO POR PRODUCTO");
        int idProductosBorrarPorProductos = 14;
        Productos prodBorrar = prodDao.buscarPorId(idProductosBorrarPorProductos);
        if (prodBorrar != null) {
            System.out.println("* Se va a borrar el producto:" + prodBorrar + ", en el próximo listado no aparecerá.");
            prodDao.eliminar(prodBorrar);
        } else {
            System.out.println("* El producto que intentas borrar no existe. ");
        }
   */ }
}
