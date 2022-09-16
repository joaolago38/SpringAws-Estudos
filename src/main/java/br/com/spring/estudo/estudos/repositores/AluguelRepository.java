package br.com.spring.estudo.estudos.repositores;


import br.com.spring.estudo.estudos.model.AluguelModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AluguelRepository extends JpaRepository<AluguelModel,Integer>{

}
