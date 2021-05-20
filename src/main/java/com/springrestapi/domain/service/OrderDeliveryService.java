package com.springrestapi.domain.service;

//import java.time.LocalDateTime;
import java.time.OffsetDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//import com.springrestapi.domain.exception.ExceptionBusiness;
import com.springrestapi.domain.model.Client;
import com.springrestapi.domain.model.Delivery;
import com.springrestapi.domain.model.StatusDelivery;
//import com.springrestapi.domain.repository.ClientRepository;
import com.springrestapi.domain.repository.DeliveryRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class OrderDeliveryService {
	
//	private ClientRepository clientRepository;
	private RegisterClientService registerClientService;
	private DeliveryRepository deliveryRepository;
	
	@Transactional
	public Delivery order(Delivery delivery) {
//		Client client = clientRepository.findById(delivery.getClient().getId())
//				.orElseThrow(() -> new ExceptionBusiness("Cliente não encontrado!"));
		
		Client client = registerClientService.search(delivery.getClient().getId());
		
		delivery.setClient(client);
		delivery.setStatus(StatusDelivery.PENDING);
		delivery.setDateOrder(OffsetDateTime.now());
		
		return deliveryRepository.save(delivery);
	}

}
