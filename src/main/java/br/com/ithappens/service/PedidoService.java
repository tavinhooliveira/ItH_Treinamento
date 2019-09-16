package br.com.ithappens.service;

import br.com.ithappens.mapper.PedidoMapper;
import br.com.ithappens.model.Cliente;
import br.com.ithappens.model.Filial;
import br.com.ithappens.model.PedidoEstoque;
import br.com.ithappens.mapper.typehandler.Status;
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

  @Autowired
  private FilialService filialService;

  @Autowired
  private ClienteService clienteService;

  public List<PedidoEstoque> listar() {
    return pedidoMapper.listarTodosPedidosEstoque();
  }

  public boolean salvar(PedidoEstoque pedidoEstoque) {
    log.info("Salvando Pedido...");
    if (pedidoEstoque.getId() != null){
      if(pedidoEstoque.getFilial() == null){
        log.error("[ERROR] - Filião não localizada não localizado!");
        throw new NotFoundException("A filial é obrigatória para esse operação!");
      }
      Filial f = filialService.buscarFilialPorId(pedidoEstoque.getFilial().getId());
      Cliente c = clienteService.buscarClienteID(pedidoEstoque.getCliente().getId());
      pedidoEstoque.setFilial(f);
      pedidoEstoque.setCliente(c);
      pedidoEstoque.setStatus(Status.ATIVO);
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

  public boolean calcelarPedido(Long id) {
    PedidoEstoque pedidoEstoque = buscarPedidoId(id);
    pedidoEstoque.setId(id);
    pedidoEstoque.setTipoReceita(pedidoEstoque.getTipoReceita());
    pedidoEstoque.setQuantidadePedidoTotal(pedidoEstoque.getQuantidadePedidoTotal());
    pedidoEstoque.setValorPedidoTotal(pedidoEstoque.getValorPedidoTotal());
    pedidoEstoque.setFilial(pedidoEstoque.getFilial());
    pedidoEstoque.setCliente(pedidoEstoque.getCliente());
    pedidoEstoque.setStatus(Status.INATIVO);
    return pedidoMapper.cancelarPedidoMapper(pedidoEstoque);
  }
}
