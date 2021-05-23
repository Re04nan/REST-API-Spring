package com.springrestapi.domain.model;

import java.math.BigDecimal;
//import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
//import javax.validation.Valid;
//import javax.validation.constraints.NotNull;
//import javax.validation.groups.ConvertGroup;
//import javax.validation.groups.Default;
import javax.persistence.OneToMany;

//import com.fasterxml.jackson.annotation.JsonProperty;
//import com.fasterxml.jackson.annotation.JsonProperty.Access;
//import com.springrestapi.domain.ValidationGroups;
import com.springrestapi.domain.exception.ExceptionBusiness;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Delivery {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
//	@Valid // valida se o camp id cliente foi informado
//	@ConvertGroup(from = Default.class, to = ValidationGroups.clientId.class) // converte o grupo de validação padrão para o informado
//	@JoinColumn(name = id) // para relacionar com outra tabela 
//	@NotNull
	@ManyToOne // Muitos para um
	private Client client;
	
//	@Valid
//	@NotNull
	@Embedded // abstrair em uma nova class
	private Addressee addressee;
	
//	@NotNull
	private BigDecimal rate;
	
	@OneToMany(mappedBy = "delivery", cascade = CascadeType.ALL) //mappedBy - nome da propriedade da ocorrencia inversa
	private List<Occurrence> occurrences = new ArrayList<>();
	
	@Enumerated(EnumType.STRING) // armazenar o texto
//	@JsonProperty(access = Access.READ_ONLY) // apenas leitura
	private StatusDelivery status;
	
//	@JsonProperty(access = Access.READ_ONLY) // apenas leitura
	private OffsetDateTime dateOrder;
//	private LocalDateTime dateOrder;
	
//	@JsonProperty(access = Access.READ_ONLY) // apenas leitura
	private OffsetDateTime dateCompleted;
//	private LocalDateTime dateCompleted;

	public Occurrence addOccurrence(String description) {
		Occurrence occurrence = new Occurrence();
		occurrence.setDescription(description);
		occurrence.setDateRegister(OffsetDateTime.now());
		occurrence.setDelivery(this);
		
		this.getOccurrences().add(occurrence);
		
		return occurrence;
	}

	public void completion() {
		if (cannotBeCompleted()) {
			throw new ExceptionBusiness("Entrega não pode ser finalizada");
		}
		
		setStatus(StatusDelivery.COMPLETED);
		setDateCompleted(OffsetDateTime.now());
	}
	
	public boolean canBeFinalized() {
		return StatusDelivery.PENDING.equals(getStatus());
	}
	
	public boolean cannotBeCompleted() {
		return !canBeFinalized();
	}
	

}
