package io.labsit.bank.api.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ContaCorrenteExtratoDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer numeroAgencia;
	private String numeroConta;
	private String cliente;
	private BigDecimal saldo;
	
	private List<TransacaoDTO> transacoes;
	
	public Integer getNumeroAgencia() {
		return numeroAgencia;
	}
	public void setNumeroAgencia(Integer numeroAgencia) {
		this.numeroAgencia = numeroAgencia;
	}
	public String getNumeroConta() {
		return numeroConta;
	}
	public void setNumeroConta(String numeroConta) {
		this.numeroConta = numeroConta;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public BigDecimal getSaldo() {
		return saldo;
	}
	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}
	public List<TransacaoDTO> getTransacoes() {
		return transacoes;
	}
	public void setTransacoes(List<TransacaoDTO> transacoes) {
		this.transacoes = transacoes;
	}

}
