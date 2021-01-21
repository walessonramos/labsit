package io.labsit.bank.domain.model.enums;

public enum TipoTransacaoEnum {
	
	DEPOSITO("Crédito", 1),
	SAQUE("Débito", 2);
	
	private Integer codigo;
	private String descricao;
	
	private TipoTransacaoEnum(String descricao, Integer codigo) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

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
	
	public static TipoTransacaoEnum valueOf(Integer codigo) {
		
		for(TipoTransacaoEnum item : TipoTransacaoEnum.values()) {
			if(item.getCodigo().equals(codigo)) {
				return item;
			}
		}
		return null;
	}

}
