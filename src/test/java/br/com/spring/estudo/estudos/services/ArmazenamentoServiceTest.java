package br.com.spring.estudo.estudos.services;

import br.com.spring.estudo.estudos.model.ArmazenamentoModel;
import br.com.spring.estudo.estudos.repositores.ArmazenamentoRepository;
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
public class ArmazenamentoServiceTest {
    @Mock
    private ArmazenamentoRepository armazenamentoRepository;
    @InjectMocks
    private ArmazenamentoService armazenamentoService;

    @Test
    @DisplayName("Salvando um Armazenamento")
    public void whenSaveUser_shouldReturnUmArmazenamento() {
        ArmazenamentoModel armazenamentoModel = ArmazenamentoModel.builder().addressId(1L).storeId(1).managerStaffId(1).lastUpdate(LocalDateTime.now()).build();
        when(armazenamentoRepository.save(ArgumentMatchers.any(ArmazenamentoModel.class))).thenReturn(armazenamentoModel);
        ArmazenamentoModel created = armazenamentoService.save(armazenamentoModel);
        assertThat(created.getStoreId()).isSameAs(armazenamentoModel.getStoreId());
        verify(armazenamentoRepository).save(armazenamentoModel);
    }

    @Test
    @DisplayName("deletando um Armazenamento")
    public void whenGivenId_shouldDeleteArmazenamento_ifFound(){
        ArmazenamentoModel armazenamentoModel = ArmazenamentoModel.builder().addressId(1L).storeId(1).managerStaffId(1).lastUpdate(LocalDateTime.now()).build();
        lenient().when(armazenamentoRepository.findById(armazenamentoModel.getStoreId())).thenReturn(Optional.of(armazenamentoModel));
        armazenamentoService.delete(armazenamentoModel);
        verify(armazenamentoRepository).delete(armazenamentoModel);
    }

    @Test(expected = RuntimeException.class)
    @DisplayName("deletando um aluguel com exception")
    public void should_throw_exception_when_Armazenamento_doesnt_exist() {
        ArmazenamentoModel armazenamentoModel = null;
        when(armazenamentoRepository.findById(armazenamentoModel.getStoreId())).thenReturn(Optional.of(armazenamentoModel));
        given(armazenamentoRepository.findById(anyInt())).willReturn(Optional.ofNullable(null));
        verify(armazenamentoRepository).delete(armazenamentoModel);
    }


}
