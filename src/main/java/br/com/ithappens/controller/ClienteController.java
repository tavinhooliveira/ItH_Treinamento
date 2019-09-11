package br.com.ithappens.controller;

import br.com.ithappens.model.Cliente;
import br.com.ithappens.service.ClienteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/cliente")
public class ClienteController {

  @Autowired
  private ClienteService clienteService;

  @GetMapping
  public ResponseEntity<List<Cliente>> listarClientes(){
    log.info("\n\nGET\n - Recuperando todos os clientes...");
    List<Cliente> clientes = clienteService.clienteService();
    log.debug("Resultado da pesquisa: "+clientes);
    return ResponseEntity.status(HttpStatus.OK).body(clientes);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<Cliente> buscarClienteID(@PathVariable("id") Long id){
    Cliente cliente = clienteService.buscarClienteID(id);
    return ResponseEntity.status(HttpStatus.OK).body(cliente);
  }

  @PostMapping
  public ResponseEntity<Void> salvarCliente(@RequestBody Cliente cliente) {
    log.info("\n\nPOST\n - Salvando o cliente...");
    clienteService.salvarCliente(cliente);
    log.debug("POST - Cliente salvo com sucesso!" + cliente.getNome());
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @PutMapping(value = "/{id}")
  public ResponseEntity<Void> atualizarCliente(@PathVariable("id") Long id, @RequestBody Cliente cliente) {
    log.info("\n\nPUT\n - Atualizando o cliente..."+id);
    clienteService.atualizarCliente(id, cliente);
    log.debug("PUT - Cliente Atualizado com sucesso!" + cliente.getNome());
    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @DeleteMapping(value = "/{id}/deletar")
  public ResponseEntity<Void> deletarCliente(@PathVariable("id") Long id) {
    log.info("\n\nDELETE\n - Deletando o cliente... "+id);
    clienteService.deletarCliente(id);
    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @PutMapping(value = "/{id}/reativar")
  public ResponseEntity<Void> reativarCliente(@PathVariable("id") Long id) {
    log.info("\n\nPUT\n - Reativando o cliente... "+id);
    clienteService.reativarCliente(id);
    return ResponseEntity.status(HttpStatus.OK).build();
  }



}
