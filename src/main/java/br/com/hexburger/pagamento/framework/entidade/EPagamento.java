package br.com.hexburger.pagamento.framework.entidade;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Pagamento")
public class EPagamento {

    @Id
    private String id;

    private String idPedido;

    private String status;

    private BigDecimal valorTotal;

    private String qrCode;

    private String idExterno;

}
