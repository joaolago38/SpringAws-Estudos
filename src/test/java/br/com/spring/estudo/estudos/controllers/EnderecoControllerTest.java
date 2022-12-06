package br.com.spring.estudo.estudos.controllers;

import br.com.spring.estudo.estudos.controllers.endereco.EnderecoController;
import br.com.spring.estudo.estudos.exception.NegociosException;
import br.com.spring.estudo.estudos.model.CategoriaModel;
import br.com.spring.estudo.estudos.model.EnderecoModel;
import br.com.spring.estudo.estudos.services.CategoriaService;
import br.com.spring.estudo.estudos.services.EnderecoService;
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
@WebMvcTest(EnderecoController.class)
public class EnderecoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EnderecoService enderecoService;

        @Test
        @DisplayName("Salva um novo Endereco")
        public void createEndereco_whenPostMethod() throws Exception {
            EnderecoModel enderecoModel = EnderecoModel.builder().address("Rua Jau")
                    .addressId(1).address2("Rua Mercurio").phone("019999889059")
                    .lastUpdate(LocalDateTime.now()).cityId(1L).postalCode("37770743").district("Parque Universitario").build();
            given(enderecoService.save(enderecoModel)).willReturn(enderecoModel);
            mockMvc.perform(post("/buscarEndereco")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(com.usersapi.endpoints.util.JsonUtil.toJson(enderecoModel)))
                    .andExpect(status().isCreated());

        }

        @Test
        @DisplayName("Deleta um Categoria pelo id")
        public void removeEnderecoById_whenDeleteMethod() throws Exception {
            EnderecoModel enderecoModel = EnderecoModel.builder().address("Rua Jau")
                    .addressId(1).address2("Rua Mercurio").phone("019999889059")
                    .lastUpdate(LocalDateTime.now()).cityId(1L).postalCode("37770743").district("Parque Universitario").build();
            doNothing().when(enderecoService).delete(enderecoModel);
            mockMvc.perform(delete("/buscarEndereco/" + enderecoModel.toString())
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("Deleta um Categoria que nao existe")
        public void should_throw_exception_when_Categoria_doesnt_exist() throws Exception {
            EnderecoModel enderecoModel = EnderecoModel.builder().address("Rua Jau")
                    .addressId(1).address2("Rua Mercurio").phone("019999889059")
                    .lastUpdate(LocalDateTime.now()).cityId(1L).postalCode("37770743").district("Parque Universitario").build();
            Mockito.doThrow(new NegociosException(enderecoModel.getAddressId())).when(enderecoService).delete(enderecoModel);

            mockMvc.perform(delete("/buscarEndereco "+ enderecoModel.toString())
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNotFound());

        }


    }

