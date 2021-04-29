package com.ministerio.fitosanitarios2.modelos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Producto {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long producto_id;

	private String nombre;
	
	private String url;
	
	public Producto() {
	}

	public Producto(String nombre, String url) {
		this.nombre = nombre;
		this.url = url;
	}

	public long getProducto_id() {
		return producto_id;
	}

	public void setProducto_id(long producto_id) {
		this.producto_id = producto_id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
