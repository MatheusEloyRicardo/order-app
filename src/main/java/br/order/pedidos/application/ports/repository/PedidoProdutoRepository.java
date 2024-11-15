package br.order.pedidos.application.ports.repository;

import br.order.pedidos.domain.entities.PedidoProduto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PedidoProdutoRepository extends JpaRepository<PedidoProduto, Long>, JpaSpecificationExecutor<PedidoProduto> {

}
