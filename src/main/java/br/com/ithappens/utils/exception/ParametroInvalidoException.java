package br.com.ithappens.utils.exception;

import lombok.Data;

@Data
public class ParametroInvalidoException extends RuntimeException {

  public ParametroInvalidoException(String s) {
    super(s);
  }

  public ParametroInvalidoException(String s, Throwable throwable) {
    super(s, throwable);
  }

}