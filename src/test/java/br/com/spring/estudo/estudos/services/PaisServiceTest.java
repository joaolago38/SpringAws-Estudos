package br.com.spring.estudo.estudos.services;

import br.com.spring.estudo.estudos.model.PaisModel;
import br.com.spring.estudo.estudos.repositores.PaisRepository;
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
public class PaisServiceTest {
    @Mock
    private PaisRepository paisRespository;
    @InjectMocks
    private PaisService paisService;

    @Test
    @DisplayName("Salvando um Pais")
    public void whenSavePais_shouldReturnUmCategoria() {
        PaisModel paisModel = PaisModel.builder().country("Brasil").countryId(1).lastUpdate(LocalDateTime.now()).build();
        when(paisRespository.save(ArgumentMatchers.any(PaisModel.class))).thenReturn(paisModel);
        PaisModel created = paisService.save(paisModel);
        assertThat(created.getCountryId()).isSameAs(paisModel.getCountryId());
        verify(paisRespository).save(paisModel);
    }

    @Test
    @DisplayName("deletando um Pais")
    public void whenGivenId_shouldDeletePais_ifFound(){
        PaisModel paisModel = PaisModel.builder().country("Brasil").countryId(1).lastUpdate(LocalDateTime.now()).build();
        lenient().when(paisRespository.findById(paisModel.getCountryId())).thenReturn(Optional.of(paisModel));
        paisService.delete(paisModel);
        verify(paisRespository).delete(paisModel);
    }

    @Test(expected = RuntimeException.class)
    @DisplayName("deletando um Pais com exception")
    public void should_throw_exception_when_Categoria_doesnt_exist() {
        PaisModel paisModel = null;
        when(paisRespository.findById(paisModel.getCountryId())).thenReturn(Optional.of(paisModel));
        given(paisRespository.findById(anyInt())).willReturn(Optional.ofNullable(null));
        verify(paisRespository).delete(paisModel);
    }


}
