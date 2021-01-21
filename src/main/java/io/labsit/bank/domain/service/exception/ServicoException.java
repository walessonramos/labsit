package io.labsit.bank.domain.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class ServicoException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public ServicoException(String mensagem) {
		super(mensagem);
	}
}
