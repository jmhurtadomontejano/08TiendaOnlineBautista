<%-- 
    Document   : carrito
    Created on : 27-feb-2022, 17:48:47
    Author     : jmhur
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
        <link rel="stylesheet" href="css/styles.css"/>

        <title>JuanMi Store | Carrito</title>
    </head>
    <body>
                 <!-- BARRA DE NAVEGACIÓN -->
        <c:import url="navbar.jsp"></c:import>

        <!-- sección donde mostramos la lista del carrito -->
        <div class="container mt-4">
            <h3>Carrito</h3>
            <br>
            <div class="row">
                <div class="col-sm-8">
                    <table class="table table-hover">
                        <thead class="table-info">
                            <tr>
                                <th>ITEM</th>
                                <th>NOMBRES</th>
                                <th>DESCRIPCIÓN</th>
                                <th>PRECIO</th>
                                <th class="text-center">CANTIDAD</th>
                                <th>TOTAL</th>
                                <th>ACCIÓN</th>
                            </tr>
                        </thead>
                        <tbody>
                            <!-- Verificamos que la lista no venga vacía, si viene vacía mostramos un texto -->
                            <c:choose>
                                <c:when test="${sessionScope.carrito.size() > 0}">
                                    <c:forEach var="car" items="${sessionScope.carrito}">
                                        <tr>
                                            <td class="text-center">${car.getItem()}</td>
                                            <td>${car.getProducto().getNombre()}</td>
                                            <td>
                                                ${car.getProducto().getDescripcion()}
                                                <img src="img/${car.getProducto().getImagen()}" widht="100" height="100">
                                            </td>
                                            <td class="text-center">
                                               <fmt:formatNumber type="currency" pattern="0.00 €" value="${car.getProducto().getPrecio()}"/>
                                           </td>
                                            <td>                                                
                                                <div class="input-group">
                                                    <input type="hidden" value="${car.getProducto().getIdProducto()}" id="idp">
                                                    <!-- Verificamos que la cantidad sea distinta de uno -->
                                                    <c:choose>
                                                        <c:when test="${car.getCantidad()>1}">
                                                            <button class="btn btn-danger btn-menos" type="button" ><i class="far fa-minus"></i></button>
                                                            </c:when>
                                                            <c:otherwise>
                                                            <button class="btn btn-danger btn-menos" disabled type="button" ><i class="far fa-minus"></i></button>
                                                            </c:otherwise>
                                                        </c:choose>

                                                    <input type="number" id="cantidad" readonly class="form-control text-center" value="${car.getCantidad()}">
                                                    <button class="btn btn-success btn-mas" type="button" ><i class="far fa-plus"></i></button>
                                                </div>
                                            </td>                                            
                                            <td class="text-center">
                                                <!-- Le damos formato de dos decimales -->
                                                <fmt:formatNumber type="currency" pattern="0.00 €" value="${car.getSubtotal()}"/>
                                            </td>
                                            <td class="text-center">
                                                <button class="btn btn-danger" onclick="eliminar(${car.getProducto().getIdProducto()})" id="btn-delete"><i class="fas fa-trash-alt"></i></button>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    <tr class="text-center text-red">
                                        <td colspan="7"> CARRITO DE COMPRAS VACÍO, AGREGA PRODUCTOS PRIMERO </td>
                                    </tr>
                                </c:otherwise>
                            </c:choose>
                        </tbody>
                    </table>

                </div>
                <div class="col-sm-4">
                    <div class="card">
                        <div class="card-header">
                            <h3>Realizar Compra</h3>
                        </div>
                        <div class="card-body"> 
                            <label>Total a pagar: </label>
                            <!-- Le damos formato de dos decimales -->
                            <input type="text" value="<fmt:formatNumber type="currency" pattern="0.00 €" value="${sessionScope.totalPagar}"/>" readonly class="form-control" >
                        </div>
                        <div class="card-footer d-grid gap-2">
                            <c:choose>
                                <c:when test="${carrito.size() > 0}">
                                    <a href="Carrito?accion=realizarCompra" class="btn btn-info">Comprar <i class="far fa-bags-shopping"></i></a>
                                    </c:when>
                                    <c:otherwise>
                                    <button disabled class="btn btn-danger"><strong>CARRITO VACIO</strong></button>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
        <script src="js/funciones.js" type="text/javascript"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

    </body>
</html>