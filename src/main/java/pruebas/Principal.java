/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas;

import Dto.Categorias;
import Dto.Productos;
import java.math.BigDecimal;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author jmhur
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("unidad1");
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        
        Categorias cat = new Categorias(1, "Ordenadores", "cat0001.jpg");
        //al persistir la categoria me debe crear la tabla automaticamente
        em.persist(cat);
        
  /*      BigDecimal precio = new BigDecimal(1000);
        Productos p = new Productos(100, "Ordenador", "ordenador portatil", precio, "foto.jpg", cat);
        em.persist(p);
        em.getTransaction().commit();
    */    
        em.close();
    }
    
}
