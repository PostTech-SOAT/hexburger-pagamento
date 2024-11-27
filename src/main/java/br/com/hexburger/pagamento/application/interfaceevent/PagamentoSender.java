package br.com.hexburger.pagamento.application.interfaceevent;

import br.com.hexburger.pagamento.dominio.entidade.StatusPagamento;

public interface PagamentoSender {

    void send(String idPedido, StatusPagamento statusPagamento);

}
