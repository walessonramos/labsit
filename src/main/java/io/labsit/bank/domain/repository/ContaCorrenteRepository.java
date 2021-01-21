package io.labsit.bank.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import io.labsit.bank.domain.model.ContaCorrente;

@Repository
public interface ContaCorrenteRepository extends JpaRepository<ContaCorrente, Long>{
	
	ContaCorrente findByPessoaNumeroDocumento(String cpfCnpj);
	ContaCorrente findByNumeroConta(String numeroConta);
	
	@Query("select conta from ContaCorrente conta join fetch "
				+ "conta.listaTransacoes "
					+ "where conta.numeroConta = :conta")
	ContaCorrente buscarComTransacoes(@Param("conta") String numeroConta);
}
