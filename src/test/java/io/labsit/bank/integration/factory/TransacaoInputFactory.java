package io.labsit.bank.integration.factory;

import java.math.BigDecimal;

import io.labsit.bank.api.model.TransacaoInputDTO;

public class TransacaoInputFactory {
	
	public static TransacaoInputDTO criarTransacaoDTOComValorPositivo() {
		
		TransacaoInputDTO dto = new TransacaoInputDTO();
		dto.setValor(new BigDecimal("100") );
		
		return dto;
	}
	
	public static TransacaoInputDTO criarTransacaoDTOComValorZerado() {
		
		TransacaoInputDTO dto = new TransacaoInputDTO();
		dto.setValor(BigDecimal.ZERO);
		
		return dto;
	}

}
