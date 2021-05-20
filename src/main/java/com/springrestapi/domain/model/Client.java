package com.springrestapi.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(onlyExplicitlyIncluded = true) // usando o lombok para referenciar a criação da nossa hascode e equals, apenas do Id
@Getter
@Setter
@Entity
public class Client {
	
	@EqualsAndHashCode.Include // add o id
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // identifica o padrão da chave usado no Banco
	private Long id;
	
	@NotBlank // Jakarta validation
	@Size(min = 4, max = 60) // limitando valor em caracteres
	@Column(name = "name") //só precisa passar o name se o nome da coluna for diferente do nome da entidade/var
	private String name;
	
	@Column
	@NotBlank
	@Email
	@Size(max = 255)
	private String email;
	
	@Column
	@NotBlank
	@Size(min = 11, max = 15)
	private String telephone;
	
}
