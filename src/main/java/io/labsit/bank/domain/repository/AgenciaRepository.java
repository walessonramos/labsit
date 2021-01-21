package io.labsit.bank.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.labsit.bank.domain.model.Agencia;

@Repository
public interface AgenciaRepository extends JpaRepository<Agencia, Long>{
	
	Agencia findByNumero(Integer numero);
	

}
