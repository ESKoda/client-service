package com.kodapp.clientservice.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.kodapp.clientservice.api.entities.ClientWeather;

@Transactional(readOnly = true)
public interface ClientWeatherRepository extends JpaRepository<ClientWeather, Long> {
	
}
