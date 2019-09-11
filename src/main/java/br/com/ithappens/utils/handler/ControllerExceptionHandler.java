package br.com.ithappens.utils.handler;

import br.com.ithappens.model.DetalhesErro;
import br.com.ithappens.utils.exception.ErroGenericoException;
import br.com.ithappens.utils.exception.JaExisteException;
import br.com.ithappens.utils.exception.NotFoundException;
import br.com.ithappens.utils.exception.ParametroInvalidoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionHandler {

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<DetalhesErro> handleNotFoundException
      (NotFoundException e, HttpServletRequest request) {

    DetalhesErro erro = new DetalhesErro();
    erro.setStatus(404l);
    erro.setDescription("["+request.getMethod()+"] : " +e.getMessage());
    erro.setTimestamp(System.currentTimeMillis());
    erro.setRoute(request.getRequestURI());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
  }

  @ExceptionHandler(JaExisteException.class)
  public ResponseEntity<DetalhesErro> handleJaExisteException
      (JaExisteException e, HttpServletRequest request) {

    DetalhesErro erro = new DetalhesErro();
    erro.setStatus(400l);
    erro.setDescription("["+request.getMethod()+"] : " +e.getMessage());
    erro.setTimestamp(System.currentTimeMillis());
    erro.setRoute(request.getRequestURI());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
  }

  @ExceptionHandler(ParametroInvalidoException.class)
  public ResponseEntity<DetalhesErro> handleParametroInvalidoException
      (ParametroInvalidoException e, HttpServletRequest request) {

    DetalhesErro erro = new DetalhesErro();
    erro.setStatus(400l);
    erro.setDescription("["+request.getMethod()+"] : " +e.getMessage());
    erro.setTimestamp(System.currentTimeMillis());
    erro.setRoute(request.getRequestURI());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
  }

  @ExceptionHandler(ErroGenericoException.class)
  public ResponseEntity<DetalhesErro> handleErroGenericoException
      (ErroGenericoException e, HttpServletRequest request) {

    DetalhesErro erro = new DetalhesErro();
    erro.setStatus(500l);
    erro.setDescription("["+request.getMethod()+"] : " +e.getMessage());
    erro.setTimestamp(System.currentTimeMillis());
    erro.setRoute(request.getRequestURI());
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);
  }

}
