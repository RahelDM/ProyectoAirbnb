var map = L.map('map');
L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
    maxZoom: 19,
    attribution: '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>'
}).addTo(map);

//Lanzamos una consulta para mostrar todos los alojamientos en el mapa
buscarAlojamientos('');
rellenarDesplegablesDistrito();

const formPrecio = document.getElementById("form-precio");
formPrecio.addEventListener("submit", buscarPorPrecio);

const formHabitaciones = document.getElementById("form-habitaciones");
formHabitaciones.addEventListener("submit", buscarPorHabitaciones);

const formCalificaciones = document.getElementById("form-calificaciones");
formCalificaciones.addEventListener("submit", buscarPorCalificacion);

const formCompleto = document.getElementById("form-completo");
formCompleto.addEventListener("submit", buscarCompleto);

function rellenarDesplegablesDistrito() {
    let desplegable1 = document.getElementById('distrito');
    let desplegable2 = document.getElementById('distritoHab');
    let desplegable3 = document.getElementById('distritoCalificacion');
    let desplegable4 = document.getElementById('distritoCompleto');
    fetch('http://localhost:8080/distrito/').then(function (response) {
        return response.json();
    }).then(function (datos) {
        datos.forEach(distrito => {
            let opcion = document.createElement('option');
            opcion.value = distrito.id;
            opcion.text = distrito.nombre;

            desplegable1.add(opcion);
            let otra = opcion.cloneNode(true);
            desplegable2.add(otra);
            let otra2 = opcion.cloneNode(true);
            desplegable3.add(otra2);
            let otra3 = opcion.cloneNode(true);
            desplegable4.add(otra3);
        });
    }).catch(function (err) {
        // There was an error
        console.warn('Something went wrong.', err);
    });
}

function buscarPorPrecio(event) {
    event.preventDefault();
    const datos = new FormData(event.target);
    const datosObj = Object.fromEntries(datos.entries());
    //Búsqueda por distrito
    if (datosObj.min.length === 0 && datosObj.max.length === 0) {
        buscarAlojamientos('distrito/' + datosObj.distrito);
    } else if (datosObj.min.length === 0 || datosObj.max.length === 0) {
        alert("Debes introducir precio mínimo y máximo");
    } else {
        buscarAlojamientos('distrito/' + datosObj.distrito +
            '/min/' + datosObj.min + '/max/' + datosObj.max);
    }
}

function buscarPorCalificacion(event) {
    event.preventDefault();
    const datos = new FormData(event.target);
    const datosObj = Object.fromEntries(datos.entries());
    //Búsqueda por distrito
    if (datosObj.minCalificacion.length === 0 && datosObj.maxCalificacion.length === 0) {
        buscarAlojamientos('distrito/' + datosObj.distritoCalificacion);
    } else if (datosObj.minCalificacion.length === 0 || datosObj.maxCalificacion.length === 0) {
        alert("Debes introducir calificación mínima y máxima");
    } else {
        buscarAlojamientos('distrito/' + datosObj.distritoCalificacion +
            '/minCalificacion/' + datosObj.minCalificacion + '/maxCalificacion/' + datosObj.maxCalificacion);
    }
}


function buscarPorHabitaciones(event) {
    event.preventDefault();

    const datos = new FormData(event.target);
    const datosObj = Object.fromEntries(datos.entries());

    if (datosObj.habitaciones.length === 0 && datosObj.banos.length === 0) {
        buscarAlojamientos('distrito/' + datosObj.distritoHab);
    } else if (datosObj.habitaciones.length !== 0 && datosObj.banos.length === 0) {
        buscarAlojamientos('distrito/' + datosObj.distritoHab + '/habitaciones/' + datosObj.habitaciones);
    } else if (datosObj.habitaciones.length !== 0 && datosObj.banos.length !== 0) {
        buscarAlojamientos('distrito/' + datosObj.distritoHab +
            '/habitaciones/' + datosObj.habitaciones + '/banos/' + datosObj.banos);
    } else {
        alert('Debes introducir el número de habitaciones');
    }
}
function buscarCompleto(event) {
    event.preventDefault();

    const datos = new FormData(event.target);
    const datosObj = Object.fromEntries(datos.entries());

    if ((datosObj.minCompleta.length === 0 || datosObj.maxCompleta.length === 0)
        && (datosObj.minCalificacionCompleta.length === 0 || datosObj.maxCalificacionCompleta.length === 0)) {
        buscarAlojamientos('distrito/' + datosObj.distritoCompleto);
    } else if (datosObj.minCalificacionCompleta.length === 0 || datosObj.maxCalificacionCompleta.length === 0) {
        alert("Debes introducir calificación mínima y máxima");
    } else if (datosObj.minCompleta.length === 0 || datosObj.maxCompleta.length === 0) {
        alert("Debes introducir precio mínimo y máximo");
    } else if((datosObj.minCompleta.length !== 0 || datosObj.maxCompleta.length !== 0)
        && (datosObj.minCalificacionCompleta.length !== 0 || datosObj.maxCalificacionCompleta.length !== 0)) {
        buscarAlojamientos('distrito/' + datosObj.distritoCompleto +
            '/min/' + datosObj.minCompleta + '/max/' + datosObj.maxCompleta+ "/minCalificacion/" + datosObj.minCalificacionCompleta
            + "/maxCalificacion/" + datosObj.maxCalificacionCompleta);
    } else {
        alert('Debes introducir la calificación y el precio');
    }
}

function buscarAlojamientos(url) {
    map.spin(true);  // on
    fetch('http://localhost:8080/alojamiento/' + url).then(function (response) {
        return response.json();
    }).then(function (datos) {
        if (datos.length === 0) {
            alert("No se han encontrado resultados");
        } else {
            pintarAlojamientos(datos);
        }
        map.spin(false);
    }).catch(function (err) {
        // There was an error
        console.warn('Something went wrong.', err);
        map.spin(false);
    });
}

function pintarAlojamientos(datos) {
    var markers = L.markerClusterGroup();
    borrarMarcadores();
    datos.forEach(alojamiento => {
        var marker = L.marker([alojamiento.latitud, alojamiento.longitud]);
        marker.bindPopup("<b>" + alojamiento.nombre + "</b><br/><hr/>" +
            "<b>Propietario: </b>" + alojamiento.propietario.nombre + "<br/>" +
            "<b>Barrio: </b>" + alojamiento.barrio.nombre + "<br/>" +
            "<b>Tipo: </b>" + alojamiento.tipoHabitacion.nombre + "<br/>" +
            "<b>Precio/noche: </b>" + alojamiento.precio + "<br/>" +
            "<b>Puntuación: </b>" + alojamiento.puntuacion);
        markers.addLayer(marker)
    });
    map.addLayer(markers);

    map.fitBounds(markers.getBounds());
}

function borrarMarcadores() {
    map.eachLayer(function (layer) {
        if (layer instanceof L.MarkerClusterGroup) {
            map.removeLayer(layer);
        }
    });
}