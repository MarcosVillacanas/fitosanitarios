const API_URI = 'http://localhost:8080/api/'
const arbol = $('.arbol');

const mapa = new Map([["cultivo", "especies"], ["especie", "plagas"], ["plaga", "sustancias"], ["sustancia", "productos"]])

$(function () {
    cargarCultivos();
});

$('.arbol').on('change', '.despliegue', function () {
    if (this.checked) {    // Desplegar
        limpiarDOM($(this).closest('li'))
        desplegarLista($(this).closest('li'));
    } else {    //Contraer
        contraerLista($(this).closest('li'))
    }
});

function renderizarHijos(elemento, datos, recurso) {
    datos.forEach(e => {
        elemento.append(
            crearElementoArbol(e, recurso)
        );
    });

    asignarTooltips(elemento);
}

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

function renderizarHijo(hijo) {
    let hijoString = '';
    let iteratorIndex = 0;
    for (const dato in hijo) {
        if (dato !== 'id') {
            if (iteratorIndex == 0)
                hijoString += hijo[dato];
            else if (iteratorIndex == 1)
                hijoString += ' (' + dato + ': ' + hijo[dato];
            else
                hijoString += '; ' + dato + ': ' + hijo[dato];
            iteratorIndex++;
        }
    }
    if (iteratorIndex > 1) {
        hijoString += ')';
    }
    return `<label class="label-arbol">${hijoString}</label>`
}

const cargarCultivos = () => {
    $.getJSON(`${API_URI}cultivos`, (res) => renderizarHijos(arbol, res, 'cultivo'));
}

function limpiarDOM(origen) {
    $('.arbol li').each((_, l) => {
        if (!(l.isEqualNode(origen[0]) || $.contains(l, origen[0]))) {
            contraerLista($(l))
        }
    });
}

function desplegarLista(lista) {
    $.getJSON(getResourceFromId(lista.attr('id')), (res) => {
        let nuevaLista = $(`<ul></ul>`);
        lista.append(nuevaLista);
        let hijos = mapa.get(lista.attr('id').split('-')[0]);
        renderizarHijos(nuevaLista, res[hijos], hijos.substring(0, hijos.length - 1));
    });
}

function contraerLista(lista) {
    lista.find('.despliegue').prop("checked", false);
    lista.children('ul').remove();
}

function getResourceFromId(id) {
    let splitted = id.split('-');
    return `${API_URI}${splitted[0]}/${splitted[1]}/${mapa.get(splitted[0])}`
}

function getNombreCientifico(item) {
    return item['nombreCientifico'];
}

function asignarTooltips(elemento) {
    elemento.find('.especie, .plaga').toArray().map(e => {
        let $label = $(e).find('.label-arbol').first();
        $label.easyTooltip({
            tooltipDir: "right",
            content: $label.attr('data-tooltip')
        });
    });
}