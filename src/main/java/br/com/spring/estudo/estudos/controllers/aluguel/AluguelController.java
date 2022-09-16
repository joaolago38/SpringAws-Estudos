package br.com.spring.estudo.estudos.controllers.aluguel;

import br.com.spring.estudo.estudos.dtos.AluguelDto;
import br.com.spring.estudo.estudos.model.AluguelModel;
import br.com.spring.estudo.estudos.services.AluguelService;
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
import java.util.UUID;


@RestController
//@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/buscarAluguel")
public class AluguelController {

   final AluguelService aluguelService;

    public AluguelController(AluguelService aluguelService) {
        this.aluguelService = aluguelService;
    }


     @GetMapping("")
    public ResponseEntity<Page<AluguelModel>> getAluguelTodos(@PageableDefault(page = 0, size = 1000, sort = "rentalId", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(aluguelService.findAll(pageable));
    }

    @GetMapping("/{rental_id}")
    public ResponseEntity<Object> getBuscaPorID(@PathVariable(value = "rental_id") Integer rentalId) {
        Optional<AluguelModel> aluguelModelOptional = aluguelService.findById(rentalId);
        if (!aluguelModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluguel nao encontrado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(aluguelModelOptional.get());
    }

    @DeleteMapping("/{rentalId}")
    public ResponseEntity<Object> deleteAluguelModel(@PathVariable(value = "rentalId") Integer rentalId){
        Optional<AluguelModel> parkingSpotModelOptional = aluguelService.findById(rentalId);
        if (!parkingSpotModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking Spot not found.");
        }
        aluguelService.delete(parkingSpotModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Parking Spot deleted successfully.");
    }
    @PostMapping
    public ResponseEntity<Object> saveAluguel(@RequestBody @Valid AluguelDto aluguelDto){

        var aluguelModel = new AluguelModel();
        BeanUtils.copyProperties(aluguelDto, aluguelModel);
        aluguelModel.setLastUpdate(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(aluguelService.save(aluguelModel));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateAluguel(@PathVariable(value = "rentalId")  Integer rentalId,
                                                @RequestBody @Valid AluguelDto aluguelDto){
        Optional<AluguelModel> aluguelModelOptional = aluguelService.findById(rentalId);
        if (!aluguelModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking Spot not found.");
        }
        var aluguelModel = new AluguelModel();
        BeanUtils.copyProperties(aluguelDto, aluguelModel);
        aluguelModel.setRentalId(aluguelModelOptional.get().getRentalId());
        aluguelModel.setLastUpdate(aluguelModelOptional.get().getLastUpdate());
        aluguelModel.setCustomerId(aluguelModelOptional.get().getCustomerId());
        return ResponseEntity.status(HttpStatus.CREATED).body(aluguelService.save(aluguelModel));
    }
}
