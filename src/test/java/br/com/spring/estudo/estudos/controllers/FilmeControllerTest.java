package br.com.spring.estudo.estudos.controllers;

import br.com.spring.estudo.estudos.controllers.filme.FilmeController;
import br.com.spring.estudo.estudos.exception.NegociosException;
import br.com.spring.estudo.estudos.model.FilmeModel;
import br.com.spring.estudo.estudos.services.FilmeService;
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

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(FilmeController.class)
public class FilmeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FilmeService filmeService;

        @Test
        @DisplayName("Salva um novo Filme")
        public void createFilme_whenPostMethod() throws Exception {
            FilmeModel filmeModel = FilmeModel.builder().filmId(1).
                    description("teste").languageId(1L).length(2).title("teste")
                    .releaseYear("teste").rentalRate("teste").replacementCost("testes").build();
            given(filmeService.save(filmeModel)).willReturn(filmeModel);
            mockMvc.perform(post("/buscarFilme")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(com.usersapi.endpoints.util.JsonUtil.toJson(filmeModel)))
                    .andExpect(status().is4xxClientError());

        }

        @Test
        @DisplayName("Deleta um Filme pelo id")
        public void removeFilmeById_whenDeleteMethod() throws Exception {
            FilmeModel filmeModel = FilmeModel.builder().filmId(1).
                    description("teste").languageId(1L).length(2).title("teste")
                    .releaseYear("teste").rentalRate("teste").replacementCost("testes").build();
            doNothing().when(filmeService).delete(filmeModel);
            mockMvc.perform(delete("/buscarFilme/" + filmeModel.toString())
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("Deleta um Filme que nao existe")
        public void should_throw_exception_when_Filme_doesnt_exist() throws Exception {
            FilmeModel filmeModel = FilmeModel.builder().filmId(1).
                    description("teste").languageId(1L).length(2).title("teste")
                    .releaseYear("teste").rentalRate("teste").replacementCost("testes").build();
            Mockito.doThrow(new NegociosException(filmeModel.getFilmId())).when(filmeService).delete(filmeModel);

            mockMvc.perform(delete("/buscarAtor "+ filmeModel.toString())
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNotFound());

        }


    }

