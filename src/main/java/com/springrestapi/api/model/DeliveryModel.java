package com.springrestapi.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.springrestapi.domain.model.StatusDelivery;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeliveryModel {
	
	private Long id;
	private ClientAbstractModel client;
	private AddresseeModel addressee;
	private BigDecimal rate;
	private StatusDelivery status;
	private OffsetDateTime dateOrder;
	private OffsetDateTime dateCompleted;

}
