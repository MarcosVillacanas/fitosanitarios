package com.ministerio.fitosanitarios2.modelos;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;

@Entity
public class Cultivo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long cultivo_id;

    private String nombre;

    @ManyToMany()
    @JoinTable(name = "especie_cultivo", joinColumns = @JoinColumn(name = "cultivo_id"), inverseJoinColumns = @JoinColumn(name = "especie_id"))
    private List<Especie> especies;

    public Cultivo() {
    }

    public Cultivo(String nombre, List<Especie> especies) {
        this.nombre = nombre;
        this.especies = especies;
    }

    public long getCultivo_id() {
        return cultivo_id;
    }

    public void setCultivo_id(long cultivo_id) {
        this.cultivo_id = cultivo_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Especie> getEspecies() {
        return especies;
    }

    public void setEspecies(List<Especie> especies) {
        this.especies = especies;
    }
}
