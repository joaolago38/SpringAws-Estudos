package br.com.spring.estudo.estudos.services;

import br.com.spring.estudo.estudos.model.CategoriaModel;
import br.com.spring.estudo.estudos.model.PagamentoModel;
import br.com.spring.estudo.estudos.repositores.CategoriaRepository;
import br.com.spring.estudo.estudos.repositores.PagamentoRepository;
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
public class PagamentoServiceTest {
    @Mock
    private PagamentoRepository pagamentoRepository;
    @InjectMocks
    private PagamentoService pagamentoService;

    @Test
    @DisplayName("Salvando um Pagamento")
    public void whenSavePagamento_shouldReturnUmCategoria() {
        PagamentoModel pagamentoModel = PagamentoModel.builder().amount(1L).customerId(1L).build();
        when(pagamentoRepository.save(ArgumentMatchers.any(PagamentoModel.class))).thenReturn(pagamentoModel);
        PagamentoModel created = pagamentoService.save(pagamentoModel);
        assertThat(created.getPaymenId()).isSameAs(pagamentoModel.getPaymenId());
        verify(pagamentoRepository).save(pagamentoModel);
    }

    @Test
    @DisplayName("deletando um Pagamento")
    public void whenGivenId_shouldDeletePagamento_ifFound(){
        PagamentoModel pagamentoModel = PagamentoModel.builder().amount(1L).customerId(1L).build();
        lenient().when(pagamentoRepository.findById(pagamentoModel.getPaymenId())).thenReturn(Optional.of(pagamentoModel));
        pagamentoService.delete(pagamentoModel);
        verify(pagamentoRepository).delete(pagamentoModel);
    }

    @Test(expected = RuntimeException.class)
    @DisplayName("deletando um Pagamento com exception")
    public void should_throw_exception_when_Pagamentodoesnt_exist() {
        PagamentoModel pagamentoModell= null;
        when(pagamentoRepository.findById(pagamentoModell.getPaymenId())).thenReturn(Optional.of(pagamentoModell));
        given(pagamentoRepository.findById(anyInt())).willReturn(Optional.ofNullable(null));
        verify(pagamentoRepository).delete(pagamentoModell);
    }


}
