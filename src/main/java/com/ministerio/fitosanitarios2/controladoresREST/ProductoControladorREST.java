package com.ministerio.fitosanitarios2.controladoresREST;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ministerio.fitosanitarios2.modelos.Producto;
import com.ministerio.fitosanitarios2.repositorios.ProductoRepositorio;

@RestController
@RequestMapping("/api")
public class ProductoControladorREST {

	@Autowired
	private ProductoRepositorio productoRepositorio;
	  
	@GetMapping("/producto/{producto_id}")
	public ResponseEntity<Producto> getProducto(@PathVariable Long producto_id) {
		
		Optional<Producto> producto = productoRepositorio.findById(producto_id);
		
		if (!producto.isPresent()) 
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		else 
			return new ResponseEntity<>(producto.get(), HttpStatus.OK);
	}

}
