/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import daos.ProductosDao;
import daos.UsuariosDao;
import daos.VentasDao;
import Dto.Carrito;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

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

    
}
