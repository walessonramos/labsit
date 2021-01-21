package io.labsit.bank.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import io.labsit.bank.domain.model.Agencia;
import io.labsit.bank.domain.model.Pessoa;
import io.labsit.bank.domain.model.PessoaFisica;
import io.labsit.bank.domain.repository.AgenciaRepository;
import io.labsit.bank.domain.repository.ContaCorrenteRepository;
import io.labsit.bank.domain.repository.PessoaRepository;
import io.labsit.bank.domain.service.exception.ServicoException;
import io.labsit.bank.domain.service.impl.PessoaServiceImpl;
import io.labsit.bank.service.factory.AgenciaFactory;
import io.labsit.bank.service.factory.PessoaFactory;

@ExtendWith(SpringExtension.class)
public class PessoaServiceTest {
	
	@InjectMocks
	private PessoaServiceImpl service;
	
	@Mock
	private PessoaRepository repository;
	
	@Mock
	AgenciaRepository agenciaRepository;
	
	@Mock
	private ContaCorrenteRepository contaRepository;
	
	@Test
	void deveCadastrarUmClientePessoaFisicaComSucesso_QuandoDadosSaoValidos() {
		
		Pessoa pessoa = PessoaFactory.criarPessoaFisicaParaInclusao();
		Pessoa pessoaRetorno = PessoaFactory.retornarPessoaIncluida(pessoa);
		Agencia agencia = AgenciaFactory.criarAgenciaComDadosValidos();
		
		BDDMockito.when(agenciaRepository.findByNumero(ArgumentMatchers.anyInt())).thenReturn(agencia);
		BDDMockito.when(agenciaRepository.getOne(ArgumentMatchers.anyLong())).thenReturn(agencia);
		
		service.cadastrar(null, pessoa);
		
		assertThat(pessoaRetorno).isNotNull();
		assertThat(pessoaRetorno.getId()).isNotNull();
		assertThat(pessoaRetorno.getNome()).isEqualTo(pessoa.getNome());
		
	}
	
	@Test
	void deveFalharAoCadastrarUmClientePessoaFisica_QuandoCPFInvalido() {
		
		Pessoa pessoa = PessoaFactory.criarPessoaFisicaComCPFInvalido();
		
		Exception ex = assertThrows(
				ServicoException.class, () -> {
					service.cadastrar(null, pessoa);
				}); 
		assertThat(ex.getMessage()).contains("inválido");
		assertThat(ex.getMessage()).isEqualTo("O CPF é inválido");
	}
	
	@Test
	void deveFalharAoCadastrarUmClientePessoaFisica_QuandoCPFJaExiste() {
		
		Pessoa pessoa = PessoaFactory.criarPessoaFisicaParaInclusao();
		Pessoa pessoaExiste = PessoaFactory.retornarPessoaIncluida(pessoa);
		
		BDDMockito.when(repository.findByNumeroDocumento(ArgumentMatchers.anyString())).thenReturn(pessoaExiste);
		
		Exception ex = assertThrows(
				ServicoException.class, () -> {
					service.cadastrar(null, pessoa);
				}); 
		assertThat(ex.getMessage()).contains("Já existe cadastro para o documento ");
		assertThat(ex.getMessage()).isEqualTo("Já existe cadastro para o documento " + ((PessoaFisica)pessoa).getCPF());
	}
	
	@Test
	void deveCadastrarUmClientePessoaJuridicaComSucesso_QuandoDadosSaoValidos() {
		
		Pessoa pessoa = PessoaFactory.criarPessoaJuridicaParaInclusao();
		Pessoa pessoaRetorno = PessoaFactory.retornarPessoaIncluida(pessoa);
		Agencia agencia = AgenciaFactory.criarAgenciaComDadosValidos();
		
		BDDMockito.when(agenciaRepository.findByNumero(ArgumentMatchers.anyInt())).thenReturn(agencia);
		BDDMockito.when(agenciaRepository.getOne(ArgumentMatchers.anyLong())).thenReturn(agencia);
		
		service.cadastrar(null, pessoa);
		
		assertThat(pessoaRetorno).isNotNull();
		assertThat(pessoaRetorno.getId()).isNotNull();
		assertThat(pessoaRetorno.getNome()).isEqualTo(pessoa.getNome());
	}
	
	@Test
	void deveFalharAoCadastrarUmClientePessoaJuridica_QuandoCNPJInvalido() {
		
		Pessoa pessoa = PessoaFactory.criarPessoaJuridicaComCNPJnvalido();
		
		Exception ex = assertThrows(
				ServicoException.class, () -> {
					service.cadastrar(null, pessoa);
				}); 
		assertThat(ex.getMessage()).contains("inválido");
		assertThat(ex.getMessage()).isEqualTo("O CNPJ é inválido");
	}

}
