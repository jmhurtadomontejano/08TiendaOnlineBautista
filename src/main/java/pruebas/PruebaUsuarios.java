/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas;

import Dto.Usuarios;
import daos.UsuariosDao;
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
public class PruebaUsuarios {

    public static void main(String[] args) {
        /*Creamos un usuario y lo igualamos al UsuarioDaoImpl con la persistencia a Unidad1*/
        UsuariosDao uDao = new UsuariosDao(/*"unidad1"*/);
        /*Insertamos un usuario a la fuerza - El email debe ser unico, sino no se registrará*/
        System.out.println("INSERTAR UN USUARIO NUEVO - ASEGURATE DE QUE EL EMAIL ES UNICO:");
        String emailUsuarioNuevo = "email@gmail.com";
        Usuarios u = new Usuarios(22, "Montes", "Pablo", "Argamasilla de Alba", "Cliente", "pablo2@gmail.com", "1234");
        /* if(u.getCorreo()==uDao.buscarPorEmail(emailUsuarioNuevo).getCorreo()){
            System.out.println("* El usuario que intentas insertar ya existe");
        }else{
            
             System.out.println("Se ha insertado el usuario con email: " + emailUsuarioNuevo);
        }*/
   //     uDao.insertar(u);


        /*Obtenemos un listado de usuarios llamando a buscarTodos de UsuarioDAO para comprobar que se ha añadido el nuevo*/
        System.out.println("LISTADO DE USUARIOS: ");
   //     List<Usuarios> listaUsuarios = uDao.buscarTodos();
 /*       for (Object o : listaUsuarios) {
            Usuarios usu = (Usuarios) o;
            System.out.println("* " + usu.getIdUsuario() + " " + usu.getNombre() + " " + usu.getApellidos() + " " + usu.getCorreo());
        }
*/
        /*Buscar usuario Por ID*/
        int idBuscar = 3;
        System.out.println("BUSCAR USUARIO POR ID: " + idBuscar);
  //      System.out.println("* " + uDao.buscarPorId(idBuscar).toString());
        /*Buscar usuario Por nombre*/
        String nombreBuscar = "Juan";
        System.out.println("BUSCAR USUARIO POR NOMBRE: " + nombreBuscar);
 //       System.out.println("* " + uDao.buscarPorNombre(nombreBuscar).toString());
        /*Buscar usuario Por email*/
        String emailBuscar = "miguel@correo.es";
        System.out.println("BUSCAR USUARIO POR EMAIL: ");
  //      System.out.println("* " + uDao.buscarPorEmail(emailBuscar).toString());

        /*Modificar un usuario*/
        System.out.println("VAMOS A MODIFICAR UN USUARIO");
        Usuarios updateUser = new Usuarios(58, "Prueba", "Pepe", "Tomelloso", "Cliente", "pepsdfsdf4@correo.es", "1234");
        System.out.println("* " + updateUser);
 //       uDao.editar(updateUser);

        /*borrar usuario por ID en el parentesis introducimos el ID a borrar*/
        System.out.println("BORRAR USUARIO POR ID:");
        int idUsuarioParaBorrar = 69;
 /*       if (uDao.buscarPorId(idUsuarioParaBorrar) != null) {
            System.out.println("* Se va a borrar el usuario:" + idUsuarioParaBorrar + ", en el próximo listado no aparecerá.");
            System.out.println(uDao.buscarPorId(idUsuarioParaBorrar).toString());
            uDao.eliminar(idUsuarioParaBorrar);
        } else {
            System.out.println("* El usuario que intentas borrar por ID no existe. cambia el ID de idUsuarioParaBorrar");
        }
*/
        /*borrar usuario por Usuario en el parentesis introducimos el Usuario a borrar*/
        System.out.println("BORRAR USUARIO POR USUARIO");
  /*      int idUsuarioBorrar = 73;
        Usuarios usuBorrar = uDao.buscarPorId(idUsuarioBorrar);
        if (usuBorrar != null) {
            System.out.println("* Se va a borrar el usuario:" + usuBorrar + ", en el próximo listado no aparecerá.");
            uDao.eliminar(usuBorrar);
        } else {
            System.out.println("* El usuario que intentas borrar no existe");
        }
*/
    }

}
