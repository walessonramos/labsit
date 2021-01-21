package io.labsit.bank.api.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class AgenciaDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Long id;
	
	@NotNull(message = "Informe o número da agência")
//	@Size(message = "O número da agência deve ter 4 dígitos", max = 4, min = 4)
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
