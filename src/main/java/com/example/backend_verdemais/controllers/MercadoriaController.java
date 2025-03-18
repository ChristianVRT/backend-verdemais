package com.example.backend_verdemais.controllers;

import com.example.backend_verdemais.dto.MercadoriaDTO;
import com.example.backend_verdemais.services.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mercadoria")
public class MercadoriaController {

    private final ItemService itemService;

    public MercadoriaController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping()
    public ResponseEntity<List<MercadoriaDTO>> getAllMercadorias() {
        List<MercadoriaDTO> mercadorias = itemService.getAllMercadorias();
        return ResponseEntity.ok(mercadorias);
    }

    @PostMapping()
    public ResponseEntity<MercadoriaDTO> postMercadoria(@RequestBody MercadoriaDTO mercadoriaDTO) {
        MercadoriaDTO mercadoria = itemService.postMercadoria(mercadoriaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(mercadoria);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MercadoriaDTO> updateMercadoria(@PathVariable Long id, @RequestBody MercadoriaDTO mercadoriaDTO) {
        MercadoriaDTO mercadoria = itemService.updateMercadoria(id, mercadoriaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(mercadoria);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMercadoria(@PathVariable Long id) {
        itemService.deleteMercadoria(id);
        return ResponseEntity.noContent().build();
    }
}
