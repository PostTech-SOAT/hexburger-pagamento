package br.com.hexburger.pagamento.application.usecase;


import br.com.hexburger.pagamento.application.interfaceevent.PagamentoSender;
import br.com.hexburger.pagamento.application.interfacegateway.PagamentoGateway;
import br.com.hexburger.pagamento.dominio.entidade.StatusPagamento;

import static br.com.hexburger.pagamento.dominio.entidade.StatusPagamento.APROVADO;
import static br.com.hexburger.pagamento.dominio.entidade.StatusPagamento.RECUSADO;

public class AtualizarStatusPagamentoPedidoUseCase {

    private final PagamentoGateway pagamentoGateway;

    private final PagamentoSender pagamentoSender;

    public AtualizarStatusPagamentoPedidoUseCase(PagamentoGateway pagamentoGateway, PagamentoSender pagamentoSender) {
        this.pagamentoGateway = pagamentoGateway;
        this.pagamentoSender = pagamentoSender;
    }

    public void atualizarStatusPagamento(String idPedido, boolean pagamentoAprovado) {

        StatusPagamento status = pagamentoAprovado ? APROVADO : RECUSADO;

        pagamentoGateway.atualizarStatusPagamento(idPedido, status);
        pagamentoSender.send(idPedido, status);

    }

}
