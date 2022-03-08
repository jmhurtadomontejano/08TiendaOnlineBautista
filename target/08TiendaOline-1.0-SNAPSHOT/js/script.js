$(document).ready(function () {

    // AGREGANDO CLASE ACTIVE AL PRIMER ENLACE ====================
    $('.category_list .category_item[category="Todo"]').addClass('active');


    // FILTRANDO PRODUCTOS  ============================================
    $('.category_item').click(function () {
        var catProduct = $(this).attr('category');
        console.log(catProduct);


        // AGREGANDO CLASE ACTIVE AL ENLACE SELECCIONADO
        $('.category_item').removeClass('active');
        $(this).addClass('active');

        // OCULTANDO PRODUCTOS =========================
        $('.product_item').css('transform', 'scale(0)');
        function hideProduct() {
            $('.product_item').hide();
        }
        setTimeout(hideProduct, 400);

        // MOSTRANDO PRODUCTOS =========================
        function showProduct() {
            $('.product_item[category="' + catProduct + '"]').show();
            $('.product_item[category="' + catProduct + '"]').css('transform', 'scale(1)');
        }
        setTimeout(showProduct, 400);

        // Mostrando categoría en el botón filtrar
        $('#btn-filtrar').text("Filtrar por: " + catProduct);
        //Llamamos la funcion verificar cantidad
        verifyCant(catProduct);
    });

    // MOSTRANDO TODOS LOS PRODUCTOS =======================
    $('.category_item[category="Todo"]').click(function () {
        function showAll() {
            $('.product_item').show();
            $('.product_item').css('transform', 'scale(1)');
        }
        setTimeout(showAll, 400);
    });
});

//funcion para enviar un formulario
function sendForm() {
    //Obtenemos el botón y el texto que contiene para comparar
    var msg = document.getElementById("btnForm").textContent;
    //Si el mensaje es igual a esto, cambiaremos el mensaje.
    if (msg == "Actualizar") {
        msg = "actualizado"
        //Si no, lo cambiaremos por este
    } else {
        msg = "creado"
    }
    //Obtenemos el input de acción, para saber que realizaremos en el controlador
    var accion = document.getElementById("accion").value;
    //Creamos la url donde se atenderá la petición, junto a su acción
    var url = "Controller?accion=" + accion;
    //Realizamos la petición por ajax y método post
    $.ajax({
        type: 'POST',
        url: url,
        //Los datos enviados serán el formulario que estamos registrando en crear o actualizar producto
        data: new FormData(document.getElementById("formProducto")),
        contentType: false,
        cache: false,
        processData: false
                //Si la petición fue atendida correctamente, mostrara un alerta de éxito
    }).done(function () {
        swal({
            title: "Producto " + msg + " con exito",
            icon: "success"
                    //Al dar click en el botón, refrescará la vista
        }).then((willDelete) => {
            parent.location.href = "Controller?accion=administrar";
        });
        //Si la petición tuvo algún error, mostrara una alerta de error
    }).fail(function () {
        swal("Error al crear", "El id del producto ya existe", "error");
    });
}

//Función para verificar cantidad de productos
function verifyCant(catProduct) {
    //Verificamos que exista el mensaje, y lo eliminamos
    if ($('#no-category').length > 0) {
        $('#no-category').remove();
    }

    //Verificamos que catProduct no sea Todo.
    if (catProduct.indexOf("Todo") === -1) {
        //Obtener cuantos div hay con esa categoria:
        var cantDiv = $('.product_item[category="' + catProduct + '"]').toArray().length;
        //Verificamos que la cantidad sea mayor a 0
        if (cantDiv === 0) {
            //Damos el mensaje de que no hay productos con esta categoria
            //Obtenemos el elemento que contiene la lista
            $('.list-item').append("<h4 class='text-center' id='no-category'>No existen elementos con esta categoria.</h4>");
        }
    }
}

