package br.com.hexburger.pagamento.interfaceadapters.events;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class PedidoCriadoEvent implements Serializable {

    private String idPedido;

    private BigDecimal valorTotal;

}
