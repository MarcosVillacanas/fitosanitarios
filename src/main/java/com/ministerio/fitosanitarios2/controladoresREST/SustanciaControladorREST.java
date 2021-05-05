package com.ministerio.fitosanitarios2.controladoresREST;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.ministerio.fitosanitarios2.Vistas;
import com.ministerio.fitosanitarios2.modelos.Sustancia;
import com.ministerio.fitosanitarios2.repositorios.SustanciaRepositorio;

@RestController
@RequestMapping("/api")
public class SustanciaControladorREST {

	@Autowired
	private SustanciaRepositorio sustanciaRepositorio;

	@GetMapping("/sustancia/{sustancia_id}/productos")
	@JsonView(Vistas.NivelSustanciasProductos.class)
	public ResponseEntity<Sustancia> getSustanciaProductos(@PathVariable Long sustancia_id) {
		
		Optional<Sustancia> sustancia = sustanciaRepositorio.findById(sustancia_id);
		
		if (!sustancia.isPresent()) 
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		else 
			return new ResponseEntity<>(sustancia.get(), HttpStatus.OK);
	}
}
