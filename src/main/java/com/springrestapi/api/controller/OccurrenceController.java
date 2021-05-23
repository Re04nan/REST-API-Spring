package com.springrestapi.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.springrestapi.api.assembler.OccurrenceAssembler;
import com.springrestapi.api.model.OccurrenceModel;
import com.springrestapi.api.model.request.OccurrenceRequest;
import com.springrestapi.domain.model.Delivery;
import com.springrestapi.domain.model.Occurrence;
import com.springrestapi.domain.service.RegisterOccurrenceService;
import com.springrestapi.domain.service.SearchDeliveryService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/deliverys/{idDelivery}/occurrences")
public class OccurrenceController {

	private SearchDeliveryService searchDeliveryService;
	private RegisterOccurrenceService registerOccurrenceService;
	private OccurrenceAssembler occurrenceAssembler;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OccurrenceModel registerOccurrence(@PathVariable Long idDelivery, 
			@Valid @RequestBody OccurrenceRequest occurrenceRequest) {
	
		Occurrence occurrenceRegistered = registerOccurrenceService
				.register(idDelivery, occurrenceRequest.getDescription());
		
		return occurrenceAssembler.toModel(occurrenceRegistered);	
	}
	
	@GetMapping
	public List<OccurrenceModel> listOccurrence(@PathVariable Long idDelivery) {
		Delivery delivery = searchDeliveryService.search(idDelivery);
		
		return occurrenceAssembler.toCollectionModel(delivery.getOccurrences());
	}
}
