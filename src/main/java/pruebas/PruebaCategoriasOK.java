/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas;

import Dto.Categorias;
import daos.CategoriasDao;
import Dto.Categorias;
import Dto.Productos;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author jmhur
 */
public class PruebaCategoriasOK {

    public static void main(String[] args) {
        /*Creamos un usuario y lo igualamos al CategoriaDaoImpl con la persistencia a Unidad1*/
        CategoriasDao catDao = new CategoriasDao(/*"unidad1"*/);
        /*Insertamos un usuario a la fuerza - El email debe ser unico, sino no se registrará*/
        Categorias c = new Categorias(59, "CategoriaPruebaJuanmi", "rutaImagen.jpg");
        catDao.createCategoria(c);
        
        
        /*Obtenemos un listado de usuarios llamando a buscarTodos de CategoriaDAO para comprobar que se ha añadido el nuevo*/
        System.out.println("LISTADO DE CATEGORIAS:");
        List<Categorias> listaCategorias = catDao.getCategorias();
        for (Object o : listaCategorias) {
            Categorias cat = (Categorias) o;
            System.out.println("* "+cat.getIdCategoria()+ " " + cat.getNombre() + " " +  cat.getImagen());
        }

        /*Buscar usuario Por ID*/
        int idCategoriaBuscar=3;
        System.out.println("BUSCAR CATEGORIA POR ID: ");
 //       System.out.println("* "+catDao.buscarPorId(idCategoriaBuscar).toString());
        /*Buscar usuario Por nombre*/
        System.out.println("BUSCAR CATEGORIA POR NOMBRE: ");
  //      System.out.println("* "+catDao.buscarPorNombre("Perfumería").toString());
        
        
        /*Modificar una categoria*/
        int idCategoriaEditar=55;
        String nombreCategoriaEditar= "Modificado";
        String imagenCategoriaModificar="imagen.jpg";
        System.out.println("Vamos a modificar la Categoria" + idCategoriaEditar + ", en el próximo listado se llamará " + nombreCategoriaEditar);
        Categorias updateCat = new Categorias(idCategoriaEditar, nombreCategoriaEditar, imagenCategoriaModificar);
  //      catDao.editar(updateCat);
        
        /*borrar categoria por ID*/
        int categoriaEliminar=54;
        System.out.println("Vamos a eliminar la categoria" + categoriaEliminar);
 //       catDao.eliminar(categoriaEliminar);

    }
}
