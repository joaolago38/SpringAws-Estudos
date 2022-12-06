package br.com.spring.estudo.estudos.controllers;

import br.com.spring.estudo.estudos.controllers.categoria.CategoriaController;
import br.com.spring.estudo.estudos.controllers.cidade.CidadeController;
import br.com.spring.estudo.estudos.exception.NegociosException;
import br.com.spring.estudo.estudos.model.CategoriaModel;
import br.com.spring.estudo.estudos.model.CidadeModel;
import br.com.spring.estudo.estudos.services.CategoriaService;
import br.com.spring.estudo.estudos.services.CidadeService;
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
@WebMvcTest(CidadeController.class)
public class CidadeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CidadeService cidadeService;

        @Test
        @DisplayName("Salva um novo Cidade")
        public void createCidade_whenPostMethod() throws Exception {
            CidadeModel cidadeModel = CidadeModel.builder().cityid(1).countryId(2).lastUpdate(LocalDateTime.now()).build();
            given(cidadeService.save(cidadeModel)).willReturn(cidadeModel);
            mockMvc.perform(post("/buscarCidade")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(com.usersapi.endpoints.util.JsonUtil.toJson(cidadeModel)))
                    .andExpect(status().is4xxClientError());

        }

        @Test
        @DisplayName("Deleta um Cidade pelo id")
        public void removeCidadeById_whenDeleteMethod() throws Exception {
            CidadeModel cidadeModel = CidadeModel.builder().cityid(1).countryId(2).lastUpdate(LocalDateTime.now()).build();
            doNothing().when(cidadeService).delete(cidadeModel);
            mockMvc.perform(delete("/buscarCidade/" + cidadeModel.getCityid().toString())
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("Deleta um Cidade que nao existe")
        public void should_throw_exception_when_Cidade_doesnt_exist() throws Exception {
            CidadeModel cidadeModel = CidadeModel.builder().cityid(null).countryId(2).lastUpdate(LocalDateTime.now()).build();
            Mockito.doThrow(new NegociosException(cidadeModel.getCityid())).when(cidadeService).delete(cidadeModel);

            mockMvc.perform(delete("/buscarCidade/" + cidadeModel.getCityid().toString())
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNotFound());

        }


    }

