package io.labsit.bank.service.factory;

import io.labsit.bank.domain.model.ContaCorrente;
import io.labsit.bank.domain.model.Pessoa;
import io.labsit.bank.domain.model.PessoaFisica;
import io.labsit.bank.domain.model.PessoaJuridica;
import io.labsit.bank.domain.util.Util;

public class PessoaFactory {
	
	public static PessoaFisica criarPessoaFisicaParaInclusao() {
		PessoaFisica pf = new PessoaFisica();
		pf.setNome("Agenor de Oliveira");
		pf.setCPF(Util.removeCaracteresEspeciais("992.352.030-74"));
		return pf;
	}
	
	public static Pessoa retornarPessoaIncluida(Pessoa pessoa) {
		Pessoa pessoaRetorno = new PessoaFisica();
		pessoaRetorno.setId(1L);
		pessoaRetorno.setConta(pessoa.getConta());
		pessoaRetorno.setNome(pessoa.getNome());
		pessoaRetorno.setNumeroDocumento(pessoa.getNumeroDocumento());
		return pessoaRetorno;
	}
	
	public static PessoaFisica criarPessoaFisicaComCPFInvalido() {
		PessoaFisica pf = new PessoaFisica();
		pf.setNome("João Nogueira");
		pf.setCPF(Util.removeCaracteresEspeciais("002.352.035-75"));
		return pf;
	}
	
	public static PessoaFisica criarPessoaFisicaParaAtualizacao() {
		PessoaFisica pf = criarPessoaFisicaComDadosValidos();
		pf.setNome("Nelson Cavaquinho");
		return pf;
	}
	
	public static PessoaFisica criarPessoaFisicaComDadosValidos() {
		
		PessoaFisica pf = new PessoaFisica();
		pf.setNome("Nelson Gonçalves");
		pf.setCPF(Util.removeCaracteresEspeciais("035.390.740-58"));
		pf.setNumeroDocumento("03539074058");
		ContaCorrente conta = ContaCorrenteFactory.criarContaCorrenteParaClienteValido();
		conta.setNumeroConta(Util.removeCaracteresEspeciais(pf.getCPF()));
		conta.setPessoa(pf);
		pf.setConta(conta);
		
		return pf;
	}
	
	public static PessoaJuridica criarPessoaJuridicaParaInclusao() {
		PessoaJuridica pfpj = new PessoaJuridica();
		pfpj.setNome("WORLD SA");
		pfpj.setCNPJ(Util.removeCaracteresEspeciais("86.468.472/0001-99"));
		return pfpj;
	}
	
	public static PessoaJuridica criarPessoaJuridicaComCNPJnvalido() {
		PessoaJuridica pf = new PessoaJuridica();
		pf.setNome("PACIFIC OCEAN INC");
		pf.setCNPJ(Util.removeCaracteresEspeciais("00.000.111/1111-00"));
		return pf;
	}
	
	public static PessoaJuridica criarPessoaJuridicaComDadosValidos() {
		
		PessoaJuridica pj = new PessoaJuridica();
		pj.setNome("Empresa ABC");
		pj.setCNPJ(Util.removeCaracteresEspeciais("86.468.472/0001-99"));
		pj.setNumeroDocumento("86468472000199");
		ContaCorrente conta = ContaCorrenteFactory.criarContaCorrenteParaClienteValido();
		conta.setNumeroConta(Util.removeCaracteresEspeciais(pj.getCNPJ()));
		conta.setPessoa(pj);
		pj.setConta(conta);
		return pj;
	}
	
}
