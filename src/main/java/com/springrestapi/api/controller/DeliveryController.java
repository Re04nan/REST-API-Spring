package com.springrestapi.api.controller;

import java.util.List;

import javax.validation.Valid;

//import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.springrestapi.api.assembler.DeliveryAssembler;
import com.springrestapi.api.model.DeliveryModel;
import com.springrestapi.api.model.request.DeliveryRequest;
import com.springrestapi.domain.model.Delivery;
import com.springrestapi.domain.repository.DeliveryRepository;
import com.springrestapi.domain.service.CompletionDeliveryService;
import com.springrestapi.domain.service.OrderDeliveryService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/deliverys")
public class DeliveryController {

	private DeliveryRepository deliveryRepository;
	private OrderDeliveryService orderDeliveryService;
//	private ModelMapper modelMapper;
	private DeliveryAssembler deliveryAssembler;
	private CompletionDeliveryService completionDeliveryService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED) // 201
	public DeliveryModel request(@Valid @RequestBody DeliveryRequest deliveryRequest) {
		Delivery newDelivery = deliveryAssembler.toEntity(deliveryRequest);
		
		return deliveryAssembler.toModel(orderDeliveryService.order(newDelivery));
	}
	
	@PutMapping("/{idDelivery}/completion")
	@ResponseStatus(HttpStatus.NO_CONTENT) // 204
	public void completion(@PathVariable Long idDelivery) {
		completionDeliveryService.finish(idDelivery);
	}
	
	@GetMapping
	public List<DeliveryModel> list(){
		return deliveryAssembler.toCollectionModel(deliveryRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DeliveryModel> search(@PathVariable Long id){ // passando a responsabilidade para model
//	public ResponseEntity<Delivery> search(@PathVariable Long id){ 
		return deliveryRepository.findById(id) // se tiver vazio 
				.map(delivery -> ResponseEntity.ok(deliveryAssembler.toModel(delivery)))
				.orElse(ResponseEntity.notFound().build());
				
//				.map(delivery -> {
//					DeliveryModel deliveryModel = deliveryAssembler.toModel(delivery);
//					DeliveryModel deliveryModel = modelMapper.map(delivery, DeliveryModel.class); // usando o Model Mapper para conversão de destinos
					
//					DeliveryModel deliveryModel = new DeliveryModel();
//					deliveryModel.setId(delivery.getId());
//					deliveryModel.setNameClient(delivery.getClient().getName());
//					deliveryModel.setAddressee(new AddresseeModel());
//					deliveryModel.getAddressee().setName(delivery.getAddressee().getName());
//					deliveryModel.getAddressee().setPlace(delivery.getAddressee().getPlace());
//					deliveryModel.getAddressee().setNumber(delivery.getAddressee().getNumber());
//					deliveryModel.getAddressee().setComplement(delivery.getAddressee().getComplement());
//					deliveryModel.getAddressee().setDistrict(delivery.getAddressee().getDistrict());
//					deliveryModel.setRate(delivery.getRate());
//					deliveryModel.setStatus(delivery.getStatus());
//					deliveryModel.setDateOrder(delivery.getDateOrder());
//					deliveryModel.setDateCompleted(delivery.getDateCompleted());
					
//					return ResponseEntity.ok(deliveryModel);
//				})
//				.map(ResponseEntity::ok)
//				.orElse(ResponseEntity.notFound().build()); // retorna 404
	}
}
