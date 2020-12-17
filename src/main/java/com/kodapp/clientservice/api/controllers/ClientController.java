package com.kodapp.clientservice.api.controllers;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
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
import com.kodapp.clientservice.api.response.Response;
import com.kodapp.clientservice.api.services.ClientService;

import javassist.NotFoundException;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

	private static final Logger log = LoggerFactory.getLogger(ClientController.class);

	@Autowired
	private ClientService clientService;
		
	@Autowired
	private HttpServletRequest request;
	
	public ClientController() {
		
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
		
		response.setData(this.convertClientToClientDto(client.get()));
		return ResponseEntity.ok(response);
	}
	


	/**
	 * Converte a entidade Client para ClientDto
	 * @param idtClient
	 * @return ClientDto
	 */	
	private ClientDto convertClientToClientDto(Client client) {
		ClientDto clientDto = new ClientDto();
		clientDto.setIdtClient(Optional.of(client.getIdtClient()));
		clientDto.setNamClient(client.getNamClient());
		clientDto.setAgeClient(client.getAgeClient());
		return clientDto;
	}
	
	/**
	 * Cadastra um cliente
	 * @param clientDto
	 * @param result
	 * @return ResponseEntity<Response<ClientDto>>
	 * @throws ParseException
	 */
	@PostMapping
	public ResponseEntity<Response<ClientDto>> createClient(@Valid @RequestBody ClientDto clientDto, BindingResult result) throws ParseException{
		log.info("Cadastrando cliente: {}", clientDto.toString());
		Response<ClientDto> response = new Response<ClientDto>();
		Client client = this.convertClientDtoToClient(clientDto,result);
		
		if(result.hasErrors()) {
			log.info("Erro validando cliente: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		client= this.clientService.createClient(client);
		response.setData(this.convertClientToClientDto(client));
		return ResponseEntity.ok(response);
	}

	/**
	 * Converte um ClienteDto para uma entidade Client
	 * @param clientDto
	 * @param result
	 * @return client
	 * @throws ParseException
	 */
	private Client convertClientDtoToClient(ClientDto clientDto, BindingResult result) throws ParseException {
		Client client = new Client();
		String ipAddress = request.getRemoteAddr();
		
		if(ipAddress.equals("0:0:0:0:0:0:0:1")) {
			ipAddress="177.188.238.45";
		}
		
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
		client.setCodIpAddress(ipAddress);
		return client;
	}

	
	/**
	 * Atualiza os dados de um cliente informando o id do cliente
	 * @param idtClient
	 * @param clientDto
	 * @param result
	 * @return ResponseEntity<Response<ClientDto>>
	 * @throws ParseException
	 */
	@PutMapping(value = "/{idtClient}")
	public ResponseEntity<Response<ClientDto>> updateCLient(@PathVariable("idtClient") Long idtClient, @Valid @RequestBody ClientDto clientDto, BindingResult result) throws ParseException{
		log.info("Atualizando um cliente: {}", clientDto.toString());
		Response<ClientDto> response = new Response<ClientDto>(); //response vai armazenar ClientDto
		clientDto.setIdtClient(Optional.of(idtClient));
		Client client = this.convertClientDtoToClient(clientDto, result);
		
		if(result.hasErrors()) {
			log.error("Erro validando cliente: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		client = this.clientService.createClient(client);
		response.setData(this.convertClientToClientDto(client));
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping(value = "/{idtClient}")
	public ResponseEntity<Response<String>> deleteClientPorId(@PathVariable("idtClient") Long idtClient){
		log.info("Deletando cliente por ID: {}", idtClient);
		Response<String> response = new Response<String>(); //response soh vai retornar os erros por isso String
		Optional<Client> client = this.clientService.findClientById(idtClient);
		
		if(!client.isPresent()) {
			log.info("Erro ao deletar cliente, devido ao cliente id: {} ser invalido", idtClient);
			response.getErrors().add("Erro ao deletar cliente. Registro nao encontrado para o id " + idtClient);
			return ResponseEntity.badRequest().body(response);
		}
		
		this.clientService.deleteClientById(idtClient);
		return ResponseEntity.ok(new Response<String>());
	}
	
    @GetMapping(value = "/all")
    public Iterable<Client> returnListAllClient(){
        return clientService.listAllClient();
    }
	
}
