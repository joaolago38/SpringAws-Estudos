package br.com.spring.estudo.estudos.services;

import br.com.spring.estudo.estudos.model.FilmeAtorModel;
import br.com.spring.estudo.estudos.repositores.FilmeActorRespository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class FilmeAtorService {

    final FilmeActorRespository filmeActorRespository;

    public FilmeAtorService(FilmeActorRespository filmeActorRespository) {
        this.filmeActorRespository = filmeActorRespository;
    }

    @Transactional
    public FilmeAtorModel save(FilmeAtorModel filmeAtorModel) {
        return filmeActorRespository.save(filmeAtorModel);
    }

    public Page<FilmeAtorModel> findAll(Pageable pageable) {
        return filmeActorRespository.findAll(pageable);
    }

    public Optional<FilmeAtorModel> findById(Integer actorId) {
        return filmeActorRespository.findById(actorId);
    }
    @Transactional
    public void delete(FilmeAtorModel filmeAtorModel) {
        filmeActorRespository.delete(filmeAtorModel);
    }
}
