package br.com.spring.estudo.estudos.repositores;


import br.com.spring.estudo.estudos.model.ArmazenamentoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ArmazenamentoRepository extends JpaRepository<ArmazenamentoModel, Integer  > {
    @Query("SELECT u FROM ArmazenamentoModel u ")
    public ArmazenamentoModel buscaTodoDadosBase( ArmazenamentoModel armazenamentoModel);

}
