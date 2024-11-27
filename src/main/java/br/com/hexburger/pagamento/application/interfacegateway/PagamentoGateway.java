package br.com.hexburger.pagamento.application.interfacegateway;

import br.com.hexburger.pagamento.dominio.entidade.Pagamento;
import br.com.hexburger.pagamento.dominio.entidade.StatusPagamento;

import java.util.Optional;

public interface PagamentoGateway {

    void persistirPagamento(Pagamento pagamento);

    void atualizarStatusPagamento(String idPedido, StatusPagamento status);

    Optional<String> buscarStatusPorPedido(String idPedido);

}
