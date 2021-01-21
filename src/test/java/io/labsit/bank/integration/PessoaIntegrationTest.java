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
import io.labsit.bank.integration.factory.ClienteDTOFactory;

/**
 * Testes de integração de Clientes.
 * @author walesson
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class PessoaIntegrationTest {
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@LocalServerPort
	private int porta;
	
	@Test
	void deveCadastrarClienteComSucesso_QuandoDadosValidos() {
		
		ClienteDTO dto = ClienteDTOFactory.criarClientePessoaFisicaDTOComCPFValido();
		ResponseEntity<ClienteDTO> retorno = restTemplate.postForEntity("/clientes", dto, ClienteDTO.class);
		
		assertThat(retorno.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		assertThat(retorno.getBody()).isNotNull();
		
	}
	
	@Test
	void deveFalharAoCadastrarCliente_QuandoCPFInvalido() {
		
		ClienteDTO dto = ClienteDTOFactory.criarClientePessoaFisicaDTOComCPFInvalido();
		ResponseEntity<ClienteDTO> retorno = restTemplate.postForEntity("/clientes", dto, ClienteDTO.class);
		
		assertThat(retorno.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
	}

}
