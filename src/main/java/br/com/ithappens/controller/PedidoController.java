package br.com.ithappens.controller;

import br.com.ithappens.mapper.FilialMapper;
import br.com.ithappens.model.Filial;
import br.com.ithappens.model.PedidoEstoque;
import br.com.ithappens.service.FilialService;
import br.com.ithappens.service.PedidoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/pedido")
public class PedidoController {

  @Autowired
  private PedidoService pedidoService;

  @Autowired
  private FilialService filialService;

  @Autowired
  private FilialMapper filialMapper;

  public List<Filial> listar() {
    return filialMapper.listarTodasFilias();
  }

  /*Buscar todos*/
  @GetMapping
  public ResponseEntity<List<PedidoEstoque>> listarPedidos() {
    log.info("Buscando todos os pedidos...");
    List<PedidoEstoque> pedidos = pedidoService.listar();
    log.debug("Pedidos retornados!");
    return ResponseEntity.status(HttpStatus.OK).body(pedidos);
  }

  /*Salvar*/
  @PostMapping
  public ResponseEntity<Void> salvar(@RequestBody PedidoEstoque pedidoEstoque) {
    log.info("\n\nPOST\n\n");
    Filial f = filialService.buscarFilialPorId(pedidoEstoque.getFilial().getId());
    pedidoEstoque.setFilial(f);
    pedidoService.salvar(pedidoEstoque);
    log.debug("Pedido salvo com sucesso!");
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  /*Buscar Por ID*/
  @GetMapping(value = "/id")
  public ResponseEntity<PedidoEstoque> buscarId(@PathVariable("id") Long id) {
    log.info("\n\nGET\n\n - Recuperando um pedido por ID: " + id);
    PedidoEstoque pedidoEstoque = pedidoService.buscarPedidoId(id);
    log.debug("Pedido salvo com sucesso!");
    return ResponseEntity.status(HttpStatus.OK).body(pedidoEstoque);
  }


}


