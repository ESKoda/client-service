package com.kodapp.clientservice.api.services.impl;


import static org.springframework.http.HttpMethod.GET;

import java.io.File;
import java.io.Serializable;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.web.JsonPath;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kodapp.clientservice.api.interfaces.web.LocationWeatherRepresentation;
import com.kodapp.clientservice.api.interfaces.web.WoeidWeatherRepresentation;

@Service
public class LocationWeatherServiceImpl implements Serializable{

	private static final long serialVersionUID = 1369897712873951283L;

	
	@Value("${spring.webservice.metaweather.title.url}")
	private String urlGetLocationWeather;
	
	@Value("${spring.webservice.metaweather.woeid.url}")
	private String urlGetMinMaxTemperature;	

	@Value("${spring.webservice.metaweather.latlong.url}")
	private String urlGetWoeid;
	
	
	public LocationWeatherRepresentation[] getLocation(String title)  throws Exception {
		
		String url = urlGetLocationWeather.replace("{title}", title.toString());
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE);
		headers.set("Resource-Version", "1");
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.exchange(url, GET,  new HttpEntity<String>(headers), String.class);
		
		if (response.getStatusCode() == HttpStatus.OK) {
			ObjectMapper mapper = new ObjectMapper();
			LocationWeatherRepresentation[] result = mapper.readValue(response.getBody(), LocationWeatherRepresentation[].class);
			return result;
		} else if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
			return null;
		}
		
		return null;
	}
	
	public LocationWeatherRepresentation[] getLocationByLattLong(Float latt, Float longit)  throws Exception {
		
		String url = urlGetWoeid.replace("{latt}", latt.toString().trim()).replace("{longit}", longit.toString().trim());
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE);
		headers.set("Resource-Version", "1");
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.exchange(url, GET,  new HttpEntity<String>(headers), String.class);
		
		if (response.getStatusCode() == HttpStatus.OK) {
			ObjectMapper mapper = new ObjectMapper();
			LocationWeatherRepresentation[] result = mapper.readValue(response.getBody(), LocationWeatherRepresentation[].class);
			return result;
		} else if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
			return null;
		}
		
		return null;
	}
	
	public WoeidWeatherRepresentation getTemperatureByWoeid(Long woeid)  throws Exception {
		
		String url = urlGetMinMaxTemperature.replace("{woeid}", woeid.toString());
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE);
		headers.set("Resource-Version", "1");
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class,headers);
		
		if (response.getStatusCode() == HttpStatus.OK) {
						
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY, true);			
			WoeidWeatherRepresentation[] result = mapper.readValue(response.getBody(), WoeidWeatherRepresentation[].class);
			WoeidWeatherRepresentation woeidWeatherRep = new WoeidWeatherRepresentation(result[0].getApplicableDate(), result[0].getMinTemp(), result[0].getMaxTemp());
//			result[0].getApplicableDate();
//			result[0].getMinTemp();
//			result[0].getMaxTemp();

			
			return woeidWeatherRep;
		} else if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
			return null;
		}
		
		return null;
	}
	
}
