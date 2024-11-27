package br.com.hexburger.pagamento.interfaceadapters.events;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PedidoCriadoEvent {

    private String idPedido;

    private BigDecimal valorTotal;

}
