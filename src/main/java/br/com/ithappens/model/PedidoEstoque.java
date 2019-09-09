package br.com.ithappens.model;

import lombok.Data;

import java.util.List;

@Data
public class PedidoEstoque {
  private Long id;
  private String tipoReceita;
  private Integer quantidadePedidoTotal;
  private Integer valorPedidoTotal;
  private Boolean pedidoAtivo;

  private Filial filial;
  private List<ItemPedidoEstoque> itemPedidoEstoque;
}
