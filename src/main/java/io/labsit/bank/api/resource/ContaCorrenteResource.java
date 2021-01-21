package io.labsit.bank.api.resource;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.labsit.bank.api.assembler.ContaCorrenteAssembler;
import io.labsit.bank.api.assembler.TransacaoDisassembler;
import io.labsit.bank.api.model.ContaCorrenteDTO;
import io.labsit.bank.api.model.ContaCorrenteExtratoDTO;
import io.labsit.bank.api.model.TransacaoInputDTO;
import io.labsit.bank.domain.service.ContaCorrenteService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/conta-corrente")
public class ContaCorrenteResource {
	
	@Autowired
	private ContaCorrenteService service;
	
	@Autowired
	private ContaCorrenteAssembler assembler;
	
	@Autowired
	private TransacaoDisassembler transacaoDisassembler;
	
	private static final String MSG_OPERACAO_SUCESSO = "Operação realizada com sucesso!";
	
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping("/saldo")
	@ApiOperation("Operação de consulta de saldo informando o nº da conta (CPF ou CNPJ)")
	public ContaCorrenteDTO consultarSaldo(@RequestParam("numeroConta") String numeroConta) {
		return assembler.toDTO(service.consultarSaldo(numeroConta));
	}
	
	@ApiOperation("Operação de saque informando o nº da conta (CPF ou CNPJ)")
	@ResponseStatus(code = HttpStatus.OK)
	@PostMapping("/saque")
	public String realizarSaque(@RequestParam("numeroConta")String numeroConta, @RequestBody @Valid TransacaoInputDTO TransacaoInput) {
		service.sacar(numeroConta, transacaoDisassembler.toDomainModel(TransacaoInput));
		return MSG_OPERACAO_SUCESSO;
	}
	
	@ApiOperation("Operação de depósito informando o nº da conta (CPF ou CNPJ)")
	@ResponseStatus(code = HttpStatus.OK)
	@PostMapping("/deposito")
	public String realizarDeposito(@RequestParam("numeroConta")String numeroConta, @RequestBody @Valid TransacaoInputDTO TransacaoInput) {
		service.depositar(numeroConta, transacaoDisassembler.toDomainModel(TransacaoInput));
		return MSG_OPERACAO_SUCESSO;
	}
	
	@ApiOperation("Operação de consulta de extrato informando o nº da conta (CPF ou CNPJ)")
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping("/extrato")
	public ContaCorrenteExtratoDTO consultarExtrato(@RequestParam("numeroConta") String numeroConta) {
		
		return assembler.toExtratoDTO(service.consultarExtrato(numeroConta));
	}

}
