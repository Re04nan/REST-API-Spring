package com.springrestapi.api.model.request;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddresseeRequest {
	
	@NotBlank
	private String name;
	
	@NotBlank
	private String place;
	
	@NotBlank
	private String number;
	
	@NotBlank
	private String complement;
	
	@NotBlank
	private String district;

	
}
