package br.com.spring.estudo.estudos.controllers.aluguel;

import br.com.spring.estudo.estudos.model.AluguelModel;
import br.com.spring.estudo.estudos.services.AluguelService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@RunWith(SpringRunner.class)
@WebMvcTest(AluguelController.class)
public class AluguelControllerTest  {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AluguelService aluguelService;

    @Test
    public void createAluguelModel_whenPostMethod() throws Exception {
        AluguelModel aluguelModel = AluguelModel.builder().rentalId(1).customerId(1).inventoryId(1).lastUpdate(LocalDateTime.now()).rentalDate(LocalDateTime.now()).returnDate(LocalDateTime.now()).staffId(1).build();


        given(aluguelService.save(aluguelModel)).willReturn(aluguelModel);

        mockMvc.perform(post("/buscarAluguel")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(com.usersapi.endpoints.util.JsonUtil.toJson(aluguelModel)))
                .andExpect(status().isCreated());

    }
    }

