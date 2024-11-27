package br.com.hexburger.pagamento.application.interfacegateway;

import br.com.hexburger.pagamento.application.usecase.dto.PagamentoResponse;

import java.math.BigDecimal;

public interface MercadoPagoGateway {

    PagamentoResponse gerarPagamento(String idPedido, BigDecimal valorTotal);

}
