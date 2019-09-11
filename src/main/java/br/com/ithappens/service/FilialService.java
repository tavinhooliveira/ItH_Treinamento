package br.com.ithappens.service;

import br.com.ithappens.mapper.FilialMapper;
import br.com.ithappens.model.Filial;
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
public class FilialService {

  @Autowired
  private FilialMapper filialMapper;

  @Autowired
  private UtilsService utilsService;

  public List<Filial> listar() {
    return filialMapper.listarTodasFilias();
  }

  public Filial buscarFilialPorId(Long id) {
    log.info("Pesquisando Filial por id: "+ id);
    Filial filial = filialMapper.buscarFilialPorId(id);
    if (filial == null) {
      log.error("A filial pesquisada não foi encontrada");
      throw new NotFoundException("A filial pesquisada não foi encontrada");
    }
    log.debug("Filial retornada: "+ filial.getNome());
    return filial;
  }

  public boolean salvar(Filial filial) {
    if(filial.getId() != null){
      Filial f = filialMapper.buscarFilialPorId(filial.getId());
      if(f != null){
        throw new JaExisteException("A filial : {"+f.getNome()+"} já existe no banco de dados");
      }
   }else {
      throw new ParametroInvalidoException("O paramentro ID incorreto ou nulo!");
    }
    log.debug("Filial salva com sucesso!"+ filial.getNome());
    filial.setStatus(Status.ATIVO);
    return filialMapper.salvarFilial(filial);
  }

  public void deleteFilial(Long id){
    Filial filial = buscarFilialPorId(id);
    if (!utilsService.isAtivo(filial.getStatus().getDescricao())){
        throw new ErroGenericoException("A Filial já está Inativa");
    }
    filial.setId(id);
    filial.setStatus(Status.INATIVO);
    filial.setNome(filial.getNome());
    filial.setCnpj(filial.getCnpj());
    log.debug("Deletando a filial: "+ filial.getNome());
    filialMapper.atualizar(filial);
  }

  public void reativarFilial(Long id){
    Filial filial = buscarFilialPorId(id);
    if (utilsService.isAtivo(filial.getStatus().getDescricao())){
      throw new ErroGenericoException("A Filial já está Ativa");
    }
    filial.setId(id);
    filial.setStatus(Status.ATIVO);
    filial.setNome(filial.getNome());
    filial.setCnpj(filial.getCnpj());
    log.debug("Deletando a filial: "+ filial.getNome());
    filialMapper.atualizar(filial);
  }

  public void atualizarFilial(Filial filial) {
    Filial f =  buscarFilialPorId(filial.getId());
    filial.setStatus(f.getStatus());
    log.debug("Atualizando a filial: "+ f.getNome());
    filialMapper.atualizar(filial);
  }

}
