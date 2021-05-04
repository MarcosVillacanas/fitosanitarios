package com.ministerio.fitosanitarios2.controladoresREST;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ministerio.fitosanitarios2.modelos.*;
import com.ministerio.fitosanitarios2.repositorios.*;

@RestController
public class CultivoControladorREST {

    @Autowired
    private CultivoRepositorio cultivoRepositorio;

    @Autowired
    private EspecieRepositorio especieRepositorio;

    @Autowired
    private PlagaRepositorio plagaRepositorio;

    @Autowired
    private ProductoRepositorio productoRepositorio;

    @Autowired
    private SustanciaRepositorio sustanciaRepositorio;

    @PostConstruct
    public void init() {

        // 30 productos fitosanitarios.

        List<Producto> productos = generadorProductos(new String[]{"Hidrogeno", "Helio", "Litio", "Berilio", "Boro", "Carbono", "Nitrogeno", "Oxigeno",
                "Fluor", "Neon", "Sodio", "Magnesio", "Aluminio", "Silice", "Fosforo", "Azufre", "Cloro", "Argon", "Potasio", "Calcio", "Escandio",
                "Titanio", "Vanadio", "Cromo", "Manganeso", "Hierro", "Cobalto", "Niquel", "Cobre", "Zinc"});

        assert productos.size() >= 30;

        productoRepositorio.saveAll(productos);

        // 20 sustancias activas.

        List<Sustancia> sustancias = generadorSustancias(new String[]{"Alguicida", "Antimicrobiano", "Desecante", "Defoliante", "Desinfectante",
                "Fungicida", "Herbicida", "Insecticida", "Regulador", "Acaricida", "Naftalina", "Ovicida", "Feromona", "Repelente", "Rodenticida",
                "Molusquicida", "Conservante", "Antimicotico", "Mataratas", "Taponador"});

        assert sustancias.size() >= 20;

        // RESTRICCIÓN 1
        restriccionesProductosSustancias(productos, sustancias);

        sustanciaRepositorio.saveAll(sustancias);

//		 16 plagas.

        List<Plaga> plagas = generadorPlagas(new String[]{"Pulgon", "Cochinilla", "Trip", "Oruga", "Mosca", "Escarabajo", "Saltamontes", "Gusano",
                "Caracol", "Babosa", "Hormiga", "Topo", "Nematodo", "Roya", "Mildiu", "Oidio"});

        assert plagas.size() >= 16;

        // RESTRICCIÓN 2
        restriccionesSustanciasPlagas(sustancias, plagas);

        plagaRepositorio.saveAll(plagas);

        // 8 especies

        List<Especie> especies = generadorEspecies(new String[]{"Limon", "Manzana", "Granada", "Higo", "Nectarina", "Melocoton", "Fresa", "Cereza"});

        assert especies.size() >= 8;

        // RESTRICCIÓN 3
        restriccionesPlagasEspecies(plagas, especies);

        especieRepositorio.saveAll(especies);

        // 4 categorías de cultivos

        List<Cultivo> cultivos = generadorCultivos(new String[]{"Primavera", "Verano", "Otono", "Invierno"});

        assert cultivos.size() >= 4;

        // RESTRICCIÓN 4
        restriccionesEspeciesCultivos(especies, cultivos);

        cultivoRepositorio.saveAll(cultivos);
    }

