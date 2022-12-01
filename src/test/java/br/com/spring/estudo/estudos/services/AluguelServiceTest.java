package br.com.spring.estudo.estudos.services;

import br.com.spring.estudo.estudos.model.AluguelModel;
import br.com.spring.estudo.estudos.repositores.AluguelRepository;
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
public class AluguelServiceTest {
    @Mock
    private AluguelRepository aluguelRepository;

    @InjectMocks
    private AluguelService aluguelService;

    @Test
    @DisplayName("Salvando um aluguel")
    public void whenSaveUser_shouldReturnUmAluguel() {
        AluguelModel aluguelModel = AluguelModel.builder().rentalId(1).customerId(1).inventoryId(1).lastUpdate(LocalDateTime.now()).rentalDate(LocalDateTime.now()).returnDate(LocalDateTime.now()).staffId(1).build();
        when(aluguelRepository.save(ArgumentMatchers.any(AluguelModel.class))).thenReturn(aluguelModel);
        AluguelModel created = aluguelService.save(aluguelModel);
        assertThat(created.getRentalId()).isSameAs(aluguelModel.getRentalId());
        verify(aluguelRepository).save(aluguelModel);
    }

    @Test
    @DisplayName("deletando um aluguel")
    public void whenGivenId_shouldDeleteAluguel_ifFound(){
        AluguelModel aluguelModel = AluguelModel.builder().rentalId(1).customerId(1).inventoryId(1).lastUpdate(LocalDateTime.now()).rentalDate(LocalDateTime.now()).returnDate(LocalDateTime.now()).staffId(1).build();
        lenient().when(aluguelRepository.findById(aluguelModel.getRentalId())).thenReturn(Optional.of(aluguelModel));
        aluguelService.delete(aluguelModel);
        verify(aluguelRepository).delete(aluguelModel);
    }

    @Test(expected = RuntimeException.class)
    @DisplayName("deletando um aluguel com exception")
    public void should_throw_exception_when_Aluguel_doesnt_exist() {
        AluguelModel aluguelModel = null;
        when(aluguelRepository.findById(aluguelModel.getRentalId())).thenReturn(Optional.of(aluguelModel));

        given(aluguelRepository.findById(anyInt())).willReturn(Optional.ofNullable(null));
        verify(aluguelRepository).delete(aluguelModel);
    }

    @Test
    @DisplayName("deletando um aluguel com exception")
    public void whenGivenId_shouldUpdateAluguel_ifFound() {
        AluguelModel aluguelModel = AluguelModel.builder().rentalId(1).customerId(1).inventoryId(1).lastUpdate(LocalDateTime.now()).rentalDate(LocalDateTime.now()).returnDate(LocalDateTime.now()).staffId(1).build();
        aluguelModel.setInventoryId(4);

        given(aluguelRepository.findById(aluguelModel.getRentalId())).willReturn(Optional.of(aluguelModel));
        aluguelService.save(aluguelModel);

        verify(aluguelRepository).save(aluguelModel);

    }

}
