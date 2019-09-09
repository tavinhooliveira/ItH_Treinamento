package br.com.ithappens.service;

import br.com.ithappens.mapper.PedidoMapper;
import br.com.ithappens.model.PedidoEstoque;
import br.com.ithappens.utils.exception.JaExisteException;
import br.com.ithappens.utils.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class PedidoService {

  @Autowired
  private PedidoMapper pedidoMapper;

  public List<PedidoEstoque> listar() {
    return pedidoMapper.listarTodosPedidosEstoque();
  }

  public boolean salvar(PedidoEstoque pedidoEstoque) {
    log.info("Salvando Pedido...");
    if (pedidoEstoque.getId() != null){
      PedidoEstoque p = pedidoMapper.buscarPedidosEstoquePorId(pedidoEstoque.getId());
      if(p != null){
        log.error("[ERROR] - Pedido não localizado!");
        throw new JaExisteException("O Pedido : {"+p.getId()+"} já existe no banco de dados");
      }
    }
    log.debug("Pedido Salvo com sucesso!");
    return pedidoMapper.salvarPedido(pedidoEstoque);
  }

  public PedidoEstoque buscarPedidoId(Long id) {
    PedidoEstoque pedidoEstoque = pedidoMapper.buscarPedidosEstoquePorId(id);
        if(pedidoEstoque == null){
          log.error("[ERROR] - Pedido não localizado!");
          throw new NotFoundException("O pedido não foi localizado!");
        }
    return pedidoEstoque;
  }
}
