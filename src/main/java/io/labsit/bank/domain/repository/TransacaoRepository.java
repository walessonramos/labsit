package io.labsit.bank.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.labsit.bank.domain.model.Transacao;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long>{
	
	List<Transacao> findByContaNumeroConta(String numeroConta);
	List<Transacao> findByContaPessoaNumeroDocumento(String documento);

}
