package br.com.hexburger.pagamento.bdd;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PagamentoStepDefinitions {

    private static final String ENDPOINT_PAGAMENTOS = "http://localhost:8080/v1/pagamento";

    private Response response;
    private String payload;

    @Dado("o payload com status de pagamento aprovado")
    public void oPayloadComStatusDePagamentoAprovado() {
        payload = """
            {
                "status_do_pagamento": "APROVADO",
                "codigo_pedido": "877e03ba-eef1-4c49-9dc5-d3cc480426c8",
                "total_pago": 80.0,
                "in_store_order_id": "c60f7d3f-996f-4709-a507-5f5068c0fee4"
            }
        """;
    }

    @Dado("o payload com status de pagamento recusado")
    public void oPayloadComStatusDePagamentoRecusado() {
        payload = """
            {
                "status_do_pagamento": "RECUSADO",
                "codigo_pedido": "877e03ba-eef1-4c49-9dc5-d3cc480426c8",
                "total_pago": 80.0,
                "in_store_order_id": "c60f7d3f-996f-4709-a507-5f5068c0fee4"
            }
        """;
    }

    @Dado("o payload inválido sem o status do pagamento")
    public void oPayloadInvalidoSemOStatusDoPagamento() {
        payload = """
            {
                "idPedido": "0",
                "isAprovado": true
            }
        """;
    }

    @Dado("o payload com dados inválidos para o pagamento")
    public void oPayloadComDadosInvalidosParaOPagamento() {
        payload = """
            {
                "idPedido": "0",
                "isAprovado": true,
                "status_do_pagamento": "AGUARDANDO"
            }
        """;
    }

    @Dado("o ID de pedido existente {string}")
    public void oIdDePedidoExistente(String idPedido) {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get(ENDPOINT_PAGAMENTOS + "/{idPedido}/status", idPedido);
    }

    @Dado("o ID de pedido inexistente {string}")
    public void oIdDePedidoInexistente(String idPedido) {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get(ENDPOINT_PAGAMENTOS + "/{idPedido}/status", idPedido);
    }

    @Quando("a atualização do status for solicitada")
    public void aAtualizacaoDoStatusForSolicitada() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(payload)
                .when()
                .post(ENDPOINT_PAGAMENTOS);
    }

    @Quando("o status do pagamento for buscado")
    public void oStatusDoPagamentoForBuscado() {
        // Este método será executado com o response já populado no @Dado
    }

    @Então("a resposta deve ter o status {int} Accepted")
    public void aRespostaDeveTerOStatusAccepted() {
        response.then()
                .statusCode(HttpStatus.ACCEPTED.value());
    }

    @Então("a resposta deve ter o status {int} Bad Request")
    public void aRespostaDeveTerOStatusBadRequest() {
        response.then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Então("a resposta deve ter o status {int} Not Found")
    public void aRespostaDeveTerOStatusNotFound() {
        response.then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Então("o status retornado deve ser {string}")
    public void oStatusRetornadoDeveSer(String esperado) {
        String statusRetornado = response.body().asString();
        assertEquals(esperado, statusRetornado);
    }
}
