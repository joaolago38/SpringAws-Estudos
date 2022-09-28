package br.com.spring.estudo.estudos.controllers.filme.ator;

import br.com.spring.estudo.estudos.dtos.FilmeActorDto;
import br.com.spring.estudo.estudos.model.FilmeAtorModel;
import br.com.spring.estudo.estudos.services.FilmeAtorService;
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

@RequestMapping("/buscarFilmeAtor")
public class FilmeAtorController {
    final FilmeAtorService filmeAtorService;

    public FilmeAtorController(FilmeAtorService filmeAtorService) {
        this.filmeAtorService = filmeAtorService;
    }

    @GetMapping("")
    public ResponseEntity<Page<FilmeAtorModel>> getFiomesAtoresTodas(@PageableDefault(page = 0, size = 1000, sort = "actorId", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(filmeAtorService.findAll(pageable));
    }

    @GetMapping("/{actorId}")
    public ResponseEntity<Object> getBuscaPorID(@PathVariable(value = "actorId") Integer actorId) {
        Optional<FilmeAtorModel> filmeAtorModelOptional = filmeAtorService.findById(actorId);
        if (!filmeAtorModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente nao encontrado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(filmeAtorModelOptional.get());
    }

    @DeleteMapping("/{actorId}")
    public ResponseEntity<Object> deleteFilmeAtorModel(@PathVariable(value = "actorId") Integer actorId){
        Optional<FilmeAtorModel> filmeAtorModelOptional = filmeAtorService.findById(actorId);
        if (!filmeAtorModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O Filme/Ator nao encontrado.");
        }
        filmeAtorService.delete(filmeAtorModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Cliente deletado com sucesso.");
    }

    @PostMapping
    public ResponseEntity<Object> saveFilmeAtor(@RequestBody @Valid FilmeActorDto filmeActorDto){
        var valorFilmeAtor = filmeAtorService.findById(filmeActorDto.getActorId());
        if(valorFilmeAtor.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Categoria ja cadastrado!");
        }
        var filmeAtorModel = new FilmeAtorModel();
        BeanUtils.copyProperties(filmeActorDto, filmeAtorModel);
        filmeAtorModel.setLastUpdate(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(filmeAtorService.save(filmeAtorModel));
    }

    @PutMapping("/{actorId}")
    public ResponseEntity<Object> updateCidade(@PathVariable(value = "actorId")  Integer actorId,
                                               @RequestBody @Valid FilmeActorDto filmeActorDto){
        Optional<FilmeAtorModel> filmeAtorModelOptional = filmeAtorService.findById(actorId);
        if (!filmeAtorModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente nao encontrado.");
        }
        var filmeAtorModel = new FilmeAtorModel();
        BeanUtils.copyProperties(filmeActorDto, filmeAtorModel);
        filmeAtorModel.setLastUpdate(filmeAtorModelOptional.get().getLastUpdate());
        filmeAtorModel.setFilmId(filmeAtorModelOptional.get().getFilmId());
        filmeAtorModel.setActorId(filmeAtorModelOptional.get().getActorId());

        return ResponseEntity.status(HttpStatus.CREATED).body(filmeAtorService.save(filmeAtorModel));
    }
}
