package com.kodapp.clientservice.api.interfaces.web;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude
public class IpApiRepresentation implements Serializable{

	private static final long serialVersionUID = 9091217892233393388L;

	
	@JsonProperty(value = "status")
	private String status;
	
	@JsonProperty(value = "country")
	private String country;
	
	@JsonProperty(value = "countryCode")
	private String countryCode;
	
	@JsonProperty(value = "region")
	private String region;
	
	@JsonProperty(value = "regionName")
	private String regionName;
	
	@JsonProperty(value = "city")
	private String city;
	
	@JsonProperty(value = "zip")
	private String zip;
	
	@JsonProperty(value = "lat")
	private Float lat;
	
	@JsonProperty(value = "lon")
	private Float lon;
	
	@JsonProperty(value = "timezone")
	private String timezone;
	
	@JsonProperty(value = "isp")
	private String isp;
	
	@JsonProperty(value = "org")
	private String org;
	
	@JsonProperty(value = "as")
	private String as;
	
	@JsonProperty(value = "query")
	private String query;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public Float getLat() {
		return lat;
	}

	public void setLat(Float lat) {
		this.lat = lat;
	}

	public Float getLon() {
		return lon;
	}

	public void setLon(Float lon) {
		this.lon = lon;
	}

	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	public String getIsp() {
		return isp;
	}

	public void setIsp(String isp) {
		this.isp = isp;
	}

	public String getOrg() {
		return org;
	}

	public void setOrg(String org) {
		this.org = org;
	}

	public String getAs() {
		return as;
	}

	public void setAs(String as) {
		this.as = as;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}
	
	
	
	
}


