package br.com.spring.estudo.estudos.controllers.clientes;

import br.com.spring.estudo.estudos.dtos.ClienteDto;
import br.com.spring.estudo.estudos.model.ClienteModel;
import br.com.spring.estudo.estudos.services.ClienteService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

@RestController

@RequestMapping("/buscarCliente")
public class ClientesController {

    final ClienteService clienteService;

    public ClientesController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping("")
    public ResponseEntity<Page<ClienteModel>> getCidadesTodas(@PageableDefault(page = 0, size = 1000, sort = "customerId", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.findAll(pageable));
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<Object> getBuscaPorID(@PathVariable(value = "customerId") Integer customerId) {
        Optional<ClienteModel> clienteModelOptional = clienteService.findById(customerId);
        if (!clienteModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body( " Conflict -> Cliente nao encontrado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(clienteModelOptional.get());
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<Object> deleteClienteModel(@PathVariable(value = "customerId") Integer customerId){
        Optional<ClienteModel> cidadeModelOptional = clienteService.findById(customerId);
        if (!cidadeModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(" Conflict ->Cliente nao encontrado.");
        }
        clienteService.delete(cidadeModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body(" Conflict -> Cliente deletado com sucesso.");
    }

    @PostMapping
    public ResponseEntity<Object> saveCliente(@RequestBody @Valid ClienteDto clienteDto){
        var valorCidade = clienteService.findById(clienteDto.getCustomerId());
        if(valorCidade.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Categoria ja cadastrado!");
        }
        var clienteModel = new ClienteModel();
        BeanUtils.copyProperties(clienteDto, clienteModel);
        clienteModel.setCreateDate(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.save(clienteModel));
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<Object> updateCidade(@PathVariable(value = "customerId")  Integer customerId,
                                               @RequestBody @Valid ClienteDto clienteDto){
        Optional<ClienteModel> clienteModelOptional = clienteService.findById(customerId);
        if (!clienteModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente nao encontrado.");
        }
        var clienteModel = new ClienteModel();
        BeanUtils.copyProperties(clienteDto, clienteModel);
        clienteModel.setStoreId(clienteModelOptional.get().getStoreId());
        clienteModel.setCreateDate(clienteModelOptional.get().getCreateDate());
        clienteModel.setActive(clienteModelOptional.get().getActive());
        clienteModel.setCustomerId(clienteModelOptional.get().getCustomerId());
        clienteModel.setAddressId(clienteModelOptional.get().getAddressId());
        clienteModel.setLastName(clienteModelOptional.get().getLastName());
        clienteModel.setFirstName(clienteModelOptional.get().getFirstName());
        clienteModel.setEmail(clienteModelOptional.get().getEmail());
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.save(clienteModel));
    }

}
