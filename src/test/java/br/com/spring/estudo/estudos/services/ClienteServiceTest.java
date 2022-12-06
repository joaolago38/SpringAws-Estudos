package br.com.spring.estudo.estudos.services;

import br.com.spring.estudo.estudos.model.CategoriaModel;
import br.com.spring.estudo.estudos.model.ClienteModel;
import br.com.spring.estudo.estudos.repositores.CategoriaRepository;
import br.com.spring.estudo.estudos.repositores.ClienteRepository;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ClienteServiceTest {
    @Mock
    private ClienteRepository clienteRespository;
    @InjectMocks
    private ClienteService clienteService;

    @Test
    @DisplayName("Salvando um Cliente")
    public void whenSaveAtor_shouldReturnUmCliente() {
        ClienteModel clienteModel = ClienteModel.builder().active(true).activebool(true).addressId(1).email("joaolago35@gmail.com")
        .lastName("lago").createDate(LocalDateTime.now()).customerId(1).storeId(2).build();
        when(clienteRespository.save(ArgumentMatchers.any(ClienteModel.class))).thenReturn(clienteModel);
        ClienteModel created = clienteService.save(clienteModel);
        assertThat(created.getCustomerId()).isSameAs(clienteModel.getCustomerId());
        verify(clienteRespository).save(clienteModel);
    }

    @Test
    @DisplayName("deletando um Cliente")
    public void whenGivenId_shouldDeleteCliente_ifFound(){
        ClienteModel clienteModel = ClienteModel.builder().active(true).activebool(true).addressId(1).email("joaolago35@gmail.com")
                .lastName("lago").createDate(LocalDateTime.now()).customerId(1).storeId(2).build();
        lenient().when(clienteRespository.findById(clienteModel.getCustomerId())).thenReturn(Optional.of(clienteModel));
        clienteService.delete(clienteModel);
        verify(clienteRespository).delete(clienteModel);
    }

    @Test(expected = RuntimeException.class)
    @DisplayName("deletando um Cliente com exception")
    public void should_throw_exception_when_Cliente_doesnt_exist() {
        ClienteModel clienteModel = null;
        when(clienteRespository.findById(clienteModel.getCustomerId())).thenReturn(Optional.of(clienteModel));
        given(clienteRespository.findById(anyInt())).willReturn(Optional.ofNullable(null));
        verify(clienteRespository).delete(clienteModel);
    }


}
