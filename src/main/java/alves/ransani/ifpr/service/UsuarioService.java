package alves.ransani.ifpr.service;

import alves.ransani.ifpr.dao.Usuario;
import alves.ransani.ifpr.dto.usuario.LoginDto;
import alves.ransani.ifpr.dto.usuario.UsuarioResponseDto;
import alves.ransani.ifpr.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Optional<UsuarioResponseDto> autenticar(LoginDto loginDto) {
        return usuarioRepository.findByEmail(loginDto.getEmail())
                .filter(usuario -> usuario.getSenha().equals(loginDto.getSenha()))
                .map(usuario -> {
                    UsuarioResponseDto dto = new UsuarioResponseDto();
                    dto.setId(usuario.getId());
                    dto.setNome(usuario.getNome());
                    dto.setEmail(usuario.getEmail());
                    return dto;
                });
    }

    public boolean emailDisponivel(String email) {
        return usuarioRepository.findByEmail(email).isEmpty();
    }

    public UsuarioResponseDto cadastrar(Usuario usuario) {
        Usuario salvo = usuarioRepository.save(usuario);
        UsuarioResponseDto dto = new UsuarioResponseDto();
        dto.setId(salvo.getId());
        dto.setNome(salvo.getNome());
        dto.setEmail(salvo.getEmail());
        return dto;
    }
}
