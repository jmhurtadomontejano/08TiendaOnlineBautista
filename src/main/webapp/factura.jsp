<%-- 
    Document   : factura
    Created on : 01-mar-2022, 23:18:54
    Author     : jmhur
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">    
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
        <title>JuanMi Store | Factura de venta</title>
    </head>
    <body>
        <div class="container mt-4">
            <!-- Div para realizar la impresión -->
            <div id="print">
                <div class="row justify-content-md-center">
                    <!-- Tabla donde se muestra el resumen -->
                    <div class="col col-lg-8 bg-light rounded text-center">
                        <h1>JUANMI STORE</h1>
                        <h3>Comprobante de venta</h3>
                        <table class="table table-striped table-hover table-bordered text-center">
                            <thead>
                                <tr>
                                    <th scope="col">#</th>
                                    <th scope="col">Código</th>
                                    <th scope="col">Descripción</th>
                                    <th scope="col">Precio</th>
                                    <th scope="col">Cantidad</th>
                                    <th scope="col">Total</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="car" items="${sessionScope.carrito}" varStatus="numero">
                                    <tr>
                                        <th scope="row">${numero.count}</th>
                                        <td>${car.getProducto().getIdProducto()}</td>
                                        <td>${car.getProducto().getNombre()}</td>
                                        <td>${car.getProducto().getPrecio()}</td>
                                        <td>${car.getCantidad()}</td>
                                        <td>
                                            <!-- Le damos formato de dos decimales -->
                                            <fmt:formatNumber type="currency" pattern="€ 0.00" value="${car.getSubtotal()}"/>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    
                </div>

                <div class="row justify-content-md-center">
                    <div class="col col-lg-8 bg-light text-end pb-4 mb-4">
                        Total a pagar:
                        <input id="disabledInput" class="text-center text-dark fw-bold" type="text" value="<fmt:formatNumber type="currency" pattern="€ 0.00" value="${sessionScope.totalPagar}"/>" disabled>
                    </div>
                </div>
            </div>           

            <div class="row justify-content-md-center">
                <div class="col col-lg-4">
                    <a href="Carrito?accion=vaciarCarrito" type="button" class="btn btn-outline-primary mb-2"><i class="far fa-edit"></i> Terminar
                        proceso</a>
                </div>
                <div class="col col-lg-4 text-end">
                    <button type="button" class="btn btn-outline-primary mb-2" onclick="javascript:imprimir();">Imprimir <i
                            class="fas fa-print"></i></button>
                </div>
            </div>
        </div>

        <script src="https://code.jquery.com/jquery-3.6.0.js"
        integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"
                integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB"
        crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"
                integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13"
        crossorigin="anonymous"></script>
        <script src="js/script.js"></script>
    </body>

</html>
