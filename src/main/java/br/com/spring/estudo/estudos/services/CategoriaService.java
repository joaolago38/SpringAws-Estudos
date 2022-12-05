package br.com.spring.estudo.estudos.services;

import br.com.spring.estudo.estudos.model.CategoriaModel;
import br.com.spring.estudo.estudos.repositores.CategoriaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class CategoriaService {

    final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Transactional
    public CategoriaModel save(CategoriaModel categoriaModel) {
        return categoriaRepository.save(categoriaModel);
    }

    public Page<CategoriaModel> findAll(Pageable pageable) {
        return categoriaRepository.findAll(pageable);
    }

    public Optional<CategoriaModel> findById(Integer categoryId) {
        return categoriaRepository.findById(categoryId);
    }
    @Transactional
    public void delete(CategoriaModel categoriaModel) {
        categoriaRepository.delete(categoriaModel);
    }
}
