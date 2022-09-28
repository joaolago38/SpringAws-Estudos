package br.com.spring.estudo.estudos.services;


import br.com.spring.estudo.estudos.model.PessoaModel;
import br.com.spring.estudo.estudos.repositores.PessoaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PessoaService {
 final PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    @Transactional
    public PessoaModel save(PessoaModel pessoaModel) {
        return pessoaRepository.save(pessoaModel);
    }

    public Page<PessoaModel> findAll(Pageable pageable) {
        return pessoaRepository.findAll(pageable);
    }

    public Optional<PessoaModel> findById(Integer paymenId) {
        return pessoaRepository.findById(paymenId);
    }
    @Transactional
    public void delete(PessoaModel pessoaModel) {
        pessoaRepository.delete(pessoaModel);
    }
}

