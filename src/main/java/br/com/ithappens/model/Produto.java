package br.com.ithappens.model;

import br.com.ithappens.mapper.typehandler.Status;
import lombok.Data;

@Data
public class Produto {
  private Long id;
  private String descricao;
  private Integer valorProduto;

  private Status status;
}
