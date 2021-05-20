package com.springrestapi.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springrestapi.domain.model.Client;

// JpaRepository é uma class genérica com dois parâmetros a class da entidade e o tipo do id da class

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
	// filtrar por nome identico
	List<Client> findByName(String name);
	// filtrar por conteúdo
	List<Client> findByNameContaining(String name);
	Optional<Client> findByEmail(String email);
	
}
