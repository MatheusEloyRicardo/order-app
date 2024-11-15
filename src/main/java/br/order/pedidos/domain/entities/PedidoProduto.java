package br.order.pedidos.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "tb_pedido_produto", schema = "orders")
public class PedidoProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_pedido_produto")
    @SequenceGenerator(name = "sq_pedido_produto", sequenceName = "orders.sq_pedido_produto", allocationSize = 1)
    @Column(nullable = false)
    @NotNull(message = "O id não pode ser vazio")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pedido", nullable = false)
    private Pedido pedido;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_produto", nullable = false)
    private Produto produto;

    @Column(nullable = false)
    @NotNull(message = "A quantidade não pode ser nula")
    private Integer quantidade;
}
