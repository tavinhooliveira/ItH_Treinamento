package br.com.ithappens.controller;

import br.com.ithappens.model.Produto;
import br.com.ithappens.service.ProdutoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/produto")
@Slf4j
public class ProdutoController {

  @Autowired
  private ProdutoService produtoService;

  @GetMapping
  public ResponseEntity<List<Produto>> listarProdutos() {
    log.info("\n\nGET\n\n - Buscando todos os produtos...");
    List<Produto> produtos = produtoService.listar();
    log.info("GET - Lista de Produtos retornada com Sucesso! ");
    return ResponseEntity.status(HttpStatus.OK).body(produtos);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<Produto> buscarPorId(@PathVariable("id") Long id) {
    log.info("\n\nGET\n\n - Recuperando um produto por id");
    Produto produto = produtoService.buscarPodutoPorId(id);
    return ResponseEntity.status(HttpStatus.OK).body(produto);
  }

  @PostMapping
  public ResponseEntity<Void> salvar(@RequestBody Produto produto) {
    log.debug("\n\nPOST\n\n");
    produtoService.salvarProduto(produto);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @PostMapping(value = "/lote")
  public ResponseEntity<Void> SalvarEmLote(@RequestBody List<Produto> produto) {
    produto.forEach(p -> {
      produtoService.salvarProduto(p);
    });
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
    return ResponseEntity.created(uri).build();
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
    produtoService.deleteProduto(id);
    return ResponseEntity.noContent().build();
  }

  @PutMapping(value = "/{id}")
  public ResponseEntity<Void> Atualizar(@PathVariable("id") Long id, @RequestBody Produto produto) {
    produto.setId(id);
    produtoService.atualizarProduto(produto);
    return ResponseEntity.status(HttpStatus.OK).build();
  }
}
