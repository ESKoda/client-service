package com.kodapp.clientservice.api.controllers;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodapp.clientservice.api.dtos.ClientDto;
import com.kodapp.clientservice.api.entities.Client;
import com.kodapp.clientservice.api.entities.ClientLocationIp;
import com.kodapp.clientservice.api.entities.ClientWeather;
import com.kodapp.clientservice.api.repositories.ClientLocationIpRepository;
import com.kodapp.clientservice.api.repositories.ClientWeatherRepository;
import com.kodapp.clientservice.api.response.Response;
import com.kodapp.clientservice.api.services.ClientService;
import com.kodapp.clientservice.api.services.impl.IpApiServiceImpl;
import com.kodapp.clientservice.api.services.impl.LocationWeatherServiceImpl;

import javassist.NotFoundException;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

	private static final Logger log = LoggerFactory.getLogger(ClientController.class);

	@Autowired
	private ClientService clientService;
			
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private IpApiServiceImpl ipApiServiceImpl;

	@Autowired
	private ClientLocationIpRepository clientLocationIpRepository;
	
	@Autowired
	private LocationWeatherServiceImpl locationWeatherServiceImpl;
	
	@Autowired
	private ClientWeatherRepository clientWeatherRepository;	

	ClientDto clientDto;
	
	public ClientController() {
		
	}
	
	private String getIp() {
		String ipAddress = request.getRemoteAddr();
		if(ipAddress.equals("0:0:0:0:0:0:0:1")) {
			return ipAddress="177.188.238.45";
		}
		return null;
	}
	
	/**
	 * Retorna um cliente passando um ID 
	 * @param idtClient
	 * @return ResponseEntity<Response<ClientDto>>
	 * @throws NotFoundException 
	 */
	@GetMapping(value = "/{idtClient}")
	public ResponseEntity<Response<ClientDto>> returnClientById(@PathVariable("idtClient") Long idtClient){
		log.info("Bucando cliente por ID: {}", idtClient);
		Response<ClientDto> response = new Response<ClientDto>();
		Optional<Client> client = this.clientService.findClientById(idtClient);
		
		if(!client.isPresent()) {
			log.info("Cliente não encontrado para o ID: {}", idtClient);
			response.getErrors().add("Cliente não encontrado para o id: " + idtClient);
			return ResponseEntity.badRequest().body(response);
		}
		
		clientDto = new ClientDto(client.get());
		response.setData(clientDto);
		return ResponseEntity.ok(response);
	}
	
	/**
	 * Cadastra um cliente
	 * @param clientDto
	 * @param result
	 * @return ResponseEntity<Response<ClientDto>>
	 * @throws Exception 
	 */
	@PostMapping
	public ResponseEntity<Response<ClientDto>> createClient(@Valid @RequestBody ClientDto clientDto, BindingResult result) throws Exception{
		log.info("Cadastrando cliente: {}", clientDto.toString());
		Response<ClientDto> response = new Response<ClientDto>();

		Client client = this.convertClientDtoToClient(clientDto,result);
		ClientLocationIp cliLocIp = this.convertClientDtoToClientLocationIp(result);
		ClientWeather clientWeather = this.convertClientDtoToClientWeather(cliLocIp,result);
		
		if(result.hasErrors()) {
			log.info("Erro validando cliente: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		this.clientLocationIpRepository.save(cliLocIp);
		this.clientWeatherRepository.save(clientWeather);
		client.setClientLocation(cliLocIp);
		client.setClientWeather(clientWeather);
		this.clientService.createClient(client);
		
		clientDto = new ClientDto(client);
		response.setData(clientDto);
		
		return ResponseEntity.ok(response);
	}
	
	private ClientWeather convertClientDtoToClientWeather(ClientLocationIp cliLocIp, BindingResult result) throws Exception {
		ClientDto clientDto = locationWeatherServiceImpl.getLocationByLattLong(cliLocIp.getCodLatitude(), cliLocIp.getCodLongitude());
		ClientDto clientDto2 = locationWeatherServiceImpl.getTemperatureByWoeid(clientDto.getWoeid());
		ClientWeather clientWeather = new ClientWeather(clientDto2);
		return clientWeather;
	}
	
	private ClientWeather convertClientDtoToClientWeather(ClientDto clientDto, BindingResult result,
			Client client) {
		ClientWeather clientWeather = new ClientWeather();

		if(client.getClientWeather().getIdtClientWeather() != null) {
			Optional<ClientWeather> clientWea = this.clientWeatherRepository.findById(client.getClientWeather().getIdtClientWeather());
			if(clientWea.isPresent()) {
				clientWeather = clientWea.get(); //se o id do cliente exisitir atribua para o client
			}else {
				result.addError(new ObjectError("client", "Client não encontrado"));
			}
		}
		clientWeather.setDatApplicable(clientDto.getDatApplicable());
		clientWeather.setMaxTemperature(clientDto.getMaxTemperature());
		clientWeather.setMinTemperature(clientDto.getMinTemperature());
		return clientWeather;
	}


	/**
	 * Converte um ClienteDto para uma entidade Client
	 * @param clientDto
	 * @param result
	 * @return client
	 * @throws Exception 
	 */
	private Client convertClientDtoToClient(ClientDto clientDto, BindingResult result) throws Exception {
		Client client = new Client();
				
		if(clientDto.getIdtClient().isPresent()) {
			Optional<Client> cli = this.clientService.findClientById(clientDto.getIdtClient().get());
			if(cli.isPresent()) {
				client = cli.get(); //se o id do cliente exisitir atribua para o client
			}else {
				result.addError(new ObjectError("client", "Client não encontrado"));
			}
		}

		client.setNamClient(clientDto.getNamClient());
		client.setAgeClient(clientDto.getAgeClient());
		if (clientDto.getCodIpAddress() != null) {
			client.setCodIpAddress(clientDto.getCodIpAddress());
		}
		client.setCodIpAddress(getIp());
				
		return client;
	}

	private ClientLocationIp convertClientDtoToClientLocationIp(Client client, ClientDto clientDto, BindingResult result) {
		ClientLocationIp clientLocationIp = new ClientLocationIp(clientDto);
		if(client.getClientLocation().getIdtClientLocation() != null) {
			Optional<ClientLocationIp> cliLoc = this.clientLocationIpRepository.findById(client.getClientLocation().getIdtClientLocation());
			if(cliLoc.isPresent()) {
				clientLocationIp = cliLoc.get(); //se o id do cliente exisitir atribua para o client
			}else {
				result.addError(new ObjectError("client", "Client não encontrado"));
			}
		}
		return clientLocationIp;
	}

	private ClientLocationIp convertClientDtoToClientLocationIp(BindingResult result) throws Exception {	
		ClientDto clientDto = ipApiServiceImpl.getLocalizationIp(getIp());
		ClientLocationIp clientLocationIp = new ClientLocationIp(clientDto);
		return clientLocationIp;
	}

	
	/**
	 * Atualiza os dados de um cliente informando o id do cliente
	 * @param idtClient
	 * @param clientDto
	 * @param result
	 * @return ResponseEntity<Response<ClientDto>>
	 * @throws Exception 
	 */
	@PutMapping(value = "/{idtClient}")
	public ResponseEntity<Response<ClientDto>> updateCLient(@PathVariable("idtClient") Long idtClient, @Valid @RequestBody ClientDto clientDto, BindingResult result) throws Exception{
		log.info("Atualizando um cliente: {}", clientDto.toString());
		Response<ClientDto> response = new Response<ClientDto>(); //response vai armazenar ClientDto
		clientDto.setIdtClient(Optional.of(idtClient));
		Client client = this.convertClientDtoToClient(clientDto, result);
		ClientLocationIp clientLocationIp = this.convertClientDtoToClientLocationIp(client,clientDto, result);
		ClientWeather clientWeather = this.convertClientDtoToClientWeather(clientDto, result,client);
		
		if(result.hasErrors()) {
			log.error("Erro validando cliente: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
			
		this.clientLocationIpRepository.save(clientLocationIp);
		this.clientWeatherRepository.save(clientWeather);
		client.setClientLocation(clientLocationIp);
		client.setClientWeather(clientWeather);
		this.clientService.createClient(client);

		clientDto = new ClientDto(client);
		response.setData(clientDto);
		
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping(value = "/{idtClient}")
	public ResponseEntity<Response<String>> deleteClientPorId(@PathVariable("idtClient") Long idtClient){
		log.info("Deletando cliente por ID: {}", idtClient);
		Response<String> response = new Response<String>(); //response soh vai retornar os erros por isso String
		Optional<Client> client = this.clientService.findClientById(idtClient);
		Optional<ClientLocationIp> clientLocationIp = this.clientLocationIpRepository.findById(client.get().getClientLocation().getIdtClientLocation());
		Optional<ClientWeather> clientWeather = this.clientWeatherRepository.findById(client.get().getClientWeather().getIdtClientWeather());
		
		if(!client.isPresent()) {
			log.info("Erro ao deletar cliente, devido ao cliente id: {} ser invalido", idtClient);
			response.getErrors().add("Erro ao deletar cliente. Registro nao encontrado para o id " + idtClient);
			return ResponseEntity.badRequest().body(response);
		}
		if(!clientLocationIp.isPresent()) {
			log.info("Erro ao deletar localizacao do cliente, devido a fk IdtClientLocation do cliente id: {} ser invalido", idtClient);
		}
		if(!clientWeather.isPresent()) {
			log.info("Erro ao deletar previsao do tempo do cliente, devido a fk IdtClientWeather do cliente id: {} ser invalido", idtClient);
		}		
		
		this.clientService.deleteClientById(idtClient);
		this.clientLocationIpRepository.deleteById(client.get().getClientLocation().getIdtClientLocation());
		this.clientWeatherRepository.deleteById(client.get().getClientWeather().getIdtClientWeather());
		return ResponseEntity.ok(new Response<String>());
	}
	
    @GetMapping
    public Iterable<Client> returnListAllClient(){
        return clientService.listAllClient();
    }
	
}
