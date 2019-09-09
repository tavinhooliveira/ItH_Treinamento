package br.com.ithappens.utils.exception;

import lombok.Data;

@Data
public class NotFoundException extends RuntimeException {

  public NotFoundException(String s) {
    super(s);
  }

  public NotFoundException(String s, Throwable throwable) {
    super(s, throwable);
  }

}