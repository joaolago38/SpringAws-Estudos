package br.com.spring.estudo.estudos.services;

import br.com.spring.estudo.estudos.model.FilmeModel;
import br.com.spring.estudo.estudos.repositores.FilmesRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class FilmeService {
     final FilmesRepository filmesRepository;
     public FilmeService(FilmesRepository filmesRepository) {
                this.filmesRepository = filmesRepository;
    }
    @Transactional
    public FilmeModel save(FilmeModel filmeModel) {
        return filmesRepository.save(filmeModel);
    }

    public Page<FilmeModel> findAll(Pageable pageable) {
        return filmesRepository.findAll(pageable);
    }

    public Optional<FilmeModel> findById(Integer filmId) {
        return filmesRepository.findById(filmId);
    }
    @Transactional
    public void delete(FilmeModel filmeModel) {
        filmesRepository.delete(filmeModel);
    }
}
