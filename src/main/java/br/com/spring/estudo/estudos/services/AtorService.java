package br.com.spring.estudo.estudos.services;

import br.com.spring.estudo.estudos.model.AtorModel;
import br.com.spring.estudo.estudos.repositores.AtorRespository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class AtorService {
    final AtorRespository atorRespository;

    public AtorService(AtorRespository atorRespository) {
        this.atorRespository = atorRespository;
    }

    @Transactional
    public AtorModel save(AtorModel atorModel) {
        return atorRespository.save(atorModel);
    }

    public Page<AtorModel> findAll(Pageable pageable) {
        return atorRespository.findAll(pageable);
    }

    public Optional<AtorModel> findById(Integer actorId) {
        return atorRespository.findById(actorId);
    }
    @Transactional
    public void delete(AtorModel atorModel) {
        atorRespository.delete(atorModel);
    }
}
