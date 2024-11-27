package br.com.hexburger.pagamento.application.usecase;

import br.com.hexburger.pagamento.application.exception.ResourceNotFoundException;
import br.com.hexburger.pagamento.application.interfacegateway.PagamentoGateway;

public class BuscarStatusPagamentoPedidoUseCase {

    private final PagamentoGateway pagamentoGateway;

    public BuscarStatusPagamentoPedidoUseCase(PagamentoGateway pagamentoGateway) {
        this.pagamentoGateway = pagamentoGateway;
    }

    public String buscarStatusPagamentoPedido(String idPedido) {
        return pagamentoGateway.buscarStatusPorPedido(idPedido).orElseThrow(() -> new ResourceNotFoundException("Pedido não encontrado"));
    }

}
