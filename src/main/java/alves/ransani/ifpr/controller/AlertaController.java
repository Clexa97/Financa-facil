package alves.ransani.ifpr.controller;

import alves.ransani.ifpr.dto.alerta.AlertaResponseDto;
import alves.ransani.ifpr.service.AlertaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alertas")
public class AlertaController {

    @Autowired
    private AlertaService alertaService;

    @PostMapping("/{usuarioId}")
    public ResponseEntity<AlertaResponseDto> criarAlerta(@PathVariable Long usuarioId,
                                                         @RequestBody String mensagem) {
        return ResponseEntity.ok(alertaService.criar(usuarioId, mensagem));
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<AlertaResponseDto>> listarPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(alertaService.listarPorUsuario(usuarioId));
    }

    @PutMapping("/{id}/visualizar")
    public ResponseEntity<AlertaResponseDto> marcarComoVisualizado(@PathVariable Long id) {
        return alertaService.visualizar(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
