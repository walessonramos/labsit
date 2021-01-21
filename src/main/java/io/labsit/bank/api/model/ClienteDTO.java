package io.labsit.bank.api.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.labsit.bank.domain.model.enums.TipoPessoaEnum;

@JsonInclude(Include.NON_NULL)
public class ClienteDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@NotNull(message = "Informe o nome")
	private String nome;
	
	@NotNull(message = "Informe o tipo de pessoa (1 - Pesssoa Física | 2 - Pessoa Jurídica)")
	private TipoPessoaEnum tipoPessoa;
	
	@NotNull(message = "Informe o CPF/CNPJ")
	private String cpfCnpj;
	
	private Integer numeroAgencia;
	
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

	public TipoPessoaEnum getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(TipoPessoaEnum tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public Integer getNumeroAgencia() {
		return numeroAgencia;
	}

	public void setNumeroAgencia(Integer numeroAgencia) {
		this.numeroAgencia = numeroAgencia;
	}

}
