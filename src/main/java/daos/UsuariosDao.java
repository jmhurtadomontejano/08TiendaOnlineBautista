/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import Dto.Usuarios;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import utilidades.EntityManagerUtil;

/**
 *
 * @author jmhur
 */
public class UsuariosDao {

    private EntityManagerFactory emf;
    private EntityManager em;

    public UsuariosDao() {
        //Creamos el entitymanagerfactory
        this.emf = Persistence.createEntityManagerFactory("unidad1");
        //A partir de él, creamos el entitymanager
        this.em = emf.createEntityManager();
    }

    public Usuarios getUsuarioEmail(String email) {
        try {
            //Creamos un query para realizar la consulta que tenemos en la clase usuarios, y así darle un parametro.        
            Query q = em.createNamedQuery("Usuarios.findByCorreo");
            //Le colocamos el parametro a esa consulta.
            q.setParameter("correo", email);
            //Devolvemos el resultado
            return (Usuarios) q.getSingleResult();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }finally{
            em.close();            
        }
    }
    
    public Usuarios getUsuarioId(int id){
        try {
            //Creamos un query para realizar la consulta que tenemos en la clase usuarios, y así darle un parametro.        
            Query q = em.createNamedQuery("Usuarios.findByIdUsuario");
            //Le colocamos el parametro a esa consulta.
            q.setParameter("idUsuario", id);
            //Devolvemos el resultado
            return (Usuarios) q.getSingleResult();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }finally{
            em.close();
        }
    }

    public void insertarUsuario(Usuarios u) {
        try {
            //Le decimos a la entitymanager que inicie la transacción
            em.getTransaction().begin();
            //Le decimos que inserte un objeto (Usuarios)
            em.persist(u);
            //Le pedimos a la transacción que se ejecute y complete
            em.getTransaction().commit();
        } finally {
            //Cerramos la conexión
            em.close();
        }
    }
}
