package br.order.pedidos.application.ports.service;

import br.orderpedidos.application.dtos.ListarPedidosRealizadosResponse;
import br.orderpedidos.application.dtos.RealizarPedidoRequest;
import org.springframework.data.domain.Pageable;

public interface IPedidoService {

    ListarPedidosRealizadosResponse listarPedidosRealizados(Pageable pageable);

    void realizarPedido(RealizarPedidoRequest realizarPedidoRequest);
}
