package io.labsit.bank.integration;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import io.labsit.bank.api.model.ClienteDTO;
import io.labsit.bank.api.model.TransacaoInputDTO;
import io.labsit.bank.integration.factory.ClienteDTOFactory;
import io.labsit.bank.integration.factory.TransacaoInputFactory;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TransacaoIntegrationTest {

	@Autowired
	private TestRestTemplate restTemplate;
	
	@LocalServerPort
	private int porta;
	
	@Test
	void deveRealizarUmaTransacaoDeCreditoComSucesso_QuandoDadosDeEntradaSaoValidos() {
		
		ClienteDTO clienteDTO = ClienteDTOFactory.criarClientePessoaFisicaDTOComCPFValido();
		restTemplate.postForEntity("/clientes", clienteDTO, ClienteDTO.class);
		
		TransacaoInputDTO dto = TransacaoInputFactory.criarTransacaoDTOComValorPositivo();
		
		ResponseEntity<String> retorno = restTemplate.postForEntity("/conta-corrente/deposito?numeroConta=29570767057", dto, String.class);
		assertThat(retorno.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(retorno.getBody()).isNotNull();
	}
	
	@Test
	void deveRealizarUmaTransacaoDeDebitoComSucesso_QuandoDadosDeEntradaSaoValidosEPossuiSaldo() {
		
		ClienteDTO clienteDTO = ClienteDTOFactory.criarClientePessoaFisicaDTOComCPFValido();
		restTemplate.postForEntity("/clientes", clienteDTO, ClienteDTO.class);
		TransacaoInputDTO deposito = TransacaoInputFactory.criarTransacaoDTOComValorPositivo();
		restTemplate.postForEntity("/conta-corrente/deposito?numeroConta=29570767057", deposito, String.class);
		
		TransacaoInputDTO saque = TransacaoInputFactory.criarTransacaoDTOComValorPositivo();
		ResponseEntity<String> retorno = restTemplate.postForEntity("/conta-corrente/saque?numeroConta=29570767057", saque, String.class);
		
		assertThat(retorno.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(retorno.getBody()).isNotNull();
	}
	
}
