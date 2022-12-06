package br.com.spring.estudo.estudos.services;

import br.com.spring.estudo.estudos.model.CidadeModel;
import br.com.spring.estudo.estudos.repositores.CidadeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class CidadeService {

    final CidadeRepository cidadeRepository;

    public CidadeService(CidadeRepository cidadeRepository) {
        this.cidadeRepository = cidadeRepository;
    }
    @Transactional
    public CidadeModel save(CidadeModel cidadeModel) {
        return cidadeRepository.save(cidadeModel);
    }

    public Page<CidadeModel> findAll(Pageable pageable) {
        return cidadeRepository.findAll(pageable);
    }

    public Optional<CidadeModel> findById(Integer cityid) {
        return cidadeRepository.findById(cityid);
    }
    @Transactional
    public void delete(CidadeModel cidadeModel) {
        cidadeRepository.delete(cidadeModel);
    }

}
