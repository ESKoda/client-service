package com.kodapp.clientservice.api.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "client")
public class Client implements Serializable{

	private static final long serialVersionUID = -4911835440201572887L;
	
	private Long idtClient;
	private ClientLocationIp clientLocation;
	private String namClient;
	private int ageClient;
	private String codIpAddress;
	private ClientWeather clientWeather;
	
	public Client(){
		
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getIdtClient() {
		return idtClient;
	}

	public void setIdtClient(Long idtClient) {
		this.idtClient = idtClient;
	}

	@Column(name = "nam_client", nullable = false)
	public String getNamClient() {
		return namClient;
	}

	public void setNamClient(String namClient) {
		this.namClient = namClient;
	}

	@Column(name = "age_client", nullable = false)
	public int getAgeClient() {
		return ageClient;
	}

	public void setAgeClient(int ageClient) {
		this.ageClient = ageClient;
	}

	@Column(name = "cod_ip_adress", nullable = false)
	public String getCodIpAddress() {
		return codIpAddress;
	}

	public void setCodIpAddress(String codIpAddress) {
		this.codIpAddress = codIpAddress;
	}
	
	@JoinColumn(name = "idt_client_location")
	@ManyToOne(fetch = FetchType.EAGER)
	public ClientLocationIp getClientLocation() {
		return clientLocation;
	}

	public void setClientLocation(ClientLocationIp clientLocation) {
		this.clientLocation = clientLocation;
	}

	@JoinColumn(name = "idt_client_weather")
	@ManyToOne(fetch = FetchType.EAGER)
	public ClientWeather getClientWeather() {
		return clientWeather;
	}

	public void setClientWeather(ClientWeather clientWeather) {
		this.clientWeather = clientWeather;
	}

	@Override
	public String toString() {
		return "Client [idtClient=" + idtClient + ", clientLocation=" + clientLocation + ", namClient=" + namClient
				+ ", ageClient=" + ageClient + ", codIpAddress=" + codIpAddress + ", clientWeather=" + clientWeather
				+ "]";
	}

	
	
}
