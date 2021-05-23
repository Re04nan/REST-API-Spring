package com.springrestapi.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springrestapi.domain.model.Delivery;
import com.springrestapi.domain.repository.DeliveryRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CompletionDeliveryService {
	
	private DeliveryRepository deliveryRepository;
	private SearchDeliveryService searchDeliveryService;
	
	@Transactional
	public void finish(Long idDelivery) {
		Delivery delivery = searchDeliveryService.search(idDelivery);
		
		delivery.completion();
		
		deliveryRepository.save(delivery);
	}

}
