package com.springrestapi.api.model.request;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeliveryRequest {
	
	@Valid
	@NotNull
	private ClientIdRequest client;

	@Valid
	@NotNull
	private AddresseeRequest addressee;
	
	@Valid
	@NotNull
	private BigDecimal rate;
	
}
