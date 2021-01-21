package io.labsit.bank.api.exceptionhandler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import io.labsit.bank.domain.exception.EntidadeNaoEncontradaException;
import io.labsit.bank.domain.service.exception.ServicoException;

@ControllerAdvice
public class ApiExceptionHandler {
	
	@ExceptionHandler
	public ResponseEntity<?> tratarEntidadeNaoEncontradaException(EntidadeNaoEncontradaException e){
		
		CausaException causa = new CausaException();
		causa.setDataHora(LocalDateTime.now());
		causa.setMensagem(e.getMessage());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(causa);
	}
	
	@ExceptionHandler
	public ResponseEntity<?> tratarServicoException(ServicoException e){
		
		CausaException causa = new CausaException();
		causa.setDataHora(LocalDateTime.now());
		causa.setMensagem(e.getMessage());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(causa);
	}

}
