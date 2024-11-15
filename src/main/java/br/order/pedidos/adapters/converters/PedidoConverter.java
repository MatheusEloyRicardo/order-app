package br.order.pedidos.adapters.converters;

import br.order.core.utils.DateTimeFormatterUtil;
import br.order.pedidos.domain.entities.Pedido;
import br.order.pedidos.domain.enums.PedidoStatusEnum;
import br.orderpedidos.application.dtos.PedidoDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public class PedidoConverter {

    public static PedidoDTO paraDTO(Pedido pedido) {
        if (pedido == null) {
            return null;
        }

        return PedidoDTO.builder()
                .id(pedido.getId())
                .dataPedido(pedido.getDataCriacao())
                .horaPedido(DateTimeFormatterUtil.converteHoraMinutoEmString(pedido.getHoraCriacao()))
                .status(pedido.getStatus().getDescricao())
                .valorTotal(pedido.getTotal())
                .build();
    }

    public static Pedido paraEntidade() {
        return Pedido.builder()
                .dataCriacao(LocalDate.now())
                .horaCriacao(LocalTime.now())
                .status(PedidoStatusEnum.INICIADO)
                .total(BigDecimal.ZERO)
                .build();
    }
}
