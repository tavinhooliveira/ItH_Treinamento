package br.com.ithappens.model;

import br.com.ithappens.mapper.typehandler.Status;
import lombok.Data;

@Data
public class Filial {
  private Long id;
  private String nome;
  private String cnpj;

  private Status status;

}
