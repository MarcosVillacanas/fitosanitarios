package com.ministerio.fitosanitarios2.modelos;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.ministerio.fitosanitarios2.Vistas;

@Entity
public class Sustancia {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Vistas.NivelPlagasSustancias.class)
    private long sustancia_id;

    @JsonView(Vistas.NivelPlagasSustancias.class)
    private String nombre;

    @ManyToMany()
    @JoinTable(name = "producto_sustancia", joinColumns = @JoinColumn(name = "sustancia_id"), inverseJoinColumns = @JoinColumn(name = "producto_id"))
    @JsonView(Vistas.NivelSustanciasProductos.class)
    private List<Producto> productos;

    public Sustancia() {
    }

    public Sustancia(String nombre, List<Producto> productos) {
        this.nombre = nombre;
        this.productos = productos;
    }

    @JsonProperty("id")
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
