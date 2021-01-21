package io.labsit.bank.api.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.labsit.bank.api.assembler.ClienteAssembler;
import io.labsit.bank.api.assembler.ClienteDisassembler;
import io.labsit.bank.api.model.ClienteDTO;
import io.labsit.bank.domain.service.PessoaService;

@RestController
@RequestMapping("/clientes")
public class ClienteResource {
	
	@Autowired
	private PessoaService service;
	
	@Autowired
	private ClienteAssembler assembler;
	
	@Autowired
	private ClienteDisassembler disassembler;
	
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping("/{id}")
	public ClienteDTO buscarPorId(@PathVariable Long id) {
		return assembler.toDTO(service.buscarPorId(id));
	}
	
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping
	public List<ClienteDTO> buscarTodos() {
		return assembler.toCollectionDTO(service.buscarTodos());
	}
	
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping
	public ClienteDTO cadastrar(@RequestBody @Valid ClienteDTO clienteDTO) {
		return assembler.toDTO(service.cadastrar(clienteDTO.getNumeroAgencia(), disassembler.toDomainModel(clienteDTO)));
	}
	
	@ResponseStatus(code = HttpStatus.OK)
	@PutMapping("/{id}")
	public ClienteDTO atualizar(@PathVariable Long id, @RequestBody @Valid ClienteDTO clienteDTO) {
		clienteDTO.setId(id);
		return assembler.toDTO(service.atualizar(clienteDTO.getNumeroAgencia(), disassembler.toDomainModel(clienteDTO)));
	}

}
