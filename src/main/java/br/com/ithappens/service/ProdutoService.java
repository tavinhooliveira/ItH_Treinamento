package br.com.ithappens.service;

import br.com.ithappens.mapper.ProdutoMapper;
import br.com.ithappens.model.Produto;
import br.com.ithappens.model.Status;
import br.com.ithappens.utils.exception.JaExisteException;
import br.com.ithappens.utils.exception.NotFoundException;
import br.com.ithappens.utils.exception.ParametroInvalidoException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
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
        log.error("O produto: {"+p.getDescricao()+"} já está cadastrado");
        throw new JaExisteException("O produto: {ID:"+p.getId()+" - "+p.getDescricao()+"} já está cadastrado");
      }
      produto.setStatus(Status.ATIVO);
    }else {
      log.error("O paramentro ID incorreto ou nulo!"+produto.getId());
      throw new ParametroInvalidoException("O paramentro ID incorreto ou nulo!");
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
