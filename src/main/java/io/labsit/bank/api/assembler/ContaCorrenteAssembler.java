package io.labsit.bank.api.assembler;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.labsit.bank.api.model.ContaCorrenteDTO;
import io.labsit.bank.api.model.ContaCorrenteExtratoDTO;
import io.labsit.bank.domain.model.ContaCorrente;

@Component
public class ContaCorrenteAssembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public ContaCorrenteDTO toDTO(ContaCorrente conta) {
		return modelMapper.map(conta, ContaCorrenteDTO.class);
	}
	
	public ContaCorrenteExtratoDTO toExtratoDTO(ContaCorrente conta) {
		return modelMapper.map(conta, ContaCorrenteExtratoDTO.class);
	}
	
	public List<ContaCorrenteDTO> toDTOs(List<ContaCorrente> listaContas) {
		
		List<ContaCorrenteDTO> listaDTOs = new ArrayList<ContaCorrenteDTO>();
		
		for(ContaCorrente conta : listaContas) {
			listaDTOs.add(toDTO(conta));
		}
		return listaDTOs;
	}
	
	public List<ContaCorrenteDTO> toCollectionDTO(List<ContaCorrente> listaConta){
		return listaConta.stream()
				.map(conta -> toDTO(conta))
				.collect(Collectors.toList());
	}

}
