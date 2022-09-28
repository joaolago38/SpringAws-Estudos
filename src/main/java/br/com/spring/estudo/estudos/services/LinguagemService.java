package br.com.spring.estudo.estudos.services;

import br.com.spring.estudo.estudos.model.LinguagemModel;
import br.com.spring.estudo.estudos.repositores.LinguagemRespository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class LinguagemService {
   final LinguagemRespository linguagemRespository;
    public LinguagemService(LinguagemRespository linguagemRespository) {
        this.linguagemRespository = linguagemRespository;
    }

    @Transactional
    public LinguagemModel save(LinguagemModel linguagemModel) {
        return linguagemRespository.save(linguagemModel);
    }

    public Page<LinguagemModel> findAll(Pageable pageable) {
        return linguagemRespository.findAll(pageable);
    }

    public Optional<LinguagemModel> findById(Integer filmId) {
        return linguagemRespository.findById(filmId);
    }
    @Transactional
    public void delete(LinguagemModel linguagemModel) {
        linguagemRespository.delete(linguagemModel);
    }
}
