package br.com.hexburger.pagamento.framework.repository;

import br.com.hexburger.pagamento.dominio.entidade.StatusPagamento;
import br.com.hexburger.pagamento.framework.entidade.EPagamento;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
@Testcontainers
@ActiveProfiles("test")
class PagamentoRepositorioImplIT {

    @Container
    private static final MongoDBContainer MONGO_DB_CONTAINER = new MongoDBContainer("mongo:4.0.10");

    @Autowired
    private PagamentoRepository repository;

    private PagamentoRepositorioImpl pagamentoRepositorio;

    @DynamicPropertySource
    static void mongoProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", () -> "mongodb://" + MONGO_DB_CONTAINER.getHost() + ":" + MONGO_DB_CONTAINER.getMappedPort(27017) + "/test");
    }

    @BeforeAll
    static void setupAll(@Autowired PagamentoRepository repository) {

        repository.save(new EPagamento("0a99fedc-1a33-46fe-908b-b4f73eb21ab9", "8d09b842-55c3-4bae-a612-acd8ec5100d9", StatusPagamento.AGUARDANDO.name(), BigDecimal.valueOf(100), "qrCode1", "idExterno1"));
        repository.save(new EPagamento("6725615b-3c58-4bca-860f-d148642805b2", "61082148-9f48-4c96-91e9-f3f202036fba", StatusPagamento.APROVADO.name(), BigDecimal.valueOf(200), "qrCode2", "idExterno2"));
        repository.save(new EPagamento("1303e42e-5192-4f82-a97e-35549ce6eed1", "fb906f7e-7e5e-44cd-aadc-8cbb2129afd2", StatusPagamento.RECUSADO.name(), BigDecimal.valueOf(300), "qrCode3", "idExterno3"));

    }

    @BeforeEach
    void setup() {
        pagamentoRepositorio = new PagamentoRepositorioImpl(repository);
    }

    @Test
    void devePersistirPagamento() {
        String idPagamento = UUID.randomUUID().toString();

        pagamentoRepositorio.persistirPagamento(idPagamento,
                UUID.randomUUID().toString(), StatusPagamento.AGUARDANDO.name(),
                BigDecimal.valueOf(50), "qrCode", "idExterno");

        EPagamento pagamentoPersistido = repository.findById(idPagamento).get();

        assertThat(pagamentoPersistido, is(notNullValue()));
        assertThat(pagamentoPersistido.getId(), is(equalTo(idPagamento)));
        assertThat(pagamentoPersistido.getIdPedido(), is(notNullValue()));

    }

    @Test
    void deveAtualizarStatusPagamento() {

        String idPedido = "8d09b842-55c3-4bae-a612-acd8ec5100d9";

        pagamentoRepositorio.atualizarStatusPagamento(idPedido, StatusPagamento.APROVADO);

        EPagamento pagamentoAtualizado = repository.findByIdPedido(idPedido).get();

        assertThat(pagamentoAtualizado, is(notNullValue()));
        assertThat(pagamentoAtualizado.getStatus(), is(equalTo(StatusPagamento.APROVADO.name())));

    }

    @Test
    void deveBuscarStatusPorPedido() {

        assertThat(pagamentoRepositorio.buscarStatusPorPedido("fb906f7e-7e5e-44cd-aadc-8cbb2129afd2").get(),
                is(equalTo(StatusPagamento.RECUSADO.name())));

    }

}