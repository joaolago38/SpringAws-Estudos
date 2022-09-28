package br.com.spring.estudo.estudos.services;


import br.com.spring.estudo.estudos.model.PagamentoModel;
import br.com.spring.estudo.estudos.repositores.PagamentoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PagamentoService {


   final PagamentoRepository pagamentoRepository;

    public PagamentoService(PagamentoRepository pagamentoRepository) {
        this.pagamentoRepository = pagamentoRepository;
    }

    @Transactional
    public PagamentoModel save(PagamentoModel pagamentoModel) {
        return pagamentoRepository.save(pagamentoModel);
    }

    public Page<PagamentoModel> findAll(Pageable pageable) {
        return pagamentoRepository.findAll(pageable);
    }

    public Optional<PagamentoModel> findById(Integer paymenId) {
        return pagamentoRepository.findById(paymenId);
    }
    @Transactional
    public void delete(PagamentoModel pagamentoModel) {
        pagamentoRepository.delete(pagamentoModel);
    }
}


