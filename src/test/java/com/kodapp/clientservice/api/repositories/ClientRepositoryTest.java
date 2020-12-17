package com.kodapp.clientservice.api.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.kodapp.clientservice.api.entities.Client;


@SpringBootTest
@ActiveProfiles("test")
public class ClientRepositoryTest {

	@Autowired
	private ClientRepository clientRepository;
	
	private static final String NAMCLIENT = "Erick";
	
	
	@BeforeEach
	public void setUp() throws Exception {
	
		Client client = new Client();
		client.setNamClient("Erick");
		client.setAgeClient(32);
		this.clientRepository.save(client);
	}
	
	@AfterEach
	public final void tearDown() {
		this.clientRepository.deleteAll();
	}
	
	@Test
	public void testBuscarTodosClients() {
		List<Client> client = this.clientRepository.findAll();
		assertEquals(1, client.size());
	}
	
	@Test
	public void testBuscarporNome() {
		Client client = this.clientRepository.findByNamClient(NAMCLIENT);
		
		assertEquals(NAMCLIENT, client.getNamClient());
	}
	
	@Test
	public void testBuscarPorIdade() {
		Client client = this.clientRepository.findByAgeClient(32);
		assertEquals(32, client.getAgeClient());
	}
	
	@Test
	public void testBuscarPorId() {
		Optional<Client> client = this.clientRepository.findById(1L);

		assertFalse(client.isPresent());
		
	}
	
}
