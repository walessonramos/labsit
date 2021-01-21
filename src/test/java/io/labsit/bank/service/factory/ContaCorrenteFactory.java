package io.labsit.bank.service.factory;

import java.math.BigDecimal;

import io.labsit.bank.domain.model.ContaCorrente;

public class ContaCorrenteFactory {

	public static ContaCorrente criarContaCorrenteParaNovoCliente() {

		ContaCorrente conta = new ContaCorrente();
		conta.setAgencia(AgenciaFactory.criarAgenciaParaInclusao());
		conta.setSaldo(BigDecimal.ZERO);

		return conta;
	}
	
	public static ContaCorrente criarContaCorrenteParaClienteValido() {

		ContaCorrente conta = new ContaCorrente();
		conta.setId(1L);
		conta.setAgencia(AgenciaFactory.criarAgenciaParaInclusao());
		conta.setSaldo(BigDecimal.ZERO);

		return conta;
	}
	
	public static ContaCorrente criarContaCorrenteComSaldoPositivoParaClienteValido() {

		ContaCorrente conta = new ContaCorrente();
		conta.setId(1L);
		conta.setAgencia(AgenciaFactory.criarAgenciaParaInclusao());
		conta.setSaldo(new BigDecimal("200"));

		return conta;
	}
}
