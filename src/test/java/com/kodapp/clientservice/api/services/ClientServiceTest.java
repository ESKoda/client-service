package com.kodapp.clientservice.api.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.kodapp.clientservice.api.entities.Client;
import com.kodapp.clientservice.api.repositories.ClientRepository;

@SpringBootTest
@ActiveProfiles("test")
public class ClientServiceTest {

	@MockBean
	private ClientRepository clientRepository;
	
	@Autowired
	private ClientService clientService;
	
	@BeforeEach
	public void setUp() throws Exception{
		BDDMockito.given(this.clientRepository.findById(Mockito.anyLong())).willReturn(Optional.of(new Client()));
		BDDMockito.given(this.clientRepository.save(Mockito.any(Client.class))).willReturn(new Client());
	}
	
	@Test
	public void testBuscarClientPorId() {
		Optional<Client> client = this.clientService.findClientById(1L);
		assertTrue(client.isPresent());
	}
	
	@Test
	public void testCriarClient() {
		Client client = this.clientService.createClient(new Client());
		assertNotNull(client);
	}
}
