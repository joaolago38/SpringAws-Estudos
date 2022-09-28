package br.com.spring.estudo.estudos.controllers.linguagem;

import br.com.spring.estudo.estudos.dtos.LanguagemDto;
import br.com.spring.estudo.estudos.model.LinguagemModel;
import br.com.spring.estudo.estudos.services.LinguagemService;
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

@RequestMapping("/buscarLinguagem")
public class LinguagemController {
    final LinguagemService linguagemService;

    public LinguagemController(LinguagemService linguagemService) {
        this.linguagemService = linguagemService;
    }

    @GetMapping("")
    public ResponseEntity<Page<LinguagemModel>> getLinguagemTodos(@PageableDefault(page = 0, size = 1000, sort = "filmId", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(linguagemService.findAll(pageable));
    }

    @GetMapping("/{languageId}")
    public ResponseEntity<Object> getBuscaInventariosPorID(@PathVariable(value = "languageId") Integer languageId) {
        Optional<LinguagemModel> linguagemModelOptional = linguagemService.findById(languageId);
        if (!linguagemModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(" Conflict - > Linguagem  nao encontrado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(linguagemModelOptional.get());
    }

    @DeleteMapping("/{languageId}")
    public ResponseEntity<Object> deleteLinguagemModel(@PathVariable(value = "languageId") Integer languageId){
        Optional<LinguagemModel> inventarioModelOptional = linguagemService.findById(languageId);
        if (!inventarioModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(" Conflict -> Linguagem  nao encontrado.");
        }
        linguagemService.delete(inventarioModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body(" Conflict -> Linguagem   deletado com sucesso.");
    }

    @PostMapping
    public ResponseEntity<Object> saveFilmeAtor(@RequestBody @Valid LanguagemDto languagemDto){
        var valorLinguagem = linguagemService.findById(languagemDto.getLanguageId());
        if(valorLinguagem.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Filme Categoria ja cadastrado!");
        }
        var linguagemModel = new LinguagemModel();
        BeanUtils.copyProperties(languagemDto, linguagemModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(linguagemService.save(linguagemModel));
    }

    @PutMapping("/{filmId}")
    public ResponseEntity<Object> updateLinguagem(@PathVariable(value = "languageId")  Integer languageId,
                                               @RequestBody @Valid LanguagemDto languagemDto){
        Optional<LinguagemModel> linguagemModelOptional = linguagemService.findById(languageId);
        if (!linguagemModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Filme Categoria nao encontrado.");
        }
        var linguagemModel = new LinguagemModel();
        BeanUtils.copyProperties(languagemDto, linguagemModel);
        linguagemModel.setLastUpdate(LocalDateTime.now(ZoneId.of("UTC")));
        linguagemModel.setName(linguagemModel.getName());
        linguagemModel.setLanguageId(linguagemModel.getLanguageId());
        linguagemModel.setLastUpdate(linguagemModel.getLastUpdate());
        return ResponseEntity.status(HttpStatus.CREATED).body(linguagemService.save(linguagemModel));
    }
}
