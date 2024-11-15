package br.order.pedidos.infrastructure.services;

import br.order.pedidos.domain.usecases.ListarPedidosRealizadosUseCase;
import br.order.pedidos.domain.usecases.RealizarPedidoUseCase;
import br.orderpedidos.application.dtos.ListarPedidosRealizadosResponse;
import br.orderpedidos.application.dtos.RealizarPedidoRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import br.order.pedidos.application.ports.service.IPedidoService;

@Service
@RequiredArgsConstructor
public class PedidoService implements IPedidoService {

    private final ListarPedidosRealizadosUseCase listarPedidosRealizadosUseCase;
    private final RealizarPedidoUseCase realizarPedidoUseCase;

    @Override
    public ListarPedidosRealizadosResponse listarPedidosRealizados(Pageable pageable) {
        return listarPedidosRealizadosUseCase.executar(pageable);
    }

    @Override
    public void realizarPedido(RealizarPedidoRequest realizarPedidoRequest) {
        realizarPedidoUseCase.executar(realizarPedidoRequest);
    }
}
