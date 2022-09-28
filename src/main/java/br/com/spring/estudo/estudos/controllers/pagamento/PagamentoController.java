package br.com.spring.estudo.estudos.controllers.pagamento;

import br.com.spring.estudo.estudos.dtos.PagamentoDto;
import br.com.spring.estudo.estudos.model.PagamentoModel;
import br.com.spring.estudo.estudos.services.PagamentoService;
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

@RequestMapping("/buscarPagamento")
public class PagamentoController {
 final PagamentoService pagamentoService;

    public PagamentoController(PagamentoService pagamentoService) {
        this.pagamentoService = pagamentoService;
    }

    @GetMapping("")
    public ResponseEntity<Page<PagamentoModel>> getLinguagemTodos(@PageableDefault(page = 0, size = 1000, sort = "paymenId", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(pagamentoService.findAll(pageable));
    }

    @GetMapping("/{paymenId}")
    public ResponseEntity<Object> getBuscaPagamentoPorID(@PathVariable(value = "paymenId") Integer languageId) {
        Optional<PagamentoModel> pagamentoModelOptional = pagamentoService.findById(languageId);
        if (!pagamentoModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(" Conflict - > Pagamentos  nao encontrado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(pagamentoModelOptional.get());
    }

    @DeleteMapping("/{paymenId}")
    public ResponseEntity<Object> deletePagamentoModel(@PathVariable(value = "paymenId") Integer paymenId){
        Optional<PagamentoModel> pagamentoModelOptional = pagamentoService.findById(paymenId);
        if (!pagamentoModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(" Conflict -> Pagamento  nao encontrado.");
        }
        pagamentoService.delete(pagamentoModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body(" Conflict -> Pagamento   deletado com sucesso.");
    }

    @PostMapping
    public ResponseEntity<Object> savePagamento(@RequestBody @Valid PagamentoDto pagamentoDto){
        var valorPagamento = pagamentoService.findById(pagamentoDto.getPaymentId());
        if(valorPagamento.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict:  Pagamento ja cadastrado!");
        }
        var pagamentoModel = new PagamentoModel();
        BeanUtils.copyProperties(pagamentoDto, pagamentoModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(pagamentoService.save(pagamentoModel));
    }

    @PutMapping("/{paymenId}")
    public ResponseEntity<Object> updateLinguagem(@PathVariable(value = "paymenId")  Integer paymenId,
                                                  @RequestBody @Valid PagamentoDto pagamentoDto){
        Optional<PagamentoModel> pagamentoModelOptional = pagamentoService.findById(paymenId);
        if (!pagamentoModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conflict:  Pagamento nao encontrado.");
        }
        var pagamentoModel = new PagamentoModel();
        BeanUtils.copyProperties(pagamentoDto, pagamentoModel);
        pagamentoModel.setPaymentDate(LocalDateTime.now(ZoneId.of("UTC")));
        pagamentoModel.setAmount(pagamentoModel.getAmount());
        pagamentoModel.setCustomerId(pagamentoModel.getCustomerId());
        pagamentoModel.setStaffId(pagamentoModel.getStaffId());
        pagamentoModel.setPaymentDate(pagamentoModel.getPaymentDate());
        pagamentoModel.setStaffId(pagamentoModel.getStaffId());
        return ResponseEntity.status(HttpStatus.CREATED).body(pagamentoService.save(pagamentoModel));
    }
}
