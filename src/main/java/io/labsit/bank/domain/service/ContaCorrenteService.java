package io.labsit.bank.domain.service;

import io.labsit.bank.domain.model.ContaCorrente;
import io.labsit.bank.domain.model.Transacao;

public interface ContaCorrenteService {
	
	ContaCorrente buscarPorNumero(String numeroConta);
	Transacao depositar(String numeroConta, Transacao transacao);
	Transacao sacar(String numeroConta, Transacao transacao);
	ContaCorrente consultarSaldo(String numeroConta);
	ContaCorrente consultarExtrato(String numeroConta);

}
