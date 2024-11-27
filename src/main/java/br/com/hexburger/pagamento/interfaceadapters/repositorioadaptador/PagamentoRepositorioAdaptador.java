package br.com.hexburger.pagamento.interfaceadapters.repositorioadaptador;

import br.com.hexburger.pagamento.dominio.entidade.StatusPagamento;

import java.math.BigDecimal;
import java.util.Optional;

public interface PagamentoRepositorioAdaptador {

    void persistirPagamento(String id, String idPedido, String status, BigDecimal valorTotal, String qrCode, String idExterno);

    void atualizarStatusPagamento(String idPedido, StatusPagamento status);

    Optional<String> buscarStatusPorPedido(String id);

}
