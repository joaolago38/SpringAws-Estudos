package br.com.spring.estudo.estudos.controllers.armazenamento;

import br.com.spring.estudo.estudos.dtos.ArmazenamentoDto;
import br.com.spring.estudo.estudos.model.ArmazenamentoModel;
import br.com.spring.estudo.estudos.services.ArmazenamentoService;
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
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/armazenamento")
public class ArmazenamentoController {

    final ArmazenamentoService armazenamentoService;

    public ArmazenamentoController(ArmazenamentoService armazenamentoService) {
        this.armazenamentoService = armazenamentoService;
    }



    @GetMapping("")
    public ResponseEntity<Page<ArmazenamentoModel>> getAluguelTodos(@PageableDefault(page = 0, size = 1000, sort = "rentalId", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(armazenamentoService.findAll(pageable));
    }

    @GetMapping("/{storeId}")
    public ResponseEntity<Object> getBuscaPorID(@PathVariable(value = "storeId") Integer storeId) {
        Optional<ArmazenamentoModel> armazementoModelOptional = armazenamentoService.findById(storeId);
        if (!armazementoModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Armazenamento nao encontrado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(armazementoModelOptional.get());
    }

    @DeleteMapping("/{storeId}")
    public ResponseEntity<Object> deleteArmazenamentoModel(@PathVariable(value = "storeId") Integer storeId){
        Optional<ArmazenamentoModel> armazementoModelOptional = armazenamentoService.findById(storeId);
        if (!armazementoModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Armazenamento nao econtrado.");
        }
        armazenamentoService.delete(armazementoModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Armazenamento deletado com sucesso.");
    }

    @PostMapping
    public ResponseEntity<Object> saveArmazenamento(@RequestBody @Valid ArmazenamentoDto armazenamentoDto){
        var valorArmazenamento = armazenamentoService.findById(armazenamentoDto.getStoreId());
        if(valorArmazenamento.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Armazenamento ja cadastrado!");
        }
        var armazenamentoModel = new ArmazenamentoModel();
        BeanUtils.copyProperties(armazenamentoDto, armazenamentoModel);
        armazenamentoModel.setLastUpdate(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(armazenamentoService.save(armazenamentoModel));
    }

    @PutMapping("/{storeId}")
    public ResponseEntity<Object> updateArmazenamento(@PathVariable(value = "storeId")  Integer storeId,
                                                @RequestBody @Valid ArmazenamentoDto armazenamentoDto){
        Optional<ArmazenamentoModel> armazendoModelOptional = armazenamentoService.findById(storeId);
        if (!armazendoModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Armazenamento nao encontrado.");
        }
        var armazenamentoModel = new ArmazenamentoModel();
        BeanUtils.copyProperties(armazenamentoDto, armazenamentoModel);
        armazenamentoModel.setStoreId(armazendoModelOptional.get().getStoreId());
        armazenamentoModel.setAddressId(armazendoModelOptional.get().getAddressId());
        armazenamentoModel.setManagerStaffId(armazendoModelOptional.get().getManagerStaffId());
        armazenamentoModel.setLastUpdate(armazendoModelOptional.get().getLastUpdate());

        return ResponseEntity.status(HttpStatus.CREATED).body(armazenamentoService.save(armazenamentoModel));
    }
}
