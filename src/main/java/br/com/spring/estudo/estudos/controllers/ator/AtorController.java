package br.com.spring.estudo.estudos.controllers.ator;

import br.com.spring.estudo.estudos.dtos.AtorDto;
import br.com.spring.estudo.estudos.model.AtorModel;
import br.com.spring.estudo.estudos.services.AtorService;
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
@RequestMapping("/buscarAtor")
public class AtorController {

    final AtorService atorService;

    public AtorController(AtorService atorService) {
        this.atorService = atorService;
    }




    @GetMapping("")
    public ResponseEntity<Page<AtorModel>> getAluguelTodos(@PageableDefault(page = 0, size = 1000, sort = "actorId", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(atorService.findAll(pageable));
    }

    @GetMapping("/{actorId}")
    public ResponseEntity<Object> getBuscaPorID(@PathVariable(value = "actorId") Integer actorId) {
        Optional<AtorModel> armazementoModelOptional = atorService.findById(actorId);
        if (!armazementoModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ator nao encontrado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(armazementoModelOptional.get());
    }

    @DeleteMapping("/{actorId}")
    public ResponseEntity<Object> deleteArmazenamentoModel(@PathVariable(value = "actorId") Integer actorId){
        Optional<AtorModel> armazementoModelOptional = atorService.findById(actorId);
        if (!armazementoModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ator nao econtrado.");
        }
        atorService.delete(armazementoModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Ator deletado com sucesso.");
    }

    @PostMapping
    public ResponseEntity<Object> saveArmazenamento(@RequestBody @Valid AtorDto atorDto){
        var valorAtor = atorService.findById(atorDto.getActorId());
        if(valorAtor.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Ator ja cadastrado!");
        }
        var atorModel = new AtorModel();
        BeanUtils.copyProperties(atorDto, atorModel);
        atorModel.setLastUpdate(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(atorService.save(atorModel));
    }

    @PutMapping("/{actorId}")
    public ResponseEntity<Object> updateAtor(@PathVariable(value = "actorId")  Integer actorId,
                                                      @RequestBody @Valid AtorDto atorDto){
        Optional<AtorModel> atorModelOptional = atorService.findById(actorId);
        if (!atorModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ator nao encontrado.");
        }
        var atorModel = new AtorModel();
        BeanUtils.copyProperties(atorDto, atorModel);
        atorModel.setLastUpdate(atorModelOptional.get().getLastUpdate());
        atorModel.setActorId(atorModelOptional.get().getActorId());
        atorModel.setLastName(atorModelOptional.get().getLastName());
        atorModel.setFirstName(atorModelOptional.get().getFirstName());

        return ResponseEntity.status(HttpStatus.CREATED).body(atorService.save(atorModel));
    }


}

