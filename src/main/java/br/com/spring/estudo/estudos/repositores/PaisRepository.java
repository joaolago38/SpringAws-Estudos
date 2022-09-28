package br.com.spring.estudo.estudos.repositores;

import br.com.spring.estudo.estudos.model.PaisModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaisRepository extends JpaRepository<PaisModel, Integer  > {

}
