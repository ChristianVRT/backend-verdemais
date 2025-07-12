package com.example.backend_verdemais.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TB_MERCADORIAS_PEDIDO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MercadoriasPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido_mercadoria")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_pedido", nullable = false)
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "id_mercadoria", nullable = false)
    private Mercadoria mercadoria;

    @Column(name = "quantidade_mercadorias_pedido", nullable = false)
    private int quantidade;

    @Column(name = "preco_mercadorias_pedido", nullable = false)
    private Double preco;
}
