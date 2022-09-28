package br.com.spring.estudo.estudos.services;

import br.com.spring.estudo.estudos.model.CategoriaModel;
import br.com.spring.estudo.estudos.repositores.CategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class CategoriaService {

    final CategoryRepository categoryRepository;

    public CategoriaService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    public CategoriaModel save(CategoriaModel categoriaModel) {
        return categoryRepository.save(categoriaModel);
    }

    public Page<CategoriaModel> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    public Optional<CategoriaModel> findById(Integer categoryId) {
        return categoryRepository.findById(categoryId);
    }
    @Transactional
    public void delete(CategoriaModel categoriaModel) {
        categoryRepository.delete(categoriaModel);
    }
}
