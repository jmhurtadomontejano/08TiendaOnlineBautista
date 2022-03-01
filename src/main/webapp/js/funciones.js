$(document).ready(function () {
    //Crearemos un método de escucha para cuando de click en el botón + del carrito
    $('.btn-mas').click(function () {
        //Busca el input de cantidad
        var cantidad = $(this).parent().find('#cantidad').val();
        //aumentamos en uno la cantidad
        cantidad++;
        //Obtenemos la ip del input
        var idp = $(this).parent().find('#idp').val();
        //Creamos la url donde se atenderá la solicitud
        var url = "Carrito?accion=actualizarCantidad";
        //Enviamos la solicitud para ser atendida
        $.ajax({
            type: 'GET',
            url: url,
            data: "idp=" + idp + "&cantidad=" + cantidad,
            //Si recibimos un mensaje de éxito, refrescaremos la vista
            success: function (data, textStatus, jqXHR) {
                location.href = "Carrito?accion=Carrito";
            }
        });
    });
    //Crearemos un método de escucha para cuando de click en el botón - del carrito
    $('.btn-menos').click(function () {
        //Busca el input de cantidad
        var cantidad = $(this).parent().find('#cantidad').val();
        //disminuimos en uno la cantidad
        cantidad--;
        //Obtenemos la ip del input
        var idp = $(this).parent().find('#idp').val();
        //Creamos la url donde se atenderá la solicitud
        var url = "Carrito?accion=actualizarCantidad";
        //Enviamos la solicitud para ser atendida
        $.ajax({
            type: 'GET',
            url: url,
            data: "idp=" + idp + "&cantidad=" + cantidad,
            //Si recibimos un mensaje de éxito, refrescaremos la vista
            success: function (data, textStatus, jqXHR) {
                location.href = "Carrito?accion=Carrito";
            }
        });
    });
});

//Función para eliminar producto del carrito
function eliminar(idp) {
    //Mostraremos un mensaje de confirmación para eliminar
    swal({
        title: "Esta seguro de eliminar?",
        text: "Una vez eliminado, podrás volver a agregar este producto a tu carrito",
        icon: "warning",
        buttons: true,
        dangerMode: true,
    })  
    //Si procede con la eliminación, entrará aquí
            .then((willDelete) => {
                //Si realmente confirma la eliminación entrará aquí
                if (willDelete) {
                    //Creamos la url donde se atenderá la petición
                    var url = "Carrito?accion=delete";
                    //Enviaremos la petición por ajax
                    $.ajax({
                        type: 'GET',
                        url: url,
                        data: "idp=" + idp
                    });
                    //Al realizar la petición, mostraremos un mensaje de éxito
                    swal( {
                        title: "Producto eliminado",
                        icon: "success"
                        //Al dar click en el botón refrescaremos la vista
                    }).then((willDelete) => {
                        if (willDelete) {
                            parent.location.href = "Carrito?accion=Carrito";
                        }
                    });
                    //Si no desea eliminar el producto, mostraremos una alerta de información
                } else {
                    swal({
                        title: "Producto no eliminado!",
                        icon: "info"
                    });
                }
            });

}