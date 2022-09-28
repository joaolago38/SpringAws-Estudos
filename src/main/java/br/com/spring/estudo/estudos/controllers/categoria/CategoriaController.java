package br.com.spring.estudo.estudos.controllers.categoria;

import br.com.spring.estudo.estudos.dtos.CategoriaDto;
import br.com.spring.estudo.estudos.model.CategoriaModel;
import br.com.spring.estudo.estudos.services.CategoriaService;
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
@RequestMapping("/buscarCategory")
public class CategoriaController {


    final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }


    @GetMapping("")
    public ResponseEntity<Page<CategoriaModel>> getCategoriaTodas(@PageableDefault(page = 0, size = 1000, sort = "rentalId", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(categoriaService.findAll(pageable));
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<Object> getBuscaPorID(@PathVariable(value = "categoryId") Integer categoryId) {
        Optional<CategoriaModel> categoriaModelOptional = categoriaService.findById(categoryId);
        if (!categoriaModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ator nao encontrado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(categoriaModelOptional.get());
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Object> deleteArmazenamentoModel(@PathVariable(value = "categoryId") Integer actorId){
        Optional<CategoriaModel> categoriaModelOptional = categoriaService.findById(actorId);
        if (!categoriaModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Categoria  nao econtrado.");
        }
        categoriaService.delete(categoriaModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Categoria deletado com sucesso.");
    }

    @PostMapping
    public ResponseEntity<Object> saveCategoria(@RequestBody @Valid CategoriaDto categoriaDto){
        var valorCategoria = categoriaService.findById(categoriaDto.getCategoryId());
        if(valorCategoria.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Categoria ja cadastrado!");
        }
        var categoriaModel = new CategoriaModel();
        BeanUtils.copyProperties(categoriaDto, categoriaModel);
        categoriaModel.setLastUpdate(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaService.save(categoriaModel));
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<Object> updateAtor(@PathVariable(value = "categoryId")  Integer categoryId,
                                             @RequestBody @Valid CategoriaDto categoriaDto){
        Optional<CategoriaModel> categoriaModelOptional = categoriaService.findById(categoryId);
        if (!categoriaModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Categoria nao encontrado.");
        }
        var categoriaModel = new CategoriaModel();
        BeanUtils.copyProperties(categoriaDto, categoriaModel);
        categoriaModel.setLastUpdate(categoriaModelOptional.get().getLastUpdate());
        categoriaModel.setCategoryId(categoriaModelOptional.get().getCategoryId());
       return ResponseEntity.status(HttpStatus.CREATED).body(categoriaService.save(categoriaModel));
    }
}
