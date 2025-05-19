package alves.ransani.ifpr.dto.receita;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReceitaResponseDto {
    private Long id;
    private String descricao;
    private BigDecimal valor;
    private LocalDate data;
}
