package io.labsit.bank.domain.service.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.labsit.bank.domain.model.ContaCorrente;
import io.labsit.bank.domain.model.Transacao;
import io.labsit.bank.domain.model.enums.TipoTransacaoEnum;
import io.labsit.bank.domain.repository.ContaCorrenteRepository;
import io.labsit.bank.domain.service.ContaCorrenteService;
import io.labsit.bank.domain.service.TransacaoService;
import io.labsit.bank.domain.service.exception.ServicoException;

@Service
public class ContaCorrenteServiceImpl implements ContaCorrenteService{
	
	@Autowired
	private ContaCorrenteRepository repository;
	
	@Autowired
	private TransacaoService transacaoService;
	
	@Override
	public ContaCorrente buscarPorNumero(String numeroConta) {
		
		ContaCorrente conta = repository.findByNumeroConta(numeroConta);
		if(conta == null) {
			throw new ServicoException("Conta inválida");
		}
		return conta;
	}

	
	@Override
	@Transactional
	public Transacao depositar(String numeroConta, Transacao transacao) {
		
		ContaCorrente conta = buscarPorNumero(numeroConta);
		transacao.setTipoTransacao(TipoTransacaoEnum.DEPOSITO);
		transacao.setConta(conta);
		conta.setSaldo(conta.getSaldo().add(transacao.getValor()));
		transacaoService.realizarCredito(transacao);
		repository.save(conta);
		return transacao;
	}
	
	@Override
	@Transactional
	public Transacao sacar(String numeroConta, Transacao transacao) {
		
		ContaCorrente conta = buscarPorNumero(numeroConta);
		validaDebito(conta, transacao.getValor());
		transacao.setTipoTransacao(TipoTransacaoEnum.SAQUE);
		transacao.setConta(conta);
		conta.setSaldo(conta.getSaldo().subtract(transacao.getValor()));
		transacaoService.realizarDebido(transacao);
		repository.save(conta);
		return transacao;
	}

	@Override
	public ContaCorrente consultarSaldo(String numeroConta) {
		
		ContaCorrente conta = repository.findByNumeroConta(numeroConta);
		
		if(conta == null) {
			throw new ServicoException("Conta Não Encontrada!");
		}
		
		return conta;
	}

	@Override
	public ContaCorrente consultarExtrato(String numeroConta) {
		
		ContaCorrente conta = repository.buscarComTransacoes(numeroConta);
		if(conta == null) {
			throw new ServicoException("Conta inválida!");
		}
		return conta;
	}
	
	public void validaDebito(ContaCorrente conta, BigDecimal valorDebito) {
		
		BigDecimal saldoResultante = conta.getSaldo().subtract(valorDebito);
		
		if(saldoResultante.compareTo(BigDecimal.ZERO) < 0) {
			throw new ServicoException("Saldo Insuficiente!");
		}
	}

}
