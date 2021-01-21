package io.labsit.bank.domain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.labsit.bank.domain.model.Transacao;
import io.labsit.bank.domain.repository.TransacaoRepository;
import io.labsit.bank.domain.service.TransacaoService;

@Service
public class TransacaoServiceImpl implements TransacaoService{
	
	@Autowired
	private TransacaoRepository repository;

	@Override
	public Transacao realizarCredito(Transacao transacao) {
		return repository.save(transacao);
	}

	@Override
	public Transacao realizarDebido(Transacao transacao) {
		return repository.save(transacao);
	}

	@Override
	public List<Transacao> buscarPorNumeroConta(String numeroConta) {
		return repository.findByContaNumeroConta(numeroConta);
	}

}
