package br.com.spring.estudo.estudos.services;

import br.com.spring.estudo.estudos.model.CategoriaModel;
import br.com.spring.estudo.estudos.model.InventarioModel;
import br.com.spring.estudo.estudos.repositores.CategoriaRepository;
import br.com.spring.estudo.estudos.repositores.InventarioRepository;
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
public class InventarioServiceTest {
    @Mock
    private InventarioRepository inventarioRespository;
    @InjectMocks
    private InventarioService inventarioService;

    @Test
    @DisplayName("Salvando um Inventario")
    public void whenSaveAtor_shouldReturnUmInventario() {
        InventarioModel inventarioModel = InventarioModel.builder().filmId(1).inventoryId(1).storeId(1).lastUpdate(LocalDateTime.now()).build();
        when(inventarioRespository.save(ArgumentMatchers.any(InventarioModel.class))).thenReturn(inventarioModel);
        InventarioModel created = inventarioService.save(inventarioModel);
        assertThat(created.getFilmId()).isSameAs(inventarioModel.getFilmId());
        verify(inventarioRespository).save(inventarioModel);
    }

    @Test
    @DisplayName("deletando um Inventario")
    public void whenGivenId_shouldDeleteInventario_ifFound(){
        InventarioModel inventarioModel = InventarioModel.builder().filmId(1).inventoryId(1).storeId(1).lastUpdate(LocalDateTime.now()).build();
        lenient().when(inventarioRespository.findById(inventarioModel.getFilmId())).thenReturn(Optional.of(inventarioModel));
        inventarioService.delete(inventarioModel);
        verify(inventarioRespository).delete(inventarioModel);
    }

    @Test(expected = RuntimeException.class)
    @DisplayName("deletando um Inventario com exception")
    public void should_throw_exception_when_Inventario_doesnt_exist() {
        InventarioModel inventarioModel = null;
        when(inventarioRespository.findById(inventarioModel.getFilmId())).thenReturn(Optional.of(inventarioModel));
        given(inventarioRespository.findById(anyInt())).willReturn(Optional.ofNullable(null));
        verify(inventarioRespository).delete(inventarioModel);
    }


}
