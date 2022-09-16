package br.com.spring.estudo.estudos.repositores;


import br.com.spring.estudo.estudos.model.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<ClienteModel, Integer  > {

}
