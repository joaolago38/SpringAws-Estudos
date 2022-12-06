package br.com.spring.estudo.estudos.services;

import br.com.spring.estudo.estudos.model.CategoriaModel;
import br.com.spring.estudo.estudos.model.CidadeModel;
import br.com.spring.estudo.estudos.repositores.CategoriaRepository;
import br.com.spring.estudo.estudos.repositores.CidadeRepository;
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
public class CidadeServiceTest {
    @Mock
    private CidadeRepository cidadeRespository;
    @InjectMocks
    private CidadeService cidadeService;

    @Test
    @DisplayName("Salvando um Cidade")
    public void whenSaveAtor_shouldReturnUmCidade() {
        CidadeModel cidadeModel = CidadeModel.builder().cityid(1).countryId(2).lastUpdate(LocalDateTime.now()).build();
        when(cidadeRespository.save(ArgumentMatchers.any(CidadeModel.class))).thenReturn(cidadeModel);
        CidadeModel created = cidadeService.save(cidadeModel);
        assertThat(created.getCityid()).isSameAs(cidadeModel.getCityid());
        verify(cidadeRespository).save(cidadeModel);
    }

    @Test
    @DisplayName("deletando um Cidade")
    public void whenGivenId_shouldDeleteCidade_ifFound(){
        CidadeModel cidadeModel = CidadeModel.builder().cityid(1).countryId(2).lastUpdate(LocalDateTime.now()).build();
        lenient().when(cidadeRespository.findById(cidadeModel.getCityid())).thenReturn(Optional.of(cidadeModel));
        cidadeService.delete(cidadeModel);
        verify(cidadeRespository).delete(cidadeModel);
    }

    @Test(expected = RuntimeException.class)
    @DisplayName("deletando um Cidade com exception")
    public void should_throw_exception_when_Categoria_doesnt_exist() {
        CidadeModel cidadeModel= null;
        when(cidadeRespository.findById(cidadeModel.getCityid())).thenReturn(Optional.of(cidadeModel));
        given(cidadeRespository.findById(anyInt())).willReturn(Optional.ofNullable(null));
        verify(cidadeRespository).delete(cidadeModel);
    }


}
