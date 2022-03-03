<%-- 
    Document   : consultarVentas
    Created on : 28-feb-2022, 22:00:50
    Author     : jmhur
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
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

        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <div class="container-fluid">
                <a class="navbar-brand" href="#">Juan Store</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <!-- Menú de la Izquierda -->
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                        <li class="nav-item">
                            <a class="nav-link" aria-current="page" href="index.jsp">Inicio</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" aria-current="page" href="Controller?accion=mostrar">Ver Articulos</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="Carrito?accion=Carrito">Carrito (<label id="count-carrito">
                                    <!-- validamos que el contador sea mayor a 0, si no podemos 0 -->
                                    <c:choose>
                                        <c:when test="${sessionScope.contador > 0}">
                                            ${sessionScope.contador}
                                        </c:when>
                                        <c:otherwise>
                                            0
                                        </c:otherwise>
                                    </c:choose>
                                </label>) <i class="fas fa-shopping-cart"></i></a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="Controller?accion=administrar">Administrar Productos</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active" href="consultarVentas.jsp">Consultar Ventas</a>
                        </li>

                    </ul>
                    <!-- Menú de la Derecha -->
                    <ul class="navbar-nav">                        
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                ${sessionScope.name}
                            </a>
                            <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
                                <li><a class="dropdown-item" href="Carrito?accion=cerrar">Cerrar Sesión</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>        

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

        <!-- Modal para seleccionar fecha -->
        <div class="modal fade" id="modalFecha" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable modal-lg">
                <div class="modal-content">
                    <div class="modal-header text-center">
                        <h5 class="modal-title" id="exampleModalLabel"></h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">                        
                        <div class="tab-content" id="pills-tabContent">
                            <!-- Formulario inicio de sesión -->
                            <form class="row g-3" onsubmit="return false;" id="formFechas">

                                <div class="col-6">
                                    <div class="form-floating">
                                        <input type="date" class="form-control" name="floatingDate1" id="floatingDate1"
                                               placeholder="15-02-22">
                                        <label for="floatingDate1">Fecha Inicio</label>
                                    </div>
                                </div> 
                                <div class="col-6">
                                    <div class="form-floating">
                                        <input type="date" class="form-control" name="floatingDate2" id="floatingDate2"
                                               placeholder="15-02-22">
                                        <label for="floatingDate2">Fecha Final</label>
                                    </div>
                                </div>                           

                                <div class="w-100"></div>
                                <div class="row g-3 justify-content-end align-self-end">
                                    <div class="col-auto">
                                        <button type="button" class="btn btn-secondary mb-3"
                                                data-bs-dismiss="modal">Cerrar</button>
                                        <button type="submit" onclick="validarFechas()" class="btn btn-primary mb-3">Consultar</button>
                                    </div>
                                </div>
                            </form>                
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Modal para mostrar y seleccionar usuario -->
        <div class="modal fade" id="modalUser" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable modal-lg">
                <div class="modal-content">
                    <div class="modal-header text-center">
                        <h5 class="modal-title" id="exampleModalLabel"></h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">                        
                        <div class="tab-content" id="pills-tabContent">
                            <!-- Formulario inicio de sesión -->
                            <form class="row g-3" action="Controller" method="get">
                                <input type="text" value="filterByUser" hidden id="accion" name="accion">
                                <div class="col">
                                    <div class="form-floating">
                                        <select class="form-select" name="floatingIdUser" id="floatingIdUser" aria-label="Floating label select example">
                                            <c:forEach var="u" items="${sessionScope.listaUsers}">
                                                <option value="${u.getIdUsuario()}">${u.getNombre()} ${u.getApellidos()}</option>
                                            </c:forEach>
                                        </select>
                                        <label for="floatingIdUser">Seleccione Usuario</label>
                                    </div>
                                </div>                                

                                <div class="w-100"></div>
                                <div class="row g-3 justify-content-end align-self-end">
                                    <div class="col-auto">
                                        <button type="button" class="btn btn-secondary mb-3"
                                                data-bs-dismiss="modal">Cerrar</button>
                                        <button type="submit" class="btn btn-primary mb-3">Filtrar</button>
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
