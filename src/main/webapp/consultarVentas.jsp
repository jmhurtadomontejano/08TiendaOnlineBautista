<%-- 
    Document   : consultarVentas
    Created on : 28-feb-2022, 22:00:50
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
                <div class="row justify-content-between">
                    <div class="col">
                        <h2>Listado de ventas</h2>                    
                    </div>
                    <div class="col text-end">
                        <div class="btn-group dropstart mb-2">
                            <button type="button" id="btn-filtrar" class="btn btn-secondary dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
                                Filtrar por: Todo
                            </button>
                            <ul class="dropdown-menu category_list">
                                <li><a href="Controller?accion=consultarVentas" class="dropdown-item category_item" category="Todo">Todo</a></li>
                                <li><a data-bs-toggle="modal" data-bs-target="#modalFecha"  class="dropdown-item category_item" category="Fechas">Entre dos fechas</a></li>
                                <li><a data-bs-toggle="modal" data-bs-target="#modalUser" class="dropdown-item category_item" category="Usuarios">Por usuario</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
                <!-- Listado de ventas -->
                <div class="row g-4 mt-2">
                <c:choose>
                    <c:when test="${sessionScope.listaVentas.size()>0}">
                        <table class="table text-center table-bordered">
                            <thead>
                                <tr>
                                    <th scope="col">Id</th>
                                    <th scope="col">Cliente</th>
                                    <th scope="col">Fecha</th>
                                    <th scope="col">Estado</th>
                                    <th scope="col">Acción</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="v" items="${sessionScope.listaVentas}">
                                    <tr>
                                        <th scope="row">${v.getIdVenta()}</th>
                                        <td>${v.getCliente().getNombre()} ${v.getCliente().getApellidos()}</td>
                                        <td>
                                            <!-- Formateamos la fecha -->
                                <fmt:formatDate type="date" dateStyle = "medium"  pattern="dd 'de' MMMM 'del' yyyy" value = "${v.getFecha()}" />
                                </td>
                                <td>${v.getEstado()}</td>
                                <td>
                                    <button type="button" onclick="loadModalVenta(${v.getIdVenta()})" data-bs-toggle="modal" data-bs-target="#modalVenta" class="btn btn-outline-warning"><i class="fas fa-edit"></i></button>
                                </td>
                                </tr>
                            </c:forEach>

                            </tbody>
                        </table>
                    </c:when>
                    <c:otherwise>
                        <h2 class="text-center text-red">NO HAY VENTAS REGISTRADAS PARA ${sessionScope.msg}</h2>
                    </c:otherwise>
                </c:choose>

            </div>
        </div>
        <!-- Modal para Modificar -->
        <div class="modal fade" id="modalVenta" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable modal-lg">
                <div class="modal-content">
                    <div class="modal-header text-center">
                        <h5 class="modal-title" id="exampleModalLabel"></h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">                        
                        <div class="tab-content" id="pills-tabContent">
                            <!-- Formulario inicio de sesión -->
                            <form class="row g-3" onsubmit="return false;" id="formVenta" method="post">

                                <div class="col-6">
                                    <div class="form-floating">
                                        <input type="number" class="form-control" name="floatingId" id="floatingId"
                                               placeholder="1" readonly>
                                        <label for="floatingId">idVenta</label>
                                    </div>
                                </div>
                                <div class="col-6">
                                    <div class="form-floating">
                                        <input type="text" class="form-control" name="floatingName" id="floatingName"
                                               placeholder="Enrique" readonly>
                                        <label for="floatingName">Cliente</label>
                                    </div>
                                </div>
                                <div class="col-6">
                                    <div class="form-floating">
                                        <input type="date" class="form-control" name="floatingDate" id="floatingDate"
                                               placeholder="15-02-22" readonly>
                                        <label for="floatingDate">Fecha</label>
                                    </div>
                                </div>
                                <div class="col-6">
                                    <div class="form-floating">
                                        <select class="form-select" name="floatingState" id="floatingState" aria-label="Floating label select example">
                                            <option value="Pagado">Pagado</option>
                                            <option value="Esperando envio">Esperando envio</option>
                                            <option value="Enviado">Enviado</option>
                                            <option value="Entregado">Entregado</option>
                                        </select>
                                        <label for="floatingState">Seleccione Estado</label>
                                    </div>
                                </div>                                

                                <div class="w-100"></div>
                                <div class="row g-3 justify-content-end align-self-end">
                                    <div class="col-auto">
                                        <button type="button" class="btn btn-secondary mb-3"
                                                data-bs-dismiss="modal">Cerrar</button>
                                        <button type="button" onclick="sendFormState()" class="btn btn-primary mb-3">Editar</button>
                                    </div>
                                </div>
                            </form>                
                        </div>
                    </div>
                </div>
            </div>
        </div>



        <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <script src="js/script.js"></script>
    </body>
</html>
