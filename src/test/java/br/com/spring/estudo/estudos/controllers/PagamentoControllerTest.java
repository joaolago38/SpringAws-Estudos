package br.com.spring.estudo.estudos.controllers;

import br.com.spring.estudo.estudos.controllers.categoria.CategoriaController;
import br.com.spring.estudo.estudos.controllers.pagamento.PagamentoController;
import br.com.spring.estudo.estudos.exception.NegociosException;
import br.com.spring.estudo.estudos.model.CategoriaModel;
import br.com.spring.estudo.estudos.model.LinguagemModel;
import br.com.spring.estudo.estudos.model.PagamentoModel;
import br.com.spring.estudo.estudos.services.CategoriaService;
import br.com.spring.estudo.estudos.services.PagamentoService;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PagamentoController.class)
public class PagamentoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PagamentoService pagamentoService;

        @Test
        @DisplayName("Salva um novo Pagamento")
        public void createCategoria_whenPostMethod() throws Exception {
            PagamentoModel pagamentoModel = PagamentoModel.builder().amount(1L).customerId(1L)
                    .paymenId(1).paymentDate(LocalDateTime.now()).rentalId(1L).staffId(1L).build();
            given(pagamentoService.save(pagamentoModel)).willReturn(pagamentoModel);
            mockMvc.perform(post("/buscarPagamento")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(com.usersapi.endpoints.util.JsonUtil.toJson(pagamentoModel)))
                    .andExpect(status().is4xxClientError());

        }

        @Test
        @DisplayName("Deleta um Pagamento pelo id")
        public void removeCategoriaById_whenDeleteMethod() throws Exception {
            PagamentoModel pagamentoModel = PagamentoModel.builder().amount(1L).customerId(1L)
                    .paymenId(1).paymentDate(LocalDateTime.now()).rentalId(1L).staffId(1L).build();
            doNothing().when(pagamentoService).delete(pagamentoModel);
            mockMvc.perform(delete("/buscarPagamento/" + pagamentoModel.toString())
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("Deleta um Pagamento que nao existe")
        public void should_throw_exception_when_Categoria_doesnt_exist() throws Exception {
            PagamentoModel pagamentoModel = PagamentoModel.builder().amount(1L).customerId(1L)
                    .paymenId(1).paymentDate(LocalDateTime.now()).rentalId(1L).staffId(1L).build();
            Mockito.doThrow(new NegociosException(pagamentoModel.getPaymenId())).when(pagamentoService).delete(pagamentoModel);

            mockMvc.perform(delete("/buscarPagamento "+ pagamentoModel.toString())
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNotFound());

        }


    }

