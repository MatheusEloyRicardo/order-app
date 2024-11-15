package br.order.pedidos.domain.usecases;

import br.order.pedidos.application.ports.repository.PedidoRepository;
import br.order.pedidos.domain.entities.Pedido;
import br.order.pedidos.domain.enums.PedidoStatusEnum;
import br.orderpedidos.application.dtos.ListarPedidosRealizadosResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class ListarPedidosRealizadosUseCaseTest {

    @Mock
    private PedidoRepository repository;

    @InjectMocks
    private ListarPedidosRealizadosUseCase useCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExecutar() {
        Pageable pageable = PageRequest.of(0, 10);

        Pedido pedido1 = Pedido.builder()
                .id(1L)
                .total(new BigDecimal("150.50"))
                .status(PedidoStatusEnum.PENDENTE)
                .dataCriacao(LocalDate.now())
                .horaCriacao(LocalTime.now())
                .build();

        Page<Pedido> pedidoPage = new PageImpl<>(List.of(pedido1), pageable, 1);

        when(repository.findAll(pageable)).thenReturn(pedidoPage);

        ListarPedidosRealizadosResponse response = useCase.executar(pageable);

        assertNotNull(response);
        verify(repository, times(1)).findAll(pageable);
    }
}
