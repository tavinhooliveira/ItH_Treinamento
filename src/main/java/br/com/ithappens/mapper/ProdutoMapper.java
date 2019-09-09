package br.com.ithappens.mapper;

import br.com.ithappens.model.Produto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ProdutoMapper {

  List<Produto> listarTodosProdutos();

  Produto buscarProdutoPorId(@Param("id") Long id);

  boolean salvarProduto(@Param("produto") Produto produto);

  boolean deleteProduto(@Param("id") Long id);

  void atualizarProduto(@Param("produto") Produto produto);

}
