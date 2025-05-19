package alves.ransani.ifpr.service;
import alves.ransani.ifpr.dao.Despesa;
import alves.ransani.ifpr.dto.despesa.DespesaCreateDto;
import alves.ransani.ifpr.dto.despesa.DespesaResponseDto;
import alves.ransani.ifpr.mapper.DespesaMapper;
import alves.ransani.ifpr.repository.DespesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DespesaService {
    @Autowired
    private DespesaRepository despesaRepository;

    public DespesaResponseDto criarDespesa(DespesaCreateDto dto) {
        Despesa despesa = DespesaMapper.toEntity(dto);
        Despesa salva = despesaRepository.save(despesa);
        return DespesaMapper.toResponseDto(salva);
    }

    public List<DespesaResponseDto> listarTodas() {
        return despesaRepository.findAll()
                .stream()
                .map(DespesaMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    public Optional<DespesaResponseDto> buscarPorId(Long id) {
        return despesaRepository.findById(id)
                .map(DespesaMapper::toResponseDto);
    }

    public Optional<DespesaResponseDto> atualizarDespesa(Long id, DespesaCreateDto dto) {
        return despesaRepository.findById(id).map(despesaExistente -> {
            despesaExistente.setDescricao(dto.getDescricao());
            despesaExistente.setValor(dto.getValor());
            despesaExistente.setData(dto.getData());
            despesaExistente.setCategoria(dto.getCategoria());
            Despesa atualizada = despesaRepository.save(despesaExistente);
            return DespesaMapper.toResponseDto(atualizada);
        });
    }

    public boolean deletarDespesa(Long id) {
        return despesaRepository.findById(id).map(despesa -> {
            despesaRepository.delete(despesa);
            return true;
        }).orElse(false);
    }

    public List<DespesaResponseDto> buscarPorCategoria(Despesa.Categoria categoria) {
        List<Despesa> despesas = despesaRepository.findByCategoria(categoria);
        return despesas.stream()
                .map(DespesaMapper::toResponseDto)
                .collect(Collectors.toList());
    }

}
