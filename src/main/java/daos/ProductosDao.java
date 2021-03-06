/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import Dto.Productos;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author jmhur
 */
public class ProductosDao {

    private EntityManagerFactory emf;
    private EntityManager em;

    public ProductosDao() {
        //Creamos el entitymanagerfactory
        this.emf = Persistence.createEntityManagerFactory("unidad1");
        //A partir de él, creamos el entitymanager
        this.em = emf.createEntityManager();
    }

    public List<Productos> getProductos() {
        try {
            //Creamos un query a través de la entitymanager
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            //Realizamos un query de select de la case productos (Productos.class)
            //Lo que hace esta consulta es decirle a la query que seleccione los datos de la clase productos
            cq.select(cq.from(Productos.class));
            //Creamos el query através del criteria
            Query q = em.createQuery(cq);
            //Devolvemos el resultado
            return q.getResultList();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    //Método para obtener los productos ordenados por el precio menor
    public List<Productos> getProductosLow() {
        try {
            //Creamos un query a través de la entitymanager, de la clase Productos
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            //Realizamos un root de Productos para realizar el ordenamiento.
            Root<Productos> r = cq.from(Productos.class);
            //Ordenamos al criteria query que realice el select
            cq.select(r);
            //Realizamos el ordenamiento con el select.
            cq.orderBy(em.getCriteriaBuilder().asc(r.get("precio")));
            //Creamos el query.
            Query q = em.createQuery(cq);
            //Devolvemos el resultado
            return q.getResultList();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    //Método para obtener los productos ordenados por el precio mayor
    public List<Productos> getProductosHigh() {
        try {
            //Creamos un query a través de la entitymanager, de la clase Productos
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            //Realizamos un root de Productos para realizar el ordenamiento.
            Root<Productos> r = cq.from(Productos.class);
            //Ordenamos al criteria query que realice el select
            cq.select(r);
            //Realizamos el ordenamiento con el select.
            cq.orderBy(em.getCriteriaBuilder().desc(r.get("precio")));
            //Creamos el query.
            Query q = em.createQuery(cq);
            //Devolvemos el resultado
            return q.getResultList();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Productos getProductoId(int id) {
        try {
            //Creamos un query para realizar la consulta que tenemos en la clase usuarios, y así darle un parametro.        
            Query q = em.createNamedQuery("Productos.findByIdProducto");
            //Le colocamos el parametro a esa consulta.
            q.setParameter("idProducto", id);
            //Devolvemos el resultado
            return (Productos) q.getSingleResult();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            //Si no existe retornamos null
            return null;
        }
    }

    public void delete(int id) {
        try {
            //Creamos una entidad producto
            Productos producto;
            //Iniciamos el proceso de transacción
            em.getTransaction().begin();
            //Buscamos el objeto en la base de datos
            producto = em.getReference(Productos.class, id);
            //Eliminamos el objeto a través de la consulta remove
            em.remove(producto);
            //Guardamos cambios
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void updateProduct(Productos p) {
        try {
            //Iniciamos el proceso de transacción
            em.getTransaction().begin();
            //Realizamos la actualización
            em.merge(p);
            //Guardamos cambios
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void createProduct(Productos p) {
        try {
            //Le decimos a la entitymanager que inicie la transacción
            em.getTransaction().begin();
            //Le decimos que inserte un objeto (Usuarios)
            em.persist(p);
            //Le pedimos a la transacción que se ejecute y complete
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
