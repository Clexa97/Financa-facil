package alves.ransani.ifpr.dto.alerta;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AlertaResponseDto {
    private Long id;
    private String mensagem;
    private LocalDate dataAlerta;
}
