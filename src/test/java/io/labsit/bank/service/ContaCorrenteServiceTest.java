package io.labsit.bank.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import io.labsit.bank.domain.model.ContaCorrente;
import io.labsit.bank.domain.model.Transacao;
import io.labsit.bank.domain.model.enums.TipoTransacaoEnum;
import io.labsit.bank.domain.repository.ContaCorrenteRepository;
import io.labsit.bank.domain.service.TransacaoService;
import io.labsit.bank.domain.service.exception.ServicoException;
import io.labsit.bank.domain.service.impl.ContaCorrenteServiceImpl;
import io.labsit.bank.service.factory.ContaCorrenteFactory;
import io.labsit.bank.service.factory.TransacaoFactory;

@ExtendWith(SpringExtension.class)
public class ContaCorrenteServiceTest {

	@InjectMocks
	private ContaCorrenteServiceImpl service;

	@Mock
	private ContaCorrenteRepository repository;

	@Mock
	private TransacaoService transacaoService;

	@Test
	void deveRealizarUmaTransacaoDeCreditoComSucesso_QuandoDadosValidos() {

		ContaCorrente conta = ContaCorrenteFactory.criarContaCorrenteParaClienteValido();
		Transacao transacao = TransacaoFactory.criarTransacaoDeCreditoComValorPositivo();

		BDDMockito.when(repository.findByNumeroConta(ArgumentMatchers.anyString())).thenReturn(conta);
		BDDMockito.when(transacaoService.realizarCredito(ArgumentMatchers.any())).thenReturn(transacao);
		BDDMockito.when(repository.save(ArgumentMatchers.any())).thenReturn(conta);

		service.depositar("82578150206", transacao);

		assertThat(conta.getSaldo()).isPositive();
		assertThat(conta.getSaldo()).isEqualTo(new BigDecimal("100"));
		assertThat(transacao).isNotNull();
		assertThat(transacao.getConta()).isEqualTo(conta);
		assertThat(transacao.getTipoTransacao()).isEqualTo(TipoTransacaoEnum.DEPOSITO);

	}

	@Test
	void deveFalharAoRealizarUmaTransacaoDeCredito_QuandoContaCorrenteEInvalida() {

		Transacao transacao = TransacaoFactory.criarTransacaoDeCreditoComValorPositivo();
		BDDMockito.when(repository.findByNumeroConta(ArgumentMatchers.anyString())).thenReturn(null);

		Exception ex = assertThrows(ServicoException.class, () -> {
			service.depositar("0", transacao);
		});
		assertThat(ex.getMessage()).isEqualTo("Conta inválida");
	}

	@Test
	void deveRealizarUmaTransacaoDeDebitoComSucesso_QuandoContaCorrenteEValidaEPossuiSaldo() {
		
		ContaCorrente conta = ContaCorrenteFactory.criarContaCorrenteComSaldoPositivoParaClienteValido();
		Transacao transacao = TransacaoFactory.criarTransacaoDeDebitoComValorPositivo();

		BDDMockito.when(repository.findByNumeroConta(ArgumentMatchers.anyString())).thenReturn(conta);
		BDDMockito.when(transacaoService.realizarDebido(ArgumentMatchers.any())).thenReturn(transacao);
		BDDMockito.when(repository.save(ArgumentMatchers.any())).thenReturn(conta);

		service.sacar("82578150206", transacao);

		assertThat(conta.getSaldo()).isPositive();
		assertThat(conta.getSaldo()).isEqualTo(new BigDecimal("100"));
		assertThat(transacao).isNotNull();
		assertThat(transacao.getConta()).isEqualTo(conta);
		assertThat(transacao.getTipoTransacao()).isEqualTo(TipoTransacaoEnum.SAQUE);
	}

	@Test
	void deveFalharAoRealizarUmaTransacaoDeDebito_QuandoContaCorrenteNaoPossuiSaldo() {
		
		ContaCorrente conta = ContaCorrenteFactory.criarContaCorrenteParaClienteValido();
		Transacao transacao = TransacaoFactory.criarTransacaoDeDebitoComValorPositivo();

		BDDMockito.when(repository.findByNumeroConta(ArgumentMatchers.anyString())).thenReturn(conta);
		
		Exception ex = assertThrows(ServicoException.class, () -> {
			service.sacar("82578150206", transacao);
		});
		assertThat(ex.getMessage()).isEqualTo("Saldo Insuficiente!");
	}

	@Test
	void deveFalharAoRealizarUmaTransacaoDeDebito_QuandoContaCorrenteEInvalida() {
		
		Transacao transacao = TransacaoFactory.criarTransacaoDeDebitoComValorPositivo();

		BDDMockito.when(repository.findByNumeroConta(ArgumentMatchers.anyString())).thenReturn(null);
		
		Exception ex = assertThrows(ServicoException.class, () -> {
			service.sacar("0", transacao);
		});
		assertThat(ex.getMessage()).isEqualTo("Conta inválida");
	}

}
