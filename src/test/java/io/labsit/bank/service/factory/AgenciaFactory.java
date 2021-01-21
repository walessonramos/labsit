package io.labsit.bank.service.factory;

import io.labsit.bank.domain.model.Agencia;

public class AgenciaFactory {

	public static Agencia criarAgenciaParaInclusao() {
		
		Agencia agencia = new Agencia();
		agencia.setNumero(9999);
		return agencia;
	}

	public static Agencia criarAgenciaParaAtualizacao() {
		Agencia agencia = new Agencia();
		agencia.setId(1L);
		agencia.setNumero(1233);
		return agencia;
	}

	public static Agencia criarAgenciaComDadosValidos() {
		Agencia agencia = new Agencia();
		agencia.setId(1L);
		agencia.setNumero(1234);
		return agencia;
	}

}
