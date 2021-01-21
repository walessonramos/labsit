package io.labsit.bank.api.assembler;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.labsit.bank.api.model.ClienteDTO;
import io.labsit.bank.domain.model.Pessoa;
import io.labsit.bank.domain.model.PessoaFisica;
import io.labsit.bank.domain.model.PessoaJuridica;
import io.labsit.bank.domain.model.enums.TipoPessoaEnum;
import io.labsit.bank.domain.util.Util;

@Component
public class ClienteDisassembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Pessoa toDomainModel(ClienteDTO clienteDTO) {
		clienteDTO.setCpfCnpj(Util.removeCaracteresEspeciais(clienteDTO.getCpfCnpj()));
		if(clienteDTO.getTipoPessoa().equals(TipoPessoaEnum.PESSOA_FISICA)) {
			
			return toDomainModelPf(clienteDTO);
		}
		
		return toDomainModelPj(clienteDTO);
	}
	
	public PessoaFisica toDomainModelPf(ClienteDTO clienteDTO) {
		return modelMapper.map(clienteDTO, PessoaFisica.class);
	}
	
	public PessoaJuridica toDomainModelPj(ClienteDTO clienteDTO) {
		return modelMapper.map(clienteDTO, PessoaJuridica.class);
	}
	
	public List<Pessoa> toDomainModel(List<ClienteDTO> listaPessoaDTO) {
		
		List<Pessoa> listaPessoa = new ArrayList<Pessoa>();
		
		for(ClienteDTO input : listaPessoaDTO) {
			listaPessoa.add(toDomainModel(input));
		}
		return listaPessoa;
	}

}
