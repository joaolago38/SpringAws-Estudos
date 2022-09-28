package br.com.spring.estudo.estudos.controllers.pais;

import br.com.spring.estudo.estudos.dtos.PaisDto;
import br.com.spring.estudo.estudos.model.PaisModel;
import br.com.spring.estudo.estudos.services.PaisService;
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
@RequestMapping("/buscarPais")
public class PaisController {
 final PaisService paisService;

    public PaisController(PaisService paisService) {
        this.paisService = paisService;
    }
    @GetMapping("")
    public ResponseEntity<Page<PaisModel>> getPaisTodos(@PageableDefault(page = 0, size = 1000, sort = "countryId", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(paisService.findAll(pageable));
    }

    @GetMapping("/{countryId}")
    public ResponseEntity<Object> getBuscaPaisPorID(@PathVariable(value = "countryId") Integer countryId) {
        Optional<PaisModel> paisModelOptional = paisService.findById(countryId);
        if (!paisModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(" Conflict - > Pais  nao encontrado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(paisModelOptional.get());
    }

    @DeleteMapping("/{countryId}")
    public ResponseEntity<Object> deletePagamentoModel(@PathVariable(value = "countryId") Integer countryId){
        Optional<PaisModel> paisModelOptional = paisService.findById(countryId);
        if (!paisModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(" Conflict -> Pais  nao encontrado.");
        }
        paisService.delete(paisModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body(" Conflict -> Pais   deletado com sucesso.");
    }

    @PostMapping
    public ResponseEntity<Object> savePagamento(@RequestBody @Valid PaisDto paisDto){
        var paisValor = paisService.findById(paisDto.getCountryId());
        if(paisValor.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict:  Pais ja cadastrado!");
        }
        var paisModel = new PaisModel();
        paisModel.setLastUpdate(LocalDateTime.now(ZoneId.of("UTC")));
        BeanUtils.copyProperties(paisDto, paisModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(paisService.save(paisModel));
    }

    @PutMapping("/{paymenId}")
    public ResponseEntity<Object> updateLinguagem(@PathVariable(value = "paymenId")  Integer paymenId,
                                                  @RequestBody @Valid PaisDto paisDto){
        Optional<PaisModel> paisModelOptional = paisService.findById(paymenId);
        if (!paisModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conflict:  Pagamento nao encontrado.");
        }
        var paisModel = new PaisModel();
        paisModel.setLastUpdate(LocalDateTime.now(ZoneId.of("UTC")));
        BeanUtils.copyProperties(paisDto, paisModel);
        paisModel.setCountry(paisModel.getCountry());
        paisModel.setCountryId(paisModel.getCountryId());
        return ResponseEntity.status(HttpStatus.CREATED).body(paisService.save(paisModel));
    }
}
