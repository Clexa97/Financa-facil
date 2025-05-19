package alves.ransani.ifpr.dto.despesa;

import alves.ransani.ifpr.dao.Despesa;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DespesaCreateDto {
    private String descricao;
    private BigDecimal valor;
    private LocalDate data;
    private Despesa.Categoria categoria;
}
