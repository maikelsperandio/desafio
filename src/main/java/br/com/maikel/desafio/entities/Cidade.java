package br.com.maikel.desafio.entities;

import java.io.Serializable;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;

@Entity("cidades")
public class Cidade implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	private ObjectId id;

	@Property(value="ibge_id")
	private Long ibgeId;

	@Property(value="uf")
	private String uf;

	@Property(value="name")
	private String name;

	@Property(value="capital")
	private Boolean capital;

	@Property(value="lon")
	private Double longitude;

	@Property(value="lat")
	private Double latitude;

	@Property(value="no_accents")
	private String noAccents;

	@Property(value="alternative_names")
	private String alternativeNames;

	@Property(value="microregion")
	private String microRegiao;

	@Property(value="mesoregion")
	private String mesoRegiao;

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public Long getIbgeId() {
		return ibgeId;
	}

	public void setIbgeId(Long ibgeId) {
		this.ibgeId = ibgeId;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getCapital() {
		return capital;
	}

	public void setCapital(Boolean capital) {
		this.capital = capital;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public String getNoAccents() {
		return noAccents;
	}

	public void setNoAccents(String noAccents) {
		this.noAccents = noAccents;
	}

	public String getAlternativeNames() {
		return alternativeNames;
	}

	public void setAlternativeNames(String alternativeNames) {
		this.alternativeNames = alternativeNames;
	}

	public String getMicroRegiao() {
		return microRegiao;
	}

	public void setMicroRegiao(String microRegiao) {
		this.microRegiao = microRegiao;
	}

	public String getMesoRegiao() {
		return mesoRegiao;
	}

	public void setMesoRegiao(String mesoRegiao) {
		this.mesoRegiao = mesoRegiao;
	}
}
