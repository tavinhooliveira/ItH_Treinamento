package br.com.ithappens.model;

import lombok.Data;

@Data
public class Cliente {

  private Long id;
  private String nome;
  private String documentoDeIdentificacao;

  private Status status;

}
