package io.labsit.bank.core.modelmapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.labsit.bank.api.model.ContaCorrenteDTO;
import io.labsit.bank.domain.model.ContaCorrente;

@Configuration
public class ModelMapperConfig {
	
	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		
		modelMapper.createTypeMap(ContaCorrente.class, ContaCorrenteDTO.class)
			.<String>addMapping(entidade -> entidade.getPessoa().getNome(),
					(dto, valor) -> dto.setCliente(valor));
		
		return modelMapper;
	}
}