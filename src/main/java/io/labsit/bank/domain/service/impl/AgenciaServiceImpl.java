package io.labsit.bank.domain.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.labsit.bank.domain.exception.EntidadeNaoEncontradaException;
import io.labsit.bank.domain.model.Agencia;
import io.labsit.bank.domain.repository.AgenciaRepository;
import io.labsit.bank.domain.service.AgenciaService;
import io.labsit.bank.domain.service.exception.ServicoException;

@Service
public class AgenciaServiceImpl implements AgenciaService{
	
	private static final String MSG_AGENCIA_NAO_ENCONTRADA = "Agencia não encontrada!";

	private static final String MSG_AGENCIA_DUPLICIDADE = "Já existe agencia cadastrada com o número ";
	
	@Autowired
	private AgenciaRepository repository;
	
	@Override
	public Agencia buscarPorId(Long id) {
		return repository.findById(id)
					.orElseThrow(() -> new EntidadeNaoEncontradaException(MSG_AGENCIA_NAO_ENCONTRADA));
	}
	
	@Override
	public Agencia buscarPorNumero(Integer numero) {
		
		Agencia agencia = repository.findByNumero(numero);
		if(agencia == null) {
			throw new EntidadeNaoEncontradaException(MSG_AGENCIA_NAO_ENCONTRADA);
		}
		
		return agencia;
	}
	
	@Override
	public List<Agencia> buscarTodas() {
		return repository.findAll();
	}

	@Override
	public Agencia cadastrar(Agencia agencia) {
		validaInclusao(agencia);
		return repository.save(agencia);
	}

	@Override
	public Agencia atualizar(Agencia agencia) {
		
		validaAtualizacao(agencia);
		Agencia agenciaBD = buscarPorId(agencia.getId());
		BeanUtils.copyProperties(agencia, agenciaBD, "id");
		return repository.save(agenciaBD);
	}
	
	public void validaInclusao(Agencia agencia) {
		
		Agencia agenciaExistente = repository.findByNumero(agencia.getNumero());
		
		if(agenciaExistente != null) {
			throw new ServicoException(MSG_AGENCIA_DUPLICIDADE + agencia.getNumero());
		}
	}
	
	public void validaAtualizacao(Agencia agencia) {
		
		Agencia agenciaExistente = repository.findByNumero(agencia.getNumero());
		
		if(agenciaExistente != null 
					&& !agencia.getId().equals(agenciaExistente.getId())) {
			
			throw new ServicoException(MSG_AGENCIA_DUPLICIDADE + agencia.getNumero());
		}
	}

}
