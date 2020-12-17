package com.kodapp.clientservice.api.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "client")
public class Client implements Serializable{

	private static final long serialVersionUID = -4911835440201572887L;
	
	private Long idtClient;
	private String namClient;
	private int ageClient;
	private String codIpAddress;
	
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

	@Override
	public String toString() {
		return "Client [idtClient=" + idtClient + ", namClient=" + namClient + ", ageClient=" + ageClient
				+ ", codIpAddress=" + codIpAddress + "]";
	}
	
	
	
}
