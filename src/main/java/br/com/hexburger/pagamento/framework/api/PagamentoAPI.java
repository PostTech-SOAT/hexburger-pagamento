package br.com.hexburger.pagamento.framework.api;

import br.com.hexburger.pagamento.application.exception.ResourceNotFoundException;
import br.com.hexburger.pagamento.framework.rabbitmq.PagamentoSenderService;
import br.com.hexburger.pagamento.framework.repository.PagamentoRepositorioImpl;
import br.com.hexburger.pagamento.interfaceadapters.controller.PagamentoController;
import br.com.hexburger.pagamento.interfaceadapters.dto.PagamentoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/pagamento")
public class PagamentoAPI {

    private final PagamentoRepositorioImpl pagamentoRepositorio;

    private final PagamentoSenderService pagamentoSenderService;

    @PostMapping
    public ResponseEntity<Object> atualizarStatusDoPagamento(@RequestBody PagamentoDTO pagamentoDTO) {
        PagamentoController controller = new PagamentoController();
        controller.atualizarStatusDoPagamento(pagamentoDTO, pagamentoRepositorio, pagamentoSenderService);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/{idPedido}/status")
    @Operation(summary = "Buscar status de pagamento do pedido")
    public ResponseEntity<String> buscarStatusPagamentoPedido(@PathVariable @Parameter(description = "ID do pedido", required = true, schema = @Schema(type = "string", example = "877e03ba-eef1-4c49-9dc5-d3cc480426c8")) String idPedido) {
        PagamentoController controller = new PagamentoController();
        try {
            return ResponseEntity.ok(controller.buscarStatusPagamentoPedido(idPedido, pagamentoRepositorio));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
