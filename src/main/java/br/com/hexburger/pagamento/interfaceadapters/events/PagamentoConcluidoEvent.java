package br.com.hexburger.pagamento.interfaceadapters.events;

import br.com.hexburger.pagamento.dominio.entidade.StatusPagamento;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PagamentoConcluidoEvent {

    private String idPedido;

    private StatusPagamento status;

}
