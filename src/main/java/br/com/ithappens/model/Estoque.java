package br.com.ithappens.model;

import br.com.ithappens.mapper.typehandler.Status;
import lombok.Data;

@Data
public class Estoque {
  private Long id;
  private Integer produto;
  private Integer filial;
  private Integer quantidade;

  private Status status;
}

