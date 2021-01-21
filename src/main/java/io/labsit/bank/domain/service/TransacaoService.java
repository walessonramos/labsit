package io.labsit.bank.domain.service;

import java.util.List;

import io.labsit.bank.domain.model.Transacao;

public interface TransacaoService {
	
	Transacao realizarCredito(Transacao transacao);
	Transacao realizarDebido(Transacao transacao);
	List<Transacao> buscarPorNumeroConta(String numeroConta);

}
