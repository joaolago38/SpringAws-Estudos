package br.com.spring.estudo.estudos.services;

import br.com.spring.estudo.estudos.model.LinguagemModel;
import br.com.spring.estudo.estudos.repositores.LinguagemRespository;
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
public class LinguagemServiceTest {
    @Mock
    private LinguagemRespository linguagemRespository;
    @InjectMocks
    private LinguagemService linguagemService;

    @Test
    @DisplayName("Salvando um Linguagem")
    public void whenSaveAtor_shouldReturnUmCategoria() {
        LinguagemModel linguagemModel = LinguagemModel.builder().languageId(1).name("testes").lastUpdate(LocalDateTime.now()).build();
        when(linguagemRespository.save(ArgumentMatchers.any(LinguagemModel.class))).thenReturn(linguagemModel);
        LinguagemModel created = linguagemService.save(linguagemModel);
        assertThat(created.getLanguageId()).isSameAs(linguagemModel.getLanguageId());
        verify(linguagemRespository).save(linguagemModel);
    }

    @Test
    @DisplayName("deletando um Linguagem")
    public void whenGivenId_shouldDeleteCategoria_ifFound(){
        LinguagemModel linguagemModel = LinguagemModel.builder().languageId(1).name("testes").lastUpdate(LocalDateTime.now()).build();
        lenient().when(linguagemRespository.findById(linguagemModel.getLanguageId())).thenReturn(Optional.of(linguagemModel));
        linguagemService.delete(linguagemModel);
        verify(linguagemRespository).delete(linguagemModel);
    }

    @Test(expected = RuntimeException.class)
    @DisplayName("deletando um Linguagem com exception")
    public void should_throw_exception_when_Categoria_doesnt_exist() {
        LinguagemModel linguagemModel = null;
        when(linguagemRespository.findById(linguagemModel.getLanguageId())).thenReturn(Optional.of(linguagemModel));
        given(linguagemRespository.findById(anyInt())).willReturn(Optional.ofNullable(null));
        verify(linguagemRespository).delete(linguagemModel);
    }


}
