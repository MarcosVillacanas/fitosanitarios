package com.ministerio.fitosanitarios2.controladores;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ministerio.fitosanitarios2.modelos.Especie;
import com.ministerio.fitosanitarios2.repositorios.EspecieRepositorio;

@RestController
public class EspecieControlador {

	@Autowired
	private EspecieRepositorio especieRepositorio;
	
	@GetMapping("/especie/{especie_id}")
	public ResponseEntity<Especie> getEspecie(@PathVariable Long especie_id) {
		
		Optional<Especie> especie = especieRepositorio.findById(especie_id);
		
		if (!especie.isPresent()) 
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		else 
			return new ResponseEntity<>(especie.get(), HttpStatus.OK);
	}

}
