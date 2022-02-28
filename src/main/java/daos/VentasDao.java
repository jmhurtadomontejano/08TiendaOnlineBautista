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
import Dto.DetalleventaPK;
import Dto.Usuarios;
import java.util.ArrayList;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;

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
    
    public int createVenta(Ventas v){
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
        } catch(Exception e) {
            System.out.println(e.getMessage());            
            return 0;
        }
    }
    
    public void createDetalle(Detalleventas dv){
        try {
            //Le decimos a la entitymanager que inicie la transacción
            em.getTransaction().begin();
            //Le decimos que inserte un objeto (Usuarios)
            em.persist(dv);
            //Le pedimos a la transacción que se ejecute y complete            
            em.getTransaction().commit();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

}