    @GetMapping("/cultivos")
    public ResponseEntity<List<Cultivo>> getCultivos() {

        List<Cultivo> cultivos = cultivoRepositorio.findAll();

        if (cultivos == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(cultivos, (cultivos.isEmpty()) ? HttpStatus.NO_CONTENT : HttpStatus.OK);
    }

    @GetMapping("/cultivo/{cultivo_id}")
    public ResponseEntity<Cultivo> getCultivo(@PathVariable Long cultivo_id) {

        Optional<Cultivo> cultivo = cultivoRepositorio.findById(cultivo_id);

        if (!cultivo.isPresent())
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(cultivo.get(), HttpStatus.OK);
    }

    private List<Producto> generadorProductos(String[] nombreProductos) {

        return Arrays.stream(nombreProductos).map(nombre -> new Producto(nombre)).collect(Collectors.toList());
    }

    private List<Sustancia> generadorSustancias(String[] nombreSustancias) {

        return Arrays.stream(nombreSustancias).map(nombre -> new Sustancia(nombre, new LinkedList<>())).collect(Collectors.toList());
    }

    private void restriccionesProductosSustancias(List<Producto> productos, List<Sustancia> sustancias) {

        // Al menos 5 productos deberán contener al menos 2 sustancias activas diferentes.

        Producto hidrogeno = productos.get(0);
        Sustancia alguicida = sustancias.get(0);
        alguicida.getProductos().add(hidrogeno);
        Sustancia antimicrobiano = sustancias.get(1);
        antimicrobiano.getProductos().add(hidrogeno);

        Producto helio = productos.get(1);
        Sustancia desecante = sustancias.get(2);
        desecante.getProductos().add(helio);
        Sustancia defoliante = sustancias.get(3);
        defoliante.getProductos().add(helio);

        Producto litio = productos.get(2);
        Sustancia desinfectante = sustancias.get(4);
        desinfectante.getProductos().add(litio);
        Sustancia fungicida = sustancias.get(5);
        fungicida.getProductos().add(litio);

        Producto berilio = productos.get(3);
        Sustancia herbicida = sustancias.get(6);
        herbicida.getProductos().add(berilio);
        Sustancia insecticida = sustancias.get(7);
        insecticida.getProductos().add(berilio);

        Producto boro = productos.get(4);
        Sustancia regulador = sustancias.get(8);
        regulador.getProductos().add(boro);
        Sustancia acaricida = sustancias.get(9);
        acaricida.getProductos().add(boro);
    }

    private List<Plaga> generadorPlagas(String[] nombrePlagas) {

        return Arrays.stream(nombrePlagas).map(nombre -> new Plaga(nombre, "Plagea " + nombre + " Infectatis", new LinkedList<>())).collect(Collectors.toList());
    }

    private void restriccionesSustanciasPlagas(List<Sustancia> sustancias, List<Plaga> plagas) {

        // Al menos dos sustancias deberán ser aplicables a 3 plagas diferentes, y 4 sustancias a al menos dos plagas diferentes.

        Sustancia alguicida = sustancias.get(0);
        Plaga pulgon = plagas.get(0);
        pulgon.getSustancias().add(alguicida);
        Plaga cochinilla = plagas.get(1);
        cochinilla.getSustancias().add(alguicida);
        Plaga trip = plagas.get(2);
        trip.getSustancias().add(alguicida);

        Sustancia antimicrobiano = sustancias.get(1);
        Plaga oruga = plagas.get(3);
        oruga.getSustancias().add(antimicrobiano);
        Plaga mosca = plagas.get(4);
        mosca.getSustancias().add(antimicrobiano);
        Plaga escarabajo = plagas.get(5);
        escarabajo.getSustancias().add(antimicrobiano);

        Sustancia desecante = sustancias.get(2);
        Plaga saltamontes = plagas.get(6);
        saltamontes.getSustancias().add(desecante);
        Plaga gusano = plagas.get(7);
        gusano.getSustancias().add(desecante);

        Sustancia defoliante = sustancias.get(3);
        Plaga caracol = plagas.get(8);
        caracol.getSustancias().add(defoliante);
        Plaga babosa = plagas.get(9);
        babosa.getSustancias().add(defoliante);

        Sustancia desinfectante = sustancias.get(4);
        Plaga hormiga = plagas.get(10);
        hormiga.getSustancias().add(desinfectante);
        Plaga topo = plagas.get(11);
        topo.getSustancias().add(desinfectante);

        Sustancia fungicida = sustancias.get(4);
        Plaga nematodo = plagas.get(12);
        nematodo.getSustancias().add(fungicida);
        Plaga roya = plagas.get(13);
        roya.getSustancias().add(fungicida);
    }

    private List<Especie> generadorEspecies(String[] nombreEspecies) {

        return Arrays.stream(nombreEspecies).map(nombre -> new Especie(nombre, "Especea " + nombre + " Plantatus", new LinkedList<>())).collect(Collectors.toList());
    }

    private void restriccionesPlagasEspecies(List<Plaga> plagas, List<Especie> especies) {

        // Al menos una plaga debera  afectar a 3 especies diferentes, y dos plagas a al menos dos especies diferentes.

        Plaga pulgon = plagas.get(0);
        Especie limon = especies.get(0);
        limon.getPlagas().add(pulgon);
        Especie manzana = especies.get(1);
        manzana.getPlagas().add(pulgon);
        Especie granada = especies.get(2);
        granada.getPlagas().add(pulgon);

        Plaga cochinilla = plagas.get(1);
        Especie higo = especies.get(3);
        higo.getPlagas().add(cochinilla);
        Especie nectarina = especies.get(4);
        nectarina.getPlagas().add(cochinilla);

        Plaga trip = plagas.get(2);
        Especie melocoton = especies.get(5);
        melocoton.getPlagas().add(trip);
        Especie fresa = especies.get(6);
        fresa.getPlagas().add(trip);
    }

    private List<Cultivo> generadorCultivos(String[] nombreCultivos) {

        return Arrays.stream(nombreCultivos).map(nombre -> new Cultivo(nombre, new LinkedList<>())).collect(Collectors.toList());
    }

    private void restriccionesEspeciesCultivos(List<Especie> especies, List<Cultivo> cultivos) {

        //  Al menos una especie deberá pertenecer a dos o más categorías.

        Especie limon = especies.get(0);
        Cultivo primavera = cultivos.get(0);
        primavera.getEspecies().add(limon);
        Cultivo verano = cultivos.get(1);
        verano.getEspecies().add(limon);

        Especie manzana = especies.get(1);
        Cultivo otono = cultivos.get(2);
        otono.getEspecies().add(manzana);
        Cultivo invierno = cultivos.get(3);
        invierno.getEspecies().add(manzana);
    }
}
