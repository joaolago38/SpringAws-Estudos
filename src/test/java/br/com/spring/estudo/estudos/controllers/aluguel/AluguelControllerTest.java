package br.com.spring.estudo.estudos.controllers.aluguel;

import br.com.spring.estudo.estudos.exception.NegociosException;
import br.com.spring.estudo.estudos.model.AluguelModel;
import br.com.spring.estudo.estudos.services.AluguelService;
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
@WebMvcTest(AluguelController.class)
public class AluguelControllerTest  {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AluguelService aluguelService;

        @Test
        @DisplayName("Salva um novo Aluguel")
        public void createAluguelModel_whenPostMethod() throws Exception {
            AluguelModel aluguelModel = AluguelModel.builder().rentalId(1).customerId(1).inventoryId(1).lastUpdate(LocalDateTime.now()).rentalDate(LocalDateTime.now()).returnDate(LocalDateTime.now()).staffId(1).build();
            given(aluguelService.save(aluguelModel)).willReturn(aluguelModel);
            mockMvc.perform(post("/buscarAluguel")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(com.usersapi.endpoints.util.JsonUtil.toJson(aluguelModel)))
                    .andExpect(status().isBadRequest());

        }

        @Test
        @DisplayName("Deleta um Aluguel pelo id")
        public void removeUserById_whenDeleteMethod() throws Exception {
            AluguelModel aluguelModel = AluguelModel.builder().rentalId(1).customerId(1).inventoryId(1).lastUpdate(LocalDateTime.now()).rentalDate(LocalDateTime.now()).returnDate(LocalDateTime.now()).staffId(1).build();

            doNothing().when(aluguelService).delete(aluguelModel);

            mockMvc.perform(delete("/buscarAluguel/" + aluguelModel.toString())
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("Deleta um Aluguel que nao existe")
        public void should_throw_exception_when_aluguel_doesnt_exist() throws Exception {
            AluguelModel aluguelModel = AluguelModel.builder().rentalId(1).inventoryId(1).lastUpdate(LocalDateTime.now()).rentalDate(LocalDateTime.now()).returnDate(LocalDateTime.now()).staffId(1).build();

            Mockito.doThrow(new NegociosException(aluguelModel.getRentalId())).when(aluguelService).delete(aluguelModel);

            mockMvc.perform(delete("/buscarAluguel "+ aluguelModel.toString())
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNotFound());

        }


    }

