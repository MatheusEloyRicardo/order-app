package br.order.pedidos.adapters.controllers;

import br.order.pedidos.application.ports.service.IPedidoService;
import br.orderpedidos.application.dtos.ListarPedidosRealizadosResponse;
import br.orderpedidos.application.dtos.RealizarPedidoRequest;
import br.orderpedidos.application.dtos.RealizarPedidoRequestProdutosInner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

class PedidosControllerTest {

    @Mock
    private IPedidoService service;

    @InjectMocks
    private PedidosController controller;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testListarPedidosRealizados() {
        ListarPedidosRealizadosResponse response = new ListarPedidosRealizadosResponse();
        when(service.listarPedidosRealizados(any(Pageable.class))).thenReturn(response);

        ResponseEntity<ListarPedidosRealizadosResponse> responseEntity = controller.listarPedidosRealizados(any(Pageable.class));

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        verify(service, times(1)).listarPedidosRealizados(any());
    }

    @Test
    public void testRealizarPedido() {
        RealizarPedidoRequest pedidoRequest = new RealizarPedidoRequest();
        RealizarPedidoRequestProdutosInner inner = new RealizarPedidoRequestProdutosInner();
        inner.setIdProduto(1L);
        inner.setQuantidade(2L);
        pedidoRequest.addProdutosItem(inner);

        doNothing().when(service).realizarPedido(pedidoRequest);

        ResponseEntity<Void> responseEntity = controller.realizarPedido(pedidoRequest);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        verify(service, times(1)).realizarPedido(pedidoRequest);
    }
}
