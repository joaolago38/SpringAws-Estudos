package br.com.spring.estudo.estudos.services;

import br.com.spring.estudo.estudos.model.CategoriaModel;
import br.com.spring.estudo.estudos.repositores.CategoriaRepository;
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
public class FilmeCategoriaServiceTest {
    @Mock
    private CategoriaRepository categoriaRespository;
    @InjectMocks
    private CategoriaService categoriaService;

    @Test
    @DisplayName("Salvando um Categoria")
    public void whenSaveAtor_shouldReturnUmCategoria() {
        CategoriaModel categoriaModel = CategoriaModel.builder().categoryId(1).lastUpdate(LocalDateTime.now()).build();
        when(categoriaRespository.save(ArgumentMatchers.any(CategoriaModel.class))).thenReturn(categoriaModel);
        CategoriaModel created = categoriaService.save(categoriaModel);
        assertThat(created.getCategoryId()).isSameAs(categoriaModel.getCategoryId());
        verify(categoriaRespository).save(categoriaModel);
    }

    @Test
    @DisplayName("deletando um Categoria")
    public void whenGivenId_shouldDeleteCategoria_ifFound(){
        CategoriaModel categoriaModel = CategoriaModel.builder().categoryId(1).lastUpdate(LocalDateTime.now()).build();
        lenient().when(categoriaRespository.findById(categoriaModel.getCategoryId())).thenReturn(Optional.of(categoriaModel));
        categoriaService.delete(categoriaModel);
        verify(categoriaRespository).delete(categoriaModel);
    }

    @Test(expected = RuntimeException.class)
    @DisplayName("deletando um Categoria com exception")
    public void should_throw_exception_when_Categoria_doesnt_exist() {
        CategoriaModel categoriaModel= null;
        when(categoriaRespository.findById(categoriaModel.getCategoryId())).thenReturn(Optional.of(categoriaModel));
        given(categoriaRespository.findById(anyInt())).willReturn(Optional.ofNullable(null));
        verify(categoriaRespository).delete(categoriaModel);
    }


}
