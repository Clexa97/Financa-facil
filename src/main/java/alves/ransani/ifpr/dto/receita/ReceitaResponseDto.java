package alves.ransani.ifpr.dto.receita;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReceitaResponseDto {
    private Long id;
    private String descricao;
    private BigDecimal valor;
    private LocalDate data;
}
