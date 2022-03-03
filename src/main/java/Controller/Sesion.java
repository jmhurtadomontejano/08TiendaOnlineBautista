/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Controller;

import Dto.Usuarios;
import daos.UsuariosDao;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author jmhur
 */
@WebServlet(name = "Sesion", urlPatterns = {"/Sesion"})
public class Sesion extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Obtenemos la acción a seguir
        String accion = request.getParameter("accion");
        //Creamos el objeto que nos realiza las consultas a la bd.
        UsuariosDao dao = new UsuariosDao();
        Usuarios u;
        //Obtenemos la url desde donde se origina la petición, para redireccionar.
        String url = request.getHeader("Referer");
        switch (accion) {
            case "sesion":
                //Obtenemos los datos del formulario
                String email = request.getParameter("email");
                String pass = request.getParameter("pass");
                //Buscamos el correo en la base de datos.
                u = dao.getUsuarioEmail(email);
                //Verificamos si el usuario existe
                if (u != null) {
                    //Verificamos que la contraseña coincida
                    if (u.getClaveusu().equals(pass)) {
                        //Creamos la sesión
                        HttpSession sesion = request.getSession();
                        //Añadimos los atributos a la sesión
                        sesion.setAttribute("id", u.getIdUsuario());
                        sesion.setAttribute("name", u.getNombre() + " " + u.getApellidos());
                        sesion.setAttribute("role", u.getPerfil());
//Establecemos tiempo máximo de duracion de sesion
                        sesion.setMaxInactiveInterval(1000);

                        //Enviamos la respuesta
                        response.sendRedirect(url);
                    } else {
                        //Creamos el objeto sesion
                        HttpSession s = request.getSession();
                        //Enviamos los atributos del error
                        s.setAttribute("url", url);
                        s.setAttribute("error", "Contraseña incorrecta");
                        //Enviamos el error
                        response.sendRedirect("error.jsp");
                    }
                } else {
                    //Creamos el objeto sesion
                    HttpSession s = request.getSession();
                    //Enviamos los atributos del error
                    s.setAttribute("url", url);
                    s.setAttribute("error", "Usuario no existe");
                    //Enviamos el error
                    response.sendRedirect("error.jsp");
                }
                break;            
            case "registro":
                //Obtenemos todos los valores del formulario
                String name = request.getParameter("name");
                String lastname = request.getParameter("lastname");
                String location = request.getParameter("location");
                email = request.getParameter("email");
                pass = request.getParameter("pass");
                String perfil = "Cliente";
                //Creamos el objeto usuario.
                u = new Usuarios(0, lastname, name, location, perfil, email, pass);
                //Lo insertamos a la base de datos
                dao.insertarUsuario(u);
                response.sendRedirect(url);
                break;
            default:
                break;
        }
    }
// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
