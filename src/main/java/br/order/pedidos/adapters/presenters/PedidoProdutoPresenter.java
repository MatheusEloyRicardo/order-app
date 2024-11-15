package br.order.pedidos.adapters.presenters;

import br.order.pedidos.adapters.converters.PedidoConverter;
import br.order.pedidos.domain.entities.Pedido;
import br.orderpedidos.application.dtos.ListarPedidosRealizadosResponse;
import org.springframework.data.domain.Page;

public class PedidoProdutoPresenter {

    public static ListarPedidosRealizadosResponse paraResponse(Page<Pedido> pedidos) {
        return ListarPedidosRealizadosResponse.builder()
                .content(pedidos.map(PedidoConverter::paraDTO).getContent())
                .pageable(pedidos.getPageable())
                .totalElements((int) pedidos.getTotalElements())
                .totalPages(pedidos.getTotalPages())
                .last(pedidos.isLast())
                .first(pedidos.isFirst())
                .empty(pedidos.isEmpty())
                .build();
    }
}
