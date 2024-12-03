package br.com.hexburger.pagamento.framework.api;

import br.com.hexburger.pagamento.dominio.entidade.StatusPagamento;
import br.com.hexburger.pagamento.framework.entidade.EPagamento;
import br.com.hexburger.pagamento.framework.rabbitmq.PagamentoSenderService;
import br.com.hexburger.pagamento.framework.repository.PagamentoRepositorioImpl;
import br.com.hexburger.pagamento.framework.repository.PagamentoRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@ActiveProfiles("test")
class PagamentoAPITest {

    private MockMvc mockMvc;

    @Container
    private static final MongoDBContainer MONGO_DB_CONTAINER = new MongoDBContainer("mongo:4.0.10");

    @Autowired
    private PagamentoRepositorioImpl repositorio;

    @Mock
    private PagamentoSenderService pagamentoSenderService;

    @DynamicPropertySource
    static void mongoProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", () -> "mongodb://" + MONGO_DB_CONTAINER.getHost() + ":" + MONGO_DB_CONTAINER.getMappedPort(27017) + "/test");
    }

    @BeforeAll
    static void setupAll(@Autowired PagamentoRepository repository) {

        repository.save(new EPagamento("f9aae3f8-4063-4fcd-8dab-b5cf3c6e4ed8", "b001927d-f946-4bcf-8f4a-5b73694a2f7e", StatusPagamento.AGUARDANDO.name(), BigDecimal.valueOf(100), "qrCode1", "idExterno1"));

    }

    @BeforeEach
    void setUp() {

        PagamentoAPI pagamentoAPI = new PagamentoAPI(repositorio, pagamentoSenderService);
        mockMvc = MockMvcBuilders.standaloneSetup(pagamentoAPI)
                .addFilter((request, response, chain) -> {
                    response.setCharacterEncoding("UTF-8");
                    chain.doFilter(request, response);
                }, "/*")
                .build();

    }

    @Test
    void deveAtualizarStatusDoPagamentoAprovado() throws Exception {

        mockMvc.perform(post("/v1/pagamento")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"status_do_pagamento\": \"APROVADO\", \"codigo_pedido\": \"877e03ba-eef1-4c49-9dc5-d3cc480426c8\"," +
                                " \"total_pago\": 80.0, \"in_store_order_id\": \"c60f7d3f-996f-4709-a507-5f5068c0fee4\"}"))
                .andExpect(status().isAccepted());

    }

    @Test
    void deveAtualizarStatusDoPagamentoRecusado() throws Exception {

        mockMvc.perform(post("/v1/pagamento")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"status_do_pagamento\": \"RECUSADO\", \"codigo_pedido\": \"877e03ba-eef1-4c49-9dc5-d3cc480426c8\"," +
                                " \"total_pago\": 80.0, \"in_store_order_id\": \"c60f7d3f-996f-4709-a507-5f5068c0fee4\"}"))
                .andExpect(status().isAccepted());

    }

    @Test
    void deveBuscarStatusPagamentoPedido() throws Exception {

        MvcResult mvcResult = mockMvc.perform(get("/v1/pagamento/{idPedido}/status", "b001927d-f946-4bcf-8f4a-5b73694a2f7e")
                .contentType(MediaType.APPLICATION_JSON)).andReturn();

        assertEquals(mvcResult.getResponse().getContentAsString(), StatusPagamento.AGUARDANDO.name());

    }

    @Test
    void deveLancarExcecaoAoBuscarStatusPagamentoInexistente() throws Exception {

        mockMvc.perform(get("/v1/pagamento/{idPedido}/status", "0")
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());

    }

}
