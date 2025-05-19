package alves.ransani.ifpr.service;
import alves.ransani.ifpr.dao.Despesa;
import alves.ransani.ifpr.dto.despesa.DespesaCreateDto;
import alves.ransani.ifpr.dto.despesa.DespesaResponseDto;
import alves.ransani.ifpr.mapper.DespesaMapper;
import alves.ransani.ifpr.repository.DespesaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.MockitoAnnotations;

public class DespesaServiceTest {

    @InjectMocks
    private DespesaService despesaService;

    @Mock
    private DespesaRepository despesaRepository;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveCriarDespesaComSucesso() {
        DespesaCreateDto dto = new DespesaCreateDto();
        dto.setDescricao("Luz");
        dto.setValor(new BigDecimal("120.50"));
        dto.setData(LocalDate.of(2025, 5, 19));
        dto.setCategoria(Despesa.Categoria.MORADIA);

        Despesa despesa = DespesaMapper.toEntity(dto);
        despesa.setId(1L);

        when(despesaRepository.save(any(Despesa.class))).thenReturn(despesa);

        DespesaResponseDto response = despesaService.criarDespesa(dto);

        assertNotNull(response);
        assertEquals("Luz", response.getDescricao());
        assertEquals(new BigDecimal("120.50"), response.getValor());
        assertEquals(Despesa.Categoria.MORADIA, response.getCategoria());
    }

    @Test
    void deveRetornarDespesaPorId() {
        Despesa despesa = new Despesa();
        despesa.setId(1L);
        despesa.setDescricao("Internet");
        despesa.setValor(new BigDecimal("99.90"));
        despesa.setData(LocalDate.of(2025, 5, 15));
        despesa.setCategoria(Despesa.Categoria.OUTROS);

        when(despesaRepository.findById(1L)).thenReturn(Optional.of(despesa));

        Optional<DespesaResponseDto> resultado = despesaService.buscarPorId(1L);

        assertTrue(resultado.isPresent());
        assertEquals("Internet", resultado.get().getDescricao());
    }
}
