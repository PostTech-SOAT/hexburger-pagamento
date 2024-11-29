package br.com.hexburger.pagamento.framework.repository;

import br.com.hexburger.pagamento.dominio.entidade.StatusPagamento;
import br.com.hexburger.pagamento.framework.entidade.EPagamento;
import br.com.hexburger.pagamento.interfaceadapters.repositorioadaptador.PagamentoRepositorioAdaptador;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

@Component
@AllArgsConstructor
public class PagamentoRepositorioImpl implements PagamentoRepositorioAdaptador {

    private final PagamentoRepository repository;

    @Override
    public void persistirPagamento(String id, String idPedido, String status, BigDecimal valorTotal, String qrCode, String idExterno) {
        repository.save(new EPagamento(id, idPedido, status, valorTotal, qrCode, idExterno));
    }

    @Override
    public void atualizarStatusPagamento(String idPedido, StatusPagamento status) {
        repository.findById(idPedido).ifPresent(pagamento -> {
            pagamento.setStatus(status.name());
            repository.save(pagamento);
        });
    }

    @Override
    public Optional<String> buscarStatusPorPedido(String idPedido) {
        return repository.findStatusByIdPedido(idPedido)
                .map(EPagamento::getStatus);
    }

}
