package br.com.hexburger.pagamento.framework.rabbitmq;

import br.com.hexburger.pagamento.application.interfacegateway.MercadoPagoGateway;
import br.com.hexburger.pagamento.interfaceadapters.controller.PagamentoController;
import br.com.hexburger.pagamento.interfaceadapters.events.PedidoCriadoEvent;
import br.com.hexburger.pagamento.interfaceadapters.repositorioadaptador.PagamentoRepositorioAdaptador;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PedidoQueueListener {

    private final MercadoPagoGateway mercadoPagoGateway;

    private final PagamentoRepositorioAdaptador repositorio;

    @RabbitListener(queues = "pedido-queue")
    public void receiveMessage(PedidoCriadoEvent pedidoCriadoEvent) {

        PagamentoController controller = new PagamentoController();
        controller.gerarPagamento(pedidoCriadoEvent, repositorio, mercadoPagoGateway);

    }

}
