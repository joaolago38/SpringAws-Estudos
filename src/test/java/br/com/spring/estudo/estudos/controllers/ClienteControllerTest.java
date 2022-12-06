package br.com.spring.estudo.estudos.controllers;

import br.com.spring.estudo.estudos.controllers.categoria.CategoriaController;
import br.com.spring.estudo.estudos.controllers.clientes.ClientesController;
import br.com.spring.estudo.estudos.exception.NegociosException;
import br.com.spring.estudo.estudos.model.CategoriaModel;
import br.com.spring.estudo.estudos.model.ClienteModel;
import br.com.spring.estudo.estudos.services.CategoriaService;
import br.com.spring.estudo.estudos.services.ClienteService;
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
@WebMvcTest(ClientesController.class)
public class ClienteControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteService clienteService;

        @Test
        @DisplayName("Salva um novo Cliente")
        public void createCliente_whenPostMethod() throws Exception {
            ClienteModel clienteModel = ClienteModel.builder().active(true).activebool(true).addressId(1).email("joaolago35@gmail.com")
                    .lastName("lago").createDate(LocalDateTime.now()).customerId(1).storeId(2).build();
            given(clienteService.save(clienteModel)).willReturn(clienteModel);
            mockMvc.perform(post("/buscarCliente")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(com.usersapi.endpoints.util.JsonUtil.toJson(clienteModel)))
                    .andExpect(status().is4xxClientError());

        }

        @Test
        @DisplayName("Deleta um Cliente pelo id")
        public void removeClienteById_whenDeleteMethod() throws Exception {
            ClienteModel clienteModel = ClienteModel.builder().active(true).activebool(true).addressId(1).email("joaolago35@gmail.com")
                    .lastName("lago").createDate(LocalDateTime.now()).customerId(1).storeId(2).build();
            doNothing().when(clienteService).delete(clienteModel);
            mockMvc.perform(delete("/buscarCliente/" + clienteModel.toString())
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("Deleta um Cliente que nao existe")
        public void should_throw_exception_when_Cliente_doesnt_exist() throws Exception {
            ClienteModel clienteModel = ClienteModel.builder().active(true).activebool(true).addressId(1).email("joaolago35@gmail.com")
                    .lastName("lago").createDate(LocalDateTime.now()).customerId(1).storeId(2).build();
            Mockito.doThrow(new NegociosException(clienteModel.getCustomerId())).when(clienteService).delete(clienteModel);

            mockMvc.perform(delete("/clienteModel "+ clienteModel.toString())
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNotFound());

        }


    }

