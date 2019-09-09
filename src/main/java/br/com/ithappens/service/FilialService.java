package br.com.ithappens.service;

import br.com.ithappens.mapper.FilialMapper;
import br.com.ithappens.model.Filial;
import br.com.ithappens.utils.exception.JaExisteException;
import br.com.ithappens.utils.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class FilialService {

  @Autowired
  private FilialMapper filialMapper;

  public List<Filial> listar() {
    return filialMapper.listarTodasFilias();
  }

  public Filial buscarFilialPorId(Long id) {
    log.info("Pesquisando Filial por id...");
    Filial filial = filialMapper.buscarFilialPorId(id);
    if (filial == null) {
      throw new NotFoundException("A filial pesquisada não foi encontrada");
    }
    log.debug("Filial retornada.."+ filial.getNome());
    return filial;
  }

  public boolean salvar(Filial filial) {
    log.info("Salvando filial...");
    if(filial.getId() != null){
      Filial f = filialMapper.buscarFilialPorId(filial.getId());
      if(f != null){
        throw new JaExisteException("A filião : {"+f.getNome()+"} já existe no banco de dados");
      }
   }
    log.debug("Filial salva com sucesso!");
    return filialMapper.salvarFilial(filial);
  }

  public boolean deleteFilial(Long id){
    Filial filial = buscarFilialPorId(id);
    return filialMapper.deleteFilial(filial.getId());
  }

  public void atualizarFilial(Filial filial) {
    buscarFilialPorId(filial.getId());
    filialMapper.atualizar(filial);
  }

}
