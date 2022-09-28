package br.com.spring.estudo.estudos.services;

import br.com.spring.estudo.estudos.model.InventarioModel;
import br.com.spring.estudo.estudos.repositores.InventarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class InventarioService {
 final InventarioRepository inventarioRepository;

    public InventarioService(InventarioRepository inventarioRepository) {
        this.inventarioRepository = inventarioRepository;
    }

    @Transactional
    public InventarioModel save(InventarioModel inventarioModel) {
        return inventarioRepository.save(inventarioModel);
    }

    public Page<InventarioModel> findAll(Pageable pageable) {
        return inventarioRepository.findAll(pageable);
    }

    public Optional<InventarioModel> findById(Integer filmId) {
        return inventarioRepository.findById(filmId);
    }
    @Transactional
    public void delete(InventarioModel inventarioModel) {
        inventarioRepository.delete(inventarioModel);
    }
}

