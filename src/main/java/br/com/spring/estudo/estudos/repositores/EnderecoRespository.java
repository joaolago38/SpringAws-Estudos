package br.com.spring.estudo.estudos.repositores;


import br.com.spring.estudo.estudos.model.EnderecoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRespository extends JpaRepository<EnderecoModel, Integer  > {

}
