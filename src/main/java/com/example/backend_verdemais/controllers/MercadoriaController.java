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
    public ResponseEntity<MercadoriaDTO> saveOrUpdateMercadoria(@RequestBody MercadoriaDTO mercadoriaDTO) {
        MercadoriaDTO mercadoria = itemService.saveOrUpdateMercadoria(mercadoriaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(mercadoria);
    }

    @PostMapping("/deletar")
    public ResponseEntity<MercadoriaDTO> deleteMercadoria(@RequestBody MercadoriaDTO mercadoriaDTO) {
        MercadoriaDTO mercadoria = itemService.deleteMercadoria(mercadoriaDTO);
        return ResponseEntity.status(HttpStatus.OK).body(mercadoria);
    }
}
