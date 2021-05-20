package com.springrestapi.exceptionhandler;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(Include.NON_NULL) // Não inclui campos null
@Getter
@Setter
public class Problem {
	
	private Integer status;
	private LocalDateTime dataHour;
	private String title;
	private List<Field> fields;
	
	@AllArgsConstructor
	@Getter
	public static class Field{
		private String name;
		private String mensage;
	}

}
