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
        item.setNome(mercadoriaDTO.nome());
        item.setPreco(mercadoriaDTO.preco());
        item.setHabilitado(mercadoriaDTO.habilitado());
        item.setUser(userRepository.findByName(mercadoriaDTO.username()));

        Item itemSaved = itemRepository.save(item);
        return new MercadoriaDTO(
                item.getId(),
                itemSaved.getNome(),
                itemSaved.getPreco(),
                itemSaved.getHabilitado(),
                itemSaved.getUser().getName()
        );
    }

    public MercadoriaDTO updateMercadoria(MercadoriaDTO mercadoriaDTO) {
        Item existingItem = itemRepository.findById(mercadoriaDTO.id())
                .orElseThrow(() -> new IllegalArgumentException("Mercadoria não encontrada para o ID: " + mercadoriaDTO.id()));

        existingItem.setNome(mercadoriaDTO.nome());
        existingItem.setPreco(mercadoriaDTO.preco());
        existingItem.setHabilitado(mercadoriaDTO.habilitado());
        existingItem.setUser(userRepository.findByName(mercadoriaDTO.username()));

        Item updatedItem = itemRepository.save(existingItem);
        return new MercadoriaDTO(
                updatedItem.getId(),
                updatedItem.getNome(),
                updatedItem.getPreco(),
                updatedItem.getHabilitado(),
                updatedItem.getUser() != null ? updatedItem.getUser().getName() : null
        );
    }

    public MercadoriaDTO saveOrUpdateMercadoria(MercadoriaDTO mercadoriaDTO) {
        if (isNull(mercadoriaDTO.id())) {
            return postMercadoria(mercadoriaDTO);
        }
        return updateMercadoria(mercadoriaDTO);
    }
}

