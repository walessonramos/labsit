package io.labsit.bank.service.factory;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import io.labsit.bank.domain.model.Transacao;
import io.labsit.bank.domain.model.enums.TipoTransacaoEnum;

public class TransacaoFactory {

	public static Transacao criarTransacaoDeCreditoComValorPositivo() {

		Transacao transacao = new Transacao();
		transacao.setData(LocalDateTime.now());
		transacao.setTipoTransacao(TipoTransacaoEnum.DEPOSITO);
		transacao.setValor(new BigDecimal("100"));

		return transacao;
	}

	public static Transacao criarTransacaoDeDebitoComValorPositivo() {

		Transacao transacao = new Transacao();
		transacao.setData(LocalDateTime.now());
		transacao.setTipoTransacao(TipoTransacaoEnum.SAQUE);
		transacao.setValor(new BigDecimal("100"));

		return transacao;
	}
	
}
