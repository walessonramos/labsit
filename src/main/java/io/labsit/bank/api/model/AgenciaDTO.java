package io.labsit.bank.api.model;

import java.io.Serializable;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class AgenciaDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Long id;
	
	@NotNull(message = "Informe o número da agência")
	@Digits(integer = 4, fraction = 0, message = "O número da agência deve ter até 4 dígitos")
	private Integer numeroAgencia;

	public Integer getNumeroAgencia() {
		return numeroAgencia;
	}

	public void setNumeroAgencia(Integer numeroAgencia) {
		this.numeroAgencia = numeroAgencia;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
