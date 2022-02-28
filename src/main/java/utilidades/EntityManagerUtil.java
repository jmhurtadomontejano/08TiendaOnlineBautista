/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *
 * @author jmhur
 */
public class EntityManagerUtil {
 
    private static EntityManager em;
/**
 * Obtiene un Entity Manager a partir de una unidad de persistencia
 * @param up  Unidad de persistencia
 * @return 
 */
    public static EntityManager getEM(String up) {
        if (em == null || !em.isOpen()) {
            em = Persistence.createEntityManagerFactory(up).createEntityManager();
        }
        return em;
    }

}
