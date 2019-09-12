package br.com.ithappens.model;

import br.com.ithappens.mapper.typehandler.Status;
import br.com.ithappens.mapper.typehandler.TipoReceita;
import lombok.Data;

import java.util.List;

@Data
public class PedidoEstoque {
  private Long id;
  private Integer quantidadePedidoTotal;
  private Integer valorPedidoTotal;

  private TipoReceita tipoReceita;
  private Status status;

  private Cliente cliente;
  private Filial filial;
  private List<ItemPedidoEstoque> itemPedidoEstoque;
}
