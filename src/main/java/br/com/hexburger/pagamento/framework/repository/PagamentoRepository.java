package br.com.hexburger.pagamento.framework.repository;

import br.com.hexburger.pagamento.framework.entidade.EPagamento;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PagamentoRepository extends MongoRepository<EPagamento, String> {

    @Query(value = "{'idPedido' : ?0}")
    @Update(value = "{'$set' : {'status' : ?1}}")
    void atualizarStatusPagamento(String idPedido, String status);

    Optional<String> findStatusByIdPedido(String idPedido);

}
