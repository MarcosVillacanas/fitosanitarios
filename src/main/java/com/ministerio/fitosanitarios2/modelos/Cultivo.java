package com.ministerio.fitosanitarios2.modelos;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.ministerio.fitosanitarios2.Vistas;

import javax.persistence.JoinColumn;

@Entity
public class Cultivo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Vistas.NivelCultivos.class)
    private long cultivo_id;

    @JsonView(Vistas.NivelCultivos.class)
    private String nombre;

    @ManyToMany()
    @JoinTable(name = "especie_cultivo", joinColumns = @JoinColumn(name = "cultivo_id"), inverseJoinColumns = @JoinColumn(name = "especie_id"))
    @JsonView(Vistas.NivelCultivosEspecies.class)
    private List<Especie> especies;

    public Cultivo() {
    }

    public Cultivo(String nombre, List<Especie> especies) {
        this.nombre = nombre;
        this.especies = especies;
    }

    @JsonProperty("id")
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
