package br.order.pedidos.domain.enums;

public enum PedidoStatusEnum {
    INICIADO(0, "Iniciado"),
    PENDENTE(1, "Pendente"),
    ENVIADO(2, "Enviado"),
    ENTREGUE(3, "Entregue");

    private final int codigo;
    private final String descricao;

    PedidoStatusEnum(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static PedidoStatusEnum fromCodigo(int codigo) {
        for (PedidoStatusEnum status : values()) {
            if (status.getCodigo() == codigo) {
                return status;
            }
        }
        throw new IllegalArgumentException("Código inválido: " + codigo);
    }
}
