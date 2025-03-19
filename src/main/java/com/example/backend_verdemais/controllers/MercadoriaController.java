package com.example.backend_verdemais.controllers;

import com.example.backend_verdemais.dto.MercadoriaDTO;
import com.example.backend_verdemais.services.MercadoriaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mercadoria")
public class MercadoriaController {

    private final MercadoriaService mercadoriaService;

    public MercadoriaController(MercadoriaService mercadoriaService) {
        this.mercadoriaService = mercadoriaService;
    }

    @GetMapping()
    public ResponseEntity<List<MercadoriaDTO>> getAllMercadorias() {
        List<MercadoriaDTO> mercadorias = mercadoriaService.getAllMercadorias();
        return ResponseEntity.ok(mercadorias);
    }

    @PostMapping()
    public ResponseEntity<MercadoriaDTO> postMercadoria(@RequestBody MercadoriaDTO mercadoriaDTO) {
        MercadoriaDTO mercadoria = mercadoriaService.postMercadoria(mercadoriaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(mercadoria);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MercadoriaDTO> updateMercadoria(@PathVariable Long id, @RequestBody MercadoriaDTO mercadoriaDTO) {
        MercadoriaDTO mercadoria = mercadoriaService.updateMercadoria(id, mercadoriaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(mercadoria);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMercadoria(@PathVariable Long id) {
        mercadoriaService.deleteMercadoria(id);
        return ResponseEntity.noContent().build();
    }
}
