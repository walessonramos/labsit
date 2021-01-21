package io.labsit.bank.domain.service;

import java.util.List;

import io.labsit.bank.domain.model.Pessoa;

public interface PessoaService {
	
	Pessoa buscarPorId(Long id);
	List<Pessoa> buscarTodos();
	Pessoa cadastrar(Integer numeroAgencia, Pessoa pessoa);
	Pessoa atualizar(Integer numeroAgencia, Pessoa pessoa);
	
}
