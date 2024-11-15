package br.order.pedidos.domain.usecases;

import br.order.pedidos.adapters.presenters.PedidoProdutoPresenter;
import br.order.pedidos.application.ports.repository.PedidoRepository;
import br.order.pedidos.domain.entities.Pedido;
import br.orderpedidos.application.dtos.ListarPedidosRealizadosResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ListarPedidosRealizadosUseCase {

    private final PedidoRepository repository;

    public ListarPedidosRealizadosResponse executar(Pageable pageable) {
        Page<Pedido> pedidoProdutos = repository.findAll(pageable);
        return PedidoProdutoPresenter.paraResponse(pedidoProdutos);
    }
}
