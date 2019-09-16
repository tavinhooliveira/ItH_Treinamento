package br.com.ithappens.service;

import br.com.ithappens.mapper.ItemMapper;
import br.com.ithappens.mapper.ProdutoMapper;
import br.com.ithappens.model.ItemPedidoEstoque;
import br.com.ithappens.mapper.typehandler.Status;
import br.com.ithappens.model.Produto;
import br.com.ithappens.utils.exception.NotFoundException;
import br.com.ithappens.utils.exception.ParametroInvalidoException;
import ch.qos.logback.core.net.SyslogOutputStream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Service
public class ItemPedidoService {

  @Autowired
  private ItemMapper itemMapper;

  @Autowired
  private ProdutoMapper produtoMapper;


  public List<ItemPedidoEstoque> listar() {
    return itemMapper.listarTodosItemsDoPedidos();
  }

  public ItemPedidoEstoque buscarItemPorId(Long id) {
    ItemPedidoEstoque itemPedido = itemMapper.buscarItemPorId(id);
    if (itemPedido == null) {
      log.error("[ERROR] - Item do pedido não localizado!");
      throw new NotFoundException("Item do pedido não localizado!");
    }
    return itemPedido;
  }

  public void salvarItemsNoPedido(ItemPedidoEstoque itemPedidoEstoque) {
    log.info("Salvando item no Pedido...");
    try {
      if(requestBodyValidation(itemPedidoEstoque)) {
        itemPedidoEstoque.setStatus(Status.ATIVO);
        log.info("Testando o valor do produto: "+itemPedidoEstoque.getProduto());
        Produto produto = produtoMapper.buscarProdutoPorId(itemPedidoEstoque.getProduto().getId());
        itemPedidoEstoque.setValorDoPedido(calculateCost(itemPedidoEstoque.getQuantidadePedido(), produto.getValorProduto()));
        itemMapper.salvaritemDoPedido(itemPedidoEstoque);

      }else{
        throw new ParametroInvalidoException("Parametro invalido ou nulo no request");
      }
    }catch (Exception e){
      log.error("Não foi possivel salvar os itns no pedido"+ e);
    }
  }

  // Calcular o valor sobre a quantidade
  public BigDecimal calculateCost(Integer itemQuantity, BigDecimal itemPrice) {
    BigDecimal itemCost;
    BigDecimal totalCost = BigDecimal.ZERO;
    itemCost = itemPrice.multiply(new BigDecimal(itemQuantity));
    totalCost = totalCost.add(itemCost);
    return totalCost;
  }

  private boolean requestBodyValidation(ItemPedidoEstoque itemPedidoEstoque) {
    if (itemPedidoEstoque.getProduto().getId() == null) {
      log.error("Parametro: {produto} invalido ou nulo no request");
      return false;
    } else if (itemPedidoEstoque.getPedidoEstoque().getId() == null) {
      log.error("Parametro: {pedido} invalido ou nulo no request");
      return false;
    }
    return true;
  }


}
