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
import com.ministerio.fitosanitarios2.modelos.Plaga;
import com.ministerio.fitosanitarios2.repositorios.PlagaRepositorio;

@RestController
@RequestMapping("/api")
public class PlagaControladorREST {

	@Autowired
	private PlagaRepositorio plagaRepositorio;
	
	@GetMapping("/plaga/{plaga_id}/sustancias")
	@JsonView(Vistas.NivelPlagasSustancias.class)
	public ResponseEntity<Plaga> getPlagaSustancias(@PathVariable Long plaga_id) {
		
		Optional<Plaga> plaga = plagaRepositorio.findById(plaga_id);
		
		if (!plaga.isPresent()) 
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		else 
			return new ResponseEntity<>(plaga.get(), HttpStatus.OK);
	}
	  
}
