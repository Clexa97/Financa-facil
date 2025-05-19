package alves.ransani.ifpr.mapper;

import alves.ransani.ifpr.dao.ContaParcelada;
import alves.ransani.ifpr.dto.conta.ContaParceladaCreateDto;
import alves.ransani.ifpr.dto.conta.ContaParceladaResponseDto;

public class ContaParceladaMapper {

    public static ContaParcelada toEntity(ContaParceladaCreateDto dto) {
        ContaParcelada conta = new ContaParcelada();
        conta.setDescricao(dto.getDescricao());
        conta.setValorTotal(dto.getValorTotal());
        conta.setQuantidadeParcelas(dto.getQuantidadeParcelas());
        conta.setDataInicio(dto.getDataInicio());
        return conta;
    }

    public static ContaParceladaResponseDto toResponseDto(ContaParcelada conta) {
        ContaParceladaResponseDto dto = new ContaParceladaResponseDto();
        dto.setId(conta.getId());
        dto.setDescricao(conta.getDescricao());
        dto.setValorTotal(conta.getValorTotal());
        dto.setQuantidadeParcelas(conta.getQuantidadeParcelas());
        dto.setDataInicio(conta.getDataInicio());
        return dto;
    }
}