//Función para el buscador
//Creamos un evento cuando una tecla se suelte realizará esto
document.addEventListener("keyup", e => {
    //Buscará el input con el id buscador
    if (e.target.matches('#buscador')) {
        //Si la tecla oprimida es escape, limpiará el buscador
        if (e.key === "Escape") {
            e.target.value = "";
            $('.product_item').each(function () {
                $(this).show()
            });
        }
        //Si no es escape realizará lo siguiente
        else {
            //Recorremos todos los items con clase product.item, buscando el atributo que incluya la letra 
            //o palabra que estamos escribiendo, si coincide mostrará esos items, de lo contrario los oculta
            $('.product_item').each(function () {
                $(this).attr("product_name").toLowerCase().includes(e.target.value.toLowerCase())
                        ? $(this).show()
                        : $(this).hide();
            });
        }
    }
});

//Función para realizar una impresión
function imprimir() {
    //Obtenemos el div que vamos a imprimir
    var content = document.getElementById('print');
    //Creamos una variable que nos abra una nueva pestaña
    w = window.open();
    //Le añadimos las etiquetas de html y los estilos (Para que tome tal cual tenemos la página)
    w.document.write("<html><head><title></title>");
    //Escribimos los links del css
    w.document.write("<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css\" type=\"text/css\"/>");
    w.document.write("<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css\" type=\"text/css\"/>");
    //Cerramos la etiqueta head y abrimos el body
    w.document.write("</head><body>");
    //Imprimimos el div que obtuvimos de html
    w.document.write(content.innerHTML);
    //Cerramos el html
    w.document.write("</body></html>");
    //Realizamos una espera, para que se carguen los estilos y la página, y luego abrimos la pestaña de impresión.
    setTimeout(function () {
        w.document.close();
        w.print();
        w.close();
    }, 200);
}

//Funcion para cargar los detalles de los productos, y su respectiva tabla
function loadDetails(id) {
    let tabla = document.getElementsByClassName("table")[1];
    fetch('http://localhost:8080/08TiendaOline/webresources/generic/detalleVentas/' + id)
            .then(response => response.json())
            .then(json => {
                //Remover la tabla para insertar una nueva
                while (tabla.firstChild) {
                    tabla.removeChild(tabla.firstChild);
                }
                //Creación de la tabla
                let thead = document.createElement("thead");
                let tr = document.createElement("tr");
                let celdaNum = document.createElement("th");
                celdaNum.scope = "col";
                celdaNum.textContent = "#";
                let producto = document.createElement("th");
                producto.scope = "col";
                producto.textContent = "Producto";
                let precio = document.createElement("th");
                precio.scope = "col";
                precio.textContent = "Precio";
                let cantidad = document.createElement("th");
                cantidad.scope = "col";
                cantidad.textContent = "Cantidad";
                let total = document.createElement("th");
                total.scope = "col";
                total.textContent = "Total";
                tr.appendChild(celdaNum);
                tr.appendChild(producto);
                tr.appendChild(precio);
                tr.appendChild(cantidad);
                tr.appendChild(total);
                thead.appendChild(tr);
                tabla.appendChild(thead);

                let tbody = document.createElement("tbody");
                let totalPagar = 0;
                //Creamos las row con un for
                for (i = 0; i < json.length; i++) {
                    let fila = document.createElement("tr");
                    let primeraCol = document.createElement("th");
                    primeraCol.scope = "col";
                    primeraCol.textContent = i + 1;
                    let producto = document.createElement("td");
                    producto.textContent = json[i].productos.nombre;
                    let precioUnitario = document.createElement("td");
                    precioUnitario.textContent = json[i].pvp;
                    let cantidad = document.createElement("td");
                    cantidad.textContent = json[i].cantidad;
                    let total = document.createElement("td");
                    let precio = json[i].pvp * json[i].cantidad;
                    //Limitamos el precio a dos decimales
                    total.textContent = precio.toFixed(2);
                    totalPagar += precio;
                    fila.appendChild(primeraCol);
                    fila.appendChild(producto);
                    fila.appendChild(precioUnitario);
                    fila.appendChild(cantidad);
                    fila.appendChild(total);
                    tbody.appendChild(fila);
                    tabla.appendChild(tbody);
                }
                //Añadimos como paso final un campo para el total a pagar
                let newFila = document.createElement("tr");
                let totalPagado = document.createElement("th");
                totalPagado.colSpan = "3";
                totalPagado.textContent = "Total pagado: ";
                let totalPagadoNum = document.createElement("td");
                totalPagadoNum.colSpan = "2";
                totalPagadoNum.textContent = totalPagar;
                newFila.appendChild(totalPagado);
                newFila.appendChild(totalPagadoNum);
                tbody.appendChild(newFila);
                tabla.appendChild(tbody);
            });
}

