package com.example.backend_verdemais.services;

import com.example.backend_verdemais.dto.UsuarioResumidoDTO;
import com.example.backend_verdemais.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<UsuarioResumidoDTO> getAllUsuariosResumidos() {
        return usuarioRepository.findAllUsuariosResumidos();
    }

}
