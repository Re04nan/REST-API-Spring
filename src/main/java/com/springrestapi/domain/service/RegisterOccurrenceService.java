package com.springrestapi.domain.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//import com.springrestapi.domain.exception.ExceptionBusiness;
import com.springrestapi.domain.model.Delivery;
import com.springrestapi.domain.model.Occurrence;
//import com.springrestapi.domain.repository.DeliveryRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class RegisterOccurrenceService {
	
	private SearchDeliveryService searchDeliveryService;

	@Transactional
	public Occurrence register(Long idDelivery, String description) {
		Delivery delivery = searchDeliveryService.search(idDelivery);
				
		return delivery.addOccurrence(description);
	}
}
