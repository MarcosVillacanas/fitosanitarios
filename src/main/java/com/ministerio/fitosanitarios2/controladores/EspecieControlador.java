package com.ministerio.fitosanitarios2.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ministerio.fitosanitarios2.modelos.Especie;
import com.ministerio.fitosanitarios2.repositorios.EspecieRepositorio;

@RestController
public class EspecieControlador {

	@Autowired
	private EspecieRepositorio especieRepositorio;
	
	@GetMapping("/especies")
	public ResponseEntity<List<Especie>> getEspecies() {
		
		List<Especie> especies = especieRepositorio.findAll();
		
		if (especies == null) 
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		else 
			return new ResponseEntity<>(especies, (especies.isEmpty())? HttpStatus.NO_CONTENT : HttpStatus.OK);
	}
	  

}
