package io.labsit.bank.api.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.labsit.bank.domain.model.enums.TipoTransacaoEnum;

@JsonInclude(Include.NON_NULL)
public class TransacaoDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private LocalDateTime data;
	private BigDecimal valor;
	private TipoTransacaoEnum tipoTransacao;
	
	public LocalDateTime getData() {
		return data;
	}
	public void setData(LocalDateTime data) {
		this.data = data;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public TipoTransacaoEnum getTipoTransacao() {
		return tipoTransacao;
	}
	public void setTipoTransacao(TipoTransacaoEnum tipoTransacao) {
		this.tipoTransacao = tipoTransacao;
	}

}
