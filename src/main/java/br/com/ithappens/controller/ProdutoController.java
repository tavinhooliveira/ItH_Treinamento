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
    log.info("\n\nGET\n - Buscando todos os produtos...");
    List<Produto> produtos = produtoService.listar();
    log.debug("GET - Lista de Produtos retornada com Sucesso! ");
    return ResponseEntity.status(HttpStatus.OK).body(produtos);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<Produto> buscarPorId(@PathVariable("id") Long id) {
    log.info("\n\nGET\n - Recuperando um produto por id");
    Produto produto = produtoService.buscarPodutoPorId(id);
    log.debug("GET - produto retornado, codigo: " + produto.getId());
    return ResponseEntity.status(HttpStatus.OK).body(produto);
  }

  @PostMapping
  public ResponseEntity<Void> salvar(@RequestBody Produto produto) {
    log.info("\n\nPOST\n - Salvando o produto: ID:"+produto.getId()+"Descição:"+produto.getDescricao());
    produtoService.salvarProduto(produto);
    log.debug("Produto salvo com sucesso!");
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @PostMapping(value = "/lote")
  public ResponseEntity<Void> SalvarEmLote(@RequestBody List<Produto> produto) {
    log.info("Salvando produtos em lote...");
    produto.forEach(p -> {
      produtoService.salvarProduto(p);
      log.debug("Produto salvo: " + p.getId()+"-"+p.getDescricao());
    });
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
    return ResponseEntity.created(uri).build();
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
    produtoService.deleteProduto(id);
    log.debug("DELETE - Produto deletado com sucesso");
    return ResponseEntity.noContent().build();
  }

  @PutMapping(value = "/{id}")
  public ResponseEntity<Void> Atualizar(@PathVariable("id") Long id, @RequestBody Produto produto) {
    produto.setId(id);
    produtoService.atualizarProduto(produto);
    return ResponseEntity.status(HttpStatus.OK).build();
  }

}