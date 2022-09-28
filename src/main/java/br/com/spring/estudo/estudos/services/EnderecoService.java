package br.com.spring.estudo.estudos.services;

import br.com.spring.estudo.estudos.model.EnderecoModel;
import br.com.spring.estudo.estudos.repositores.EnderecoRespository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class EnderecoService {
     final EnderecoRespository enderecoRepository;

    public EnderecoService(EnderecoRespository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }
    @Transactional
    public EnderecoModel save(EnderecoModel enderecoModel) {
        return enderecoRepository.save(enderecoModel);
    }

    public Page<EnderecoModel> findAll(Pageable pageable) {
        return enderecoRepository.findAll(pageable);
    }

    public Optional<EnderecoModel> findById(Integer addressId) {
        return enderecoRepository.findById(addressId);
    }
    @Transactional
    public void delete(EnderecoModel enderecoModel) {
        enderecoRepository.delete(enderecoModel);
    }
}
