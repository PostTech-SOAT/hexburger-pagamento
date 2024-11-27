package br.com.hexburger.pagamento.framework.integracao;

import br.com.hexburger.pagamento.application.interfacegateway.MercadoPagoGateway;
import br.com.hexburger.pagamento.application.usecase.dto.PagamentoResponse;
import br.com.hexburger.pagamento.framework.integracao.dto.PagamentoRequestDTO;
import br.com.hexburger.pagamento.framework.integracao.dto.PagamentoResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Component
public class MercadoPagoAPI implements MercadoPagoGateway {

    public PagamentoResponse gerarPagamento(String idPedido, BigDecimal valorTotal) {
        ResponseEntity<PagamentoResponseDTO> responseEntity = new RestTemplate().postForEntity("https://hexburger.app.n8n.cloud/webhook/gerar-qr-code-pagamento",
                new PagamentoRequestDTO(idPedido, valorTotal), PagamentoResponseDTO.class);
        return responseEntity.getBody();
    }

}