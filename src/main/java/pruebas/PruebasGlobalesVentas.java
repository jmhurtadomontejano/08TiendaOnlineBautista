/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas;

import daos.VentasDao;
import Dto.Detalleventas;
import Dto.DetalleventasPK;
import Dto.Usuarios;
import Dto.Ventas;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import utilidades.EntityManagerUtil;
import daos.ProductosDao;

/**
 *
 * @author jmhur
 */
public class PruebasGlobalesVentas {

    public static void main(String[] args) throws ParseException {
        /*Creamos una venta y lo igualamos al VentaDaoImpl con la persistencia a Unidad1*/
        VentasDao ventDao = new VentasDao(/*"unidad1"*/);
        ProductosDao prodDao = new ProductosDao(/*"unidad1"*/);


        /*1. Consultar una Venta por ID*/
        int idVenta = 1;
        System.out.println("");
        System.out.println("1. CONSULTAR VENTA POR ID: ");
  //      System.out.println("* " + ventDao.consultarVenta(idVenta).toString());
        System.out.println("");
        /*2. Anidarle un detalle de Ventas*/
        System.out.println("2. AÑADIR UN DETALLE DE VENTA: ");
        /*Creamos el objeto que contiene sus llaves primarias*/
        int idProducto = 11;
        DetalleventasPK dvpk = new DetalleventasPK(idVenta, idProducto);
        //Cantidad del producto a llevar
        int cantidad = 10;
        //Precio del producto, utilizamos prodDao para buscar el producto en la BD y obtener su precio
        BigDecimal bd = prodDao.getProductoId(idProducto).getPrecio();
        //Creamos el objeto detalle venta, con sus atributos
        Detalleventas dv = new Detalleventas(dvpk, bd, cantidad);
        //Creamos el objeto venta, para buscar la venta y poderle añadir un detalle
//        Ventas v = ventDao.consultarVenta(idVenta);
        //Realizamos la persistencia para guardar el detalleventa
//        boolean insertado = ventDao.añadirUnDetalleVenta(v, dv);
        //Imprimimos el resultado
  //      System.out.println("* El detalle venta " + idProducto + " fue insertado alpedido " + idVenta + ". Result:" + insertado);


        /*3. Consultar los detalles de la venta una venta*/
        System.out.println("");
        System.out.println("3. CONSULTAR DETALLES DE VENTA POR ID: " + idVenta);
 //       List<Detalleventas> detalles = ventDao.consultaDetalleUnaVenta(idVenta);
/*        for (Detalleventas dv3 : detalles) {
            System.out.println("* " + dv3.toString());
        }
*/
        /*4. Insertar una venta con 2 lineas de detalle*/
        System.out.println("");
        String up = "unidad1";
        EntityManager em = EntityManagerUtil.getEM(up);
        em.getTransaction().begin();
        //creo variables
        Ventas nuevaVenta = new Ventas();
        int idVentaInsertar = 66;
        nuevaVenta.setIdVenta(idVentaInsertar);
        nuevaVenta.setCliente(em.find(Usuarios.class, 3));
         /*Como he tenido problemas para introducir la fecha directamente, creo variables para transformarla*/
        String fechaEntrada = "12/03/2021"; // Entrada recogida como sea 
        DateFormat fechaFormateada = new SimpleDateFormat("DD/MM/YYYY"); // Creo un formato de fecha
        Date fecha = fechaFormateada.parse(fechaEntrada); // Creamos un date con la fechaEntrada en el formato especificado
        nuevaVenta.setFecha(fecha);

        System.out.println("4. INSERTAR VENTA CON 2 LINEAS DE DETALLE: " + idVentaInsertar);

       

        List<Detalleventas> detalleVentaLista = new ArrayList();
        //DETALLE VENTA 1
        Detalleventas dv1 = new Detalleventas();
    //    dv1.setDetalleventaPK(new DetalleventasPK(idVentaInsertar, 6));
        dv1.setCantidad(1);
        dv1.setPvp(new BigDecimal(2.7));
        dv1.setPctDescuento(BigDecimal.ZERO);
//        dv1.setProducto(prodDao.getProductoId(2));
        detalleVentaLista.add(dv1);

        //DETALLE VENTA 2
        Detalleventas dv2 = new Detalleventas();
   //     dv2.setDetalleventaPK(new DetalleventasPK(idVentaInsertar, 7));
        dv2.setCantidad(1);
        dv2.setPvp(new BigDecimal(4.6));
        dv1.setPctDescuento(BigDecimal.ZERO);
        detalleVentaLista.add(dv2);

        //añado los detalles a la nuevaVenta
  //      nuevaVenta.setDetalleventaList(detalleVentaLista);
/*        if (ventDao.consultarVenta(idVentaInsertar) == null) {
            em.persist(nuevaVenta);
            em.getTransaction().commit();
            System.out.println("Venta: " + idVentaInsertar + " insertada");
        } else {
            System.out.println("*Ya existe un registro con ese ID " + idVentaInsertar + ", cambia el valor de idVentaNueva en PruebasVentas.java");
        }
*/
        /*5. Obtener los siguiente campos num_venta, nombre cliente, [producto, precio, cantidad, total]*/
        System.out.println("");
        System.out.println("5. OBTENER LOS SIGUIENTE CAMPOS: NUM_VENTA; NOMBRECLIENTE; [Producto, precio, cantidad, total]");
        //Obtenemos la venta:
        idVenta = 66;
 /*       v = ventDao.consultarVenta(idVenta);
        //Obtenemos el detalle de esa venta
        detalleVentaLista = ventDao.consultaDetalleUnaVenta(idVenta);
        //Ahora imprimimos los datos
        System.out.println("Num_Venta: " + idVenta);
        System.out.println("Nombre Cliente: " + v.getCliente().getNombre() + " " + v.getCliente().getApellidos());
        System.out.println("* Productos: ");
        for (Detalleventas dv4 : detalleVentaLista) {
            System.out.println("Producto: " + dv4.getProducto().getNombre());
            System.out.println("Precio: " + dv4.getProducto().getPrecio());
            System.out.println("Cantidad: " + dv4.getCantidad());
            System.out.println("Total: " + (dv4.getProducto().getPrecio().doubleValue() * dv4.getCantidad()));
        }*/

    }
}
