package br.com.ithappens.mapper;

import br.com.ithappens.model.ItemPedidoEstoque;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ItemMapper {

  List<ItemPedidoEstoque> listarTodosItemsDoPedidos();

  ItemPedidoEstoque buscarItemPorId(@Param("id")Long id);

  boolean salvaritemDoPedido(@Param("itempedidoestoque") ItemPedidoEstoque itemPedidoEstoque);

}
