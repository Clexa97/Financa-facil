package alves.ransani.ifpr.service;


import alves.ransani.ifpr.dao.Alerta;
import alves.ransani.ifpr.dao.Parcela;
import alves.ransani.ifpr.dto.alerta.AlertaResponseDto;
import alves.ransani.ifpr.mapper.AlertaMapper;
import alves.ransani.ifpr.repository.AlertaRepository;
import alves.ransani.ifpr.repository.ParcelaRepository;
import alves.ransani.ifpr.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AlertaService {

    @Autowired
    private AlertaRepository alertaRepository;

    @Autowired
    private ParcelaRepository parcelaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;
    public Alerta criarAlertaManual(Long usuarioId, String mensagem) {
        Alerta alerta = new Alerta();
        alerta.setMensagem(mensagem);
        alerta.setDataGeracao(LocalDate.now());
        alerta.setVisualizado(false);
        alerta.setUsuario(usuarioRepository.findById(usuarioId).orElse(null));
        return alertaRepository.save(alerta);
    }


    @Scheduled(cron = "0 0 8 * * *")
    public void gerarAlertasDeParcelas() {
        LocalDate hoje = LocalDate.now();
        LocalDate dataLimite = hoje.plusDays(3);

        List<Parcela> parcelas = parcelaRepository.findByDataVencimentoBetween(hoje, dataLimite);

        for (Parcela parcela : parcelas) {
            if (!parcela.isPaga() && parcela.getContaParcelada() != null) {
                Long usuarioId = parcela.getContaParcelada().getUsuario().getId();
                String mensagem = "Parcela nº " + parcela.getNumero() + " vence em breve: " + parcela.getDataVencimento();


                boolean alertaExistente = alertaRepository.findAll().stream()
                        .anyMatch(alerta ->
                                alerta.getMensagem().equals(mensagem) &&
                                        alerta.getUsuario().getId().equals(usuarioId));

                if (!alertaExistente) {
                    criarAlertaManual(usuarioId, mensagem);
                }
            }
        }
    }

    public AlertaResponseDto criar(Long usuarioId, String mensagem) {
        Alerta alerta = new Alerta();
        alerta.setMensagem(mensagem);
        alerta.setDataGeracao(LocalDate.now());
        alerta.setVisualizado(false);
        alerta.setUsuario(usuarioRepository.findById(usuarioId).orElse(null));

        alerta = alertaRepository.save(alerta);
        return AlertaMapper.toResponseDto(alerta);
    }

    public List<AlertaResponseDto> listarPorUsuario(Long usuarioId) {
        return alertaRepository.findAll().stream()
                .filter(alerta -> alerta.getUsuario() != null && alerta.getUsuario().getId().equals(usuarioId))
                .map(AlertaMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    public Optional<AlertaResponseDto> visualizar(Long id) {
        return alertaRepository.findById(id).map(alerta -> {
            alerta.setVisualizado(true);
            alertaRepository.save(alerta);
            return AlertaMapper.toResponseDto(alerta);
        });
    }
}
