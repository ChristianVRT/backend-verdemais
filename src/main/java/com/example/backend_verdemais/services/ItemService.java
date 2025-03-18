package com.example.backend_verdemais.services;

import com.example.backend_verdemais.dto.MercadoriaDTO;
import com.example.backend_verdemais.entities.Item;
import com.example.backend_verdemais.mappers.MercadoriaMapper;
import com.example.backend_verdemais.repositories.ItemRepository;
import com.example.backend_verdemais.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserRepository userRepository;

    public List<MercadoriaDTO> getAllMercadorias() {
        List<Item> items = itemRepository.findAll();
        return items.stream()
                .map(MercadoriaMapper::paraDTO).collect(Collectors.toList());
    }

    public MercadoriaDTO postMercadoria(MercadoriaDTO mercadoriaDTO) {

        Item item = new Item();

        preencheMercadoria(mercadoriaDTO, item);

        Item itemSalvo = itemRepository.saveAndFlush(item);
        return MercadoriaMapper.paraDTO(itemSalvo);
    }

    public MercadoriaDTO updateMercadoria(Long id, MercadoriaDTO mercadoriaDTO) {

        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Mercadoria não encontrada para o ID: " + id));

        preencheMercadoria(mercadoriaDTO, item);

        Item itemAtualizado = itemRepository.saveAndFlush(item);
        return MercadoriaMapper.paraDTO(itemAtualizado);
    }

    public void preencheMercadoria(MercadoriaDTO mercadoriaDTO, Item item) {

        item.setNome(mercadoriaDTO.nome());
        item.setPreco(mercadoriaDTO.preco());
        item.setHabilitado(mercadoriaDTO.habilitado());
        item.setUser(userRepository.findByName(mercadoriaDTO.username()));

        MercadoriaMapper.paraDTO(item);
    }

    public MercadoriaDTO deleteMercadoria(Long id) {

        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Mercadoria não encontrada para o ID: " + id));;

        itemRepository.delete(item);

        return MercadoriaMapper.paraDTO(item);

    }


}

