package com.springrestapi.api.model.request;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ClientIdRequest {
	
	@NotNull
	private Long id;

}
