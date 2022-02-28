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
public class UsuariosDao implements UsuarioDAO {

    String up = "unidad1";

    public UsuariosDao(String up) {
        this.up = "unidad1";
    }

    public UsuariosDao() {

    }

    @Override
    public boolean insertar(Usuarios u) {
        boolean isInsertado = false;
        try {
            EntityManager em = EntityManagerUtil.getEM(up);
            em.getTransaction().begin();
            if (em.find(Usuarios.class, u.getIdUsuario()) == null) {
                em.persist(u);
                isInsertado = true;
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            ex.getMessage();
        }
        return isInsertado;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Usuarios> buscarTodos() {
        EntityManager em = utilidades.EntityManagerUtil.getEM(up);
        String jpql = "select u from Usuario u";
        Query q = em.createQuery(jpql);
        List<Usuarios> lista = q.getResultList();
        return lista;
    }

       @Override
    public Usuarios buscarPorId(int id) {
        Usuarios usu = null;
        try {
            EntityManager em = utilidades.EntityManagerUtil.getEM(up);
            String jpql = "from Usuario u where u.idUsuario = :pid";
            Query q = em.createQuery(jpql);
            q.setParameter("pid", id);
            usu = (Usuarios) q.getSingleResult();
        } catch (Exception ex) {
            ex.getMessage();
        } finally {
            return usu;
        }
    }

    @Override
    public Usuarios buscarPorNombre(String nombre) {
        Usuarios usu = null;
        try {
            EntityManager em = utilidades.EntityManagerUtil.getEM(up);
            String jpql = "from Usuario u where u.nombre = :pnombre";
            Query q = em.createQuery(jpql);
            q.setParameter("pnombre", nombre);
            usu = (Usuarios) q.getSingleResult();
        } catch (Exception ex) {
            ex.getMessage();
        } finally {
            return usu;
        }
    }

    @Override
    public Usuarios buscarPorEmail(String email) {
        Usuarios usu = null;
        try {
            EntityManager em = utilidades.EntityManagerUtil.getEM(up);
            String jpql = "from Usuario u where u.correo = :pemail";
            Query q = em.createQuery(jpql);
            q.setParameter("pemail", email);
            usu = (Usuarios) q.getSingleResult();
        } catch (Exception ex) {
            ex.getMessage();
        } finally {
            return usu;
        }
    }

    @Override
    public boolean editar(int id) {
        boolean isEdit = false;
        try {
            EntityManager em = utilidades.EntityManagerUtil.getEM(up);
            em.getTransaction().begin();
            em.merge(id);
            em.getTransaction().commit();
            em.close();
        } catch (Exception ex) {
            ex.getMessage();
        }
        return isEdit;
    }

    @Override
    public boolean editar(Usuarios u) {
        boolean isEdit = false;
        try {
            EntityManager em = utilidades.EntityManagerUtil.getEM(up);
            em.getTransaction().begin();
            em.merge(u);
            em.getTransaction().commit();
            em.close();
        } catch (Exception ex) {
            ex.getMessage();
        }
        return isEdit;
    }


    
     @Override
    public boolean eliminar(int id) {
        boolean isEliminado = false;
        try {
            EntityManager em = EntityManagerUtil.getEM(up);
            em.getTransaction().begin();

            Usuarios uBorrar = em.find(Usuarios.class, id);
            if (uBorrar != null) {
                em.remove(uBorrar);
                isEliminado = true;
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            ex.getMessage();
        }
        return isEliminado;
    }

    @Override
public boolean eliminar(Usuarios u) {
        boolean isEliminado = false;
        try {
            EntityManager em = EntityManagerUtil.getEM(up);
            em.getTransaction().begin();
            
            Usuarios uBorrar = em.find(Usuarios.class, u.getIdUsuario());
            if (uBorrar != null) {
                u = em.merge(uBorrar);
                em.remove(u);
                isEliminado = true;
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            ex.getMessage();
        }
        return isEliminado;
    }
}
