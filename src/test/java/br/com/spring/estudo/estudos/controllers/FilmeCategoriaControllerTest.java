package br.com.spring.estudo.estudos.controllers;

import br.com.spring.estudo.estudos.controllers.filme.categoria.FilmeCategoriaController;
import br.com.spring.estudo.estudos.exception.NegociosException;
import br.com.spring.estudo.estudos.model.CategoriaModel;
import br.com.spring.estudo.estudos.model.FilmeCategoriaModel;
import br.com.spring.estudo.estudos.services.CategoriaService;
import br.com.spring.estudo.estudos.services.FilmeCategoriaService;
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
@WebMvcTest(FilmeCategoriaController.class)
public class FilmeCategoriaControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FilmeCategoriaService filmeCategoriaService;

        @Test
        @DisplayName("Salva um novo FilmeCategoria")
        public void createCategoria_whenPostMethod() throws Exception {
            FilmeCategoriaModel filmeCategoriaModel = FilmeCategoriaModel.builder().filmId(1).categoryId(1).lastUpdate(LocalDateTime.now()).build();
            given(filmeCategoriaService.save(filmeCategoriaModel)).willReturn(filmeCategoriaModel);
            mockMvc.perform(post("/buscarCategoria")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(com.usersapi.endpoints.util.JsonUtil.toJson(filmeCategoriaModel)))
                    .andExpect(status().isCreated());

        }

        @Test
        @DisplayName("Deleta um FilmeCategoria pelo id")
        public void removeCategoriaById_whenDeleteMethod() throws Exception {
            FilmeCategoriaModel filmeCategoriaModel = FilmeCategoriaModel.builder().filmId(1).categoryId(1).lastUpdate(LocalDateTime.now()).build();
            doNothing().when(filmeCategoriaService).delete(filmeCategoriaModel);
            mockMvc.perform(delete("/buscarCategoria/" + filmeCategoriaModel.toString())
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().is5xxServerError());
        }

        @Test
        @DisplayName("Deleta um FilmeCategoria que nao existe")
        public void should_throw_exception_when_Categoria_doesnt_exist() throws Exception {
            FilmeCategoriaModel filmeCategoriaModel = FilmeCategoriaModel.builder().filmId(1).categoryId(1).lastUpdate(LocalDateTime.now()).build();
            Mockito.doThrow(new NegociosException(filmeCategoriaModel.getCategoryId())).when(filmeCategoriaService).delete(filmeCategoriaModel);

            mockMvc.perform(delete("/buscarAtor "+ filmeCategoriaModel.toString())
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNotFound());

        }


    }

