package br.com.spring.estudo.estudos.services;

import br.com.spring.estudo.estudos.model.FilmeCategoriaModel;
import br.com.spring.estudo.estudos.repositores.FilmeCategoryRespository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class FilmeCategoriaService {

    final FilmeCategoryRespository filmeCategoryRespository;

    public FilmeCategoriaService(FilmeCategoryRespository filmeCategoryRespository) {
        this.filmeCategoryRespository = filmeCategoryRespository;
    }
    @Transactional
    public FilmeCategoriaModel save(FilmeCategoriaModel filmeCategoriaModel) {
        return filmeCategoryRespository.save(filmeCategoriaModel);
    }

    public Page<FilmeCategoriaModel> findAll(Pageable pageable) {
        return filmeCategoryRespository.findAll(pageable);
    }

    public Optional<FilmeCategoriaModel> findById(Integer filmId) {
        return filmeCategoryRespository.findById(filmId);
    }
    @Transactional
    public void delete(FilmeCategoriaModel filmeCategoriaModel) {
        filmeCategoryRespository.delete(filmeCategoriaModel);
    }
    
}
