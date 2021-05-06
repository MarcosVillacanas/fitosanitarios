const API_URI = 'http://localhost:8080/api/'
const arbol = $('.arbol');
const generator = idGenerator();

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

function renderizarHijos(elemento, datos, resource) {
    //TODO: que sólo lleguen los nombres
    datos.forEach(e => {
        const id = generator.next().value;
        elemento.append(
            `<li id=cult-${e.id} class="${resource}">
                <div class="d-flex">
                    <label class="checkbox bounce">
                        <input type='checkbox' class='despliegue'>
                        <svg viewBox="0 0 21 21">
                            <polyline points="5 10.75 8.5 14.25 16 6"></polyline>
                        </svg>
                    </label>
                    <label class="label-arbol">${e.nombre}</label>
                </div>
            </li>`
        )
    })
}

const cargarCultivos = () => {
    $.getJSON(`${API_URI}cultivos`, (res) => renderizarHijos(arbol, res, 'cultivo'));
}

function limpiarDOM(origen) {
    $('.arbol li').each((i, l) => {
        if (!(l.isEqualNode(origen[0]) || $.contains(l, origen[0]))) {
            contraerLista($(l))
        }
    });
}

function desplegarLista(lista) {
    const endpoint = lista.attr('id').split('-')[1];
    console.log(endpoint);
    $.getJSON(`${API_URI}cultivos`, (res) => {
        let nuevaLista = $(`<ul></ul>`);
        lista.append(nuevaLista);
        renderizarHijos(nuevaLista, res, 'cultivo')
    });
}

function contraerLista(lista) {
    lista.find('.despliegue').prop("checked", false);
    lista.children('ul').remove();
}

function* idGenerator() {
    let nId = 1;
    while (true) {
        yield `c${nId}`;
        nId++;
    }
}

function getResourceFromId(id) {
    let splitted = id.split('-');
    // const endpoint = mapaRecursos.get(splitted[0]);
    // return [endpoint, splitted[1]];
    return `${API_URI}${splitted[0]}/${splitted[1]}`
}