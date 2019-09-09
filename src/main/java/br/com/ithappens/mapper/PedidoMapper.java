package br.com.ithappens.mapper;

import br.com.ithappens.model.PedidoEstoque;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PedidoMapper {


  List<PedidoEstoque> listarTodosPedidosEstoque();

  PedidoEstoque buscarPedidosEstoquePorId(@Param("id") Long id);

  boolean salvarPedido(@Param("pedidoEstoque") PedidoEstoque pedidoEstoque);
}
