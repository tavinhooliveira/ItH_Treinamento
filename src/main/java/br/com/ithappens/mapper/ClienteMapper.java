package br.com.ithappens.mapper;

import br.com.ithappens.model.Cliente;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ClienteMapper {

  List<Cliente> listarTodosClientes();

  Cliente buscarClienteID(@Param("id") Long id);

  boolean salvarClienteMapper(@Param("cliente") Cliente cliente);

  boolean atualizarCliente(@Param("cliente") Cliente cliente);

}
