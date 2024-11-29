package br.com.hexburger.pagamento.interfaceadapters.dto;

import br.com.hexburger.pagamento.framework.integracao.dto.PagamentoRequestDTO;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class PagamentoRequestDTOTest {

    @Test
    void deveCriarPagamentoRequest() {

        PagamentoRequestDTO pagamento = new PagamentoRequestDTO("d1278178-74ce-4b66-b9d7-d323ff780f85", BigDecimal.TEN);

        assertThat(pagamento, is(notNullValue()));
        assertThat(pagamento.getExternal_reference(), is("d1278178-74ce-4b66-b9d7-d323ff780f85"));
        assertThat(pagamento.getTotal_amount(), is(BigDecimal.TEN));

    }

}
