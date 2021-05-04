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
public class Especie {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long especie_id;
	
	private String nombreVulgar;
	
	private String nombreCientifico;
	
	@ManyToMany()
    @JoinTable(name="plaga_especie", joinColumns=@JoinColumn(name="especie_id"), inverseJoinColumns=@JoinColumn(name="plaga_id")) 
	private List<Plaga> plagas;

	public Especie() {
	}

	public Especie(String nombreVulgar, String nombreCientifico, List<Plaga> plagas) {
		this.nombreVulgar = nombreVulgar;
		this.nombreCientifico = nombreCientifico;
		this.plagas = plagas;
	}

	public long getEspecie_id() {
		return especie_id;
	}

	public void setEspecie_id(long especie_id) {
		this.especie_id = especie_id;
	}

	public String getNombreVulgar() {
		return nombreVulgar;
	}

	public void setNombreVulgar(String nombreVulgar) {
		this.nombreVulgar = nombreVulgar;
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
