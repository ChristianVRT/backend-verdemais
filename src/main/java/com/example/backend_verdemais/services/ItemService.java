package com.example.backend_verdemais.services;

import com.example.backend_verdemais.dto.MercadoriaDTO;
import com.example.backend_verdemais.entities.Item;
import com.example.backend_verdemais.entities.User;
import com.example.backend_verdemais.repositories.ItemRepository;
import com.example.backend_verdemais.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserRepository userRepository;

    public List<MercadoriaDTO> getAllMercadorias() {
        List<Item> items = itemRepository.findAll();
        return items.stream()
                .map(item -> new MercadoriaDTO(
                        item.getId(),
                        item.getNome(),
                        item.getPreco(),
                        item.getHabilitado(),
                        item.getUser().getName()
                )).collect(Collectors.toList());
    }

    public MercadoriaDTO postMercadoria(MercadoriaDTO mercadoriaDTO) {
        Item item = new Item();
        item.setId(mercadoriaDTO.id());
        item.setNome(mercadoriaDTO.nome());
        item.setPreco(mercadoriaDTO.preco());
        item.setHabilitado(mercadoriaDTO.habilitado());
        item.setUser(userRepository.findByName(mercadoriaDTO.name()));

        Item itemSaved = itemRepository.save(item);
        return new MercadoriaDTO(
                itemSaved.getId(),
                itemSaved.getNome(),
                itemSaved.getPreco(),
                itemSaved.getHabilitado(),
                itemSaved.getUser() != null ? itemSaved.getUser().getName() : null
        );
    }
}

