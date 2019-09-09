package br.com.ithappens.model;

import lombok.Data;

import java.util.List;

@Data
public class Produto {
  private Long id;
  private String descricao;
  private Integer valorProduto;
  private String status;

  private List<ItemPedidoEstoque> itemPedidoEstoque;

}
