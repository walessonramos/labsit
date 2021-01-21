package io.labsit.bank.domain.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "T_PESSOA_FISICA")
@DiscriminatorValue("1")
@PrimaryKeyJoinColumn(name = "T_PESSOA_ID")
public class PessoaFisica extends Pessoa{
	
	private static final long serialVersionUID = 1L;
	
	@Column(name = "T_PESSOA_FISICA_CPF")
	private String CPF;
	
	@PrePersist
	@PreUpdate
	public void prePersist() {
		super.setNumeroDocumento(this.CPF);
		System.out.println();
	}

	public String getCPF() {
		return CPF;
	}

	public void setCPF(String cPF) {
		CPF = cPF;
	}
	
}
