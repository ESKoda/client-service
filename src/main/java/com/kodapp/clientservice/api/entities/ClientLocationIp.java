package com.kodapp.clientservice.api.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "client_location")
public class ClientLocationIp implements Serializable {

	private static final long serialVersionUID = 9107951610347190605L;

	private Long idtClientLocation;
	private String namCountry;
	private String namRegion;
	private String namCity;
	private String codZip;
	private Float codLatitude;
	private Float codLongitude;
	
	public ClientLocationIp() {
		
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getIdtClientLocation() {
		return idtClientLocation;
	}

	public void setIdtClientLocation(Long idtClientLocation) {
		this.idtClientLocation = idtClientLocation;
	}


	@Column(name = "nam_country", nullable = false)
	public String getNamCountry() {
		return namCountry;
	}

	public void setNamCountry(String namCountry) {
		this.namCountry = namCountry;
	}

	@Column(name = "nam_region", nullable = false)
	public String getNamRegion() {
		return namRegion;
	}

	public void setNamRegion(String namRegion) {
		this.namRegion = namRegion;
	}

	@Column(name = "nam_city", nullable = false)
	public String getNamCity() {
		return namCity;
	}

	public void setNamCity(String namCity) {
		this.namCity = namCity;
	}
	
	@Column(name = "cod_zip", nullable = false)
	public String getCodZip() {
		return codZip;
	}

	public void setCodZip(String codZip) {
		this.codZip = codZip;
	}

	@Column(name = "cod_latitude", nullable = false)
	public Float getCodLatitude() {
		return codLatitude;
	}

	public void setCodLatitude(Float codLatitude) {
		this.codLatitude = codLatitude;
	}

	@Column(name = "cod_longitude", nullable = false)
	public Float getCodLongitude() {
		return codLongitude;
	}

	public void setCodLongitude(Float codLongitude) {
		this.codLongitude = codLongitude;
	}

	@Override
	public String toString() {
		return "ClientLocationIp [idtClientLocation=" + idtClientLocation + ", namCountry=" + namCountry
				+ ", namRegion=" + namRegion + ", namCity=" + namCity + ", codZip=" + codZip + ", codLatitude="
				+ codLatitude + ", codLongitude=" + codLongitude + "]";
	}


	
	
}