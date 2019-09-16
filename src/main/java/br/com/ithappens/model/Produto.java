package br.com.ithappens.model;

import br.com.ithappens.mapper.typehandler.Status;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class Produto {
  private Long id;
  private String descricao;
  private BigDecimal valorProduto;

  private Status status;
}
