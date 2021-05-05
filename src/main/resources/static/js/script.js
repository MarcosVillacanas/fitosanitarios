const API_URI = 'http://localhost:8080/api/'
const arbol = $('.arbol');
const generator = idGenerator();

$(function () {
    cargarCultivos();
});

$('.arbol').on('click', '.despliegue', function () {
    if ($(this).val() === '+') {    // Desplegar
        limpiarDOM($(this).closest('li'))
        desplegarLista($(this).closest('li'));
    } else {    //Contraer
        contraerLista($(this).closest('li'))
    }
});

function renderizarHijos(elemento, datos) {
    //TODO: que sólo lleguen los nombres
    datos.forEach(e => {

        elemento.append(
            `<li>
                <input type='button' value='+' id=${generator.next().value} class='despliegue'>
                <label for="c2" class="label-arbol">${e.nombre}</label>
            </li>`
        )
    })
}

const cargarCultivos = () => {
    $.getJSON(`${API_URI}cultivos`, (res) => renderizarHijos(arbol, res));
}

function limpiarDOM(origen) {
    $('.arbol li').each((i, l) => {
        if (!$.contains(l, origen[0])) {
            contraerLista($(l))
        }
    });
}

function desplegarLista(lista) {
    lista.find('.despliegue').first().val('-');
    $.getJSON(`${API_URI}cultivos`, (res) => {
        let nuevaLista = $(`<ul></ul>`);
        lista.append(nuevaLista);
        renderizarHijos(nuevaLista, res)
    });
}

function contraerLista(lista) {
    lista.find('.despliegue').first().val('+');
    lista.children('ul').remove();
}

function* idGenerator() {
    let nId = 1;
    while (true) {
        yield `c${nId}`;
        nId++;
    }
}