package io.labsit.bank.domain.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import io.labsit.bank.domain.model.enums.TipoTransacaoEnum;
import io.labsit.bank.domain.model.enums.converter.TipoTransacaoEnumConverter;

@Entity
@Table(name = "T_TRANSACAO")
public class Transacao implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "T_TRANSACAO_ID")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "T_CONTA_CORRENTE_ID")
	private ContaCorrente conta;
	
	@Convert(converter = TipoTransacaoEnumConverter.class)
	@Column(name = "T_TRANSACAO_TIPO")
	private TipoTransacaoEnum tipoTransacao;
	
	@Column(name = "T_TRANSACAO_DATA")
	private LocalDateTime data;
	
	@Column(name = "T_TRANSACAO_VALOR")
	private BigDecimal valor;
	
	@PrePersist
	private void prePersist() {
		if(this.data == null ) {
			this.data = LocalDateTime.now();
		}
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public ContaCorrente getConta() {
		return conta;
	}
	public void setConta(ContaCorrente conta) {
		this.conta = conta;
	}
	public TipoTransacaoEnum getTipoTransacao() {
		return tipoTransacao;
	}
	public void setTipoTransacao(TipoTransacaoEnum tipoTransacao) {
		this.tipoTransacao = tipoTransacao;
	}
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transacao other = (Transacao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
