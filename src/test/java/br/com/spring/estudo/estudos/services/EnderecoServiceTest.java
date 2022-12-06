package br.com.spring.estudo.estudos.services;

import br.com.spring.estudo.estudos.model.CategoriaModel;
import br.com.spring.estudo.estudos.model.EnderecoModel;
import br.com.spring.estudo.estudos.repositores.CategoriaRepository;
import br.com.spring.estudo.estudos.repositores.EnderecoRespository;
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
public class EnderecoServiceTest {
    @Mock
    private EnderecoRespository enderecoRespository;
    @InjectMocks
    private EnderecoService enderecoService;

    @Test
    @DisplayName("Salvando um Categoria")
    public void whenSaveAtor_shouldReturnUmCategoria() {
        EnderecoModel enderecoModel = EnderecoModel.builder().address("Rua Jau")
                .addressId(1).address2("Rua Mercurio").phone("019999889059")
                .lastUpdate(LocalDateTime.now()).cityId(1L).postalCode("37770743").district("Parque Universitario").build();
        when(enderecoRespository.save(ArgumentMatchers.any(EnderecoModel.class))).thenReturn(enderecoModel);
        EnderecoModel created = enderecoService.save(enderecoModel);
        assertThat(created.getAddressId()).isSameAs(enderecoModel.getAddressId());
        verify(enderecoRespository).save(enderecoModel);
    }

    @Test
    @DisplayName("deletando um Categoria")
    public void whenGivenId_shouldDeleteCategoria_ifFound(){
        EnderecoModel enderecoModel = EnderecoModel.builder().address("Rua Jau")
                .addressId(1).address2("Rua Mercurio").phone("019999889059")
                .lastUpdate(LocalDateTime.now()).cityId(1L).postalCode("37770743").district("Parque Universitario").build();
        lenient().when(enderecoRespository.findById(enderecoModel.getAddressId())).thenReturn(Optional.of(enderecoModel));
        enderecoService.delete(enderecoModel);
        verify(enderecoRespository).delete(enderecoModel);
    }

    @Test(expected = RuntimeException.class)
    @DisplayName("deletando um Categoria com exception")
    public void should_throw_exception_when_Categoria_doesnt_exist() {
        EnderecoModel enderecoModel = null;
        when(enderecoRespository.findById(enderecoModel.getAddressId())).thenReturn(Optional.of(enderecoModel));
        given(enderecoRespository.findById(anyInt())).willReturn(Optional.ofNullable(null));
        verify(enderecoRespository).delete(enderecoModel);
    }


}
