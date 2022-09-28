package br.com.spring.estudo.estudos.controllers.endereco;

import br.com.spring.estudo.estudos.dtos.EnderecoDto;
import br.com.spring.estudo.estudos.model.EnderecoModel;
import br.com.spring.estudo.estudos.services.EnderecoService;
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
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/buscarEndereco")
public class EnderecoController {

   final EnderecoService enderecoService;

    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }


    @GetMapping("")
    public ResponseEntity<Page<EnderecoModel>> getEnderecoTodos(@PageableDefault(page = 0, size = 1000, sort = "rentalId", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(enderecoService.findAll(pageable));
    }

    @GetMapping("/{addressId}")
    public ResponseEntity<Object> getBuscaEnderecoPorID(@PathVariable(value = "addressId") Integer addressId) {
        Optional<EnderecoModel> enderecoModelOptional = enderecoService.findById(addressId);
        if (!enderecoModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Endereço nao encontrado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(enderecoModelOptional.get());
    }

    @DeleteMapping("/{addressId}")
    public ResponseEntity<Object> deleteAluguelModel(@PathVariable(value = "addressId") Integer addressId){
        Optional<EnderecoModel> enderecoModelOptional = enderecoService.findById(addressId);
        if (!enderecoModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Endereço nao econtrado.");
        }
        enderecoService.delete(enderecoModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Endereço deletado com sucesso");
    }
    @PostMapping
    public ResponseEntity<Object> saveAluguel(@RequestBody @Valid EnderecoDto enderecoDto){
        var valorEndereco = enderecoService.findById(enderecoDto.getAddressId());
        if(valorEndereco .isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Endereço ja cadastrado!");
        }
        var enderecoModel = new EnderecoModel();
        BeanUtils.copyProperties(enderecoDto, enderecoModel);
        enderecoModel.setLastUpdate(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(enderecoService.save(enderecoModel));
    }

    @PutMapping("/{addressId}")
    public ResponseEntity<Object> updateAluguel(@PathVariable(value = "addressId")  Integer addressId,
                                                @RequestBody @Valid EnderecoDto enderecoDto){
        Optional<EnderecoModel> enderecoModelOptional = enderecoService.findById(addressId);
        if (!enderecoModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(" Conflict -> Endereço nao econtrado.");
        }
        var enderecoModel = new EnderecoModel();
        BeanUtils.copyProperties(enderecoDto, enderecoModel);
        enderecoModel.setAddressId(enderecoModelOptional.get().getAddressId());
        enderecoModel.setLastUpdate(enderecoModelOptional.get().getLastUpdate());
        enderecoModel.setAddress(enderecoModelOptional.get().getAddress());
        enderecoModel.setDistrict(enderecoModelOptional.get().getDistrict());
        enderecoModel.setPhone(enderecoModelOptional.get().getPhone());
        enderecoModel.setPostalCode(enderecoModelOptional.get().getPostalCode());
        enderecoModel.setCityId(enderecoModelOptional.get().getCityId());
        enderecoModel.setPhone(enderecoModelOptional.get().getPhone());
        return ResponseEntity.status(HttpStatus.CREATED).body(enderecoService.save(enderecoModel));
    }
}
