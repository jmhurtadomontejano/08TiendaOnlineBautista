/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas;

import Dto.Ventas;
import daos.VentasDao;
import Dto.Detalleventas;
import Dto.DetalleventasPK;
import Dto.Usuarios;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import utilidades.EntityManagerUtil;
import daos.ProductosDao;

/**
 *
 * @author jmhur
 */
public class PruebaVentasBackup {

    public static void main(String[] args) throws ParseException {
        /*Creamos una venta y lo igualamos al VentaDaoImpl con la persistencia a Unidad1*/
        VentasDao ventDao = new VentasDao(/*"unidad1"*/);
        ProductosDao prodDao = new ProductosDao(/*"unidad1"*/);

        /*Insertamos una venta a la fuerza */
 /*Como he tenido problemas para introducir la fecha directamente, creo variables para transformarla*/
        String fechaEntrada = "12/03/2021"; // Entrada recogida como sea 
        DateFormat fechaFormateada = new SimpleDateFormat("DD/MM/YYYY"); // Creo un formato de fecha
        Date fecha = fechaFormateada.parse(fechaEntrada); // Creamos un date con la fechaEntrada en el formato especificado
        System.out.println("INSERTAR UNA VENTA");
        Usuarios cliente = new Usuarios(3);
        //Venta v = new Venta(23, fecha, cliente);
        //ventDao.insertarVenta(v);

        //como lo tiene Bautista para añadir DetalleVentas
        String up = "unidad1";
        EntityManager em = EntityManagerUtil.getEM(up);
        em.getTransaction().begin();

        Ventas nuevaVenta = new Ventas();
        int ultimaVenta = 2;
        int idVentaNueva = 55;
        nuevaVenta.setIdVenta(idVentaNueva);
        nuevaVenta.setCliente(em.find(Usuarios.class, 3));
        nuevaVenta.setFecha(fecha);
        List<Detalleventas> detalleVentaLista = new ArrayList();

        //DETALLE VENTA 1
        Detalleventas dv1 = new Detalleventas();
     //   dv1.setDetalleventaPK(new DetalleventasPK(idVentaNueva, 4));
   /*     dv1.setCantidad(1);
        dv1.setPvp(new BigDecimal(2.7));
        dv1.setPctDescuento(BigDecimal.ZERO);
        dv1.setProducto(prodDao.getProductoId(2));
        detalleVentaLista.add(dv1);
*/
        //DETALLE VENTA 2
        Detalleventas dv2 = new Detalleventas();
     //   dv2.setDetalleventaPK(new DetalleventasPK(idVentaNueva, 5));
        dv2.setCantidad(1);
        dv2.setPvp(new BigDecimal(4.6));
        dv1.setPctDescuento(BigDecimal.ZERO);
        detalleVentaLista.add(dv2);

        //añado los detalles a la nuevaVenta
  /*      nuevaVenta.setDetalleventaList(detalleVentaLista);
        if (ventDao.consultarVenta(idVentaNueva) == null) {
            em.persist(nuevaVenta);
            em.getTransaction().commit();
                System.out.println("Venta: "+idVentaNueva +" insertada");
        } else {
            System.out.println("* Ya existe un registro con ese ID "+ idVentaNueva+", cambia el valor de idVentaNueva en PruebasVentas.java");
        }

        /*Obtenemos un listado de ventas llamando a buscarTodas de VentaDAO para comprobar que se ha añadido la Venta
        System.out.println("LISTADO DE VENTAS:");
        List<Ventas> listaVentas = ventDao.buscarTodas();
        for (Object o : listaVentas) {
            Ventas vent = (Ventas) o;
            System.out.println(vent.getIdVenta() + " " + vent.getCliente() + " " + vent.getFecha());
        }
*/
        /*Buscar ventaPor ID*/
      System.out.println("BUSCAR VENTA POR ID: ");
//        System.out.println("* "+ventDao.consultarVenta(3).toString());

     /*Modificar una venta*/
        int idVentaModificar = 41;
        System.out.println("MODIFICAR LA VENTA con ID: " + idVentaModificar);
 //       Ventas updateVenta = new Ventas(idVentaModificar, fecha, cliente);
  //      ventDao.editarVenta(updateVenta);

        /*borrar ventapor ID*/
        int idVentaEliminar = 47;
        System.out.println("BORRAR VENTA POR ID: " + idVentaEliminar);
 //       Ventas ventaBorrar = ventDao.consultarVenta(idVentaEliminar);
 /*       if (ventaBorrar != null) {
            ventDao.eliminarVenta(idVentaEliminar);
            System.out.println("* Se ha borrado la Venta " + idVentaEliminar);
        } else {
            System.out.println("* La venta que intentas borrar con el id:"+ idVentaEliminar+", no existe cambia el valor de idVentaEliminar");
        }
*/
    }
}
