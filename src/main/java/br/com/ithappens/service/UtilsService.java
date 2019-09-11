package br.com.ithappens.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UtilsService {

  boolean isAtivo(String status){
    if(status == "Ativo"){
      return true;
    }
    return false;
  }

  boolean verificarParamsID(Long id){
    if(id == null){ return false;}
    return true;
  }

}
