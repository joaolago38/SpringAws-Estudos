package br.com.spring.estudo.estudos.controllers;

import br.com.spring.estudo.estudos.controllers.categoria.CategoriaController;
import br.com.spring.estudo.estudos.controllers.linguagem.LinguagemController;
import br.com.spring.estudo.estudos.exception.NegociosException;
import br.com.spring.estudo.estudos.model.CategoriaModel;
import br.com.spring.estudo.estudos.model.LinguagemModel;
import br.com.spring.estudo.estudos.services.CategoriaService;
import br.com.spring.estudo.estudos.services.LinguagemService;
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
@WebMvcTest(LinguagemController.class)
public class LinguagemControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LinguagemService linguagemService;

        @Test
        @DisplayName("Salva um novo Linguagem")
        public void createLinguagem_whenPostMethod() throws Exception {
            LinguagemModel linguagemModel = LinguagemModel.builder().languageId(1).name("testes").lastUpdate(LocalDateTime.now()).build();
            given(linguagemService.save(linguagemModel)).willReturn(linguagemModel);
            mockMvc.perform(post("/buscarLinguagem")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(com.usersapi.endpoints.util.JsonUtil.toJson(linguagemModel)))
                    .andExpect(status().is4xxClientError());

        }

        @Test
        @DisplayName("Deleta um Linguagem pelo id")
        public void removeLinguagemById_whenDeleteMethod() throws Exception {
            LinguagemModel linguagemModel = LinguagemModel.builder().languageId(1).name("testes").lastUpdate(LocalDateTime.now()).build();
            doNothing().when(linguagemService).delete(linguagemModel);
            mockMvc.perform(delete("/buscarLinguagem/" + linguagemModel.toString())
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("Deleta um Linguagem que nao existe")
        public void should_throw_exception_when_Linguagem_doesnt_exist() throws Exception {
            LinguagemModel linguagemModel = LinguagemModel.builder().languageId(1).name("testes").lastUpdate(LocalDateTime.now()).build();
            Mockito.doThrow(new NegociosException(linguagemModel.getLanguageId())).when(linguagemService).delete(linguagemModel);

            mockMvc.perform(delete("/buscarLinguagem "+ linguagemModel.toString())
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNotFound());

        }


    }

