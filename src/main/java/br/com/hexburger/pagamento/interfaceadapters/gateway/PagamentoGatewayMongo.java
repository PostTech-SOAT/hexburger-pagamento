package br.com.hexburger.pagamento.interfaceadapters.gateway;

import br.com.hexburger.pagamento.application.interfacegateway.PagamentoGateway;
import br.com.hexburger.pagamento.dominio.entidade.Pagamento;
import br.com.hexburger.pagamento.dominio.entidade.StatusPagamento;
import br.com.hexburger.pagamento.interfaceadapters.repositorioadaptador.PagamentoRepositorioAdaptador;

import java.util.Optional;

public class PagamentoGatewayMongo implements PagamentoGateway {

    private final PagamentoRepositorioAdaptador repository;

    public PagamentoGatewayMongo(PagamentoRepositorioAdaptador repository) {
        this.repository = repository;
    }

    @Override
    public void persistirPagamento(Pagamento pagamento) {
        repository.persistirPagamento(pagamento.getId(), pagamento.getIdPedido(), pagamento.getStatus().name(), pagamento.getValorTotal(), pagamento.getQrCode(), pagamento.getIdExterno());
    }

    @Override
    public void atualizarStatusPagamento(String idPedido, StatusPagamento status) {
        repository.atualizarStatusPagamento(idPedido, status);
    }

    @Override
    public Optional<String> buscarStatusPorPedido(String idPedido) {
        return repository.buscarStatusPorPedido(idPedido);
    }

}
