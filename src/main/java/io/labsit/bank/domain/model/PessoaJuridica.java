package io.labsit.bank.domain.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "T_PESSOA_JURIDICA")
@DiscriminatorValue("2")
@PrimaryKeyJoinColumn(name = "T_PESSOA_ID")
public class PessoaJuridica extends Pessoa{
	
	private static final long serialVersionUID = 1L;
	
	@Column(name = "T_PESSOA_JURIDICA_CNPJ")
	private String CNPJ;
	
	@PrePersist
	@PreUpdate
	public void prePersist() {
		super.setNumeroDocumento(this.CNPJ);
	}

	public String getCNPJ() {
		return CNPJ;
	}

	public void setCNPJ(String cNPJ) {
		CNPJ = cNPJ;
	}

}
