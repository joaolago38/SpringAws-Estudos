package br.com.spring.estudo.estudos.repositores;


import br.com.spring.estudo.estudos.model.LinguagemModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LinguagemRespository extends JpaRepository<LinguagemModel, Integer  > {


}
