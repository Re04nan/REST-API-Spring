package com.springrestapi.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springrestapi.domain.exception.ExceptionBusiness;
import com.springrestapi.domain.model.Client;
import com.springrestapi.domain.repository.ClientRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class RegisterClientService {
	private ClientRepository clientRepository;
	
	public Client search(Long id) {
		return clientRepository.findById(id)
				.orElseThrow(() -> new ExceptionBusiness("Cliente não encontrado!"));
	}
	
	@Transactional
	public Client saveClient(Client client) {
		boolean usedEmail = clientRepository.findByEmail(client.getEmail())
				.stream()
				.anyMatch(usedClient -> !usedClient.equals(client));
		
		if(usedEmail) {
			throw new ExceptionBusiness("E-mail já em uso!");
		}
		
		return clientRepository.save(client);
	}
	
	@Transactional
	public void deleteClient(Long id) {
		clientRepository.deleteById(id);
	}

}
