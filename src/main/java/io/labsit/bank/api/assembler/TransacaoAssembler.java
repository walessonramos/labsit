package io.labsit.bank.api.assembler;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.labsit.bank.api.model.TransacaoDTO;
import io.labsit.bank.api.model.TransacaoInputDTO;
import io.labsit.bank.domain.model.Transacao;

@Component
public class TransacaoAssembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public TransacaoInputDTO toInputDTO(Transacao transacao) {
		return modelMapper.map(transacao, TransacaoInputDTO.class);
	}
	
	public TransacaoDTO toDTO(Transacao transacao) {
		return modelMapper.map(transacao, TransacaoDTO.class);
	}
	
	public List<TransacaoDTO> toDTOs(List<Transacao> listaTransacoes) {
		
		List<TransacaoDTO> listaDTOs = new ArrayList<TransacaoDTO>();
		
		for(Transacao transacao : listaTransacoes) {
			listaDTOs.add(toDTO(transacao));
		}
		return listaDTOs;
	}
	
	public List<TransacaoDTO> toCollectionDTO(List<Transacao> listaPessoas){
		return listaPessoas.stream()
				.map(pessoa -> toDTO(pessoa))
				.collect(Collectors.toList());
	}

}
