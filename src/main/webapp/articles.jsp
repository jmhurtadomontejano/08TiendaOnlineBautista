<%-- 
    Document   : articles
    Created on : 25-feb-2022, 11:00:52
    Author     : jmhur
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>JuanMi Store | Artículos</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
        <link rel="stylesheet" href="css/styles.css"/>
    </head>
    <body>
        <c:import url="navbar.jsp"></c:import>

            <div class="container">
                <div class="row g-4 list-item">
                    <div class="col-3">
                        <div class="btn-group dropend mb-2">
                            <button type="button" id="btn-filtrar" class="btn btn-secondary dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
                                Filtrar por: Todo
                            </button>
                            <ul class="dropdown-menu category_list">
                                <li><a href="#" class="dropdown-item category_item" category="Todo">Todo</a></li>
                                <c:forEach var="c" items="${categorias}">
                                <li><a href="#" class="dropdown-item category_item" category="${c.getNombre()}">${c.getNombre()}</a></li>
                                </c:forEach>
                        </ul>
                    </div>
                </div>
                <div class="col-7">
                    <input id="buscador" class="form-control me-2" type="search" placeholder="escribe aqui los parametros de busqueda que quieras" aria-label="Search">
                </div>
                <div class="col">                    
                    <div class="btn-group dropstart">
                        <button type="button" class="btn btn-secondary dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
                            Ordenar por:
                        </button>
                        <ul class="dropdown-menu price_list">
                            <!--- Verificamos que order sea todo para añadir el active -->
                            <c:if test="${sessionScope.user == 'admin'}">
                                <li><a href="Controller?accion=mostrar" class="dropdown-item price_item" category="Todo">Esto no se ve</a></li>
                                </c:if>
                            <li><a href="Controller?accion=mostrar" class="dropdown-item price_item
                                   <c:if test = "${order=='todo'}">
                                       active
                                   </c:if>" 
                                   category="Todo">Todo</a>
                            </li>
                            <li><a href="Controller?accion=menor" class="dropdown-item price_item
                                   <c:if test = "${order=='menor'}">
                                       active
                                   </c:if>
                                   " category="Menor">Menor Precio</a>
                            </li>
                            <li><a href="Controller?accion=mayor" class="dropdown-item price_item
                                   <c:if test = "${order=='mayor'}">
                                       active
                                   </c:if>
                                   " category="Mayor">Mayor Precio</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>

            <!-- Listado de productos -->
            <div class="row g-4">
                <!-- Recorremos el listado de objetos que recibimos desde el controller -->
                <c:forEach var="p" items="${productos}">
                    <div class="col-4 product_item" category="${p.getCategoria().getNombre()}" product_name="${p.getNombre()}">
                        <div class="card" style="width: 18rem;">
                            <div class="card-header">
                                <h5 class="card-title text-center">${p.getNombre()}</h5>
                            </div>
                            <img src="img/${p.getImagen()}" class="card-img-top" alt="${p.getNombre()}" style="height: 14rem;">
                            <div class="card-body">

                                <p class="card-text">${p.getDescripcion()}</p>

                            </div>
                            <div class="card-footer text-center">
                                <p class="card-text text-center card-price">
                                    ${p.getPrecio()} &euro;
                                </p>
                                <c:choose>
                                    <c:when test="${p.getDisponible() == 'No'}">
                                        <button class="btn btn-danger" disabled>Producto no disponible <i class="far fa-ban"></i></button>
                                        </c:when>
                                        <c:when test="${sessionScope.name != null}">
                                        <a href="Carrito?accion=agregarCarrito&id=${p.getIdProducto()}" class="btn btn-primary">Agregar a Carrito <i class="fas fa-cart-plus"></i></a>
                                        </c:when>
                                        <c:otherwise>
                                        <a href="#" onclick="addMessage()" data-bs-toggle="modal" data-bs-target="#modalRegistro" class="btn btn-primary">Agregar a Carrito <i class="fas fa-cart-plus"></i></a>
                                        </c:otherwise>
                                    </c:choose>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>

        <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
        <script src="js/script.js"></script>

        <script>
                                            function addMessage() {
                                                document.getElementById('alert-text').innerHTML = "Para añadir al carrito, debes iniciar sesión primero";
                                            }

                                            function removeMessage() {
                                                document.getElementById('alert-text').innerHTML = "";
                                            }
        </script>
    </body> 
</html>
