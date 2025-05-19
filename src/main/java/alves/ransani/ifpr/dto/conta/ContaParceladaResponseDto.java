package alves.ransani.ifpr.dto.conta;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContaParceladaResponseDto {
    private Long id;
    private String descricao;
    private BigDecimal valorTotal;
    private int quantidadeParcelas;
    private LocalDate dataInicio;
}
