package br.com.spring.estudo.estudos.controllers.cidade;

import br.com.spring.estudo.estudos.dtos.CidadeDto;
import br.com.spring.estudo.estudos.model.CidadeModel;
import br.com.spring.estudo.estudos.services.CidadeService;
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
@RequestMapping("/buscarCidade")
public class CidadeController {

    final CidadeService cidadeService;

    public CidadeController(CidadeService cidadeService) {
        this.cidadeService = cidadeService;
    }
    @GetMapping("")
    public ResponseEntity<Page<CidadeModel>> getCidadesTodas(@PageableDefault(page = 0, size = 1000, sort = "cityid", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(cidadeService.findAll(pageable));
    }

    @GetMapping("/{cityId}")
    public ResponseEntity<Object> getBuscaPorID(@PathVariable(value = "cityId") Integer cityId) {
        Optional<CidadeModel> cidadeModelOptional = cidadeService.findById(cityId);
        if (!cidadeModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cidade nao encontrado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(cidadeModelOptional.get());
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Object> deleteCidadeModel(@PathVariable(value = "cityId") Integer cityId){
        Optional<CidadeModel> cidadeModelOptional = cidadeService.findById(cityId);
        if (!cidadeModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cidade nao encontrado.");
        }
        cidadeService.delete(cidadeModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Cidade deletado com sucesso.");
    }

    @PostMapping
    public ResponseEntity<Object> saveCidade(@RequestBody @Valid CidadeDto cidadeDto){
        var valorCidade = cidadeService.findById(cidadeDto.getCityId());
        if(valorCidade.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Categoria ja cadastrado!");
        }
        var cidadeModel = new CidadeModel();
        BeanUtils.copyProperties(cidadeDto, cidadeModel);
        cidadeModel.setLastUpdate(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(cidadeService.save(cidadeModel));
    }

    @PutMapping("/{cityId}")
    public ResponseEntity<Object> updateCidade(@PathVariable(value = "cityId")  Integer cityId,
                                             @RequestBody @Valid  CidadeDto cidadeDto){
        Optional<CidadeModel> cidadeModelOptional = cidadeService.findById(cityId);
        if (!cidadeModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Categoria nao encontrado.");
        }
        var cidadeModel = new CidadeModel();
        BeanUtils.copyProperties(cidadeDto, cidadeModel);
        cidadeModel.setLastUpdate(cidadeModelOptional.get().getLastUpdate());
        cidadeModel.setCountryId(cidadeModelOptional.get().getCountryId());
        cidadeModel.setCityid(cidadeModelOptional.get().getCityid());
        return ResponseEntity.status(HttpStatus.CREATED).body(cidadeService.save(cidadeModel));
    }
}
