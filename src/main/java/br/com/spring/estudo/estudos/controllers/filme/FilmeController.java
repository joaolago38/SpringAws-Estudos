package br.com.spring.estudo.estudos.controllers.filme;

import br.com.spring.estudo.estudos.dtos.FilmeDto;
import br.com.spring.estudo.estudos.model.FilmeModel;
import br.com.spring.estudo.estudos.services.FilmeService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/buscarFilme")
public class FilmeController {

    final FilmeService filmeService;

    public FilmeController(FilmeService filmeService) {
        this.filmeService = filmeService;
    }


    @GetMapping("")
    public ResponseEntity<Page<FilmeModel>> getFilmesTodos(@PageableDefault(page = 0, size = 1000, sort = "filmId", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(filmeService.findAll(pageable));
    }

    @GetMapping("/{filmId}")
    public ResponseEntity<Object> getBuscaFilmeCategoriaPorID(@PathVariable(value = "filmId") Integer filmId) {
        Optional<FilmeModel> filmeModelOptional = filmeService.findById(filmId);
        if (!filmeModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(" Conflict - > Filme  nao encontrado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(filmeModelOptional.get());
    }

    @DeleteMapping("/{actorId}")
    public ResponseEntity<Object> deleteFilmeCategoriaModel(@PathVariable(value = "filmId") Integer filmId){
        Optional<FilmeModel> filmeModelOptional = filmeService.findById(filmId);
        if (!filmeModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(" Conflict -> Filme  nao encontrado.");
        }
        filmeService.delete(filmeModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body(" Conflict -> Filme   deletado com sucesso.");
    }

    @PostMapping
    public ResponseEntity<Object> saveFilmeAtor(@RequestBody @Valid FilmeDto filmeDto){
        var valorFilme = filmeService.findById(filmeDto.getFilmId());
        if(valorFilme.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Filme Categoria ja cadastrado!");
        }
        var filmeModel = new FilmeModel();
        BeanUtils.copyProperties(filmeDto, filmeModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(filmeService.save(filmeModel));
    }

    @PutMapping("/{filmId}")
    public ResponseEntity<Object> updateCidade(@PathVariable(value = "filmId")  Integer filmId,
                                               @RequestBody @Valid FilmeDto filmeDto){
        Optional<FilmeModel> filmeModelOptional = filmeService.findById(filmId);
        if (!filmeModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Filme Categoria nao encontrado.");
        }
        var filmeModel = new FilmeModel();
        BeanUtils.copyProperties(filmeDto, filmeModel);
        filmeModel.setFilmId(filmeModelOptional.get().getFilmId());
        filmeModel.setDescription(filmeModelOptional.get().getDescription());
        filmeModel.setLength(filmeModelOptional.get().getLength());
        filmeModel.setRentalDuration(filmeModelOptional.get().getRentalDuration());
        filmeModel.setReplacementCost(filmeModelOptional.get().getReplacementCost());
        filmeModel.setReleaseYear(filmeModelOptional.get().getReleaseYear());
        filmeModel.setTitle(filmeModelOptional.get().getTitle());
        return ResponseEntity.status(HttpStatus.CREATED).body(filmeService.save(filmeModel));
    }
}