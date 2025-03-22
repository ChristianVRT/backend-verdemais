package com.example.backend_verdemais.services;

import com.example.backend_verdemais.dto.MercadoriaDTO;
import com.example.backend_verdemais.dto.UsuarioDTO;
import com.example.backend_verdemais.entities.Mercadoria;
import com.example.backend_verdemais.entities.Usuario;
import com.example.backend_verdemais.mappers.MercadoriaMapper;
import com.example.backend_verdemais.mappers.UsuarioMapper;
import com.example.backend_verdemais.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<UsuarioDTO> getAllUsuariosResumidos() {
        return usuarioRepository.findAllUsuariosResumidos();
    }

    public UsuarioDTO desativarUsuario(Long id, UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuario não encontrada para o ID: " + id));

        usuario.setNome(usuarioDTO.nome());
        usuario.setHabilitado(usuarioDTO.habilitado());
        usuario.setEmail(usuarioDTO.email());

        Usuario usuarioAtualizado = usuarioRepository.save(usuario);
        return UsuarioMapper.paraDTO(usuarioAtualizado);
    }

}
