package com.example.backend_verdemais.services;

import com.example.backend_verdemais.dto.request.MercadoriaRequestDTO;
import com.example.backend_verdemais.entities.Mercadoria;
import com.example.backend_verdemais.entities.Usuario;
import com.example.backend_verdemais.mappers.MercadoriaMapper;
import com.example.backend_verdemais.repositories.MercadoriaRepository;
import com.example.backend_verdemais.repositories.UsuarioRepository;
import com.example.backend_verdemais.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class MercadoriaService {

    @Autowired
    private MercadoriaRepository mercadoriaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TokenService tokenService;

    public List<MercadoriaRequestDTO> getAllMercadorias() {
        List<Mercadoria> mercadorias = mercadoriaRepository.findAll();
        return mercadorias.stream()
                .map(MercadoriaMapper::paraDTO).collect(Collectors.toList());
    }

    public MercadoriaRequestDTO postMercadoria(MercadoriaRequestDTO mercadoriaRequestDTO, Usuario usuario) {

        Mercadoria mercadoria = new Mercadoria();
        preencheMercadoria(mercadoriaRequestDTO, mercadoria, usuario);
        Mercadoria mercadoriaSalvo = mercadoriaRepository.save(mercadoria);
        return MercadoriaMapper.paraDTO(mercadoriaSalvo);
    }

    public MercadoriaRequestDTO updateMercadoria(Long id, MercadoriaRequestDTO mercadoriaRequestDTO, Usuario usuario) {

        Mercadoria mercadoria = mercadoriaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Mercadoria não encontrada para o ID: " + id));

        preencheMercadoria(mercadoriaRequestDTO, mercadoria, usuario);
        Mercadoria mercadoriaAtualizado = mercadoriaRepository.save(mercadoria);
        return MercadoriaMapper.paraDTO(mercadoriaAtualizado);
    }

    private void preencheMercadoria(MercadoriaRequestDTO mercadoriaRequestDTO, Mercadoria mercadoria, Usuario usuario) {
        mercadoria.setNome(mercadoriaRequestDTO.nome());
        mercadoria.setPreco(mercadoriaRequestDTO.preco());
        mercadoria.setHabilitado(mercadoriaRequestDTO.habilitado());
        mercadoria.setQuantidade(mercadoriaRequestDTO.quantidade());
        mercadoria.setUsuario(usuario);
    }

    public void deleteMercadoria(Long id) {
        Mercadoria mercadoria = mercadoriaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Mercadoria não encontrada para o ID: " + id));;
        mercadoriaRepository.delete(mercadoria);
    }

}

