package io.labsit.bank.domain.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.labsit.bank.domain.exception.EntidadeNaoEncontradaException;
import io.labsit.bank.domain.model.Agencia;
import io.labsit.bank.domain.model.ContaCorrente;
import io.labsit.bank.domain.model.Pessoa;
import io.labsit.bank.domain.model.PessoaFisica;
import io.labsit.bank.domain.model.PessoaJuridica;
import io.labsit.bank.domain.repository.AgenciaRepository;
import io.labsit.bank.domain.repository.ContaCorrenteRepository;
import io.labsit.bank.domain.repository.PessoaRepository;
import io.labsit.bank.domain.service.PessoaService;
import io.labsit.bank.domain.service.exception.ServicoException;
import io.labsit.bank.domain.util.Util;

@Service
public class PessoaServiceImpl implements PessoaService {

	private static final String MSG_DOCUMENTO_DUPLICIDADE = "Já existe cadastro para o documento ";

	@Autowired
	private PessoaRepository repository;

	@Autowired
	AgenciaRepository agenciaRepository;
	
	@Autowired
	private ContaCorrenteRepository contaRepository;

	@Override
	public Pessoa buscarPorId(Long id) {
		return repository.findById(id)
				.orElseThrow(() -> 
					new EntidadeNaoEncontradaException("Cliente não encontrado!"));
	}

	@Override
	public List<Pessoa> buscarTodos() {
		return repository.findAll();
	}
	
	@Transactional
	@Override
	public Pessoa cadastrar(Integer numeroAgencia, Pessoa pessoa) {

		validaOperacao(numeroAgencia, pessoa);
		ContaCorrente conta = new ContaCorrente();
		if (numeroAgencia != null) {
			Agencia agencia = agenciaRepository.findByNumero(numeroAgencia);
			conta.setAgencia(agencia);
		} else {
			Agencia agencia = agenciaRepository.getOne(1L);
			conta.setAgencia(agencia);
		}
		conta.setPessoa(pessoa);
		conta.setSaldo(BigDecimal.ZERO);
		pessoa.setConta(conta);

		return repository.save(pessoa);
	}
	
	@Transactional
	@Override
	public Pessoa atualizar(Integer numeroAgencia, Pessoa pessoa) {

		validaOperacao(numeroAgencia, pessoa);
		Pessoa pessoaBD = buscarPorId(pessoa.getId());
		
		if (numeroAgencia != null) {
			Agencia agencia = agenciaRepository.findByNumero(numeroAgencia);
			ContaCorrente conta = contaRepository.findByPessoaNumeroDocumento(pessoa.getNumeroDocumento());
			conta.setAgencia(agencia);
			pessoa.setConta(conta);
			BeanUtils.copyProperties(pessoa, pessoaBD, "id");
		}else {
			BeanUtils.copyProperties(pessoa, pessoaBD, "id", "conta");
		}
		
		return repository.save(pessoaBD);
	}

	private void validaOperacao(Integer numeroAgencia, Pessoa pessoa) {

		String documento = "";

		if (pessoa instanceof PessoaFisica) {
			PessoaFisica pf = (PessoaFisica) pessoa;
			documento = pf.getCPF();
			if (!Util.validaCPF(documento)) {
				throw new ServicoException("O CPF é inválido");
			}
		}

		if (pessoa instanceof PessoaJuridica) {
			PessoaJuridica pj = (PessoaJuridica) pessoa;
			documento = pj.getCNPJ();
			if (!Util.validaCNPJ(documento)) {
				throw new ServicoException("O CNPJ é inválido");
			}
		}

		if (numeroAgencia != null) {
			Agencia agencia = agenciaRepository.findByNumero(numeroAgencia);
			if (agencia == null) {
				throw new ServicoException("A Agencia informada é inválida!");
			}
		}

		Pessoa pessoaJaExiste = repository.findByNumeroDocumento(documento);

		// Se inserindo
		if (pessoa.getId() == null && pessoaJaExiste != null) {
			throw new ServicoException(MSG_DOCUMENTO_DUPLICIDADE + documento);
		}

		// Se atualizando
		if (pessoa.getId() != null && pessoaJaExiste != null && !pessoaJaExiste.getId().equals(pessoa.getId())) {
			throw new ServicoException(MSG_DOCUMENTO_DUPLICIDADE + documento);
		}
	}
}
