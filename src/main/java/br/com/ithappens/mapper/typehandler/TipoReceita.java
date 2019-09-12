package br.com.ithappens.mapper.typehandler;

public enum TipoReceita {

  ENTRADA("Entrada"),
  SAIDA("Saida");

  private String descricao;

  private TipoReceita(String descricao) {
    this.descricao = descricao;
  }

  public String getDescricao() {
    return descricao;
  }

}
