package com.kodapp.clientservice.api.interfaces.web;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude
public class LocationWeatherRepresentation implements Serializable{

	private static final long serialVersionUID = 3099554252699853935L;

	@JsonProperty(value = "title")
	private String title;
	
	@JsonProperty(value = "location_type")
	private String locationType;
	
	@JsonProperty(value = "latt_long")
	private String lattLong;
	
	@JsonProperty(value = "woeid")
	private int woeid;
	
	@JsonProperty(value = "distance")
	private int distance;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLocationType() {
		return locationType;
	}

	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}

	public String getLattLong() {
		return lattLong;
	}

	public void setLattLong(String lattLong) {
		this.lattLong = lattLong;
	}

	public int getWoeid() {
		return woeid;
	}

	public void setWoeid(int woeid) {
		this.woeid = woeid;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	@Override
	public String toString() {
		return "LocationWeatherRepresentation [title=" + title + ", locationType=" + locationType + ", lattLong="
				+ lattLong + ", woeid=" + woeid + ", distance=" + distance + "]";
	}
	

	
}
