package br.com.spring.estudo.estudos.controllers.inventario;

import br.com.spring.estudo.estudos.dtos.InventarioDto;
import br.com.spring.estudo.estudos.model.InventarioModel;
import br.com.spring.estudo.estudos.services.InventarioService;
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

@RequestMapping("/buscarInventario")
public class InventarioController {
  final InventarioService inventarioService;

    public InventarioController(InventarioService inventarioService) {
        this.inventarioService = inventarioService;
    }


    @GetMapping("")
    public ResponseEntity<Page<InventarioModel>> getInventariosTodos(@PageableDefault(page = 0, size = 1000, sort = "filmId", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(inventarioService.findAll(pageable));
    }

    @GetMapping("/{filmId}")
    public ResponseEntity<Object> getBuscaInventariosPorID(@PathVariable(value = "filmId") Integer filmId) {
        Optional<InventarioModel> inventarioModelOptional = inventarioService.findById(filmId);
        if (!inventarioModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(" Conflict - > Inventario  nao encontrado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(inventarioModelOptional.get());
    }

    @DeleteMapping("/{actorId}")
    public ResponseEntity<Object> deleteFilmeCategoriaModel(@PathVariable(value = "filmId") Integer filmId){
        Optional<InventarioModel> inventarioModelOptional = inventarioService.findById(filmId);
        if (!inventarioModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(" Conflict -> Inventario  nao encontrado.");
        }
        inventarioService.delete(inventarioModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body(" Conflict -> Inventario   deletado com sucesso.");
    }

    @PostMapping
    public ResponseEntity<Object> saveFilmeAtor(@RequestBody @Valid InventarioDto inventarioDto){
        var valorInventario = inventarioService.findById(inventarioDto.getFilmId());
        if(valorInventario.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Filme Categoria ja cadastrado!");
        }
        var inventarioModel = new InventarioModel();
        BeanUtils.copyProperties(inventarioDto, inventarioModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(inventarioService.save(inventarioModel));
    }

    @PutMapping("/{filmId}")
    public ResponseEntity<Object> updateCidade(@PathVariable(value = "filmId")  Integer filmId,
                                               @RequestBody @Valid InventarioDto inventarioDto){
        Optional<InventarioModel> inventarioModelOptional = inventarioService.findById(filmId);
        if (!inventarioModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Filme Categoria nao encontrado.");
        }
        var inventarioModel = new InventarioModel();
        BeanUtils.copyProperties(inventarioDto, inventarioModel);
        inventarioModel.setFilmId(inventarioModelOptional.get().getFilmId());
        inventarioModel.setInventoryId(inventarioModelOptional.get().getInventoryId());
        inventarioModel.setStoreId(inventarioModelOptional.get().getStoreId());
        inventarioModel.setLastUpdate(inventarioModelOptional.get().getLastUpdate());
        return ResponseEntity.status(HttpStatus.CREATED).body(inventarioService.save(inventarioModel));
    }
}
