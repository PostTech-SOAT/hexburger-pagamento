package br.com.hexburger.pagamento.dominio.entidade;

import java.math.BigDecimal;
import java.util.UUID;

import static br.com.hexburger.pagamento.dominio.entidade.StatusPagamento.AGUARDANDO;

public class Pagamento {

    private final String id;

    private final String idPedido;

    private final StatusPagamento status;

    private final BigDecimal valorTotal;

    private String qrCode;

    private String idExterno;

    public Pagamento(String idPedido, BigDecimal valorTotal) {
        id = UUID.randomUUID().toString();
        this.idPedido = idPedido;
        this.status = AGUARDANDO;
        this.valorTotal = valorTotal;
    }

    public String getId() {
        return id;
    }

    public String getIdPedido() {
        return idPedido;
    }

    public StatusPagamento getStatus() {
        return status;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public String getQrCode() {
        return qrCode;
    }

    public String getIdExterno() {
        return idExterno;
    }

    public void setInformacoesPagamento(String qrCode, String idExterno) {
        this.qrCode = qrCode;
        this.idExterno = idExterno;
    }

}
