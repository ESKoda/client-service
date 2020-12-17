package com.kodapp.clientservice.api.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "client_weather")
public class ClientWeather implements Serializable{

	private static final long serialVersionUID = 934829784713965879L;

	private Long idtClientWeather;
	private Client idtClient;
	private Float min_temperature;
	private Float max_temperature;
	
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


	@OneToOne
	@JoinColumn(name = "idt_client")
	public Client getIdtClient() {
		return idtClient;
	}

	public void setIdtClient(Client idtClient) {
		this.idtClient = idtClient;
	}

	@Column(name = "min_temperature", nullable = false)
	public Float getMin_temperature() {
		return min_temperature;
	}

	public void setMin_temperature(Float min_temperature) {
		this.min_temperature = min_temperature;
	}

	@Column(name = "max_temperature", nullable = false)
	public Float getMax_temperature() {
		return max_temperature;
	}

	public void setMax_temperature(Float max_temperature) {
		this.max_temperature = max_temperature;
	}

	@Override
	public String toString() {
		return "ClientWeather [idtClientWeather=" + idtClientWeather + ", idtClient=" + idtClient + ", min_temperature="
				+ min_temperature + ", max_temperature=" + max_temperature + "]";
	}
	
	
	
	
}	