//Función para eliminar un producto a través de javascript, ajax y alertas
function eliminar(id) {
    //Creamos una alerta de confirmación antes de eliminar
    swal({
        title: "Esta seguro de eliminar el producto?",
        text: "Una vez eliminado, este producto desaparecera del catalogo",
        icon: "warning",
        buttons: true,
        dangerMode: true
                //Si realmente desea eliminarlo entraremos a este método
    }).then((willDelete) => {
        //Si desea eliminarlo realizara este procedimiento
        if (willDelete) {
            //Creamos la url donde se tratara el archivo desde Java
            var url = "Controller?accion=delete";
            //Enviamos una petición a través de ajax
            $.ajax({
                type: 'GET',
                url: url,
                data: "idp=" + id
                        //Si la petición fue exitosa mostrara un alert confirmando
            }).done(function () {
                swal({
                    title: "Producto eliminado con exito",
                    icon: "success"
                            //Al dar click al botón, refrescará la vista
                }).then((willDelete) => {
                    parent.location.href = "Controller?accion=administrar";
                });
                //Si hubo algún error en la petición, mostrara un mensaje de error
            }).fail(function () {
                swal("Error al eliminar", "Producto tiene una venta registrada", "error");
            });
            //Si no lo desea eliminar simplemente mostrara un alert diciendo que no lo elimino
        } else {
            swal({
                title: "Producto no eliminado!",
                icon: "info"
            });
        }
    });
}

//Función para cargar el modal con sus datos
function loadModal(id) {
    //Hacemos un llamado al fetch para buscar los datos
    fetch('http://localhost:8080/08TiendaOline/webresources/generic/producto/' + id)
            .then(response => response.json())
            .then(json => {
                //Obtenemos el titulo del modal y lo cambiamos
                document.getElementById("exampleModalLabel").innerHTML = "Actualizar Producto";
                //Le damos el texto al botón de enviar el formulario
                document.getElementById("btnForm").innerHTML = "Actualizar";
                //Cambiamos el valor del input de accion
                document.getElementById("accion").value = "updateProduct";
                $("#floatingId").attr("readonly", "readonly");
                //Cambiamos las propiedades de los input y agregamos los datos
                document.getElementById("floatingId").value = json.idProducto;
                document.getElementById("floatingNameProduct").value = json.nombre;
                document.getElementById("floatingDesc").value = json.descripcion;
                document.getElementById("floatingPrice").value = json.precio;

                //Recorremos el select de disponibilidad
                var selectDisp = document.getElementById("floatingDisp");
                for (var i = 0; i < selectDisp.length; i++)
                {
                    //Una vez encontrado el select que coincida con el estado lo seleccionamos.
                    if (selectDisp.options[i].text == json.disponible)
                    {
                        // seleccionamos el valor que coincide
                        selectDisp.selectedIndex = i;
                    }
                }

                //Recorremos el select de categoria
                var select = document.getElementById("floatingCategoria");
                for (var i = 0; i < select.length; i++)
                {
                    //Una vez encontrado el select que coincida con el estado lo seleccionamos.
                    if (select.options[i].text == json.categoria.nombre)
                    {
                        // seleccionamos el valor que coincide
                        select.selectedIndex = i;
                    }
                }

            });
}

