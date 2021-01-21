package io.labsit.bank.domain.service;

import java.util.List;

import io.labsit.bank.domain.model.Agencia;

public interface AgenciaService {
	
	Agencia buscarPorId(Long id);
	Agencia buscarPorNumero(Integer numero);
	Agencia cadastrar(Agencia agencia);
	Agencia atualizar(Agencia agencia);
	List<Agencia> buscarTodas();
	
}
