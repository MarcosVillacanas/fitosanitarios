package com.ministerio.fitosanitarios2.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ministerio.fitosanitarios2.modelos.Cultivo;
import com.ministerio.fitosanitarios2.repositorios.CultivoRepositorio;

@RestController
public class CultivoControlador {

	@Autowired
	private CultivoRepositorio cultivoRepositorio;
	
	@GetMapping("/cultivos")
	public ResponseEntity<List<Cultivo>> getCultivos() {
		
		List<Cultivo> cultivos = cultivoRepositorio.findAll();
		
		if (cultivos == null) 
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		else 
			return new ResponseEntity<>(cultivos, (cultivos.isEmpty())? HttpStatus.NO_CONTENT : HttpStatus.OK);
	}
	  

}
