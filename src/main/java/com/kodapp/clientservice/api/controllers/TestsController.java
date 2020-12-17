package com.kodapp.clientservice.api.controllers;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodapp.clientservice.api.entities.Client;
import com.kodapp.clientservice.api.interfaces.web.IpApiRepresentation;
import com.kodapp.clientservice.api.interfaces.web.LocationWeatherRepresentation;
import com.kodapp.clientservice.api.interfaces.web.WoeidWeatherRepresentation;
import com.kodapp.clientservice.api.services.ClientService;
import com.kodapp.clientservice.api.services.impl.IpApiServiceImpl;
import com.kodapp.clientservice.api.services.impl.LocationWeatherServiceImpl;

import javassist.NotFoundException;

@RestController
@RequestMapping("/api/clients")
public class TestsController {

	private static final Logger log = LoggerFactory.getLogger(ClientController.class);

	@Autowired
	private ClientService clientService;
	
	@Autowired
	private LocationWeatherServiceImpl locationWeatherService;
	
	@Autowired
	private IpApiServiceImpl ipApiService;
		
	public TestsController() {
		
	}
	
	
	@GetMapping(value = "/teste/{idtClient}")
	public ResponseEntity<Client> returnClientById(@PathVariable("idtClient") Long idtClient) throws NotFoundException{
		log.info("Bucando cliente por ID: {}", idtClient);
		Client client = this.clientService.findClientById(idtClient).orElseThrow(() -> new NotFoundException("Cliente não encontrado"));
		return ResponseEntity.ok(client);
	}
	

	@GetMapping(value = "/location/{title}")
	public ResponseEntity<LocationWeatherRepresentation[]> returnLocation(@PathVariable("title") String title) throws Exception {
		log.info("Bucando localizacao por nome: {}", title);
		LocationWeatherRepresentation[] location = this.locationWeatherService.getLocation(title);		
		return ResponseEntity.ok(location);
	}
	
	@GetMapping(value = "/location/lattlong/{latt},{longit}")
	public ResponseEntity<LocationWeatherRepresentation[]> returnLocationByLattLong(@PathVariable("latt") Float latt, @PathVariable("longit") Float longit) throws Exception {
		log.info("Bucando localizacao por latitude: {}", latt + " e longitude: {}", longit);
		LocationWeatherRepresentation[] location = this.locationWeatherService.getLocationByLattLong(latt, longit);		
		return ResponseEntity.ok(location);
	}
	
	@GetMapping(value = "/location/woeid/{woeid}")
	public ResponseEntity<WoeidWeatherRepresentation> returnTemperature(@PathVariable("woeid") Long woeid) throws Exception {
		log.info("Bucando temperatura por woeid: {}", woeid);
		WoeidWeatherRepresentation location = this.locationWeatherService.getTemperatureByWoeid(woeid);		
		return ResponseEntity.ok(location);
	}
	
	
	@GetMapping(value = "/ipAddress/{ipAddress}")
	public ResponseEntity<IpApiRepresentation> returnLocationByIp(@PathVariable("ipAddress") String ipAddress) throws Exception {
		log.info("Bucando localizacao por IP: {}", ipAddress);
		IpApiRepresentation locationByIp = this.ipApiService.getLocationByIp(ipAddress);
		return ResponseEntity.ok(locationByIp);
	}
	
	@GetMapping(value = "/ipAddress/testeIp")
	public ResponseEntity<IpApiRepresentation> returnTestIp(HttpServletRequest request) throws Exception {
		String ipAddress = request.getRemoteAddr();
		log.info("Bucando localizacao por IP: {}", ipAddress);
		
		if(ipAddress.equals("0:0:0:0:0:0:0:1")) {
			ipAddress="177.188.238.45";
		}
		
		IpApiRepresentation locationByIp = this.ipApiService.getLocationByIp(ipAddress);
		return ResponseEntity.ok(locationByIp);
	}

		
}
