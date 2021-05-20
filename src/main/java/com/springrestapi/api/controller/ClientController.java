package com.springrestapi.api.controller;

import java.util.List;
//import java.util.Optional;

import javax.validation.Valid;

//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.springrestapi.domain.model.Client;
import com.springrestapi.domain.repository.ClientRepository;
import com.springrestapi.domain.service.RegisterClientService;

import lombok.AllArgsConstructor;


@AllArgsConstructor // gerando construtor do clientRepository 
@RestController
@RequestMapping("/clients")
public class ClientController {
		
//	@PersistenceContext
//	private EntityManager manager; // variável de acesso
	
	@Autowired
	private ClientRepository clientRepository;
	private RegisterClientService registerClientService;
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteClient(@PathVariable Long id){
		if(!clientRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		
//		clientRepository.deleteById(id);
		registerClientService.deleteClient(id);
		// 204
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED) // 201
	public Client addClient(@Valid @RequestBody Client client) {
//		return clientRepository.save(client);
		return registerClientService.saveClient(client); 
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Client> updateClient(@Valid @PathVariable Long id, @RequestBody Client client){
		if(!clientRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		
		client.setId(id);
//		client = clientRepository.save(client);
		client = registerClientService.saveClient(client);
		// 200
		return ResponseEntity.ok(client);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Client> search(@PathVariable Long id) {
		return clientRepository.findById(id)
				//.map(client -> ResponseEntity.ok(client))
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
		
		//		Optional<Client> client = clientRepository.findById(id);
//		
//		if(client.isPresent()) {
//			return ResponseEntity.ok(client.get());
//		}
//		
//		return ResponseEntity.notFound().build();
	}

	@GetMapping
	public List<Client> list() {
		return clientRepository.findAll();

		
//		return manager.createQuery("from Client", Client.class)
//				.getResultList();
		
// 		Exemplo de teste
//		Client clientOne = new Client();
//		clientOne.setId(1L);
//		clientOne.setName("Harry Potter");
//		clientOne.setEmail("harrypotter93/4@hogwarts.com");
//		clientOne.setTelephone("+55 90101-3020");
//		
//		// Usando o var para variáveis do Java 11
//		var clientTwo = new Client();
//		clientTwo.setId(2L);
//		clientTwo.setName("Hermione Granger");
//		clientTwo.setEmail("hermionegranger-secret@hogwarts.com");
//		clientTwo.setTelephone("+55 92105-0521");
//		
//		return Arrays.asList(clientOne, clientTwo);
	}
	
}
