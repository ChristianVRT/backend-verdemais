package com.example.backend_verdemais.services;

import com.example.backend_verdemais.dto.MercadoriaDTO;
import com.example.backend_verdemais.entities.Mercadoria;
import com.example.backend_verdemais.mappers.MercadoriaMapper;
import com.example.backend_verdemais.repositories.MercadoriaRepository;
import com.example.backend_verdemais.repositories.UsuarioRepository;
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

    public List<MercadoriaDTO> getAllMercadorias() {
        List<Mercadoria> mercadorias = mercadoriaRepository.findAll();
        return mercadorias.stream()
                .map(MercadoriaMapper::paraDTO).collect(Collectors.toList());
    }

    public MercadoriaDTO postMercadoria(MercadoriaDTO mercadoriaDTO) {

        Mercadoria mercadoria = new Mercadoria();

        preencheMercadoria(mercadoriaDTO, mercadoria);

        Mercadoria mercadoriaSalvo = mercadoriaRepository.saveAndFlush(mercadoria);
        return MercadoriaMapper.paraDTO(mercadoriaSalvo);
    }

    public MercadoriaDTO updateMercadoria(Long id, MercadoriaDTO mercadoriaDTO) {

        Mercadoria mercadoria = mercadoriaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Mercadoria não encontrada para o ID: " + id));

        preencheMercadoria(mercadoriaDTO, mercadoria);

        Mercadoria mercadoriaAtualizado = mercadoriaRepository.saveAndFlush(mercadoria);
        return MercadoriaMapper.paraDTO(mercadoriaAtualizado);
    }

    public void preencheMercadoria(MercadoriaDTO mercadoriaDTO, Mercadoria mercadoria) {

        mercadoria.setNome(mercadoriaDTO.nome());
        mercadoria.setPreco(mercadoriaDTO.preco());
        mercadoria.setHabilitado(mercadoriaDTO.habilitado());
        mercadoria.setUsuario(usuarioRepository.findByName(mercadoriaDTO.username()));

        MercadoriaMapper.paraDTO(mercadoria);
    }

    public MercadoriaDTO deleteMercadoria(Long id) {

        Mercadoria mercadoria = mercadoriaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Mercadoria não encontrada para o ID: " + id));;

        mercadoriaRepository.delete(mercadoria);

        return MercadoriaMapper.paraDTO(mercadoria);

    }


}

