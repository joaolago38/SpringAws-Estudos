package br.com.spring.estudo.estudos.services;

import br.com.spring.estudo.estudos.model.ArmazenamentoModel;
import br.com.spring.estudo.estudos.model.AtorModel;
import br.com.spring.estudo.estudos.repositores.ArmazenamentoRepository;
import br.com.spring.estudo.estudos.repositores.AtorRespository;
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
public class AtorServiceTest {
    @Mock
    private AtorRespository atorRespository;
    @InjectMocks
    private AtorService atorService;

    @Test
    @DisplayName("Salvando um Ator")
    public void whenSaveAtor_shouldReturnUmArmazenamento() {
        AtorModel atorModel = AtorModel.builder().actorId(1).firstName("testes").lastName("testes").lastUpdate(LocalDateTime.now()).build();
        when(atorRespository.save(ArgumentMatchers.any(AtorModel.class))).thenReturn(atorModel);
        AtorModel created = atorService.save(atorModel);
        assertThat(created.getActorId()).isSameAs(atorModel.getActorId());
        verify(atorRespository).save(atorModel);
    }

    @Test
    @DisplayName("deletando um Ator")
    public void whenGivenId_shouldDeleteAtor_ifFound(){
        AtorModel atorModel = AtorModel.builder().actorId(1).firstName("testes").lastName("testes").lastUpdate(LocalDateTime.now()).build();
        lenient().when(atorRespository.findById(atorModel.getActorId())).thenReturn(Optional.of(atorModel));
        atorService.delete(atorModel);
        verify(atorRespository).delete(atorModel);
    }

    @Test(expected = RuntimeException.class)
    @DisplayName("deletando um Ator com exception")
    public void should_throw_exception_when_Armazenamento_doesnt_exist() {
        AtorModel atorModel= null;
        when(atorRespository.findById(atorModel.getActorId())).thenReturn(Optional.of(atorModel));
        given(atorRespository.findById(anyInt())).willReturn(Optional.ofNullable(null));
        verify(atorRespository).delete(atorModel);
    }


}
