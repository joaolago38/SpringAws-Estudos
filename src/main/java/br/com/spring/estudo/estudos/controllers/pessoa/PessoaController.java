package br.com.spring.estudo.estudos.controllers.pessoa;

import br.com.spring.estudo.estudos.dtos.PaisDto;
import br.com.spring.estudo.estudos.dtos.PessoaDto;
import br.com.spring.estudo.estudos.model.PaisModel;
import br.com.spring.estudo.estudos.model.PessoaModel;
import br.com.spring.estudo.estudos.services.PessoaService;
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

@RequestMapping("/buscarPessoa")
public class PessoaController {
    final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @GetMapping("")
    public ResponseEntity<Page<PessoaModel>> getPaisTodos(@PageableDefault(page = 0, size = 1000, sort = "staffId", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.findAll(pageable));
    }

    @GetMapping("/{staffId}")
    public ResponseEntity<Object> getBuscaPaisPorID(@PathVariable(value = "staffId") Integer staffId) {
        Optional<PessoaModel> pessoaModelOptional = pessoaService.findById(staffId);
        if (!pessoaModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(" Conflict - > Pessoa  nao encontrado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(pessoaModelOptional.get());
    }

    @DeleteMapping("/{staffId}")
    public ResponseEntity<Object> deletePagamentoModel(@PathVariable(value = "staffId") Integer staffId){
        Optional<PessoaModel> pessoaModelOptional = pessoaService.findById(staffId);
        if (!pessoaModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(" Conflict -> Pessoa  nao encontrado.");
        }
        pessoaService.delete(pessoaModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body(" Conflict -> Pessoa   deletado com sucesso.");
    }

    @PostMapping
    public ResponseEntity<Object> savePagamento(@RequestBody @Valid PessoaDto pessoaDto){
        var pessoaValor = pessoaService.findById(pessoaDto.getStaffId());
        if(pessoaValor.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict:  Pais ja cadastrado!");
        }
        var pessoaModel = new PessoaModel();
        pessoaModel.setLastUpdate(LocalDateTime.now(ZoneId.of("UTC")));
        BeanUtils.copyProperties(pessoaDto, pessoaModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaService.save(pessoaModel));
    }

    @PutMapping("/{paymenId}")
    public ResponseEntity<Object> updateLinguagem(@PathVariable(value = "paymenId")  Integer paymenId,
                                                  @RequestBody @Valid PessoaDto pessoaDto){
        Optional<PessoaModel> paisModelOptional = pessoaService.findById(paymenId);
        if (!paisModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conflict:  Pagamento nao encontrado.");
        }
        var pessoaModel = new PessoaModel();
        pessoaModel.setLastUpdate(LocalDateTime.now(ZoneId.of("UTC")));
        BeanUtils.copyProperties(pessoaDto, pessoaModel);
        pessoaModel.setEmail(pessoaModel.getEmail());
        pessoaModel.setActive(pessoaModel.getActive());
        pessoaModel.setPassword(pessoaModel.getPassword());
        pessoaModel.setFirstName(pessoaModel.getFirstName());
        pessoaModel.setPicture(pessoaModel.getPicture());
        pessoaModel.setAddressId(pessoaModel.getAddressId());
        pessoaModel.setStaffId(pessoaModel.getStaffId());
        pessoaModel.setEmail(pessoaModel.getEmail());
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaService.save(pessoaModel));
    }
}
