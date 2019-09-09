package br.com.ithappens.controller;

import br.com.ithappens.model.Filial;
import br.com.ithappens.service.FilialService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/filial")
@Slf4j
public class FilialController {

  @Autowired
  private FilialService filialService;

  //BuscarTodos
  @GetMapping
  public ResponseEntity<List<Filial>> listarFilial() {
    log.info("\n\nGET\n\n - Retornando uma lista de Filiais");
    List<Filial> filiais = filialService.listar();
    log.info("GET - Lista de Filiais Filiais retornada com sucesso! ");
    return ResponseEntity.status(HttpStatus.OK).body(filiais);
  }

  //BuscarFilialPorID
  @GetMapping(value = "/{id}")
  public ResponseEntity<Filial> buscarId(@PathVariable("id") Long id) {
    log.info("\n\nGET\n\n - Recuperando uma filial por ID");
    Filial filial = filialService.buscarFilialPorId(id);
    return ResponseEntity.status(HttpStatus.OK).body(filial);
  }

  //Salvar
  @PostMapping
  public ResponseEntity<Void> salvar(@RequestBody Filial filial) {
    log.info("\n\nPOST\n\n");
    filialService.salvar(filial);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  //Deletar Filial
  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
    filialService.deleteFilial(id);
    return ResponseEntity.noContent().build();
  }

  @PutMapping(value = "/{id}")
  public ResponseEntity<Void> Atualizar(@PathVariable("id") Long id, @RequestBody Filial filial) {
    filial.setId(id);
    filialService.atualizarFilial(filial);
    return ResponseEntity.status(HttpStatus.OK).build();
  }

}