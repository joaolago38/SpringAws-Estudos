package br.com.spring.estudo.estudos.services;


import br.com.spring.estudo.estudos.model.PaisModel;
import br.com.spring.estudo.estudos.repositores.PaisRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PaisService {

    final PaisRepository paisRepository;

    public PaisService(PaisRepository paisRepository) {
        this.paisRepository = paisRepository;
    }


    @Transactional
    public PaisModel save(PaisModel paisModel) {
        return paisRepository.save(paisModel);
    }

    public Page<PaisModel> findAll(Pageable pageable) {
        return paisRepository.findAll(pageable);
    }

    public Optional<PaisModel> findById(Integer paymenId) {
        return paisRepository.findById(paymenId);
    }
    @Transactional
    public void delete(PaisModel paisModel) {
        paisRepository.delete(paisModel);
    }
}
