package br.com.hexburger.pagamento.interfaceadapters.events;

import br.com.hexburger.pagamento.dominio.entidade.StatusPagamento;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class PagamentoConcluidoEvent implements Serializable {

    private String idPedido;

    private StatusPagamento status;

}
