package br.com.hexburger.pagamento.interfaceadapters.dto;

import java.math.BigDecimal;

public class PagamentoDTO {

    private final String status_do_pagamento;

    private final String codigo_pedido;

    private final BigDecimal total_pago;

    private final String in_store_order_id;

    public PagamentoDTO(String status_do_pagamento, String codigo_pedido, BigDecimal total_pago, String in_store_order_id) {
        this.status_do_pagamento = status_do_pagamento;
        this.codigo_pedido = codigo_pedido;
        this.total_pago = total_pago;
        this.in_store_order_id = in_store_order_id;
    }

    public boolean isAprovado() {
        return status_do_pagamento.equalsIgnoreCase("Aprovado");
    }

    public String getIdPedido() {
        return codigo_pedido;
    }

    public BigDecimal getTotalPago() {
        return total_pago;
    }

    public String getIdExternoPagamento() {
        return in_store_order_id;
    }
}
