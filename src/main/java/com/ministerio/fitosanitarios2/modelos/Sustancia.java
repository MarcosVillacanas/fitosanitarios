package com.ministerio.fitosanitarios2.modelos;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Sustancia {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long sustancia_id;

    private String nombre;

    @ManyToMany()
    @JoinTable(name = "producto_sustancia", joinColumns = @JoinColumn(name = "sustancia_id"), inverseJoinColumns = @JoinColumn(name = "producto_id"))
    private List<Producto> productos;

    public Sustancia() {
    }

    public Sustancia(String nombre, List<Producto> productos) {
        this.nombre = nombre;
        this.productos = productos;
    }

    public long getSustancia_id() {
        return sustancia_id;
    }

    public void setSustancia_id(long sustancia_id) {
        this.sustancia_id = sustancia_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
}
