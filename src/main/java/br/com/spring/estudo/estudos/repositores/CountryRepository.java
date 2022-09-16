package br.com.spring.estudo.estudos.repositores;



import br.com.spring.estudo.estudos.model.PaisModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CountryRepository extends JpaRepository<PaisModel, Integer  > {

}
