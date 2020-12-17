package com.kodapp.clientservice.api.interfaces.web;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonInclude
public class WoeidWeatherRepresentation implements Serializable{

	private static final long serialVersionUID = -8189327768721554019L;
		
	@JsonProperty(value = "applicable_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date applicableDate;
	
	@JsonProperty(value = "min_temp")
	private Float minTemp;
	
	@JsonProperty(value = "max_temp")
	private Float maxTemp;

	public WoeidWeatherRepresentation(Date applicableDate, Float minTemp, Float maxTemp) {
		this.applicableDate =applicableDate;
		this.minTemp=minTemp;
		this.maxTemp=maxTemp;
	}
	
	public Date getApplicableDate() {
		return applicableDate;
	}

	public void setApplicableDate(Date applicableDate) {
		this.applicableDate = applicableDate;
	}

	public Float getMinTemp() {
		return minTemp;
	}

	public void setMinTemp(Float minTemp) {
		this.minTemp = minTemp;
	}

	public Float getMaxTemp() {
		return maxTemp;
	}

	public void setMaxTemp(Float maxTemp) {
		this.maxTemp = maxTemp;
	}

	@Override
	public String toString() {
		return "WoeidWeatherRepresentation [applicableDate=" + applicableDate + ", minTemp=" + minTemp + ", maxTemp="
				+ maxTemp + "]";
	}
	
	
	
}