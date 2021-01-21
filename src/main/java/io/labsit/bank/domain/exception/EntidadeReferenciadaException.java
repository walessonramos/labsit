package io.labsit.bank.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class EntidadeReferenciadaException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public EntidadeReferenciadaException(String mensagem) {
		super(mensagem);
	}

}
