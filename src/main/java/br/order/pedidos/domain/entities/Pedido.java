package br.order.pedidos.domain.entities;

import br.order.pedidos.domain.enums.PedidoStatusEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "tb_pedido", schema = "orders")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_pedido")
    @SequenceGenerator(name = "sq_pedido", sequenceName = "orders.sq_pedido", allocationSize = 1)
    @Column(nullable = false)
    @NotNull(message = "O id não pode ser vazio")
    private Long id;

    @Column(precision = 10, scale = 2, nullable = false)
    @NotNull(message = "O valor total não pode ser nulo")
    private BigDecimal total;

    @Enumerated(EnumType.ORDINAL)
    private PedidoStatusEnum status;

    @Column(name = "data_criacao", nullable = false)
    @NotNull(message = "A data de criacao não pode ser vazia")
    private LocalDate dataCriacao;

    @Column(name = "hora_criacao", nullable = false)
    @NotNull(message = "A hora de criacao não pode ser vazia")
    private LocalTime horaCriacao;
}
