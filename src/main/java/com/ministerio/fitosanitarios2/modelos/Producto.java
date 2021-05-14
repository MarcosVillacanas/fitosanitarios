package com.ministerio.fitosanitarios2.modelos;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.ministerio.fitosanitarios2.Vistas;

@Entity
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Vistas.NivelSustanciasProductos.class)
    private long producto_id;

    @JsonView(Vistas.NivelSustanciasProductos.class)
    private String nombre;

    @JsonView(Vistas.NivelSustanciasProductos.class)
    private String url;

    public Producto() {
    }

    public Producto(String nombre) {
        this.nombre = nombre;
        this.url = "https://es.wikipedia.org/wiki/Especial:Aleatoria";
    }

    @JsonProperty("id")
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
