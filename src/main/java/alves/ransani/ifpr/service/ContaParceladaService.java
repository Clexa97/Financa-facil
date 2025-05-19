package alves.ransani.ifpr.service;

import alves.ransani.ifpr.dao.ContaParcelada;
import alves.ransani.ifpr.dao.Usuario;
import alves.ransani.ifpr.dto.conta.ContaParceladaCreateDto;
import alves.ransani.ifpr.dto.conta.ContaParceladaResponseDto;
import alves.ransani.ifpr.mapper.ContaParceladaMapper;
import alves.ransani.ifpr.repository.ContaParceladaRepository;
import alves.ransani.ifpr.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContaParceladaService {

    @Autowired
    private ContaParceladaRepository contaParceladaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;


    public ContaParceladaResponseDto criarContaParcelada(ContaParceladaCreateDto dto) {
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        ContaParcelada conta = ContaParceladaMapper.toEntity(dto);
        conta.setUsuario(usuario);
        ContaParcelada salva = contaParceladaRepository.save(conta);
        return ContaParceladaMapper.toResponseDto(salva);
    }

    public List<ContaParceladaResponseDto> listarTodas() {
        return contaParceladaRepository.findAll().stream()
                .map(ContaParceladaMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    public Optional<ContaParceladaResponseDto> buscarPorId(Long id) {
        return contaParceladaRepository.findById(id)
                .map(ContaParceladaMapper::toResponseDto);
    }
    public Optional<ContaParceladaResponseDto> atualizar(Long id, ContaParceladaCreateDto dto) {
        return contaParceladaRepository.findById(id).map(conta -> {
            conta.setDescricao(dto.getDescricao());
            conta.setValorTotal(dto.getValorTotal());
            conta.setQuantidadeParcelas(dto.getQuantidadeParcelas());
            conta.setDataInicio(dto.getDataInicio());
            ContaParcelada atualizada = contaParceladaRepository.save(conta);
            return ContaParceladaMapper.toResponseDto(atualizada);
        });
    }

    public boolean deletar(Long id) {
        return contaParceladaRepository.findById(id).map(conta -> {
            contaParceladaRepository.delete(conta);
            return true;
        }).orElse(false);
    }
}
