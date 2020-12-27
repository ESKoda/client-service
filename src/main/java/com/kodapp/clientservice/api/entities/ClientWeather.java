package com.kodapp.clientservice.api.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "client_weather")
public class ClientWeather implements Serializable{

	private static final long serialVersionUID = 934829784713965879L;

	private Long idtClientWeather;
	private Float minTemperature;
	private Float maxTemperature;
	private Date datApplicable;
	
	public ClientWeather() {
		
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getIdtClientWeather() {
		return idtClientWeather;
	}

	public void setIdtClientWeather(Long idtClientWeather) {
		this.idtClientWeather = idtClientWeather;
	}

	@Column(name = "min_temperature", nullable = false)
	public Float getMinTemperature() {
		return minTemperature;
	}

	public void setMinTemperature(Float minTemperature) {
		this.minTemperature = minTemperature;
	}

	@Column(name = "max_temperature", nullable = false)
	public Float getMaxTemperature() {
		return maxTemperature;
	}

	public void setMaxTemperature(Float maxTemperature) {
		this.maxTemperature = maxTemperature;
	}

	@Column(name = "dat_applicable", nullable = false)
	public Date getDatApplicable() {
		return datApplicable;
	}

	public void setDatApplicable(Date date) {
		this.datApplicable = date;
	}

	@Override
	public String toString() {
		return "ClientWeather [idtClientWeather=" + idtClientWeather + ", minTemperature=" + minTemperature
				+ ", maxTemperature=" + maxTemperature + ", datApplicable=" + datApplicable + "]";
	}

	
}	