package br.com.spring.estudo.estudos.controllers;

import br.com.spring.estudo.estudos.controllers.ator.AtorController;
import br.com.spring.estudo.estudos.exception.NegociosException;
import br.com.spring.estudo.estudos.model.ArmazenamentoModel;
import br.com.spring.estudo.estudos.model.AtorModel;
import br.com.spring.estudo.estudos.services.AtorService;
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
@WebMvcTest(AtorController.class)
public class AtorControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AtorService atorService;

        @Test
        @DisplayName("Salva um novo Ator")
        public void createAtor_whenPostMethod() throws Exception {
            AtorModel atorModel = AtorModel.builder().actorId(1).firstName("testes").lastName("testes").lastUpdate(LocalDateTime.now()).build();
            given(atorService.save(atorModel)).willReturn(atorModel);
            mockMvc.perform(post("/buscarAtor")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(com.usersapi.endpoints.util.JsonUtil.toJson(atorModel)))
                    .andExpect(status().is4xxClientError());

        }

        @Test
        @DisplayName("Deleta um Ator pelo id")
        public void removeAtorById_whenDeleteMethod() throws Exception {
            AtorModel atorModel = AtorModel.builder().actorId(1).firstName("testes").lastName("testes").lastUpdate(LocalDateTime.now()).build();

            doNothing().when(atorService).delete(atorModel);

            mockMvc.perform(delete("/buscarAtor/" + atorModel.toString())
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("Deleta um Ator que nao existe")
        public void should_throw_exception_when_Ator_doesnt_exist() throws Exception {
            AtorModel atorModel = AtorModel.builder().actorId(1).firstName("testes").lastName("testes").lastUpdate(LocalDateTime.now()).build();
            Mockito.doThrow(new NegociosException(atorModel.getActorId())).when(atorService).delete(atorModel);

            mockMvc.perform(delete("/buscarAtor "+ atorModel.toString())
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNotFound());

        }


    }

