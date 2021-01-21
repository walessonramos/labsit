package io.labsit.bank.api.assembler;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.labsit.bank.api.model.ClienteDTO;
import io.labsit.bank.domain.model.Pessoa;

@Component
public class ClienteAssembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	/**
	 * Método para conversão de um objeto do tipo Pessoa.class para um objeto do tipo ClienteDTO.class.
	 * 
	 * @param Pessoa
	 * @return PessoaDTO pessoa
	 * 
	 * @author Walesson Ramos
	 */
	public ClienteDTO toDTO(Pessoa pessoa) {
		return modelMapper.map(pessoa, ClienteDTO.class);
	}
	
	/**
	 * Método para conversão de uma lista de objetos do tipo Pessoa.class para uma lista de objetos do tipo ClienteDTO.class.
	 * 
	 * @param Pessoa pessoa
	 * @return List<ClienteDTO> lista
	 * 
	 * @author Walesson Ramos
	 */
	public List<ClienteDTO> toDTOs(List<Pessoa> listaPessoas) {
		
		List<ClienteDTO> listaDTOs = new ArrayList<ClienteDTO>();
		
		for(Pessoa pessoa : listaPessoas) {
			listaDTOs.add(toDTO(pessoa));
		}
		return listaDTOs;
	}
	
	/**
	 * Método para conversão de uma coleção de objetos do tipo Pessoa.class para uma coleção do tipo ClienteDTO.class.
	 * 
	 * @param listaPessoa
	 * @return List<ClienteDTO> lista
	 * 
	 *  @author Walesson Ramos
	 */
	public List<ClienteDTO> toCollectionDTO(List<Pessoa> listaPessoas){
		return listaPessoas.stream()
				.map(pessoa -> toDTO(pessoa))
				.collect(Collectors.toList());
	}

}
