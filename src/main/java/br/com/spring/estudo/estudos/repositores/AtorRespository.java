package br.com.spring.estudo.estudos.repositores;


import br.com.spring.estudo.estudos.model.AtorModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AtorRespository extends JpaRepository<AtorModel, Integer  > {

}
