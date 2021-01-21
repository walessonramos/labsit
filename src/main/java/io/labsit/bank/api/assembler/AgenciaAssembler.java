package io.labsit.bank.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.labsit.bank.api.model.AgenciaDTO;
import io.labsit.bank.domain.model.Agencia;

@Component
public class AgenciaAssembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public AgenciaDTO toDTO(Agencia agencia) {
		return modelMapper.map(agencia, AgenciaDTO.class);
	}
	
	public List<AgenciaDTO> toCollectionDTO(List<Agencia> listaAgencias){
		return listaAgencias.stream()
				.map(agencia -> toDTO(agencia))
				.collect(Collectors.toList());
	}

}
