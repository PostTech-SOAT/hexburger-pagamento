package br.com.hexburger.pagamento.dominio.entidade;

import java.math.BigDecimal;
import java.util.UUID;

import static br.com.hexburger.pagamento.dominio.entidade.StatusPagamento.AGUARDANDO;

public class Pagamento {

    private String id;

    private String idPedido;

    private StatusPagamento status;

    private BigDecimal valorTotal;

    private String qrCode;

    private String idExterno;

    public Pagamento(String idPedido, BigDecimal valorTotal) {
        id = UUID.randomUUID().toString();
        this.idPedido = idPedido;
        this.status = AGUARDANDO;
        this.valorTotal = valorTotal;
    }

    public Pagamento(String id, String idPedido, StatusPagamento status, BigDecimal valorTotal, String qrCode, String idExterno) {
        this.id = id;
        this.idPedido = idPedido;
        this.status = status;
        this.valorTotal = valorTotal;
        this.qrCode = qrCode;
        this.idExterno = idExterno;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(String idPedido) {
        this.idPedido = idPedido;
    }

    public StatusPagamento getStatus() {
        return status;
    }

    public void setStatus(StatusPagamento status) {
        this.status = status;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public String getIdExterno() {
        return idExterno;
    }

    public void setIdExterno(String idExterno) {
        this.idExterno = idExterno;
    }

    public void setInformacoesPagamento(String qrCode, String idExterno) {
        this.qrCode = qrCode;
        this.idExterno = idExterno;
    }

}
