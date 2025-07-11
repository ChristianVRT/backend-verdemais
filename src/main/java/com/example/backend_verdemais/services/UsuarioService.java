package com.example.backend_verdemais.services;

import com.example.backend_verdemais.dto.request.UsuarioRequestDTO;
import com.example.backend_verdemais.entities.Usuario;
import com.example.backend_verdemais.mappers.UsuarioMapper;
import com.example.backend_verdemais.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<UsuarioRequestDTO> getAllUsuariosResumidos() {
        return usuarioRepository.findAllUsuariosResumidos();
    }

    public UsuarioRequestDTO updateUsuario(UsuarioRequestDTO usuarioDTO) {
        Usuario usuario = usuarioRepository.findById(usuarioDTO.id())
                .orElseThrow(() -> new IllegalArgumentException("Usuario não encontrada para o ID: " + usuarioDTO.id()));

        usuario.setId(usuarioDTO.id());
        usuario.setNome(usuarioDTO.nome());
        usuario.setHabilitado(usuarioDTO.habilitado());
        usuario.setEmail(usuarioDTO.email());

        Usuario usuarioAtualizado = usuarioRepository.save(usuario);
        return UsuarioMapper.paraDTO(usuarioAtualizado);
    }

}
