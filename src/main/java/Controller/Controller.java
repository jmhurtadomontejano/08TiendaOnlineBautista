/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import daos.CategoriasDao;
import daos.ProductosDao;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author jmhur
 */
@WebServlet(name = "Controller", urlPatterns = {"/Controller"})
public class Controller extends HttpServlet {

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
        //Obtenemos la acción recibida desde la vista
        String accion = request.getParameter("accion");
        System.out.println(accion);
        //Hacemos un switch para obtener los casos y darle un tratamiento.
        switch (accion) {
            //Para el caso mostrar obtenemos todos los productos de la base de datos
            case "mostrar":
                //Creamos el objeto ProductosDao para hacer la consulta a la BD.
                ProductosDao dao = new ProductosDao();
                //Creamos el objeto CategoriasDao para hacer la consulta de categorias a la BD.
                CategoriasDao cdao = new CategoriasDao();
                //Enviamos como atributo la lista de productos obtenida desde la base de datos.
                request.setAttribute("productos", dao.getProductos());
                //Enviamos como atributo la lista de categorias obtenida desde la base de datos.
                request.setAttribute("categorias", cdao.getCategorias());
                //Enviamos el atributo Todo para que la vista sepa que se filtra por todo
                request.setAttribute("order", "todo");
                
                request.getRequestDispatcher("articles.jsp").forward(request, response);

                break;
            case "menor":
                //Inicializamos el objeto ProductosDao.
                dao = new ProductosDao();
                //Inicializamos el objeto categoriasDao
                cdao = new CategoriasDao();
                //Enviamos como atributo las categorías.
                request.setAttribute("categorias", cdao.getCategorias());
                //Usamos el objeto ProductosDao, para realizar la consulta con los menores y enviamos como atributo
                request.setAttribute("productos", dao.getProductosLow());
                //Enviamos el atributo Menor para que la vista sepa que se filtra por precio Menor
                request.setAttribute("order", "menor");
                //Redirigimos a la página de articulos.
                request.getRequestDispatcher("articles.jsp").forward(request, response);
                break;
            case "mayor":
                //Inicializamos el objeto ProductosDao.
                dao = new ProductosDao();
                //Inicializamos el objeto categoriasDao
                cdao = new CategoriasDao();
                //Enviamos como atributo las categorías.
                request.setAttribute("categorias", cdao.getCategorias());
                //Usamos el objeto ProductosDao, para realizar la consulta con los menores y enviamos como atributo
                request.setAttribute("productos", dao.getProductosHigh());
                //Enviamos el atributo Mayor para que la vista sepa que se filtra por precio Mayor
                request.setAttribute("order", "mayor");
                //Redirigimos a la página de articulos.
                request.getRequestDispatcher("articles.jsp").forward(request, response);
                break;
            default:
                String url = request.getHeader("Referer");
                System.out.println(url);
                response.sendRedirect(url);
                break;
        }
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
