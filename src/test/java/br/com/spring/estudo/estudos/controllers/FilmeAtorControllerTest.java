package br.com.spring.estudo.estudos.controllers;

import br.com.spring.estudo.estudos.controllers.categoria.CategoriaController;
import br.com.spring.estudo.estudos.exception.NegociosException;
import br.com.spring.estudo.estudos.model.CategoriaModel;
import br.com.spring.estudo.estudos.services.CategoriaService;
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
@WebMvcTest(CategoriaController.class)
public class FilmeAtorControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoriaService categoriaService;

        @Test
        @DisplayName("Salva um novo Categoria")
        public void createCategoria_whenPostMethod() throws Exception {
            CategoriaModel categoriaModel = CategoriaModel.builder().categoryId(1).lastUpdate(LocalDateTime.now()).build();
            given(categoriaService.save(categoriaModel)).willReturn(categoriaModel);
            mockMvc.perform(post("/buscarCategory")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(com.usersapi.endpoints.util.JsonUtil.toJson(categoriaModel)))
                    .andExpect(status().is4xxClientError());

        }

        @Test
        @DisplayName("Deleta um Categoria pelo id")
        public void removeCategoriaById_whenDeleteMethod() throws Exception {
            CategoriaModel categoriaModel = CategoriaModel.builder().categoryId(1).lastUpdate(LocalDateTime.now()).build();
            doNothing().when(categoriaService).delete(categoriaModel);
            mockMvc.perform(delete("/buscarCategory/" + categoriaModel.toString())
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("Deleta um Categoria que nao existe")
        public void should_throw_exception_when_Categoria_doesnt_exist() throws Exception {
            CategoriaModel categoriaModel = CategoriaModel.builder().categoryId(1).lastUpdate(LocalDateTime.now()).build();
            Mockito.doThrow(new NegociosException(categoriaModel.getCategoryId())).when(categoriaService).delete(categoriaModel);

            mockMvc.perform(delete("/buscarAtor "+ categoriaModel.toString())
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNotFound());

        }


    }

