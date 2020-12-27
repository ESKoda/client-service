package com.kodapp.clientservice.api.services.impl;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE ;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kodapp.clientservice.api.dtos.ClientDto;
import com.kodapp.clientservice.api.interfaces.web.IpApiRepresentation;

@Service
public class IpApiServiceImpl implements Serializable{

	private static final long serialVersionUID = -733731050588178158L;

	@Value("${spring.webservice.ipapi.url}")
	private String urlGetIpApi;
		
	public ClientDto getLocalizationIp(String ipAddress)  throws Exception {
		
		String url = urlGetIpApi.replace("{ipAddress}", ipAddress.toString());
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", APPLICATION_JSON_VALUE);
		headers.set("Resource-Version", "1");
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.exchange(url, GET,  new HttpEntity<String>(headers), String.class);
		
		if (response.getStatusCode() == HttpStatus.OK) {
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			IpApiRepresentation result = mapper.readValue(response.getBody(), IpApiRepresentation.class);
						
			ClientDto clientDto = new ClientDto();
			clientDto.setCodLatitude(result.getLat());
			clientDto.setCodLongitude(result.getLon());
			clientDto.setCodZip(result.getZip());
			clientDto.setNamCity(result.getCity());
			clientDto.setNamCountry(result.getCountry());
			clientDto.setNamRegion(result.getRegionName());
			
			return clientDto;
		} else if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
			return null;
		}
		
		return null;
	}
	
	
}
