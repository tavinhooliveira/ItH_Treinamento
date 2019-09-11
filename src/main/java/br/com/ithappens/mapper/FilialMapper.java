package br.com.ithappens.mapper;

import br.com.ithappens.model.Filial;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface FilialMapper {

  List<Filial> listarTodasFilias();

  Filial buscarFilialPorId(@Param("id") Long id);

  boolean salvarFilial(@Param("filial") Filial filial);

  void atualizar(@Param("filial") Filial filial);
}
