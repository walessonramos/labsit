package io.labsit.bank.domain.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TipoPessoaEnum {
	
	PESSOA_FISICA("Pessoa Física", 1),
	PESSOA_JURIDICA("Pessoa Jurídica", 2);
	
	private Integer codigo;
	private String descricao;
	
	private TipoPessoaEnum(String descricao, Integer codigo) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	@JsonValue
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	@JsonCreator
	public static TipoPessoaEnum valueOf(Integer codigo) {
		for(TipoPessoaEnum item : TipoPessoaEnum.values()) {
			if(item.getCodigo().equals(codigo)) {
				return item;
			}
		}
		return null;
	}

}
