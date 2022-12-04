package br.com.spring.estudo.estudos.controllers;

import br.com.spring.estudo.estudos.controllers.armazenamento.ArmazenamentoController;
import br.com.spring.estudo.estudos.exception.NegociosException;
import br.com.spring.estudo.estudos.model.AluguelModel;
import br.com.spring.estudo.estudos.model.ArmazenamentoModel;
import br.com.spring.estudo.estudos.services.AluguelService;
import br.com.spring.estudo.estudos.services.ArmazenamentoService;
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
@WebMvcTest(ArmazenamentoController.class)
public class ArmazenamentoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ArmazenamentoService armazenamentoService;

        @Test
        @DisplayName("Salva um novo Armazenamento")
        public void createArmazenamentoModel_whenPostMethod() throws Exception {
            ArmazenamentoModel armazenamentoModel = ArmazenamentoModel.builder().addressId(1L).storeId(1).managerStaffId(1).lastUpdate(LocalDateTime.now()).build();
            given(armazenamentoService.save(armazenamentoModel)).willReturn(armazenamentoModel);
            mockMvc.perform(post("/armazenamento")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(com.usersapi.endpoints.util.JsonUtil.toJson(armazenamentoModel)))
                    .andExpect(status().isCreated());

        }

        @Test
        @DisplayName("Deleta um Armazenamento pelo id")
        public void removeArmazenamentoById_whenDeleteMethod() throws Exception {
            ArmazenamentoModel armazenamentoModel = ArmazenamentoModel.builder().addressId(1L).storeId(1).managerStaffId(1).lastUpdate(LocalDateTime.now()).build();

            doNothing().when(armazenamentoService).delete(armazenamentoModel);

            mockMvc.perform(delete("/armazenamento/" + armazenamentoModel.toString())
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("Deleta um Armazenamento que nao existe")
        public void should_throw_exception_when_Armazenamento_doesnt_exist() throws Exception {
            ArmazenamentoModel armazenamentoModel = ArmazenamentoModel.builder().addressId(1L).storeId(1).managerStaffId(1).lastUpdate(LocalDateTime.now()).build();

            Mockito.doThrow(new NegociosException(armazenamentoModel.getStoreId())).when(armazenamentoService).delete(armazenamentoModel);

            mockMvc.perform(delete("/buscarArmazenamentoModel "+ armazenamentoModel.toString())
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNotFound());

        }


    }

