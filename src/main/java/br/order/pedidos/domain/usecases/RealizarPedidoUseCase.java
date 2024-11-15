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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Component
public class RealizarPedidoUseCase {

    private final PedidoRepository pedidoRepository;
    private final ProdutoRepository produtoRepository;
    private final PedidoProdutoRepository pedidoProdutoRepository;

    public void executar(RealizarPedidoRequest realizarPedidoRequest) {
        Pedido pedido = PedidoConverter.paraEntidade();
        pedido = pedidoRepository.save(pedido);

        pedido.setTotal(salvaPedidoProduto(realizarPedidoRequest, pedido));
        pedidoRepository.save(pedido);
    }

    private BigDecimal salvaPedidoProduto(RealizarPedidoRequest request, Pedido pedido) {
        BigDecimal valorTotal = BigDecimal.ZERO;

        for (RealizarPedidoRequestProdutosInner item : request.getProdutos()) {
            Produto produto = produtoRepository.findById(item.getIdProduto())
                    .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));

            BigDecimal valorItem = produto.getPreco().multiply(new BigDecimal(item.getQuantidade()));
            valorTotal = valorTotal.add(valorItem);

            PedidoProduto pedidoProduto = PedidoProduto.builder()
                .pedido(pedido)
                .produto(produto)
                .quantidade(item.getQuantidade().intValue())
                .build();

            pedidoProdutoRepository.save(pedidoProduto);
        }

        return valorTotal;
    }
}
