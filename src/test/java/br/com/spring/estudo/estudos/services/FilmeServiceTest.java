package br.com.spring.estudo.estudos.services;

import br.com.spring.estudo.estudos.model.CategoriaModel;
import br.com.spring.estudo.estudos.model.FilmeModel;
import br.com.spring.estudo.estudos.repositores.CategoriaRepository;
import br.com.spring.estudo.estudos.repositores.FilmesRepository;
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
public class FilmeServiceTest {
    @Mock
    private FilmesRepository filmesRespository;
    @InjectMocks
    private FilmeService filmeService;

    @Test
    @DisplayName("Salvando um Filme")
    public void whenSaveAtor_shouldReturnUmCategoria() {
        FilmeModel filmeModel = FilmeModel.builder().filmId(1).
                description("teste").languageId(1L).length(2).title("teste")
                .releaseYear("teste").rentalRate("teste").replacementCost("testes").build();
        when(filmesRespository.save(ArgumentMatchers.any(FilmeModel.class))).thenReturn(filmeModel);
        FilmeModel created = filmeService.save(filmeModel);
        assertThat(created.getFilmId()).isSameAs(filmeModel.getFilmId());
        verify(filmesRespository).save(filmeModel);
    }

    @Test
    @DisplayName("deletando um Categoria")
    public void whenGivenId_shouldDeleteCategoria_ifFound(){
        FilmeModel filmeModel = FilmeModel.builder().filmId(1).
                description("teste").languageId(1L).length(2).title("teste")
                .releaseYear("teste").rentalRate("teste").replacementCost("testes").build();
        lenient().when(filmesRespository.findById(filmeModel.getFilmId())).thenReturn(Optional.of(filmeModel));
        filmeService.delete(filmeModel);
        verify(filmesRespository).delete(filmeModel);
    }

    @Test(expected = RuntimeException.class)
    @DisplayName("deletando um Categoria com exception")
    public void should_throw_exception_when_Categoria_doesnt_exist() {
        FilmeModel filmeModel = null;
        when(filmesRespository.findById(filmeModel.getFilmId())).thenReturn(Optional.of(filmeModel));
        given(filmesRespository.findById(anyInt())).willReturn(Optional.ofNullable(null));
        verify(filmesRespository).delete(filmeModel);
    }


}
