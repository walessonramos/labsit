package io.labsit.bank.api.model;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class TransacaoInputDTO {
	
	@NotNull(message = "Informe o valor da transação!")
	@Positive(message = "O valor da transação deve ser maior que ZERO")
	private BigDecimal valor;
	
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

}
