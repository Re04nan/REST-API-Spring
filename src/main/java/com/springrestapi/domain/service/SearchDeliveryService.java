package com.springrestapi.domain.service;

import org.springframework.stereotype.Service;

import com.springrestapi.domain.exception.EntityNotFoundException;
//import com.springrestapi.domain.exception.ExceptionBusiness;
import com.springrestapi.domain.model.Delivery;
import com.springrestapi.domain.repository.DeliveryRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class SearchDeliveryService {
	
	private DeliveryRepository deliveryRepository;
	
	public Delivery search(Long idDelivery) {
		return deliveryRepository.findById(idDelivery)
				.orElseThrow(() -> new EntityNotFoundException("Entrega não encontrada!"));
	}
}
