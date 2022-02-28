<%-- 
    Document   : index
    Created on : 17-feb-2022, 13:00:31
    Author     : jmhur
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Juan Miguel Store</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
        <link rel="stylesheet" href="css/styles.css"/>

    </head>
    <body>
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
                            <a class="nav-link active" aria-current="page" href="index.jsp">Inicio</a>
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
                        <c:if test="${sessionScope.role == 'Admin'}">
                            <li class="nav-item">
                                <a class="nav-link" href="administrarProductos.jsp">Administrar Productos</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="consultarVentas.jsp">Consultar Ventas</a>
                            </li>
                        </c:if>
                        <c:if test="${sessionScope.role == 'Cliente'}">
                            <li class="nav-item">
                                <a class="nav-link" href="consultarCompras.jsp">Consultar compras</a>
                            </li>
                        </c:if>
                    </ul>
                    <!-- Menú de la Derecha -->
                    <ul class="navbar-nav">
                        <c:choose>
                            <c:when test="${sessionScope.name == null}">
                                <li class="nav-item">
                                    <a class="nav-link" href="#" data-bs-toggle="modal" data-bs-target="#modalRegistro">Iniciar Sesión</a>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li class="nav-item dropdown">
                                    <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                        ${sessionScope.name}
                                    </a>
                                    <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
                                        <li><a class="dropdown-item" href="Carrito?accion=cerrar">Cerrar Sesión</a></li>
                                    </ul>
                                </li>
                            </c:otherwise>
                        </c:choose>
                    </ul>
                </div>
            </div>
        </nav>
        <!-- Modal para iniciar sesión o registrarse. -->
        <div class="modal fade" id="modalRegistro" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header text-center">
                        <h5 class="modal-title" id="exampleModalLabel">Inicia sesión o registrate</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <div class="row text-center">
                            <p id="alert-text" class="text-red"></p>
                        </div>
                        <!-- Menú de navegación del modal -->
                        <ul class="nav nav-pills mb-3 justify-content-center" id="pills-tab" role="tablist">
                            <li class="nav-item" role="presentation">
                                <button class="nav-link active" id="pills-home-tab" data-bs-toggle="pill"
                                        data-bs-target="#pills-home" type="button" role="tab" aria-controls="pills-home"
                                        aria-selected="true">Iniciar Sesión</button>
                            </li>
                            <li class="nav-item" role="presentation">
                                <button class="nav-link" id="pills-profile-tab" data-bs-toggle="pill"
                                        data-bs-target="#pills-profile" type="button" role="tab" aria-controls="pills-profile"
                                        aria-selected="false">Registrar</button>
                            </li>
                        </ul>
                        <div class="tab-content" id="pills-tabContent">
                            <div class="tab-pane fade show active" id="pills-home" role="tabpanel"
                                 aria-labelledby="pills-home-tab">
                                <!-- Formulario inicio de sesión -->
                                <form class="row g-3" action="Sesion" method="post">
                                    <input name="accion" type="hidden" value="sesion">
                                    <div class="form-floating mb-2">
                                        <input type="email" class="form-control" name="email" id="floatingCorreo"
                                               placeholder="example@mail.com" required>
                                        <label for="floatingCorreo">Correo</label>
                                    </div>
                                    <div class="form-floating">
                                        <input type="password" class="form-control" name="pass" id="floatingPass"
                                               placeholder="Password" required>
                                        <label for="floatingPass">Contraseña</label>
                                    </div>
                                    <div class="row g-3 justify-content-end align-self-end">
                                        <div class="col-auto">
                                            <button type="button" class="btn btn-secondary mb-3"
                                                    data-bs-dismiss="modal">Cerrar</button>
                                            <button type="submit" class="btn btn-primary mb-3">Iniciar Sesión</button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <div class="tab-pane fade" id="pills-profile" role="tabpanel"
                                 aria-labelledby="pills-profile-tab">
                                <!-- Formulario para registro -->
                                <form class="row g-3" action="Sesion" method="post">
                                    <input name="accion" type="hidden" value="registro">
                                    <div class="form-floating mb-2">
                                        <input type="text" class="form-control" name="name" id="floatingName"
                                               placeholder="Pepe" required>
                                        <label for="floatingName">Nombre</label>
                                    </div>
                                    <div class="form-floating">
                                        <input type="text" class="form-control" name="lastname" id="floatingLast"
                                               placeholder="Oliva" required>
                                        <label for="floatingLast">Apellidos</label>
                                    </div>
                                    <div class="form-floating">
                                        <input type="text" class="form-control" name="location" id="floatingLoc"
                                               placeholder="Madrid" required>
                                        <label for="floatingLoc">Localidad</label>
                                    </div>
                                    <div class="form-floating">
                                        <input type="email" class="form-control" name="email" id="floatingCorreo1"
                                               placeholder="example@gmail.com" required>
                                        <label for="floatingCorreo1">Correo</label>
                                    </div>
                                    <div class="form-floating">
                                        <input type="password" class="form-control" name="pass" id="floatingPass1"
                                               placeholder="Password" required>
                                        <label for="floatingPass1">Contraseña</label>
                                    </div>
                                    <div class="row g-3 justify-content-end align-self-end">
                                        <div class="col-auto">
                                            <button type="button" class="btn btn-secondary mb-3"
                                                    data-bs-dismiss="modal">Cerrar</button>
                                            <button type="submit" class="btn btn-primary mb-3">Registrar</button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <section class="banner">
            <div class="banner-content">
                <h1>Bienvenidos a JuanMi Store</h1>
                <h4>La mejor tienda</h4>
                <a href="Controller?accion=mostrar">Ver artículos</a>
            </div>

        </section>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>

    </body>
</html>
