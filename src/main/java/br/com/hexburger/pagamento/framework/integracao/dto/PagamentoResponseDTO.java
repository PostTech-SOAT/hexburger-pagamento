package br.com.hexburger.pagamento.framework.integracao.dto;

import br.com.hexburger.pagamento.application.usecase.dto.PagamentoResponse;

public class PagamentoResponseDTO implements PagamentoResponse {

    private String qr_data;

    private String in_store_order_id;

    public PagamentoResponseDTO(String qr_data, String in_store_order_id) {
        this.qr_data = qr_data;
        this.in_store_order_id = in_store_order_id;
    }

    @Override
    public String getQrCode() {
        return qr_data;
    }

    @Override
    public String getIdExterno() {
        return in_store_order_id;
    }
}
