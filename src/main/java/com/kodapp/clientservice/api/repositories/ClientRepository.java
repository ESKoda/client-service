package com.kodapp.clientservice.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.kodapp.clientservice.api.entities.Client;

@Transactional(readOnly = true)
public interface ClientRepository extends JpaRepository<Client, Long>{

	Client findByNamClient (String namClient);
	Client findByAgeClient (int ageClient);
}
