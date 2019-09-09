package br.com.ithappens.utils.exception;

public class ErroGenericoException extends RuntimeException {

  public ErroGenericoException(String s) {
    super(s);
  }

  public ErroGenericoException(String s, Throwable throwable) {
    super(s, throwable);
  }

  public ErroGenericoException(Throwable throwable) {
    super(throwable);
  }

}
