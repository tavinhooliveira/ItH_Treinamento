package br.com.ithappens.model;

import lombok.Data;

import java.util.List;

@Data
public class PedidoEstoque {
  private Long id;
  private String tipoReceita;
  private Integer quantidadePedidoTotal;
  private Integer valorPedidoTotal;

  private Cliente cliente;
  private Filial filial;
  private List<ItemPedidoEstoque> itemPedidoEstoque;
  private Status status;
}
