package io.labsit.bank.domain.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "T_PESSOA")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="T_PESSOA_TIPO", discriminatorType=DiscriminatorType.INTEGER)
public abstract class Pessoa implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "T_PESSOA_ID")
	private Long id;
	
	@Column(name = "T_PESSOA_NOME")
	private String nome;
	
	@Column(name = "T_PESSOA_DOCUMENTO")
	private String numeroDocumento;
	
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "pessoa")
	@JoinColumn(name = "T_CONTA_CORRENTE_ID")
	private ContaCorrente conta;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getNumeroDocumento() {
		return numeroDocumento;
	}
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}
	public ContaCorrente getConta() {
		return conta;
	}
	public void setConta(ContaCorrente conta) {
		this.conta = conta;
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
		Pessoa other = (Pessoa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
