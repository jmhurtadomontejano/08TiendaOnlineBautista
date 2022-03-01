/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import Dto.Categorias;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;

/**
 *
 * @author jmhur
 */
public class CategoriasDao {

    private EntityManagerFactory emf;
    private EntityManager em;

    public CategoriasDao() {
        //Creamos el entitymanagerfactory
        this.emf = Persistence.createEntityManagerFactory("unidad1");
        //A partir de él, creamos el entitymanager
        this.em = emf.createEntityManager();
    }
    
    public void createCategoria(Categorias c) {
        try {
            //Le decimos a la entitymanager que inicie la transacción
            em.getTransaction().begin();
            //Le decimos que inserte un objeto (Usuarios)
            em.persist(c);
            //Le pedimos a la transacción que se ejecute y complete
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<Categorias> getCategorias() {
        try {
            //Creamos un query a través de la entitymanager
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            //Realizamos un query de select de la case Categorias (Categorias.class)
            //Lo que hace esta consulta es decirle a la query que seleccione los datos de la clase Categorias
            cq.select(cq.from(Categorias.class));
            //Creamos el query através del criteria
            Query q = em.createQuery(cq);
            //Devolvemos el resultado
            return q.getResultList();
        } finally {
            //Cerramos la conexión            
            em.close();
        }
    }
 public Categorias getCategoryName(String name){
        try {
            //Creamos un query para realizar la consulta que tenemos en la clase usuarios, y así darle un parametro.        
            Query q = em.createNamedQuery("Categorias.findByNombre");
            //Le colocamos el parametro a esa consulta.
            q.setParameter("nombre", name);
            //Devolvemos el resultado
            return (Categorias) q.getSingleResult();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            //Si no existe retornamos null
            return null;
        } finally {
            em.close();
        }
    }
}
