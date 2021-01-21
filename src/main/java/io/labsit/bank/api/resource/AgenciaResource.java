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

import io.labsit.bank.api.assembler.AgenciaAssembler;
import io.labsit.bank.api.assembler.AgenciaDisassembler;
import io.labsit.bank.api.model.AgenciaDTO;
import io.labsit.bank.domain.service.AgenciaService;

@RestController
@RequestMapping("/agencias")
public class AgenciaResource {
	
	@Autowired
	private AgenciaService service;
	
	@Autowired
	private AgenciaAssembler assembler;
	
	@Autowired
	private AgenciaDisassembler disassembler;
	
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping("/{id}")
	public AgenciaDTO buscarPorId(@PathVariable Long id) {
		return assembler.toDTO(service.buscarPorId(id));
	}
	
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping
	public List<AgenciaDTO> buscarTodas() {
		return assembler.toCollectionDTO(service.buscarTodas());
	}
	
	@PostMapping("/")
	@ResponseStatus(code = HttpStatus.CREATED)
	public AgenciaDTO cadastrar(@RequestBody @Valid AgenciaDTO agencia) {
		return assembler.toDTO(service.cadastrar(disassembler.toDomainModel(agencia)));
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public AgenciaDTO atualizar(@PathVariable Long id, @RequestBody @Valid AgenciaDTO agenciaDTO) {
		agenciaDTO.setId(id);
		return assembler.toDTO(service.atualizar(disassembler.toDomainModel(agenciaDTO)));
	}

}
