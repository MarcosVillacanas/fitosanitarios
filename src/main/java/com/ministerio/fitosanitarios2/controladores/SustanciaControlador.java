package com.ministerio.fitosanitarios2.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ministerio.fitosanitarios2.modelos.Sustancia;
import com.ministerio.fitosanitarios2.repositorios.SustanciaRepositorio;

@RestController
public class SustanciaControlador {

	@Autowired
	private SustanciaRepositorio sustanciaRepositorio;
	
	@GetMapping("/sustancias")
	public ResponseEntity<List<Sustancia>> getProductos() {
		
		List<Sustancia> sustancias = sustanciaRepositorio.findAll();
		
		if (sustancias == null) 
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		else 
			return new ResponseEntity<>(sustancias, (sustancias.isEmpty())? HttpStatus.NO_CONTENT : HttpStatus.OK);
	}
	  

}
