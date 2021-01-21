package io.labsit.bank.api.assembler;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.labsit.bank.api.model.AgenciaDTO;
import io.labsit.bank.domain.model.Agencia;

@Component
public class AgenciaDisassembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Agencia toDomainModel(AgenciaDTO agenciaDTO) {
		return modelMapper.map(agenciaDTO, Agencia.class);
	}
	
	public List<Agencia> toDomainModel(List<AgenciaDTO> listaAgenciasDTO) {
		
		List<Agencia> listaAgencias = new ArrayList<Agencia>();
		
		for(AgenciaDTO input : listaAgenciasDTO) {
			listaAgencias.add(toDomainModel(input));
		}
		return listaAgencias;
	}

}
