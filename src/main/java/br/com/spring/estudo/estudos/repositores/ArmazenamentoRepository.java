package br.com.spring.estudo.estudos.repositores;


import br.com.spring.estudo.estudos.model.ArmazenamentoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArmazenamentoRepository extends JpaRepository<ArmazenamentoModel, Integer  > {

}
