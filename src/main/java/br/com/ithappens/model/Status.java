package br.com.ithappens.model;

public enum Status {

  ATIVO("Ativo"),
  INATIVO("Inativo");

  private String descricao;

  private Status(String descricao) {
    this.descricao = descricao;
  }
  public String getDescricao(){
    return descricao;
  }

}
