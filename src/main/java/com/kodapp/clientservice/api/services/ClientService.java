package com.kodapp.clientservice.api.services;

import java.util.List;
import java.util.Optional;

import com.kodapp.clientservice.api.entities.Client;

public interface ClientService {

	/**
	 * Cadastrar um cliente
	 * @param client
	 * @return Client
	 */
	public Client createClient(Client client);
		
	/**
	 * Retornar um cliente por id
	 * @param idtClient
	 * @return Optional<Client>
	 */
	public Optional<Client> findClientById(Long idtClient);
	
	/**
	 * Retornar uma lista de clientes
	 * @return List<Client>
	 */
	public List<Client> listAllClient(); //depois criar Page
	
	/**
	 * Remover um cliente informando um id do cliente
	 * @param idtClient
	 */
	public void deleteClientById(Long idtClient);
}
