package br.com.hexburger.pagamento.application.usecase;

import br.com.hexburger.pagamento.application.interfacegateway.MercadoPagoGateway;
import br.com.hexburger.pagamento.application.interfacegateway.PagamentoGateway;
import br.com.hexburger.pagamento.application.usecase.dto.PagamentoResponse;
import br.com.hexburger.pagamento.dominio.entidade.Pagamento;

public class GerarPagamentoUseCase {

    private final PagamentoGateway pagamentoGateway;
    private final MercadoPagoGateway mercadoPagoGateway;

    public GerarPagamentoUseCase(PagamentoGateway pagamentoGateway, MercadoPagoGateway mercadoPagoGateway) {
        this.pagamentoGateway = pagamentoGateway;
        this.mercadoPagoGateway = mercadoPagoGateway;
    }

    public void gerarPagamento(Pagamento pagamento) {
        PagamentoResponse pagamentoResponse = mercadoPagoGateway.gerarPagamento(pagamento.getId(), pagamento.getValorTotal());
        pagamento.setInformacoesPagamento(pagamentoResponse.getQrCode(), pagamentoResponse.getIdExterno());
        pagamentoGateway.persistirPagamento(pagamento);
    }

}
