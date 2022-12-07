package br.com.spring.estudo.estudos.controllers;

import br.com.spring.estudo.estudos.controllers.categoria.CategoriaController;
import br.com.spring.estudo.estudos.controllers.filme.ator.FilmeAtorController;
import br.com.spring.estudo.estudos.exception.NegociosException;
import br.com.spring.estudo.estudos.model.CategoriaModel;
import br.com.spring.estudo.estudos.model.FilmeAtorModel;
import br.com.spring.estudo.estudos.services.CategoriaService;
import br.com.spring.estudo.estudos.services.FilmeAtorService;
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
@WebMvcTest(FilmeAtorController.class)
public class FilmeAtorControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FilmeAtorService filmeAtorService;

        @Test
        @DisplayName("Salva um novo FilmeAtor ")
        public void createFilmeAtor_whenPostMethod() throws Exception {
            FilmeAtorModel filmeAtorModel = FilmeAtorModel.builder().filmId(1).actorId(1).filmId(1).lastUpdate(LocalDateTime.now()).build();
            given(filmeAtorService.save(filmeAtorModel)).willReturn(filmeAtorModel);
            mockMvc.perform(post("/buscarCategory")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(com.usersapi.endpoints.util.JsonUtil.toJson(filmeAtorModel)))
                    .andExpect(status().is4xxClientError());

        }

        @Test
        @DisplayName("Deleta um Categoria pelo id")
        public void removeFilmeAtorById_whenDeleteMethod() throws Exception {
            FilmeAtorModel filmeAtorModel = FilmeAtorModel.builder().filmId(1).actorId(1).filmId(1).lastUpdate(LocalDateTime.now()).build();
            doNothing().when(filmeAtorService).delete(filmeAtorModel);
            mockMvc.perform(delete("/buscarFilmeAtor/" + filmeAtorModel.toString())
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("Deleta um Categoria que nao existe")
        public void should_throw_exception_when_Categoria_doesnt_exist() throws Exception {
            FilmeAtorModel filmeAtorModel = FilmeAtorModel.builder().filmId(1).actorId(1).filmId(1).lastUpdate(LocalDateTime.now()).build();
            Mockito.doThrow(new NegociosException(filmeAtorModel.getFilmId())).when(filmeAtorService).delete(filmeAtorModel);

            mockMvc.perform(delete("/buscarFilmeAtor "+ filmeAtorModel.toString())
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNotFound());

        }


    }

