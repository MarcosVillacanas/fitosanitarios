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
public class Plaga {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonView(Vistas.NivelEspeciesPlagas.class)
	private long plaga_id;

	@JsonView(Vistas.NivelEspeciesPlagas.class)
	private String nombreVulgar;

	@JsonView(Vistas.NivelEspeciesPlagas.class)
	private String nombreCientifico;

	@JsonView(Vistas.NivelEspeciesPlagas.class)
	private String url;

	@ManyToMany()
	@JoinTable(name = "sustancia_plaga", joinColumns = @JoinColumn(name = "plaga_id"), inverseJoinColumns = @JoinColumn(name = "sustancia_id"))
	@JsonView(Vistas.NivelPlagasSustancias.class)
	private List<Sustancia> sustancias;

	public Plaga() {
	}

	public Plaga(String nombreVulgar, String nombreCientifico, List<Sustancia> sustancias) {
		this.nombreVulgar = nombreVulgar;
		this.nombreCientifico = nombreCientifico;
		this.url = "https://plagas/" + nombreVulgar;
		this.sustancias = sustancias;
	}

	@JsonProperty("id")
	public long getPlaga_id() {
		return plaga_id;
	}

	public void setPlaga_id(long plaga_id) {
		this.plaga_id = plaga_id;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<Sustancia> getSustancias() {
		return sustancias;
	}

	public void setSustancias(List<Sustancia> sustancias) {
		this.sustancias = sustancias;
	}
}
