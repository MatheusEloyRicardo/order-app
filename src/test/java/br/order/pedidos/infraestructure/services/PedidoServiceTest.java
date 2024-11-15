package br.order.pedidos.infraestructure.services;

import br.order.pedidos.domain.usecases.ListarPedidosRealizadosUseCase;
import br.order.pedidos.domain.usecases.RealizarPedidoUseCase;
import br.order.pedidos.infrastructure.services.PedidoService;
import br.orderpedidos.application.dtos.ListarPedidosRealizadosResponse;
import br.orderpedidos.application.dtos.RealizarPedidoRequest;
import br.orderpedidos.application.dtos.RealizarPedidoRequestProdutosInner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Pageable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PedidoServiceTest {

    @Mock
    private ListarPedidosRealizadosUseCase listarPedidosRealizadosUseCase;

    @Mock
    private RealizarPedidoUseCase realizarPedidoUseCase;

    @InjectMocks
    private PedidoService pedidoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListarPedidosRealizados() {
        Pageable pageable = mock(Pageable.class);
        ListarPedidosRealizadosResponse expectedResponse = new ListarPedidosRealizadosResponse();
        when(listarPedidosRealizadosUseCase.executar(pageable)).thenReturn(expectedResponse);

        ListarPedidosRealizadosResponse actualResponse = pedidoService.listarPedidosRealizados(pageable);

        assertEquals(expectedResponse, actualResponse);
        verify(listarPedidosRealizadosUseCase, times(1)).executar(pageable);
    }

    @Test
    void testRealizarPedido() {
        RealizarPedidoRequest pedidoRequest = new RealizarPedidoRequest();
        RealizarPedidoRequestProdutosInner inner = new RealizarPedidoRequestProdutosInner();
        inner.setIdProduto(1L);
        inner.setQuantidade(2L);
        pedidoRequest.addProdutosItem(inner);

        pedidoService.realizarPedido(pedidoRequest);
        verify(realizarPedidoUseCase, times(1)).executar(pedidoRequest);
    }
}
