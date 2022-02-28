<%-- 
    Document   : administrarProductos
    Created on : 27-feb-2022, 22:02:15
    Author     : jmhur
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Administrar Productos</title>
    </head>
    <body>
        <!-- Verificamos si tiene una sesión activa -->
        <c:if test="${sessionScope.name == null}">
            <c:redirect url="index.jsp"></c:redirect>
        </c:if>
        <!-- Mostramos barra de Menu Navegación -->
        <c:import url="navbar.jsp"></c:import>
    <div class="container">
            <div class="row justify-content-between">
                <div class="col">
                    <h2>Listado de productos</h2>
                </div>
                <div class="col text-end">
                    <button type="button" onclick="loadCreate()" data-bs-toggle="modal" data-bs-target="#modalActualizar" class="btn btn-primary">Crear Producto</button>
                </div>
            </div>

            <!-- Listado de productos -->
            <div class="row g-4 mt-2">
                <table class="table text-center table-bordered">
                    <thead>
                        <tr>
                            <th scope="col">Id</th>
                            <th scope="col">Nombre</th>
                            <th scope="col">Descripcion</th>
                            <th scope="col">Precio</th>
                            <th scope="col">Imagen</th>
                            <th scope="col">Categoria</th>
                            <th scope="col">Disponible</th>
                            <th scope="col">Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="p" items="${sessionScope.productsAdmin}">
                            <tr>
                                <th scope="row">${p.getIdProducto()}</th>
                                <td>${p.getNombre()}</td>
                                <td>${p.getDescripcion()}</td>
                                <td>${p.getPrecio()}</td>
                                <td>                                    
                                    <img src="img/${p.getImagen()}" widht="100" height="100" alt="${p.getNombre()}">
                                </td>
                                <td>${p.getCategoria().getNombre()}</td>
                                <td>${p.getDisponible()}</td>
                                <td width="150">
                                    <button type="button" onclick="loadModal(${p.getIdProducto()})" data-bs-toggle="modal" data-bs-target="#modalActualizar" class="btn btn-outline-warning"><i class="fas fa-edit"></i></button>
                                    <button type="button" onclick="eliminar(${p.getIdProducto()})" class="btn btn-outline-danger"><i class="fas fa-trash-alt"></i></button>                                
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>

        <!-- Modal para Modificar y Crear. -->
        <div class="modal fade" id="modalActualizar" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable modal-lg">
                <div class="modal-content">
                    <div class="modal-header text-center">
                        <h5 class="modal-title" id="exampleModalLabel"></h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">                        
                        <div class="tab-content" id="pills-tabContent">
                            <!-- Formulario inicio de sesión -->
                            <form class="row g-3" onsubmit="return false;" id="formProducto" enctype="multipart/form-data" method="post">
                                <input name="accion" type="hidden" id="accion">
                                <div class="col-6">
                                    <div class="form-floating">
                                        <input type="number" class="form-control" name="floatingId" id="floatingId"
                                               placeholder="1" required>
                                        <label for="floatingId">idProducto</label>
                                    </div>
                                </div>
                                <div class="col-6">
                                    <div class="form-floating">
                                        <input type="text" class="form-control" name="floatingName" id="floatingName"
                                               placeholder="Vino" required>
                                        <label for="floatingName">Nombre</label>
                                    </div>
                                </div>
                                <div class="col-6">
                                    <div class="form-floating">
                                        <input type="text" class="form-control" name="floatingDesc" id="floatingDesc"
                                               placeholder="Del año 2010" required>
                                        <label for="floatingDesc">Descripción</label>
                                    </div>
                                </div>
                                <div class="col-6">
                                    <div class="form-floating">
                                        <input type="text" class="form-control" name="floatingPrice" id="floatingPrice"
                                               placeholder="10" required min="1">
                                        <label for="floatingPrice">Precio</label>
                                    </div>
                                </div>                                

                                <div class="col-6">
                                    <div class="form-floating">
                                        <select class="form-select" name="floatingCategoria" id="floatingCategoria" aria-label="Floating label select example">                                            
                                            <c:forEach var="c" items="${sessionScope.categoria}">
                                                <option value="${c.getNombre()}">${c.getNombre()}</option>
                                            </c:forEach>
                                        </select>
                                        <label for="floatingCategoria">Seleccione una categoria</label>
                                    </div>
                                </div>

                                <div class="col-6">
                                    <div class="form-floating">
                                        <select class="form-select" name="floatingDisp" id="floatingDisp" aria-label="Floating label select example">
                                            <option value="Si">Si</option>
                                            <option value="No">No</option>
                                        </select>
                                        <label for="floatingDisp">Seleccione disponibilidad</label>
                                    </div>
                                </div>

                                <div class="col-6">
                                    <div class="mb-3">
                                        <label for="file" class="form-label">Seleccione un archivo</label>
                                        <input class="form-control" type="file" id="file" name="file">
                                    </div>
                                </div>

                                <div class="w-100"></div>
                                <div class="row g-3 justify-content-end align-self-end">
                                    <div class="col-auto">
                                        <button type="button" class="btn btn-secondary mb-3"
                                                data-bs-dismiss="modal">Cerrar</button>
                                        <button type="button" onclick="sendForm()" id="btnForm" class="btn btn-primary mb-3"></button>
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