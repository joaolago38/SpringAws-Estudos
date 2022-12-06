package br.com.spring.estudo.estudos.repositores;


import br.com.spring.estudo.estudos.model.CidadeModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CidadeRepository extends JpaRepository<CidadeModel, Integer  > {

}
