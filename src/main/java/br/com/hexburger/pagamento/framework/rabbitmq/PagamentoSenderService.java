package br.com.hexburger.pagamento.framework.rabbitmq;

import br.com.hexburger.pagamento.application.interfaceevent.PagamentoSender;
import br.com.hexburger.pagamento.dominio.entidade.StatusPagamento;
import br.com.hexburger.pagamento.interfaceadapters.events.PagamentoConcluidoEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PagamentoSenderService implements PagamentoSender {

    private final RabbitTemplate rabbitTemplate;

    public void send(String idPedido, StatusPagamento statusPagamento) {
        rabbitTemplate.convertAndSend("pagamento-exchange", "pagamento.concluido", new PagamentoConcluidoEvent(idPedido, statusPagamento));
    }

}
