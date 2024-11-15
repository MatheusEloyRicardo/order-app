package br.order.pedidos.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import java.math.BigDecimal;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "tb_produto", schema = "orders")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_produto")
    @SequenceGenerator(name = "sq_produto", sequenceName = "orders.sq_produto", allocationSize = 1)
    @Column(nullable = false)
    @NotNull(message = "O id não pode ser vazio")
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "O nome não pode ser vazio")
    private String nome;

    @Column(precision = 10, scale = 2, nullable = false)
    @NotNull(message = "O preco não pode ser nulo")
    private BigDecimal preco;
}
