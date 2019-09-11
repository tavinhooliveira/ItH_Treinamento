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

  /*Buscar todos as filias*/
  @GetMapping
  public ResponseEntity<List<Filial>> listarFilial() {
    log.info("\n\nGET\n - Retornando a lista de Filiais...");
    List<Filial> filiais = filialService.listar();
    log.debug("GET - Lista de Filiais Filiais retornada com sucesso! ");
    return ResponseEntity.status(HttpStatus.OK).body(filiais);
  }

  /* Buscar uma filial Por ID */
  @GetMapping(value = "/{id}")
  public ResponseEntity<Filial> buscarId(@PathVariable("id") Long id) {
    log.info("\n\nGET\n\n - Recuperando uma filial por ID");
    Filial filial = filialService.buscarFilialPorId(id);
    log.debug("GET - Filial encontrada: "+filial);
    return ResponseEntity.status(HttpStatus.OK).body(filial);
  }

  /* Salvar filial */
  @PostMapping
  public ResponseEntity<Void> salvar(@RequestBody Filial filial) {
    log.info("\n\nPOST\n\n");
    filialService.salvar(filial);
    log.debug("POST - Filial salva com sucesso" + filial.getNome());
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  /* Deletar filial por ID */
  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
    filialService.deleteFilial(id);
    log.debug("DELETE - Filia deletada com sucesso!");
    return ResponseEntity.noContent().build();
  }

  /*Filia atualizada com sucesso! */
  @PutMapping(value = "/{id}")
  public ResponseEntity<Void> Atualizar(@PathVariable("id") Long id, @RequestBody Filial filial) {
    filial.setId(id);
    filialService.atualizarFilial(filial);
    return ResponseEntity.status(HttpStatus.OK).build();
  }

}