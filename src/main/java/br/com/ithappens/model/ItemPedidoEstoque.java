package br.com.ithappens.model;

import br.com.ithappens.mapper.typehandler.Status;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ItemPedidoEstoque {
    private Long id;
    private Integer quantidadePedido;
    private BigDecimal valorDoPedido;

    //Relacionamento para a tabela de produtos
    private Produto produto;
    private PedidoEstoque pedidoEstoque;

    private Status status;

}