//Función para borrar los datos del modal
function loadCreate() {
    //Obtenemos el titulo del modal y lo cambiamos
    document.getElementById("exampleModalLabel").innerHTML = "Crear Producto";
    //Le damos el texto al botón de enviar el formulario
    document.getElementById("btnForm").innerHTML = "Crear";
    //Cambiamos el valor del input de accion
    document.getElementById("accion").value = "createProduct";
    //Cambiamos las propiedades de los input y limpiamos los datos
    $("#floatingId").removeAttr("readonly");
    document.getElementById("floatingId").value = "";
    document.getElementById("floatingName").value = "";
    document.getElementById("floatingDesc").value = "";
    document.getElementById("floatingPrice").value = "";
}

//Función para abrir modal para editar ventas
function loadModalVenta(id) {
    //Realizamos un fetch a la url para obtener los datos de la base de datos
    fetch('http://localhost:8080/08TiendaOline/webresources/generic/ventas/' + id)
            .then(response => response.json())
            .then(json => {
                //Obtenemos los campos, y le damos los valores al html
                document.getElementById("floatingId").value = json.idVenta;
                let partida = json.fecha;
                partida = partida.split("T");
                let date = new Date(partida[0].replace(/-/g, '\/'));
                document.getElementById("floatingName").value = json.cliente.nombre + " " + json.cliente.apellidos;
                document.getElementById("floatingDate").value = date.toLocaleDateString("fr-CA");

                //Recorremos el select de categoria
                var select = document.getElementById("floatingState");
                for (var i = 0; i < select.length; i++)
                {
                    //Una vez encontrado el select que coincida con el estado lo seleccionamos.
                    if (select.options[i].text == json.estado)
                    {
                        // seleccionamos el valor que coincide
                        select.selectedIndex = i;
                    }
                }

            });
}

//Función para enviar formulario de actualización de estado
function sendFormState() {
    //Creamos la url donde se tratará el archivo
    var url = "Controller?accion=actualizarPedido";
    //Enviamos una petición por método post, del formulario
    $.ajax({
        type: 'POST',
        url: url,
        data: new FormData(document.getElementById("formVenta")),
        contentType: false,
        cache: false,
        processData: false
                //Una vez ejecutada la petición y recibir done como respuesta creamos un alert para mostrar los datos
    }).done(function () {
        swal({
            title: "Estado actualizado con exito",
            icon: "success"
                    //Si le damos a ok, redireccionamos el flujo para actualizar la página
        }).then((willDelete) => {
            parent.location.href = "Controller?accion=consultarVentas";
        });
        //Si recibimos un error, creamos un alert de error
    }).fail(function () {
        swal("Error al actualizar", "El estado no se pudo actualizar", "error");
    });
}

//Función para verificar las fechas
function validarFechas() {
    //Obtenemos los datos de los input date
    var f1 = document.getElementById("floatingDate1").value;
    var f2 = document.getElementById("floatingDate2").value;
    //Comparamos si la fecha 1 es mayor que la fecha 2
    if (f1 > f2) {
        //Creamos una alerta en el input 2
        document.getElementById("floatingDate2").setCustomValidity("Esta fecha debe ser mayor");
    } else {
        //Liampiamoz la alerta en el input 2
        document.getElementById("floatingDate2").setCustomValidity("");
        //Creamos una url, donde realizaremos el tratamiento
        var url = "Controller?accion=filterByDate";
        //Creamos un arreglo de los valores que recibimos arriba de las fechas
        const datos = {
            'floatingDate1': f1,
            'floatingDate2': f2
        };
        //Enviamos la petición a través de ajax para realizar el tratamiento en la clase de Java
        $.ajax({
            type: 'GET',
            url: url,
            data: datos,
            success: function (data, textStatus, jqXHR) {
                parent.location.href = "consultarVentas.jsp";
            }

        });
    }
}