package br.com.ithappens.service;

import br.com.ithappens.mapper.ItemMapper;
import br.com.ithappens.model.ItemPedidoEstoque;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemPedidoService {

    @Autowired
    private ItemMapper itemMapper;


  public List<ItemPedidoEstoque> listar() {
    return itemMapper.listarTodosItemsDoPedidos();
  }
}
