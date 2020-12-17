package com.kodapp.clientservice.api.dtos;

import java.util.Optional;

public class ClientDto {

	private Optional<Long> idtClient = Optional.empty();
	private String namClient;
	private int ageClient;
	
	public ClientDto(){
		
	}

	public Optional<Long> getIdtClient() {
		return idtClient;
	}

	public void setIdtClient(Optional<Long> idtClient) {
		this.idtClient = idtClient;
	}

	public String getNamClient() {
		return namClient;
	}

	public void setNamClient(String namClient) {
		this.namClient = namClient;
	}

	public int getAgeClient() {
		return ageClient;
	}

	public void setAgeClient(int ageClient) {
		this.ageClient = ageClient;
	}

	@Override
	public String toString() {
		return "ClientDto [idtClient=" + idtClient + ", namClient=" + namClient + ", ageClient=" + ageClient + "]";
	}




}
