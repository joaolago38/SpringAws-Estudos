package br.com.spring.estudo.estudos.services;

import br.com.spring.estudo.estudos.model.ClienteModel;
import br.com.spring.estudo.estudos.repositores.ClienteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class ClienteService {

    final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Transactional
    public ClienteModel save(ClienteModel clienteModel) {
        return clienteRepository.save(clienteModel);
    }

    public Page<ClienteModel> findAll(Pageable pageable) {
        return clienteRepository.findAll(pageable);
    }

    public Optional<ClienteModel> findById(Integer cityid) {
        return clienteRepository.findById(cityid);
    }
    @Transactional
    public void delete(ClienteModel clienteModel) {
        clienteRepository.delete(clienteModel);
    }
}
