package io.labsit.bank.integration.factory;

import io.labsit.bank.api.model.ClienteDTO;
import io.labsit.bank.domain.model.enums.TipoPessoaEnum;

public class ClienteDTOFactory {
	
	public static ClienteDTO criarClientePessoaFisicaDTOComCPFValido() {
		ClienteDTO dto = new ClienteDTO();
		dto.setNome("Cliente Test");
		dto.setCpfCnpj("295.707.670-57");
		dto.setTipoPessoa(TipoPessoaEnum.PESSOA_FISICA);
		return dto;
	}
	
	public static ClienteDTO criarClientePessoaFisicaDTOComCPFInvalido() {
		ClienteDTO dto = new ClienteDTO();
		dto.setNome("Cliente Test");
		dto.setCpfCnpj("295.707.670-00");
		dto.setTipoPessoa(TipoPessoaEnum.PESSOA_FISICA);
		return dto;
	}

}
