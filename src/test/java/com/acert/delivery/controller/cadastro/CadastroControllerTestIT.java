package com.acert.delivery.controller.cadastro;

import com.acert.delivery.DeliveryApplication;
import com.acert.delivery.service.cliente.dto.ClienteRequestDTO;
import com.acert.delivery.service.cliente.dto.ClienteResponseDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;

@SpringBootTest(classes = {
        DeliveryApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CadastroControllerTestIT {

    @Value("${local.server.port}")
    private int applicationPort;

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    void cadastrarCliente_sucesso() {

        ClienteRequestDTO clienteRequestDTO = new ClienteRequestDTO(1L, "arthur@gmail.com", "senha");

        ResponseEntity<ClienteResponseDTO> response = restTemplate.exchange(getUrl("/cadastro"),
                HttpMethod.POST, new HttpEntity<>(clienteRequestDTO), ClienteResponseDTO.class);

        assertNotNull(response);
        assertEquals(CREATED, response.getStatusCode());
    }

    @ParameterizedTest(name = "[{index}] Bad request quando [{0}] é nulo")
    @MethodSource("payloadInvalido")
    void cadastratCliente_badRequest(String atributo, ClienteRequestDTO clienteRequestDTO) {
        ResponseEntity<ClienteResponseDTO> response = restTemplate.exchange(getUrl("/cadastro"),
                HttpMethod.POST, new HttpEntity<>(clienteRequestDTO), ClienteResponseDTO.class);

        assertEquals(BAD_REQUEST, response.getStatusCode());
    }

    private static Stream<Arguments> payloadInvalido() {
        return Stream.of(
                Arguments.of("email", new ClienteRequestDTO(1L, null, "senha")),
                Arguments.of("senha", new ClienteRequestDTO(1L, "arthur@gmail.com", null))
        );
    }

    private String getUrl(String path) {
        return "http://localhost:" + applicationPort + path;
    }
}