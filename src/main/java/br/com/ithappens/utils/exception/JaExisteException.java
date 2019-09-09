package br.com.ithappens.utils.exception;

public class JaExisteException extends RuntimeException {

  public JaExisteException(String s) {
    super(s);
  }

  public JaExisteException(String s, Throwable throwable) {
    super(s, throwable);
  }

  public JaExisteException(Throwable throwable) {
    super(throwable);
  }
}
