package br.com.spring.estudo.estudos.services;

import br.com.spring.estudo.estudos.model.ArmazenamentoModel;
import br.com.spring.estudo.estudos.repositores.ArmazenamentoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class ArmazenamentoService {

    final ArmazenamentoRepository armazenamentoRepository;

    public ArmazenamentoService(ArmazenamentoRepository armazenamentoRepository) {
        this.armazenamentoRepository = armazenamentoRepository;
    }
    @Transactional
    public ArmazenamentoModel save(ArmazenamentoModel armazenamentoModel) {
        return armazenamentoRepository.save(armazenamentoModel);
    }

    public Page<ArmazenamentoModel> findAll(Pageable pageable) {
        return armazenamentoRepository.findAll(pageable);
    }

    public Optional<ArmazenamentoModel> findById(Integer storeId) {
        return armazenamentoRepository.findById(storeId);
    }
    @Transactional
    public void delete(ArmazenamentoModel armazenamentoModel) {
        armazenamentoRepository.delete(armazenamentoModel);
    }

}
