package br.com.hexburger.pagamento.interfaceadapters.controller;

import br.com.hexburger.pagamento.application.interfaceevent.PagamentoSender;
import br.com.hexburger.pagamento.application.interfacegateway.MercadoPagoGateway;
import br.com.hexburger.pagamento.application.usecase.AtualizarStatusPagamentoPedidoUseCase;
import br.com.hexburger.pagamento.application.usecase.BuscarStatusPagamentoPedidoUseCase;
import br.com.hexburger.pagamento.application.usecase.GerarPagamentoUseCase;
import br.com.hexburger.pagamento.dominio.entidade.Pagamento;
import br.com.hexburger.pagamento.framework.repository.PagamentoRepositorioImpl;
import br.com.hexburger.pagamento.interfaceadapters.dto.PagamentoDTO;
import br.com.hexburger.pagamento.interfaceadapters.events.PedidoCriadoEvent;
import br.com.hexburger.pagamento.interfaceadapters.gateway.PagamentoGatewayMongo;
import br.com.hexburger.pagamento.interfaceadapters.repositorioadaptador.PagamentoRepositorioAdaptador;

public class PagamentoController {

    public void gerarPagamento(PedidoCriadoEvent event, PagamentoRepositorioAdaptador repositorio, MercadoPagoGateway mercadoPagoGateway) {
        GerarPagamentoUseCase useCase = new GerarPagamentoUseCase(new PagamentoGatewayMongo(repositorio), mercadoPagoGateway);
        useCase.gerarPagamento(eventToDomain(event));
    }

    private Pagamento eventToDomain(PedidoCriadoEvent event) {
        return new Pagamento(event.getIdPedido(), event.getValorTotal());
    }

    public void atualizarStatusDoPagamento(PagamentoDTO pagamentoDTO, PagamentoRepositorioAdaptador repositorio, PagamentoSender sender) {
        AtualizarStatusPagamentoPedidoUseCase useCase = new AtualizarStatusPagamentoPedidoUseCase(new PagamentoGatewayMongo(repositorio), sender);
        useCase.atualizarStatusPagamento(pagamentoDTO.getIdPedido(), pagamentoDTO.isAprovado());
    }

    public String buscarStatusPagamentoPedido(String idPedido, PagamentoRepositorioImpl pagamentoRepositorio) {
        BuscarStatusPagamentoPedidoUseCase useCase = new BuscarStatusPagamentoPedidoUseCase(new PagamentoGatewayMongo(pagamentoRepositorio));
        return useCase.buscarStatusPagamentoPedido(idPedido);
    }
}
