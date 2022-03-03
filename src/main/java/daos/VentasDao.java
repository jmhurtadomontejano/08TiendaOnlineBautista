/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import Dto.Ventas;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import utilidades.EntityManagerUtil;
import Dto.Detalleventas;
import Dto.DetalleventasPK;
import Dto.Usuarios;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author jmhur
 */
public class VentasDao {

    private EntityManagerFactory emf;
    private EntityManager em;

    public VentasDao() {
        //Creamos el entitymanagerfactory
        this.emf = Persistence.createEntityManagerFactory("unidad1");
        //A partir de él, creamos el entitymanager
        this.em = emf.createEntityManager();
    }

    public int createVenta(Ventas v) {
        try {
            //Le decimos a la entitymanager que inicie la transacción
            em.getTransaction().begin();
            //Le decimos que inserte un objeto (Usuarios)
            em.persist(v);
            //Actualizamos el objeto actual
            em.flush();
            //Le pedimos a la transacción que se ejecute y complete
            em.getTransaction().commit();
            return v.getIdVenta();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

    public void createDetalle(Detalleventas dv) {
        try {
            //Le decimos a la entitymanager que inicie la transacción
            em.getTransaction().begin();
            //Le decimos que inserte un objeto (Usuarios)
            em.persist(dv);
            //Le pedimos a la transacción que se ejecute y complete            
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Detalleventas> getDetalles(int id) {
        try {
            //Creamos un query para realizar la consulta que tenemos en la clase usuarios, y así darle un parametro.        
            Query q = em.createNamedQuery("Detalleventas.findByCodigoventa");
            //Le colocamos el parametro a esa consulta.
            q.setParameter("codigoventa", id);
            //Devolvemos el resultado
            return (List<Detalleventas>) q.getResultList();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            //Si no existe retornamos null
            return null;
        } finally {
            //Cerramos la conexión            
            em.close();
        }
    }

    public List<Ventas> getVentas(int idUser) {
        try {
            //Creamos un query para realizar la consulta que tenemos en la clase usuarios, y así darle un parametro.        
            Query q = em.createNamedQuery("Ventas.findByUser");
            //Le colocamos el parametro a esa consulta.
            q.setParameter("cliente", idUser);
            //Devolvemos el resultado
            return (List<Ventas>) q.getResultList();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            //Si no existe retornamos null
            return null;
        } finally {
            //Cerramos la conexión            
            em.close();
        }
    }

    public Ventas getVentasId(int idVenta) {
        try {
            //Creamos un query para realizar la consulta que tenemos en la clase usuarios, y así darle un parametro.        
            Query q = em.createNamedQuery("Ventas.findByIdVenta");
            //Le colocamos el parametro a esa consulta.
            q.setParameter("idVenta", idVenta);
            //Devolvemos el resultado
            return (Ventas) q.getSingleResult();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            //Si no existe retornamos null
            return null;
        } finally {
            //Cerramos la conexión            
            em.close();
        }
    }

    //Obtener las ventas listadas de la última a la primera    
    public List<Ventas> getVentas() {
        try {
            //Creamos un query a través de la entitymanager, de la clase Productos
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            //Realizamos un root de Productos para realizar el ordenamiento.
            Root<Ventas> r = cq.from(Ventas.class);
            //Ordenamos al criteria query que realice el select
            cq.select(r);
            //Realizamos el ordenamiento con el select.
            cq.orderBy(em.getCriteriaBuilder().desc(r.get("fecha")));
            //Creamos el query através del criteria
            Query q = em.createQuery(cq);
            //Devolvemos el resultado
            return q.getResultList();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void updateVentas(Ventas v) {
        try {
            //Iniciamos el proceso de transacción
            em.getTransaction().begin();
            //Realizamos la actualización
            em.merge(v);
            //Guardamos cambios
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<Ventas> getVentasFechas(Date inicio, Date hasta) {
        try {
            //Creamos un query para realizar la consulta que tenemos en la clase usuarios, y así darle un parametro.        
            Query q = em.createNamedQuery("Ventas.findByFechas");
            //Le colocamos el parametro a esa consulta.
            q.setParameter("inicio", inicio);
            q.setParameter("final", hasta);
            //Devolvemos el resultado
            return (List<Ventas>) q.getResultList();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            //Si no existe retornamos null
            return null;
        } finally {
            //Cerramos la conexión            
            em.close();
        }
    }

}
