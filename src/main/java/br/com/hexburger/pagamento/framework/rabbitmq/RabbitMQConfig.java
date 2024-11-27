package br.com.hexburger.pagamento.framework.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue pagamentoQueue() {
        return new Queue("pagamento-queue", true);
    }

    @Bean
    public Queue pedidoQueue() {
        return new Queue("pedido-queue", true);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange("pagamento-exchange");
    }

    @Bean
    public Binding pagamentoBinding(Queue pagamentoQueue, TopicExchange exchange) {
        return BindingBuilder.bind(pagamentoQueue).to(exchange).with("pagamento.#");
    }

}