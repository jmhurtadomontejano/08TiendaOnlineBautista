/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author jmhur
 */

import daos.ProductosDao;
import daos.VentasDao;
import Dto.Detalleventas;
import Dto.Productos;
import Dto.Ventas;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Cris
 */
@Path("generic")
public class GenericResource {

    @Context
    private UriInfo context;

    public GenericResource() {
    }

    @GET
    @Path("/producto/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Productos getProducto(@PathParam("id") int id) {
        ProductosDao dao = new ProductosDao();
        Productos producto = dao.getProductoId(id);
        return producto;
    }
    
    @GET
    @Path("/detalleVentas/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Detalleventas> getDetalle(@PathParam("id") int id) {
        List<Detalleventas> lista = new ArrayList();
        VentasDao dao = new VentasDao();
        lista = dao.getDetalles(id);
        return lista;
    }
    
    @GET
    @Path("/ventas/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Ventas getVentas(@PathParam("id") int id) {        
        VentasDao dao = new VentasDao();
        Ventas lista = dao.getVentasId(id);
        return lista;
    }
}
