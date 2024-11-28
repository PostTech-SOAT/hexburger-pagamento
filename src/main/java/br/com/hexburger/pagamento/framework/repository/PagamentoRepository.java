package br.com.hexburger.pagamento.framework.repository;

import br.com.hexburger.pagamento.framework.entidade.EPagamento;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PagamentoRepository extends MongoRepository<EPagamento, String> {

    Optional<String> findStatusByIdPedido(String idPedido);

}
