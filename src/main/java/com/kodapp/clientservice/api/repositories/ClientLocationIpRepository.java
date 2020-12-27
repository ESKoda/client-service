package com.kodapp.clientservice.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.kodapp.clientservice.api.entities.ClientLocationIp;

@Transactional(readOnly = true)
public interface ClientLocationIpRepository extends JpaRepository<ClientLocationIp, Long>{

}
