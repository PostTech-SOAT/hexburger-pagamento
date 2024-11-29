package br.com.hexburger.pagamento.framework.integracao;

import br.com.hexburger.pagamento.application.usecase.dto.PagamentoResponse;
import br.com.hexburger.pagamento.framework.integracao.dto.PagamentoResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

class MercadoPagoAPITest {

    @Mock
    private MercadoPagoAPI mercadoPagoAPI;

    AutoCloseable openMocks;

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveGerarPagamento() {

        PagamentoResponse pagamentoResponse = new PagamentoResponseDTO("qrCodeGerado", "idExterno");

        when(mercadoPagoAPI.gerarPagamento("6af8b967-122b-4971-b41e-c090bf6c3761", BigDecimal.valueOf(50)))
                .thenReturn(pagamentoResponse);

        PagamentoResponse pagamentoResponseMock = mercadoPagoAPI.gerarPagamento("6af8b967-122b-4971-b41e-c090bf6c3761", BigDecimal.valueOf(50));

        assertThat(pagamentoResponseMock, is(notNullValue()));
        assertThat(pagamentoResponseMock.getIdExterno(), is(equalTo(pagamentoResponse.getIdExterno())));
        assertThat(pagamentoResponseMock.getQrCode(), is(pagamentoResponse.getQrCode()));

    }

}
