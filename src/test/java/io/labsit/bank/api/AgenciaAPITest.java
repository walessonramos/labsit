package io.labsit.bank.api;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

/**
 * Testes de API - Testa os EndPoints de Agencias
 * 
 * @author Walesson Ramos
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AgenciaAPITest {
	
	@LocalServerPort
	private int port;
	
	@Test
	public void deveRetornarStatus200_QuandoConsultarTodasAgencias() {
		
		RestAssured.given()
			.basePath("/agencias")
			.port(port)
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.statusCode(HttpStatus.OK.value());
		
	}
	
	@Test
	public void deveRetornarStatus200_QuandoConsultarUnicaAgencia() {
		
		RestAssured.given()
			.basePath("/agencias/1")
			.port(port)
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.statusCode(HttpStatus.OK.value());
		
	}
	
	@Test
	public void deveRetornarStatus404_QuandoConsultarUnicaAgenciaNaoExiste() {
		
		RestAssured.given()
			.basePath("/agencias/1111111111111111")
			.port(port)
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value());
		
	}

}
