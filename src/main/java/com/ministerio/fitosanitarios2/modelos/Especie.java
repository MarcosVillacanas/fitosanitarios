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
public class Especie {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonView(Vistas.NivelCultivosEspecies.class)
	private long especie_id;

	@JsonView(Vistas.NivelCultivosEspecies.class)
	private String nombre;

	@JsonView(Vistas.NivelCultivosEspecies.class)
	private String nombreCientifico;

	@ManyToMany()
	@JoinTable(name = "plaga_especie", joinColumns = @JoinColumn(name = "especie_id"), inverseJoinColumns = @JoinColumn(name = "plaga_id"))
	@JsonView(Vistas.NivelEspeciesPlagas.class)
	private List<Plaga> plagas;

	public Especie() {
	}

	public Especie(String nombre, String nombreCientifico, List<Plaga> plagas) {
		this.nombre = nombre;
		this.nombreCientifico = nombreCientifico;
		this.plagas = plagas;
	}

	@JsonProperty("id")
	public long getEspecie_id() {
		return especie_id;
	}

	public void setEspecie_id(long especie_id) {
		this.especie_id = especie_id;
	}

	public String getnombre() {
		return nombre;
	}

	public void setnombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombreCientifico() {
		return nombreCientifico;
	}

	public void setNombreCientifico(String nombreCientifico) {
		this.nombreCientifico = nombreCientifico;
	}

	public List<Plaga> getPlagas() {
		return plagas;
	}

	public void setPlagas(List<Plaga> plagas) {
		this.plagas = plagas;
	}
}
