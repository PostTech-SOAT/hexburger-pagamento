package br.com.hexburger.pagamento.interfaceadapters.dto;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class PagamentoDTOTest {

    @Test
    void deveCriarPagamento() {

        PagamentoDTO pagamento = new PagamentoDTO("Aprovado", "123456", BigDecimal.TEN, "123456");

        assertThat(pagamento, is(notNullValue()));
        assertThat(pagamento.isAprovado(), is(true));
        assertThat(pagamento.getIdPedido(), is("123456"));
        assertThat(pagamento.getTotalPago(), is(BigDecimal.TEN));
        assertThat(pagamento.getIdExternoPagamento(), is("123456"));

    }

}
