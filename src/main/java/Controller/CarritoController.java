package Controller;

import daos.ProductosDao;
import daos.UsuariosDao;
import daos.VentasDao;
import Dto.Carrito;
import Dto.Detalleventas;
import Dto.DetalleventasPK;
import Dto.Productos;
import Dto.Usuarios;
import Dto.Ventas;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
@WebServlet(name = "Carrito", urlPatterns = {"/Carrito"})
public class CarritoController extends HttpServlet {

    //Aquí almacenaremos los productos que sean llamados en la lista
    private List<Carrito> listaCarrito = new ArrayList();
    //Cantidad de items en el carrito
    private int item;
    //Cantidad de precio a pagar
    private double totalPagar;
    //Cantidad de productos
    private int cantidad = 1;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Obtenemos la url desde donde se origino la petición.
        String url = request.getHeader("Referer");
        //Obtenemos la acción.
        String accion = request.getParameter("accion");
        //Creamos objeto Producto
        Productos p;
        //Creamos objeto ProductoDao
        ProductosDao pdao = new ProductosDao();
        //Creamos el objeto sesión
        HttpSession sesion = request.getSession();
        switch (accion) {
            case "agregarCarrito":
                //Ponemos la cantidad en 1 siempre que entre a este método.
                cantidad = 1;
                //Variable para saber si encontro el producto.
                boolean pos = false;
                //Recojemos el id mandado por la url
                int id = Integer.parseInt(request.getParameter("id"));
                //Buscamos el id en la bd
                p = pdao.getProductoId(id);
                //Verificamos si el carrito tiene más de un item
                if (listaCarrito.size() > 0) {
                    //Recorremos la listaCarrito, para saber si encontramos el producto dentro de la lista.
                    for (int i = 0; i < listaCarrito.size(); i++) {
                        //Si el producto se encuentra dentro de la lista, aumentamos su cantidad.
                        if (id == listaCarrito.get(i).getProducto().getIdProducto()) {
                            //Obtenemos la cantidad del producto
                            cantidad = listaCarrito.get(i).getCantidad();
                            //Le sumamos uno a cantidad.
                            cantidad++;
                            //Seteamos la cantidad en el producto
                            listaCarrito.get(i).setCantidad(cantidad);
                            //Calculamos el subtotal.
                            double subtotal = listaCarrito.get(i).getProducto().getPrecio().doubleValue() * cantidad;
                            //Seteamos esta propiedad en el arrayList
                            listaCarrito.get(i).setSubtotal(subtotal);
                            //Ponemos el flag en true
                            pos = true;
                        }
                    }
                    //Si no fue encontrado el producto, procedemos a ingresar el item
                    if (!pos) {
                        //Aumentamos la variable item.
                        item++;
                        //Creamos el objeto carrito
                        Carrito car = new Carrito();
                        //Le asignamos las variables recogidas anteriormente
                        car.setCantidad(cantidad);
                        car.setItem(item);
                        car.setProducto(p);
                        //Calculamos el total
                        car.setSubtotal(p.getPrecio().doubleValue() * cantidad);
                        //Añadimos a la lista
                        listaCarrito.add(car);
                    }
                }//Si no tiene ningún producto, lo agregamos normalmente.
                else {
                    //Aumentamos la variable item.
                    item++;
                    //Creamos el objeto carrito
                    Carrito car = new Carrito();
                    //Le asignamos las variables recogidas anteriormente
                    car.setCantidad(cantidad);
                    car.setItem(item);
                    car.setProducto(p);
                    //Calculamos el total
                    car.setSubtotal(p.getPrecio().doubleValue() * cantidad);
                    //Añadimos a la lista
                    listaCarrito.add(car);
                }
                //En la sesión damos el atributo
                sesion.setAttribute("contador", listaCarrito.size());
                //Redirigimos la respuesta
                response.sendRedirect(url);
                break;
            case "Carrito":
                //Inicializamos totalPagar                
                totalPagar = 0;
                //Le damos a la sesion un atributo para el carrito
                sesion.setAttribute("carrito", listaCarrito);
                //Recorremos la lista para calcular el importe con una suma.
                for (Carrito c : listaCarrito) {
                    totalPagar += c.getSubtotal();
                }
                //Le damos un atributo para totalPagar
                sesion.setAttribute("totalPagar", totalPagar);
                //Redirigimos a carrito                
                response.sendRedirect("carrito.jsp");
                break;
            case "delete":
                //Obtenemos el id producto
                id = Integer.parseInt(request.getParameter("idp"));
                //Recorremos toda la lista para encontrarlo y lo borramos
                for (int i = 0; i < listaCarrito.size(); i++) {
                    if (listaCarrito.get(i).getProducto().getIdProducto() == id) {
                        listaCarrito.remove(i);
                    }
                }
                sesion.setAttribute("contador", listaCarrito.size());
                break;
            case "cerrar":
                //Obtenemos la sesion
                sesion = request.getSession();
                sesion.invalidate();
                //Vaciamos el carrito
                listaCarrito.clear();
                totalPagar = 0;

                response.sendRedirect(url);
                break;
            case "actualizarCantidad":
                //Obtenemos los parametros que enviamos a través de javascript (funciones.js)
                int idp = Integer.parseInt(request.getParameter("idp"));
                int cant = Integer.parseInt(request.getParameter("cantidad"));
                //Buscamos el producto en la lista
                for (int i = 0; i < listaCarrito.size(); i++) {
                    //Si encontramos el producto, cambiamos su cantidad
                    if (listaCarrito.get(i).getProducto().getIdProducto() == idp) {
                        //Le asignamos la cantidad
                        listaCarrito.get(i).setCantidad(cant);
                        //Calculamos nuevamente su precio
                        double precio = listaCarrito.get(i).getProducto().getPrecio().doubleValue() * cant;
                        //Le asignamos el nuevo subtotal
                        listaCarrito.get(i).setSubtotal(precio);
                    }
                }
                break;
            case "realizarCompra":
                //Instanciamos la sesión
                sesion = request.getSession();
                //Obtenemos la variable idCliente
                int idCliente = (int) sesion.getAttribute("id");
                //Instanciamos la clase usuariosdao para buscar el usuario en la bd
                UsuariosDao u = new UsuariosDao();
                //Buscamos el cliente en la base de datos
                Usuarios user = u.getUsuarioId(idCliente);
                //Obtenemos la fecha de compra del día
                LocalDate today = LocalDate.now();
                //Parseamos la fecha a formato Date
                Date fecha = Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant());
                //Creamos el estado
                String estado = "Pagado";
                //Instanciamos el objeto ventas
                Ventas v = new Ventas(0, estado, fecha, user);
                //Creamos el producto en la bd
                VentasDao vdao = new VentasDao();
                //Insertamos y obtenemos el id
                int idVenta = vdao.createVenta(v);
                //Recorremos la lista del carrito para insertar los detalle ventas
                for (Carrito c : listaCarrito) {
                    //Obtenemos el producto
                    p = c.getProducto();
                    //Creamos las llaves primarias
                    DetalleventasPK pk = new DetalleventasPK(idVenta, p.getIdProducto());
                    //Creamos el objeto DetalleVentas
                    Detalleventas detalle = new Detalleventas(pk, new BigDecimal(c.getSubtotal()), c.getCantidad());
                    //Insertamos el detalle a la base de datos
                    vdao.createDetalle(detalle);
                }
                //Redireccionamos a factura
                response.sendRedirect("factura.jsp");
                break;
            case "vaciarCarrito":
                //Vaciamos el carrito
                listaCarrito.clear();
                //Asignamos valor a la variable contador
                sesion = request.getSession();
                sesion.setAttribute("contador", listaCarrito.size());
                totalPagar = 0;
                //Redireccionamos a inicio
                response.sendRedirect("index.jsp");
                break;
            default:
                break;
        }
    }
}
