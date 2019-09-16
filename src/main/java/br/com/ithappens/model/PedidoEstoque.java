package br.com.ithappens.model;

import br.com.ithappens.mapper.typehandler.Status;
import br.com.ithappens.mapper.typehandler.TipoReceita;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
public class PedidoEstoque {
  private Long id;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private Integer quantidadePedidoTotal;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private Integer valorPedidoTotal;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private TipoReceita tipoReceita;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private Status status;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private Cliente cliente;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private Filial filial;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private List<ItemPedidoEstoque> itemPedidoEstoque;
}
