package br.com.ithappens.model;

import lombok.Data;

import java.util.List;

@Data
public class Filial {
  private Long id;
  private String nome;
  private String cnpj;

  private Status status;

}
