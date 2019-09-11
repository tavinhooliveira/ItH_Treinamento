package br.com.ithappens.service;

import br.com.ithappens.mapper.ClienteMapper;
import br.com.ithappens.model.Cliente;
import br.com.ithappens.model.Status;
import br.com.ithappens.utils.exception.ErroGenericoException;
import br.com.ithappens.utils.exception.JaExisteException;
import br.com.ithappens.utils.exception.NotFoundException;
import br.com.ithappens.utils.exception.ParametroInvalidoException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ClienteService {

  @Autowired
  private ClienteMapper clienteMapper;

  @Autowired
  private UtilsService utilsService;

  private static final String CLIENTE_SALVO_COM_SUCESSO = "Cliente salvo com sucesso!";

  private static final String CLIENTE_DELETADO_COM_SUCESSO = "Cliente deletado com sucesso!";

  /*Buscar todos os clientes*/
  public List<Cliente> clienteService() {
    return clienteMapper.listarTodosClientes();
  }

  /*Buscar cliente por ID*/
  public Cliente buscarClienteID(Long id) {
    log.info("Pesquisando cliente por id: "+ id);
    Cliente cliente = clienteMapper.buscarClienteID(id);
    if (cliente == null) {
      log.error("Cliente Não encontrado!");
      throw new NotFoundException("O cliente pesquisado não foi encontrado!");
    }
    log.debug("Cliente retornada: "+ cliente.getNome());
    return cliente;
  }

  /*Salvar*/
  public boolean salvarCliente(Cliente cliente) {
    if(!utilsService.verificarParamsID(cliente.getId())){ throw new ParametroInvalidoException("Parametro invalido ou nulo no request");
    }
    if(cliente.getId() != null){
      Cliente c = clienteMapper.buscarClienteID(cliente.getId());
      if(c != null){
        throw new JaExisteException("O cliente {codigo:"+c.getId()+" - Nome:"+c.getNome()+"} já existe no banco de dados");
      }
    }
    log.debug(CLIENTE_SALVO_COM_SUCESSO + cliente.getNome());
    cliente.setStatus(Status.ATIVO);
    return clienteMapper.salvarClienteMapper(cliente);
  }

  /*Atualizar*/
  public boolean atualizarCliente(Long id, Cliente cliente) {
    log.debug(CLIENTE_SALVO_COM_SUCESSO + cliente.getNome());
    Cliente c = buscarClienteID(id);
    buscarClienteID(id);
    cliente.setId(id);
    cliente.setStatus(c.getStatus());
    return clienteMapper.atualizarCliente(cliente);
  }

  /*Deletar(Desativar o status do cliente)*/
  public boolean deletarCliente(Long id) {
    Cliente cliente = buscarClienteID(id);
    if(!utilsService.isAtivo(cliente.getStatus().getDescricao())){
      throw new ErroGenericoException("O cliente já está Inativo");
    }
    cliente.setId(id);
    cliente.setNome(cliente.getNome());
    cliente.setDocumentoDeIdentificacao(cliente.getDocumentoDeIdentificacao());
    cliente.setStatus(Status.INATIVO);
    log.debug(CLIENTE_DELETADO_COM_SUCESSO + cliente.getNome());
    return clienteMapper.atualizarCliente(cliente);
  }

  /*Reativar o satus do cliente*/
  public boolean reativarCliente(Long id) {
    Cliente cliente = buscarClienteID(id);
    if(utilsService.isAtivo(cliente.getStatus().getDescricao())){
        throw new ErroGenericoException("O cliente já está Ativo");
    }
    cliente.setId(id);
    cliente.setNome(cliente.getNome());
    cliente.setDocumentoDeIdentificacao(cliente.getDocumentoDeIdentificacao());
    cliente.setStatus(Status.ATIVO);
    log.debug(CLIENTE_SALVO_COM_SUCESSO + cliente.getNome());
    return clienteMapper.atualizarCliente(cliente);
  }

}
