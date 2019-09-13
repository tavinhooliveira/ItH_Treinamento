package br.com.ithappens.controller;

import br.com.ithappens.model.ItemPedidoEstoque;
import br.com.ithappens.service.ItemPedidoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/item")
public class ItemPedidoController {

  @Autowired
  private ItemPedidoService itemPedidoService;

  @GetMapping
  public ResponseEntity<List<ItemPedidoEstoque>> listarItensDoPedido(){
    log.info("\n\nGET\n -buscando todos os items");
    List<ItemPedidoEstoque> items = itemPedidoService.listar();
    return ResponseEntity.status(HttpStatus.OK).body(items);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<ItemPedidoEstoque> buscarItemPorId(@PathVariable Long id){
    log.info("\n\nGET\n -Localiando pedido por ID...");
    ItemPedidoEstoque itemPedido = itemPedidoService.buscarItemPorId(id);
    log.debug("Item do Pedido Localizado:"+ itemPedido.getId());
    return ResponseEntity.status(HttpStatus.OK).body(itemPedido);
  }

  /** TO-DO **/
  //Buscar Itens de um pedido


  @PostMapping
  public ResponseEntity<Void> salvarItens(@RequestBody ItemPedidoEstoque itemPedidoEstoque){
    log.info("\n\nGET\n - Adicionando Items ao pedido");
    itemPedidoService.salvarItemsNoPedido(itemPedidoEstoque);
    log.debug("Items adicionado ao pedido: "+itemPedidoEstoque.getPedidoEstoque().getId());
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

}
