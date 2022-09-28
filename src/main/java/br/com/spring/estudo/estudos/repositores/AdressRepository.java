package br.com.spring.estudo.estudos.repositores;


import br.com.spring.estudo.estudos.model.EnderecoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdressRepository extends JpaRepository<EnderecoModel, Integer > {

}
