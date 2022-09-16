package br.com.spring.estudo.estudos.repositores;

import br.com.spring.estudo.estudos.model.PagamentoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<PagamentoModel, Integer  > {

}
