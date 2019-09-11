package br.com.ithappens.model;

import lombok.Data;

@Data
public class ItemPedidoEstoque {
    private Long id;
    private Integer quantidadePedido;
    private Integer valorDoPedido;

    //Relacionamento para a tabela de produtos
    private Produto produto_id;
    private PedidoEstoque pedidoEstoque;

    private Status status;

}
