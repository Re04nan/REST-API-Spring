package com.springrestapi.api.model.request;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OccurrenceRequest {

	@NotBlank
	private String description;

}
