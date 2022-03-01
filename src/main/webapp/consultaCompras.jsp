<%-- 
    Document   : consultarCompras
    Created on : 01-mar-2022, 12:12:41
    Author     : jmhur
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>JuanMi Store | Consultar Ventas</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
        <link rel="stylesheet" href="css/styles.css"/>
    </head>
    <body>
        <!-- Verificamos si tiene una sesión activa -->
        <c:if test="${sessionScope.name == null}">
            <c:redirect url="index.jsp"></c:redirect>
        </c:if>
        <!-- BARRA DE NAVEGACIÓN -->
        <c:import url="navbar.jsp"></c:import>
 <div class="container">
            <h2 class="mb-3">Estado de tus últimas compras</h2>
            <table class="table table-striped table-hover table-bordered text-center">
                <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Fecha</th>
                        <th scope="col">Estado</th>
                        <th scope="col">Detalle</th>
                    </tr>
                </thead>
                <tbody>
                    <c:choose>
                        <c:when test="${sessionScope.listaVentas.size() > 0}">
                            <!-- Recorremos la lista -->
                            <c:forEach var="v" items="${sessionScope.listaVentas}" varStatus="numero">
                                <tr>                        
                                    <th scope="col">${numero.count}</th>
                                    <td>
                                        <!-- Formateamos la fecha -->
                                        <fmt:formatDate type="date" dateStyle = "medium"  pattern="dd 'de' MMMM 'del' yyyy" value = "${v.getFecha()}" />

                                    </td>
                                    <td>${v.getEstado()}</td>
                                    <td><button onclick="loadDetails(${v.getIdVenta()})" type="button" class="btn btn-outline-dark" data-bs-toggle="modal" data-bs-target="#detalleVenta">Ver Detalle <i class="far fa-search"></i></button></td>
                                </tr>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <tr>
                                <th colspan="4"> NO TIENES COMPRAS REALIZADAS </th>
                            </tr>
                        </c:otherwise>
                    </c:choose>
                </tbody>
            </table>
        </div>

        <!-- Modal detalles -->
        <div class="modal fade" id="detalleVenta" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header text-center">
                        <h5 class="modal-title" id="exampleModalLabel">Detalle de venta</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <table class="table table-striped table-hover table-bordered text-center">

                        </table>
                        <div class="row g-3 justify-content-end align-self-end">
                            <div class="col-auto">
                                <button type="button" class="btn btn-secondary mb-3" data-bs-dismiss="modal">Cerrar</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
        <script src="js/script.js"></script>       
    </body> 
</html>