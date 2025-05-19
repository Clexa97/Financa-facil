package alves.ransani.ifpr.dto.parcela;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ParcelaResponseDto {
    private Long id;
    private BigDecimal valor;
    private LocalDate dataVencimento;
    private boolean paga;
}
