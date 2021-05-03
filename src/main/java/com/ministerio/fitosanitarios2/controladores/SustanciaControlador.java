package com.ministerio.fitosanitarios2.controladores;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ministerio.fitosanitarios2.modelos.Sustancia;
import com.ministerio.fitosanitarios2.repositorios.SustanciaRepositorio;

@RestController
public class SustanciaControlador {

	@Autowired
	private SustanciaRepositorio sustanciaRepositorio;

	@GetMapping("/sustancia/{sustancia_id}")
	public ResponseEntity<Sustancia> getSustancia(@PathVariable Long sustancia_id) {
		
		Optional<Sustancia> sustancia = sustanciaRepositorio.findById(sustancia_id);
		
		if (!sustancia.isPresent()) 
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		else 
			return new ResponseEntity<>(sustancia.get(), HttpStatus.OK);
	}
}
