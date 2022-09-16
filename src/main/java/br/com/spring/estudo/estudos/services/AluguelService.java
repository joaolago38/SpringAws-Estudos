package br.com.spring.estudo.estudos.services;

import br.com.spring.estudo.estudos.model.AluguelModel;
import br.com.spring.estudo.estudos.repositores.AluguelRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class AluguelService {
    final AluguelRepository aluguelRepository;

    public AluguelService(AluguelRepository aluguelRepository) {
        this.aluguelRepository = aluguelRepository;
    }


    @Transactional
    public AluguelModel  save(AluguelModel aluguelModel) {
        return aluguelRepository.save(aluguelModel);
    }

    public Page<AluguelModel> findAll(Pageable pageable) {
        return aluguelRepository.findAll(pageable);
    }

    public Optional<AluguelModel> findById(Integer filmId) {
        return aluguelRepository.findById(filmId);
    }
    @Transactional
    public void delete(AluguelModel aluguelModel) {
        aluguelRepository.delete(aluguelModel);
    }
}
