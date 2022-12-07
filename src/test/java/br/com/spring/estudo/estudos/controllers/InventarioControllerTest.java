package br.com.spring.estudo.estudos.controllers;

import br.com.spring.estudo.estudos.controllers.inventario.InventarioController;
import br.com.spring.estudo.estudos.exception.NegociosException;
import br.com.spring.estudo.estudos.model.InventarioModel;
import br.com.spring.estudo.estudos.services.InventarioService;
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
@WebMvcTest(InventarioController.class)
public class InventarioControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InventarioService inventarioService;

        @Test
        @DisplayName("Salva um novo Inventario")
        public void createInventario_whenPostMethod() throws Exception {
            InventarioModel inventarioModel = InventarioModel.builder().filmId(1).inventoryId(1).storeId(1).lastUpdate(LocalDateTime.now()).build();
            given(inventarioService.save(inventarioModel)).willReturn(inventarioModel);
            mockMvc.perform(post("/buscarInventario")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(com.usersapi.endpoints.util.JsonUtil.toJson(inventarioModel)))
                    .andExpect(status().isCreated());

        }

        @Test
        @DisplayName("Deleta um Inventario pelo id")
        public void removeInventarioById_whenDeleteMethod() throws Exception {
            InventarioModel inventarioModel = InventarioModel.builder().filmId(1).inventoryId(1).storeId(1).lastUpdate(LocalDateTime.now()).build();
            doNothing().when(inventarioService).delete(inventarioModel);
            mockMvc.perform(delete("/buscarInventario/" + inventarioModel.toString())
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().is5xxServerError());
        }

        @Test
        @DisplayName("Deleta um Inventario que nao existe")
        public void should_throw_exception_when_Inventario_doesnt_exist() throws Exception {
            InventarioModel inventarioModel = InventarioModel.builder().filmId(1).inventoryId(1).storeId(1).lastUpdate(LocalDateTime.now()).build();
            Mockito.doThrow(new NegociosException(inventarioModel.getFilmId())).when(inventarioService).delete(inventarioModel);

            mockMvc.perform(delete("/buscarInventario " + inventarioModel.toString())
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().is4xxClientError());
        }


    }

