package br.com.hexburger.pagamento.dominio.entidade;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

class PagamentoTest {

    @Test
    void deveCriarPagamento() {
        Pagamento pagamento = new Pagamento(UUID.randomUUID().toString(), BigDecimal.TEN);

        assertThat(pagamento, is(notNullValue()));
        assertThat(pagamento.getId(), is(notNullValue()));
        assertThat(pagamento.getIdPedido(), is(notNullValue()));
        assertThat(pagamento.getStatus(), is(StatusPagamento.AGUARDANDO));
        assertThat(pagamento.getValorTotal(), is(BigDecimal.TEN));
        assertThat(pagamento.getQrCode(), is(nullValue()));
        assertThat(pagamento.getIdExterno(), is(nullValue()));

    }

    @Test
    void deveSetarInformacoesPagamento() {
        Pagamento pagamento = new Pagamento(UUID.randomUUID().toString(), BigDecimal.TEN);

        pagamento.setInformacoesPagamento("qrCode", "idExterno");

        assertThat(pagamento.getQrCode(), is("qrCode"));
        assertThat(pagamento.getIdExterno(), is("idExterno"));
    }

}
