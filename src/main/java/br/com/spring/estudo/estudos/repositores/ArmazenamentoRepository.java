package br.com.spring.estudo.estudos.repositores;


import br.com.spring.estudo.estudos.model.ArmazenamentoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArmazenamentoRepository extends JpaRepository<ArmazenamentoModel, Integer  > {

}
