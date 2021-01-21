package io.labsit.bank.api.exceptionhandler;

import java.time.LocalDateTime;

public class CausaException {
	
	private LocalDateTime dataHora;
	private String mensagem;
	
	public LocalDateTime getDataHora() {
		return dataHora;
	}
	public void setDataHora(LocalDateTime dataHota) {
		this.dataHora = dataHota;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}


}
