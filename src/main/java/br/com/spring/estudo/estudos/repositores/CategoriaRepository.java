package br.com.spring.estudo.estudos.repositores;




import br.com.spring.estudo.estudos.model.CategoriaModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<CategoriaModel, Integer  > {

}
