package br.com.ithappens.mapper;

import br.com.ithappens.model.ItemPedidoEstoque;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ItemMapper {

  List<ItemPedidoEstoque> listarTodosItemsDoPedidos();

}
