package br.com.spring.estudo.estudos.repositores;

import br.com.spring.estudo.estudos.model.FilmeModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmesRepository extends JpaRepository<FilmeModel, Integer  > {

}
