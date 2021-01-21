package io.labsit.bank.domain.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name = "T_CONTA_CORRENTE")
public class ContaCorrente implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "T_CONTA_CORRENTE_ID")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "T_AGENCIA_ID")
	private Agencia agencia;
	
	@OneToOne
	@JoinColumn(name = "T_PESSOA_ID")
	private Pessoa pessoa;
	
	@Column(name = "T_CONTA_CORRENTE_NR")
	private String numeroConta;
	
	@Column(name = "T_CONTA_CORRENTE_SALDO")
	private BigDecimal saldo;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "conta")
	private List<Transacao> listaTransacoes;
	
	@PrePersist
	private void prePersist() {
		this.numeroConta = this.getPessoa().getNumeroDocumento();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Agencia getAgencia() {
		return agencia;
	}
	public void setAgencia(Agencia agencia) {
		this.agencia = agencia;
	}
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	public String getNumeroConta() {
		return numeroConta;
	}
	public void setNumeroConta(String numeroConta) {
		this.numeroConta = numeroConta;
	}
	public BigDecimal getSaldo() {
		return saldo;
	}
	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}
	
	public List<Transacao> getListaTransacoes() {
		return listaTransacoes;
	}

	public void setListaTransacoes(List<Transacao> listaTransacoes) {
		this.listaTransacoes = listaTransacoes;
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
		ContaCorrente other = (ContaCorrente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
