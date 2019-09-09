package br.com.ithappens.service;

import br.com.ithappens.mapper.ProdutoMapper;
import br.com.ithappens.model.Produto;
import br.com.ithappens.utils.exception.JaExisteException;
import br.com.ithappens.utils.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

  @Autowired
  private ProdutoMapper produtoMapper;

  public List<Produto> listar(){
    return produtoMapper.listarTodosProdutos();
  }

  public Produto buscarPodutoPorId(Long id) {
    Produto produto = produtoMapper.buscarProdutoPorId(id);
    if (produto == null){throw new NotFoundException("Produto nao encontrado!"); }
    return produto;
  }

  public boolean salvarProduto(Produto produto) {
    if(produto.getId() != null){
      Produto p = produtoMapper.buscarProdutoPorId(produto.getId());
      if(p != null){
        throw new JaExisteException("O produto: {"+p.getDescricao()+"} já está cadastrado");
      }
    }
    return produtoMapper.salvarProduto(produto);
  }

  public boolean deleteProduto(Long id){
    Produto produto = buscarPodutoPorId(id);
    return produtoMapper.deleteProduto(produto.getId());
  }

  public void atualizarProduto(Produto produto) {
    buscarPodutoPorId(produto.getId());
    produtoMapper.atualizarProduto(produto);
  }

}
