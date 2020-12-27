package com.kodapp.clientservice.api.dtos;

import java.util.Date;
import java.util.Optional;

public class ClientDto {

	private Optional<Long> idtClient = Optional.empty();
	private String namClient;
	private int ageClient;
	private String namCountry;
	private String namRegion;
	private String namCity;
	private String codZip;
	private Float codLatitude;
	private Float codLongitude;
	private String codIpAddress;
	private Long woeid;
	private Float minTemperature;
	private Float maxTemperature;
	private Date datApplicable;
	
	
	public ClientDto(){
		
	}
	
	public ClientDto(Float minTemperature,Float maxTemperature,Date applicableDate){
		this.minTemperature=minTemperature;
		this.maxTemperature=maxTemperature;
		this.datApplicable=applicableDate;
	}
	
	public ClientDto(Long woeid) {
		this.woeid = woeid;
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


	public String getNamCountry() {
		return namCountry;
	}


	public void setNamCountry(String namCountry) {
		this.namCountry = namCountry;
	}


	public String getNamRegion() {
		return namRegion;
	}


	public void setNamRegion(String namRegion) {
		this.namRegion = namRegion;
	}


	public String getNamCity() {
		return namCity;
	}


	public void setNamCity(String namCity) {
		this.namCity = namCity;
	}


	public String getCodZip() {
		return codZip;
	}


	public void setCodZip(String codZip) {
		this.codZip = codZip;
	}


	public Float getCodLatitude() {
		return codLatitude;
	}


	public void setCodLatitude(Float codLatitude) {
		this.codLatitude = codLatitude;
	}


	public Float getCodLongitude() {
		return codLongitude;
	}


	public void setCodLongitude(Float codLongitude) {
		this.codLongitude = codLongitude;
	}

	public String getCodIpAddress() {
		return codIpAddress;
	}


	public void setCodIpAddress(String codIpAddress) {
		this.codIpAddress = codIpAddress;
	}

	public Long getWoeid() {
		return woeid;
	}


	public void setWoeid(Long woeid) {
		this.woeid = woeid;
	}


	public Float getMinTemperature() {
		return minTemperature;
	}


	public void setMinTemperature(Float minTemperature) {
		this.minTemperature = minTemperature;
	}


	public Float getMaxTemperature() {
		return maxTemperature;
	}


	public void setMaxTemperature(Float maxTemperature) {
		this.maxTemperature = maxTemperature;
	}


	public Date getDatApplicable() {
		return datApplicable;
	}


	public void setDatApplicable(Date datApplicable) {
		this.datApplicable = datApplicable;
	}


	@Override
	public String toString() {
		return "ClientDto [idtClient=" + idtClient + ", namClient=" + namClient + ", ageClient=" + ageClient
				+ ", namCountry=" + namCountry + ", namRegion=" + namRegion + ", namCity=" + namCity + ", codZip="
				+ codZip + ", codLatitude=" + codLatitude + ", codLongitude=" + codLongitude + ", codIpAddress="
				+ codIpAddress + ", woeid=" + woeid + ", minTemperature=" + minTemperature + ", maxTemperature="
				+ maxTemperature + ", datApplicable=" + datApplicable + "]";
	}


}
