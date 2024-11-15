package br.order.pedidos.domain.usecases;

import br.order.pedidos.adapters.converters.PedidoConverter;
import br.order.pedidos.application.ports.repository.PedidoProdutoRepository;
import br.order.pedidos.application.ports.repository.PedidoRepository;
import br.order.pedidos.application.ports.repository.ProdutoRepository;
import br.order.pedidos.domain.entities.Pedido;
import br.order.pedidos.domain.entities.PedidoProduto;
import br.order.pedidos.domain.entities.Produto;
import br.orderpedidos.application.dtos.RealizarPedidoRequest;
import br.orderpedidos.application.dtos.RealizarPedidoRequestProdutosInner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

class RealizarPedidoUseCaseTest {

    @Mock
    private PedidoRepository pedidoRepository;

    @Mock
    private ProdutoRepository produtoRepository;

    @Mock
    private PedidoProdutoRepository pedidoProdutoRepository;

    @InjectMocks
    private RealizarPedidoUseCase realizarPedidoUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExecutar() {
        Pedido pedidoMock = new Pedido();
        pedidoMock.setId(1L);

        Produto produtoMock = new Produto();
        produtoMock.setId(1L);
        produtoMock.setPreco(new BigDecimal("50.00"));

        RealizarPedidoRequestProdutosInner produtoRequest = new RealizarPedidoRequestProdutosInner();
        produtoRequest.setIdProduto(1L);
        produtoRequest.setQuantidade(2L);

        RealizarPedidoRequest realizarPedidoRequest = new RealizarPedidoRequest();
        realizarPedidoRequest.setProdutos(List.of(produtoRequest));

        try (MockedStatic<PedidoConverter> mockedConverter = mockStatic(PedidoConverter.class)) {
            mockedConverter.when(PedidoConverter::paraEntidade).thenReturn(pedidoMock);

            when(produtoRepository.findById(eq(1L))).thenReturn(Optional.of(produtoMock));
            when(pedidoRepository.save(any(Pedido.class))).thenReturn(pedidoMock);

            realizarPedidoUseCase.executar(realizarPedidoRequest);

            verify(pedidoRepository, times(2)).save(any(Pedido.class));
            verify(produtoRepository, times(1)).findById(1L);
            verify(pedidoProdutoRepository, times(1)).save(any(PedidoProduto.class));
        }
    }

    @Test
    void testProdutoNaoEncontrado() {
        RealizarPedidoRequestProdutosInner produtoRequest = new RealizarPedidoRequestProdutosInner();
        produtoRequest.setIdProduto(99L);
        produtoRequest.setQuantidade(2L);

        RealizarPedidoRequest realizarPedidoRequest = new RealizarPedidoRequest();
        realizarPedidoRequest.setProdutos(List.of(produtoRequest));

        try (MockedStatic<PedidoConverter> mockedConverter = mockStatic(PedidoConverter.class)) {
            mockedConverter.when(PedidoConverter::paraEntidade).thenReturn(new Pedido());
            when(produtoRepository.findById(99L)).thenReturn(Optional.empty());

            try {
                realizarPedidoUseCase.executar(realizarPedidoRequest);
            } catch (IllegalArgumentException e) {
                assertEquals("Produto não encontrado", e.getMessage());
            }

            verify(produtoRepository, times(1)).findById(99L);
            verifyNoInteractions(pedidoProdutoRepository);
        }
    }

}
