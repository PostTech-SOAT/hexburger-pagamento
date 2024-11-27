package br.com.hexburger.pagamento.framework.integracao.dto;

import java.math.BigDecimal;

public class PagamentoRequestDTO {

    private String external_reference;

    private BigDecimal total_amount;

    public PagamentoRequestDTO(String external_reference, BigDecimal total_amount) {
        this.external_reference = external_reference;
        this.total_amount = total_amount;
    }

    public String getExternal_reference() {
        return external_reference;
    }

    public BigDecimal getTotal_amount() {
        return total_amount;
    }

    public void setExternal_reference(String external_reference) {
        this.external_reference = external_reference;
    }

    public void setTotal_amount(BigDecimal total_amount) {
        this.total_amount = total_amount;
    }
}
