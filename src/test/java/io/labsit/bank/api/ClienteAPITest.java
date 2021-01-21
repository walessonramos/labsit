package io.labsit.bank.api;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

/**
 * Testes de API - Testa os EndPoints de Clientes
 * 
 * @author Walesson Ramos
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClienteAPITest {
	
	@LocalServerPort
	private int port;
	
	@Test
	public void deveRetornarStatus200_QuandoConsultarClientes() {
		
		RestAssured.given()
			.basePath("/clientes")
			.port(port)
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.statusCode(HttpStatus.OK.value());
		
	}

}
