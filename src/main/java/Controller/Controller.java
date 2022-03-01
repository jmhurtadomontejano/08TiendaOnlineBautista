/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import daos.CategoriasDao;
import daos.ProductosDao;
import daos.UsuariosDao;
import daos.VentasDao;
import Dto.Categorias;
import Dto.Productos;
import Dto.Usuarios;
import Dto.Ventas;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author jmhur
 */
@WebServlet(name = "Controller", urlPatterns = {"/Controller"})
@MultipartConfig
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
            case "consultarCompras":
                //Inicializamos el objeto ventas
                VentasDao vdao = new VentasDao();            
                //Obtenemos el id del usuario
                HttpSession sesion = request.getSession();
                int idUser = (int) sesion.getAttribute("id");
                //Realizamos la consulta
                List<Ventas> lista = vdao.getVentas(idUser);
                //Creamos el atributo de la lista en la sesion
                sesion.setAttribute("listaVentas", lista);                
                //Redireccionamos a la página web
                response.sendRedirect("consultaCompras.jsp");
                break;
            case "administrar":
                //Actualizamos el objeto ProductosDao para hacer la consulta a la BD.
                dao = new ProductosDao();
                //Actualizamos el objeto CategoriasDao
                cdao = new CategoriasDao();
                //Creamos la sesion
                sesion = request.getSession();
                //Enviamos la lista como atributo de la sesion
                sesion.setAttribute("productsAdmin", dao.getProductos());
                //Enviamos las categorias como atributo
                sesion.setAttribute("categoria", cdao.getCategorias());                
                //Enviamos la respuesta
                response.sendRedirect("administrarProductos.jsp");
                break;
            case "delete":
                //Actualizamos el objeto ProductosDao para hacer la consulta a la BD.
                dao = new ProductosDao();
                //Capturamos el id del producto
                int idp = Integer.parseInt(request.getParameter("idp"));
                //Eliminamos el producto
                dao.delete(idp);
                break;
            case "consultarVentas":
                //Actualizamos el objeto ventasDao
                vdao = new VentasDao();                
                //Inicializamos el objeto usuariosDao
                UsuariosDao udao = new UsuariosDao();    
                //Creamos la sesión
                sesion = request.getSession();
                //Damos como atributo de sesión la lista de ventas
                sesion.setAttribute("listaVentas", vdao.getVentas());
                //Asignamos el atributo de la lista de usuarios
                sesion.setAttribute("listaUsers", udao.getUsuarios());
                //Redirigimos
                response.sendRedirect("consultarVentas.jsp");
                break;
            case "filterByUser":
                //Obtenemos el id del usuario.
                idUser = Integer.valueOf(request.getParameter("floatingIdUser"));
                //Actualizamos el objeto ventasDao
                vdao = new VentasDao();
                //Creamos la sesión
                sesion = request.getSession();
                //Damos como atributo de sesión la lista de ventas
                sesion.setAttribute("listaVentas", vdao.getVentas(idUser));
                sesion.setAttribute("msg", "ESTE USUARIO");
                //Redirigimos
                response.sendRedirect("consultarVentas.jsp");
                break;
            case "filterByDate":
                //Creamos la fecha a partir del parametro enviado
                System.out.println(request.getParameter("floatingDate1"));
                LocalDate d1 = LocalDate.parse(request.getParameter("floatingDate1"));
                LocalDate d2 = LocalDate.parse(request.getParameter("floatingDate2"));
                //Parseamos el LocalDate a Date
                Date fecha1 = Date.from(d1.atStartOfDay(ZoneId.systemDefault()).toInstant());
                Date fecha2 = Date.from(d2.atStartOfDay(ZoneId.systemDefault()).toInstant());
                //Actualizamos el objeto ventasDao
                vdao = new VentasDao();
                //Creamos la sesión
                sesion = request.getSession();
                //Damos como atributo de sesión la lista de ventas
                sesion.setAttribute("listaVentas", vdao.getVentasFechas(fecha1, fecha2));
                sesion.setAttribute("msg", "ESTAS FECHAS");
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
        //Obtenemos la acción recibida desde la vista
        String accion = request.getParameter("accion");
        switch (accion) {
            case "updateProduct":
                //Intentamos obtener el archivo
                Part archivo = request.getPart("file");
                String img = "";
                //Verificamos que haya un archivo cargado.
                if (archivo.getSize() > 0) {
                    //Convertimos ese archivo cargado en un archivo real en memoria.
                    InputStream file = archivo.getInputStream();
                    //Obtenemos la ruta de ejecución de este path
                    String rutaBruta = this.getClass().getResource("").getPath();
                    //Partimos la ruta desde la frase target para obtener la ruta completa del proyecto y almacenarla en rutaBruta
                    rutaBruta = rutaBruta.split("target", 2)[0];
                    /**
                     * Creamos el archivo que guardaremos en la nueva ruta. La
                     * ruta sería: (proyecto) + "src/main/webapp/img" +
                     * nombre_Archivo Con ella ya obtenemos la ruta de la
                     * carpeta img para luego mostrarla en la página html
                     */
                    File toSave = new File(rutaBruta + "\\src\\main\\webapp\\img\\" + archivo.getSubmittedFileName());
                    //Realizamos el copiado del archivo a la carpeta definida anteriormente y le añadimos REPLACE_EXISTING
                    //Para reemplazar el archivo (si este se llamase igual) y no tener ningún error.
                    Files.copy(file, toSave.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    img = archivo.getSubmittedFileName();
                }
                //Obtenemos los demás campos
                int id = Integer.valueOf(request.getParameter("floatingId"));
                String nombre = request.getParameter("floatingName");
                String desc = request.getParameter("floatingDesc");
                BigDecimal precio = new BigDecimal(request.getParameter("floatingPrice"));
                String categoria = request.getParameter("floatingCategoria");
                String disponible = request.getParameter("floatingDisp");

                //Actualizamos el objeto productodao
                ProductosDao dao = new ProductosDao();
                //Verificamos si el nombre de la imagen sigue vacio
                if (img.equals("")) {
                    //Si es vacia buscamos el objeto
                    Productos p1 = dao.getProductoId(id);
                    img = p1.getImagen();
                }
                //Creamos el producto
                Productos p = new Productos(id, nombre, desc, precio, img, disponible);
                //Buscamos el objeto categoria en la bd
                CategoriasDao cdao = new CategoriasDao();
                //Creamos la categoria
                Categorias c = cdao.getCategoryName(categoria);
                //Asignamos la categoria
                p.setCategoria(c);
                //Actualizamos el producto
                dao.updateProduct(p);

                break;
            case "createProduct":
                //Intentamos obtener el archivo
                archivo = request.getPart("file");
                img = "";
                //Verificamos que haya un archivo cargado.
                if (archivo.getSize() > 0) {
                    //Convertimos ese archivo cargado en un archivo real en memoria.
                    InputStream file = archivo.getInputStream();
                    //Obtenemos la ruta de ejecución de este path
                    String rutaBruta = this.getClass().getResource("").getPath();
                    //Partimos la ruta desde la frase target para obtener la ruta completa del proyecto y almacenarla en rutaBruta
                    rutaBruta = rutaBruta.split("target", 2)[0];
                    /**
                     * Creamos el archivo que guardaremos en la nueva ruta. La
                     * ruta sería: (proyecto) + "src/main/webapp/img" +
                     * nombre_Archivo Con ella ya obtenemos la ruta de la
                     * carpeta img para luego mostrarla en la página html
                     */
                    File toSave = new File(rutaBruta + "\\src\\main\\webapp\\img\\" + archivo.getSubmittedFileName());
                    //Realizamos el copiado del archivo a la carpeta definida anteriormente y le añadimos REPLACE_EXISTING
                    //Para reemplazar el archivo (si este se llamase igual) y no tener ningún error.
                    Files.copy(file, toSave.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    img = archivo.getSubmittedFileName();
                }
                //Obtenemos los demás campos
                id = Integer.valueOf(request.getParameter("floatingId"));
                nombre = request.getParameter("floatingName");
                desc = request.getParameter("floatingDesc");
                precio = new BigDecimal(request.getParameter("floatingPrice"));
                categoria = request.getParameter("floatingCategoria");
                disponible = request.getParameter("floatingDisp");

                //Actualizamos el objeto productodao
                dao = new ProductosDao();
                //Verificamos si el nombre de la imagen sigue vacio
                if (img.equals("")) {
                    //Si es vacia le colocamos el nombre del producto
                    img = nombre;
                }
                //Creamos el producto
                p = new Productos(id, nombre, desc, precio, img, disponible);
                //Buscamos el objeto categoria en la bd
                cdao = new CategoriasDao();
                //Creamos la categoria
                c = cdao.getCategoryName(categoria);
                System.out.println(c.toString());
                //Asignamos la categoria
                p.setCategoria(c);
                //Actualizamos el producto
                dao.createProduct(p);
                break;
            case "actualizarPedido":
                //Obtenemos los datos que vienen a través de javascript
                id = Integer.valueOf(request.getParameter("floatingId"));
                String cliente = request.getParameter("floatingName");
                //Parseamos el string completo en dos
                String[] names = cliente.split(" ", 2);
                //Creamos la fecha a partir del parametro enviado
                LocalDate d = LocalDate.parse(request.getParameter("floatingDate"));
                //Parseamos el LocalDate a Date
                Date fecha = Date.from(d.atStartOfDay(ZoneId.systemDefault()).toInstant());
                String estado = request.getParameter("floatingState");

                //Buscamos el cliente a través del nombre
                UsuariosDao udao = new UsuariosDao();
                Usuarios u = udao.getUsuarioFullName(names[0], names[1]);
                //Creamos el objeto venta
                Ventas v = new Ventas(id, estado, fecha, u);
                //Actualizamos
                VentasDao vdao = new VentasDao();
                vdao.updateVentas(v);
                break;
            default:
                System.out.println("Llegue aquí");
                break;
        }
    }
}
