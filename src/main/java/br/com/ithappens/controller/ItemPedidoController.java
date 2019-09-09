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
  private ItemPedidoService itemPedido;

  @GetMapping
  public ResponseEntity<List<ItemPedidoEstoque>> listarItensDoPedido(){
    List<ItemPedidoEstoque> items = itemPedido.listar();
    return ResponseEntity.status(HttpStatus.OK).body(items);
  }

  @PostMapping
  public ResponseEntity<Void> SalvarItens(@RequestBody ItemPedidoEstoque itemPedidoEstoque){

    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

}
