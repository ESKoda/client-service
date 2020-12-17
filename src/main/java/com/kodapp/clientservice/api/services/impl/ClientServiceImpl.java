package com.kodapp.clientservice.api.services.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodapp.clientservice.api.entities.Client;
import com.kodapp.clientservice.api.repositories.ClientRepository;
import com.kodapp.clientservice.api.services.ClientService;

@Service
public class ClientServiceImpl implements ClientService{

	private static final Logger log = LoggerFactory.getLogger(ClientServiceImpl.class);
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Override
	public Client createClient(Client client) {
		log.info("Cadastrando um cliente: {}", client);
		return this.clientRepository.save(client);
	}

	@Override
	public Optional<Client> findClientById(Long idtClient) {
		log.info("Buscando cliente por id: {}", idtClient);
		return this.clientRepository.findById(idtClient);
	}
	
	@Override
	public List<Client> listAllClient() {
		log.info("Buscando todos os clientes: {}");
		return this.clientRepository.findAll();
	}

	@Override
	public void deleteClientById(Long idtClient) {
		log.info("Removendo o cliente por ID: {}", idtClient);
		this.clientRepository.deleteById(idtClient);
	}

}
