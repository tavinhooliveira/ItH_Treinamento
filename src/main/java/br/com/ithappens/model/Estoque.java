package br.com.ithappens.model;

import lombok.Data;

@Data
public class Estoque {
  private Long id;
  private Integer produto_id;
  private Integer filial_id;
  private Integer quantidade;
}

