package br.order.pedidos.adapters.controllers;

import br.order.pedidos.application.ports.service.IPedidoService;
import br.orderpedidos.adapters.controllers.PedidoApi;
import br.orderpedidos.application.dtos.ListarPedidosRealizadosResponse;
import br.orderpedidos.application.dtos.RealizarPedidoRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PedidosController implements PedidoApi {

    private final IPedidoService service;
    private static final Logger logger = LoggerFactory.getLogger(PedidosController.class);

    @Override
    public ResponseEntity<ListarPedidosRealizadosResponse> listarPedidosRealizados(
        @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        logger.info("Iniciando busca de Pedidos");
        return ResponseEntity.ok(service.listarPedidosRealizados(pageable));
    }

    @Override
    public ResponseEntity<Void> realizarPedido(RealizarPedidoRequest realizarPedidoRequest) {
        logger.info("Iniciando o pedido");
        service.realizarPedido(realizarPedidoRequest);
        logger.info("Pedido Criado!");
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
