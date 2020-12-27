package com.kodapp.clientservice.api.services.impl;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE ;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kodapp.clientservice.api.dtos.ClientDto;
import com.kodapp.clientservice.api.interfaces.web.LocationWeatherRepresentation;

@Service
public class LocationWeatherServiceImpl implements Serializable{

	private static final long serialVersionUID = 1369897712873951283L;

	
	@Value("${spring.webservice.metaweather.title.url}")
	private String urlGetLocationWeather;
	
	@Value("${spring.webservice.metaweather.woeid.url}")
	private String urlGetMinMaxTemperature;	

	@Value("${spring.webservice.metaweather.latlong.url}")
	private String urlGetWoeid;
	
	
	public LocationWeatherRepresentation getLocation(String title)  throws Exception {
		
		String url = urlGetLocationWeather.replace("{title}", title.toString());
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", APPLICATION_JSON_VALUE);
		headers.set("Resource-Version", "1");
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.exchange(url, GET,  new HttpEntity<String>(headers), String.class);
		
		if (response.getStatusCode() == HttpStatus.OK) {
			ObjectMapper mapper = new ObjectMapper();
			LocationWeatherRepresentation[] result = mapper.readValue(response.getBody(), LocationWeatherRepresentation[].class);
			
			LocationWeatherRepresentation loc = new LocationWeatherRepresentation();
			loc.setTitle(result[0].getTitle());
			loc.setDistance(result[0].getDistance());
			loc.setLattLong(result[0].getLattLong());
			loc.setLocationType(result[0].getLocationType());
			loc.setWoeid(result[0].getWoeid());
			return loc;
			
		} else if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
			return null;
		}
		
		return null;
	}
	
	public ClientDto getLocationByLattLong(Float latt, Float longit)  throws Exception {
		
		String url = urlGetWoeid.replace("{latt}", latt.toString().trim()).replace("{longit}", longit.toString().trim());
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", APPLICATION_JSON_VALUE);
		headers.set("Resource-Version", "1");
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.exchange(url, GET,  new HttpEntity<String>(headers), String.class);
		
		if (response.getStatusCode() == HttpStatus.OK) {
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure ( DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			//LocationWeatherRepresentation[] result = mapper.readValue(response.getBody(), LocationWeatherRepresentation[].class);
			List<ClientDto> result = mapper.readValue(response.getBody(), new TypeReference<List<ClientDto>>(){});   		
			
			ClientDto woeid = result.get(0);
			return woeid;
			
		} else if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
			return null;
		}
		
		return null;
	}
	
	
	
	public ClientDto getTemperatureByWoeid(Long woeid)  throws Exception {
		
		String url = urlGetMinMaxTemperature.replace("{woeid}", woeid.toString());
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", APPLICATION_JSON_VALUE);
		headers.set("Resource-Version", "1");
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class,headers);
		
		if (response.getStatusCode() == HttpStatus.OK) {

			String jsonBody = response.getBody();
			JsonObject jsonObject = JsonParser.parseString(jsonBody).getAsJsonObject();
			
			JsonObject arrayJson = jsonObject.get("consolidated_weather").getAsJsonArray().get(0).getAsJsonObject();
			Float maxTemperature = arrayJson.get("max_temp").getAsFloat();
			Float minTemperature = arrayJson.get("min_temp").getAsFloat();
			String applicableDateAsString = arrayJson.get("applicable_date").getAsString();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date applicableDate = sdf.parse(applicableDateAsString);
			
			return new ClientDto(minTemperature,maxTemperature,applicableDate);

//			ObjectMapper mapper = new ObjectMapper();
//			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

//			ClientDto[] result = mapper.readValue(response.getBody(), ClientDto[].class);	
//			List<ClientDto[]> result = mapper.readValue(response.getBody(), new TypeReference<List<ClientDto[]>>(){});

//			ClientDto temperature = result.get(0)[0];
//			return temperature;
			
//			Date applicableDate = result.get(0)[0].getDatApplicable();
//			Float minTemperature = result.get(0)[0].getMinTemperature();
//			Float maxTemperature = result.get(0)[0].getMaxTemperature();
//			return new ClientDto(minTemperature,maxTemperature,applicableDate);
			
//			ClientDto clientDto = new ClientDto();
//			clientDto.setDatApplicable(result[0].getApplicableDate());
//			clientDto.setMaxTemperature(result[0].getMaxTemp());
//			clientDto.setMinTemperature(result[0].getMinTemp());
//			return clientDto;
			
		} else if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
			return null;
		}
		
		return null;
	}
		
}


