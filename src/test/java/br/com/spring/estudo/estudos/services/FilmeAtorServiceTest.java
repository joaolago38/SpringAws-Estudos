package br.com.spring.estudo.estudos.services;

import br.com.spring.estudo.estudos.model.CategoriaModel;
import br.com.spring.estudo.estudos.model.FilmeAtorModel;
import br.com.spring.estudo.estudos.repositores.CategoriaRepository;
import br.com.spring.estudo.estudos.repositores.FilmeActorRespository;
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
public class FilmeAtorServiceTest {
    @Mock
    private FilmeActorRespository filmeAtorRespository;
    @InjectMocks
    private FilmeAtorService categoriaService;

    @Test
    @DisplayName("Salvando um FilmeAtor")
    public void whenSaveFilmeAtor_shouldReturnUmCategoria() {
        FilmeAtorModel filmeAtorModel = FilmeAtorModel.builder().filmId(1).actorId(1).filmId(1).lastUpdate(LocalDateTime.now()).build();
        when(filmeAtorRespository.save(ArgumentMatchers.any(FilmeAtorModel.class))).thenReturn(filmeAtorModel);
        FilmeAtorModel created = categoriaService.save(filmeAtorModel);
        assertThat(created.getFilmId()).isSameAs(filmeAtorModel.getFilmId());
        verify(filmeAtorRespository).save(filmeAtorModel);
    }

    @Test
    @DisplayName("deletando um FilmeAtor")
    public void whenGivenId_shouldDeleteFilmeAtor_ifFound(){
        FilmeAtorModel filmeAtorModel = FilmeAtorModel.builder().filmId(1).actorId(1).filmId(1).lastUpdate(LocalDateTime.now()).build();
        lenient().when(filmeAtorRespository.findById(filmeAtorModel.getFilmId())).thenReturn(Optional.of(filmeAtorModel));
        categoriaService.delete(filmeAtorModel);
        verify(filmeAtorRespository).delete(filmeAtorModel);
    }

    @Test(expected = RuntimeException.class)
    @DisplayName("deletando um FilmeAtor com exception")
    public void should_throw_exception_when_Categoria_doesnt_exist() {
        FilmeAtorModel filmeAtorModel= null;
        when(filmeAtorRespository.findById(filmeAtorModel.getFilmId())).thenReturn(Optional.of(filmeAtorModel));
        given(filmeAtorRespository.findById(anyInt())).willReturn(Optional.ofNullable(null));
        verify(filmeAtorRespository).delete(filmeAtorModel);
    }


}
