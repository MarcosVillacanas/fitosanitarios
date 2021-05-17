const API_URI = 'http://localhost:8080/api/'
const arbol = $('.arbol');

const mapa = new Map([["cultivo", "especies"], ["especie", "plagas"], ["plaga", "sustancias"], ["sustancia", "productos"]])

/**
 * Tras cargar el DOM hace una petición para cargar los cultivos (elementos iniciales)
 */
$(function () {
    $.getJSON(`${API_URI}cultivos`, (res) => renderizarHijos(arbol, res, 'cultivo'));
});

/**
 * Detecta cuando un elemento de la clase 'despliegue' ha cambiado su valor,
   lo que se corresponde con la activación o desactivación de un checkbox, y procede a
   desplegar, o contraer, el nodo correspondiente.
 */
$('.arbol').on('change', '.despliegue', function () {
    if (this.checked) {    // Desplegar
        limpiarDOM($(this).closest('li'))
        desplegarLista($(this).closest('li'));
    } else {    //Contraer
        contraerLista($(this).closest('li'))
    }
});

/**
 * Crea los elementos hijos a partir de la información recibida como consulta y los adjunta al nodo padre
 * @param {Object} elemento Elemento padre como objeto JQuery
 * @param {Object[]} datos Información en formato objeto JSON con la información para la generación de los nodos hijos
 * @param {String} recurso Tipo de recurso del modelo
 */
function renderizarHijos(elemento, datos, recurso) {
    datos.forEach(e => {
        elemento.append(
            crearElementoArbol(e, recurso)
        );
    });
    asignarTooltips(elemento);
}

/**
 * Recibe información sobre nuevo un nodo y devuelve un string con el código HTML de ese nodo.
 * @param {Object} elemento 
 * @param {String} recurso 
 * @returns {String} Código HTML con ese nuevo nodo
 */
function crearElementoArbol(elemento, recurso) {
    let newElemento =
        `<li id=${recurso}-${elemento.id} class="${recurso}">
            <div class="d-flex">
                <label class="checkbox bounce">
                    <input type='checkbox' class='despliegue' >
                    <svg viewBox="0 0 21 21">
                        <polyline points="5 10.75 8.5 14.25 16 6"></polyline>
                    </svg>
                </label>
                <label class="label-arbol" ${elemento.nombreCientifico ? "data-tooltip='"
            + elemento.nombreCientifico + "'" : ''}>${elemento.url ? "<a href='" +
                elemento.url + "' target='_blank'>" + elemento.nombre + "</a>" : elemento.nombre}</label>
            </div>
        </li>`;
    return newElemento;
}

/**
 * Contrae todas las sub-listas del árbol salvo los nodos iniciales
 * @param {Object} origen Elemento JQuery desde donde se llama a la función
 */
function limpiarDOM(origen) {
    $('.arbol li').each((_, l) => {
        if (!(l.isEqualNode(origen[0]) || $.contains(l, origen[0]))) {
            contraerLista($(l))
        }
    });
}

/**
 * Expande la lista pasada como argumento, cargando en DOM todos sus hijos
 * @param {Object} lista Lista enmascarada como objeto JQuery
 */
function desplegarLista(lista) {
    $.getJSON(getResourceFromId(lista.attr('id')), (res) => {
        let nuevaLista = $(`<ul></ul>`);
        lista.append(nuevaLista);
        let hijos = mapa.get(lista.attr('id').split('-')[0]);
        renderizarHijos(nuevaLista, res[hijos], hijos.substring(0, hijos.length - 1));
    });
}

/**
 * Contrae la lista pasada como argumento, eliminando del DOM todos sus hijos
 * @param {Object} lista Lista enmascarada como objeto JQuery
 */
function contraerLista(lista) {
    lista.find('.despliegue').prop("checked", false);
    lista.children('ul').remove();
}

/**
 * Identifica el tipo de elemento a partir de su id y devuelve la url para consulta
 * @param {String} id Identificador del elemento.
 * @returns {String} Devuelve la URI para la consulta de ese elemento
 */
function getResourceFromId(id) {
    let splitted = id.split('-');
    return `${API_URI}${splitted[0]}/${splitted[1]}/${mapa.get(splitted[0])}`
}

/**
 * @param {Object} item 
 * @returns {String} Devuelve el nombre científico de un objeto. Si no existe tal propiedad, devuelve 'undefined'
 */
function getNombreCientifico(item) {
    return item['nombreCientifico'];
}

/**
 * @param {Object} elemento Recibe un elemento JQuery para buscar todos sus hijos para asignarles el tooltip.
 */
function asignarTooltips(elemento) {
    elemento.find('.especie, .plaga').toArray().map(e => {
        let $label = $(e).find('.label-arbol').first();
        $label.easyTooltip({
            tooltipDir: "right",
            content: $label.attr('data-tooltip')
        });
    });
}