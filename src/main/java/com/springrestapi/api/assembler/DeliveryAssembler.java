package com.springrestapi.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.springrestapi.api.model.DeliveryModel;
import com.springrestapi.api.model.request.DeliveryRequest;
import com.springrestapi.domain.model.Delivery;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class DeliveryAssembler {
	
	private ModelMapper modelMapper;
	
	public DeliveryModel toModel(Delivery delivery) {
			return modelMapper.map(delivery, DeliveryModel.class);
	}
	
	public List<DeliveryModel> toCollectionModel(List<Delivery> delivery){
		return delivery.stream()
				.map(this::toModel)
				.collect(Collectors.toList());
	}

	public Delivery toEntity(DeliveryRequest deliveryRequest) {
		return modelMapper.map(deliveryRequest, Delivery.class);
	}
}
