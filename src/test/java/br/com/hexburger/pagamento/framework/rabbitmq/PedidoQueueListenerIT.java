package br.com.hexburger.pagamento.framework.rabbitmq;

import br.com.hexburger.pagamento.application.interfacegateway.MercadoPagoGateway;
import br.com.hexburger.pagamento.application.usecase.dto.PagamentoResponse;
import br.com.hexburger.pagamento.dominio.entidade.StatusPagamento;
import br.com.hexburger.pagamento.framework.entidade.EPagamento;
import br.com.hexburger.pagamento.framework.integracao.dto.PagamentoResponseDTO;
import br.com.hexburger.pagamento.framework.repository.PagamentoRepositorioImpl;
import br.com.hexburger.pagamento.framework.repository.PagamentoRepository;
import br.com.hexburger.pagamento.interfaceadapters.events.PedidoCriadoEvent;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;

@SpringBootTest
@Testcontainers
@ActiveProfiles("test")
class PedidoQueueListenerIT {

    @Container
    private static final MongoDBContainer MONGO_DB_CONTAINER = new MongoDBContainer("mongo:4.0.10");

    @Autowired
    private PedidoQueueListener pedidoQueueListener;

    @MockitoBean
    private MercadoPagoGateway mercadoPagoGateway;

    @MockitoBean
    private PagamentoRepositorioImpl pagamentoRepositorio;

    @Autowired
    private PagamentoRepository repository;

    AutoCloseable openMocks;

    @DynamicPropertySource
    static void mongoProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", () -> "mongodb://" + MONGO_DB_CONTAINER.getHost() + ":" + MONGO_DB_CONTAINER.getMappedPort(27017) + "/test");
    }

    @BeforeAll
    static void setupAll(@Autowired PagamentoRepository repository) {

        repository.save(new EPagamento("8573b339-9ee9-450d-ba38-025f105aff5c", "41614a4d-a136-4914-8902-52d2d6306ded", StatusPagamento.AGUARDANDO.name(), BigDecimal.valueOf(100), null, null));

    }

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveReceberMensagem() {

        PedidoCriadoEvent pedidoCriadoEvent = new PedidoCriadoEvent();
        pedidoCriadoEvent.setIdPedido("41614a4d-a136-4914-8902-52d2d6306ded");
        pedidoCriadoEvent.setValorTotal(BigDecimal.valueOf(100));

        PagamentoResponse pagamentoResponse = new PagamentoResponseDTO("meuQrCode", "idExterno");

        doReturn(pagamentoResponse).when(mercadoPagoGateway).gerarPagamento(pedidoCriadoEvent.getIdPedido(), pedidoCriadoEvent.getValorTotal());

        pedidoQueueListener.receiveMessage(pedidoCriadoEvent);

        verify(pagamentoRepositorio, times(1)).persistirPagamento(anyString(), anyString(), anyString(), any(BigDecimal.class), anyString(), anyString());

    }

}
