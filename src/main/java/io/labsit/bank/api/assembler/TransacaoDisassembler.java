package io.labsit.bank.api.assembler;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.labsit.bank.api.model.TransacaoDTO;
import io.labsit.bank.api.model.TransacaoInputDTO;
import io.labsit.bank.domain.model.Transacao;

@Component
public class TransacaoDisassembler {

	@Autowired
	private ModelMapper modelMapper;

	public Transacao toDomainModel(TransacaoInputDTO transcaoInputDTO) {

		return modelMapper.map(transcaoInputDTO, Transacao.class);
	}

	public Transacao toDomainModel(TransacaoDTO transcaoDTO) {

		return modelMapper.map(transcaoDTO, Transacao.class);
	}

	public List<Transacao> toDomainModel(List<TransacaoDTO> listaTransacoesDTO) {

		List<Transacao> listaTransacoes = new ArrayList<Transacao>();

		for (TransacaoDTO input : listaTransacoesDTO) {
			listaTransacoes.add(toDomainModel(input));
		}
		return listaTransacoes;
	}

}
