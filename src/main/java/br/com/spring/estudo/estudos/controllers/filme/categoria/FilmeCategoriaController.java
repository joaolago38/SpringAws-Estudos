package br.com.spring.estudo.estudos.controllers.filme.categoria;

import br.com.spring.estudo.estudos.dtos.FilmeCategoryDto;
import br.com.spring.estudo.estudos.model.FilmeCategoriaModel;
import br.com.spring.estudo.estudos.services.FilmeCategoriaService;
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

@RequestMapping("/buscarCategoria")
public class FilmeCategoriaController {


  final FilmeCategoriaService filmeCategoriaService;

    public FilmeCategoriaController(FilmeCategoriaService filmeCategoriaService) {
        this.filmeCategoriaService = filmeCategoriaService;
    }

    @GetMapping("")
    public ResponseEntity<Page<FilmeCategoriaModel>> getFilmesCategoriasTodas(@PageableDefault(page = 0, size = 1000, sort = "actorId", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(filmeCategoriaService.findAll(pageable));
    }

    @GetMapping("/{filmId}")
    public ResponseEntity<Object> getBuscaFilmeCategoriaPorID(@PathVariable(value = "filmId") Integer filmId) {
        Optional<FilmeCategoriaModel> filmeCategoriaModelOptional = filmeCategoriaService.findById(filmId);
        if (!filmeCategoriaModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(" Conflict - > Filme Categoria nao encontrado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(filmeCategoriaModelOptional.get());
    }

    @DeleteMapping("/{actorId}")
    public ResponseEntity<Object> deleteFilmeCategoriaModel(@PathVariable(value = "filmId") Integer filmId){
        Optional<FilmeCategoriaModel> filmeCategoriaModelOptional = filmeCategoriaService.findById(filmId);
        if (!filmeCategoriaModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(" Conflict -> Filme Categoria nao encontrado.");
        }
        filmeCategoriaService.delete(filmeCategoriaModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body(" Conflict -> Filme Categoria  deletado com sucesso.");
    }

    @PostMapping
    public ResponseEntity<Object> saveFilmeAtor(@RequestBody @Valid FilmeCategoryDto filmeCategoryDto){
        var valorFilmeCategoria = filmeCategoriaService.findById(filmeCategoryDto.getCategoryId());
        if(valorFilmeCategoria.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Filme Categoria ja cadastrado!");
        }
        var filmeCategoriaModel = new FilmeCategoriaModel();
        BeanUtils.copyProperties(filmeCategoryDto, filmeCategoriaModel);
        filmeCategoriaModel.setLastUpdate(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(filmeCategoriaService.save(filmeCategoriaModel));
    }

    @PutMapping("/{filmId}")
    public ResponseEntity<Object> updateCidade(@PathVariable(value = "filmId")  Integer filmId,
                                               @RequestBody @Valid FilmeCategoryDto filmeCategoryDto){
        Optional<FilmeCategoriaModel> filmeAtorModelOptional = filmeCategoriaService.findById(filmId);
        if (!filmeAtorModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Filme Categoria nao encontrado.");
        }
        var filmeCategoriaModel = new FilmeCategoriaModel();
        BeanUtils.copyProperties(filmeCategoryDto, filmeCategoriaModel);
        filmeCategoriaModel.setLastUpdate(filmeAtorModelOptional.get().getLastUpdate());
        filmeCategoriaModel.setFilmId(filmeAtorModelOptional.get().getFilmId());
        filmeCategoriaModel.setCategoryId(filmeAtorModelOptional.get().getCategoryId());

        return ResponseEntity.status(HttpStatus.CREATED).body(filmeCategoriaService.save(filmeCategoriaModel));
    }
}
