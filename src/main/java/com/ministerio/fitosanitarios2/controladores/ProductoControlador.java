package com.ministerio.fitosanitarios2.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ministerio.fitosanitarios2.modelos.Producto;
import com.ministerio.fitosanitarios2.repositorios.ProductoRepositorio;

@RestController
public class ProductoControlador {

	@Autowired
	private ProductoRepositorio productoRepositorio;
	
	@GetMapping("/productos")
	public ResponseEntity<List<Producto>> getProductos() {
		
		List<Producto> productos = productoRepositorio.findAll();
		
		if (productos == null) 
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		else 
			return new ResponseEntity<>(productos, (productos.isEmpty())? HttpStatus.NO_CONTENT : HttpStatus.OK);
	}
	  

}
