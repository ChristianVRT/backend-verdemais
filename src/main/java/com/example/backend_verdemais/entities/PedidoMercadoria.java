package com.example.backend_verdemais.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TB_PEDIDO_MERCADORIA")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PedidoMercadoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido_mercadoria")
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_pedido", nullable = false)
    private Pedido pedido;

    @OneToOne
    @JoinColumn(name = "id_mercadoria", nullable = false)
    private Mercadoria mercadoria;

    @Column(name = "quantidade_pedido_mercadoria", nullable = false)
    private int quantidade;

    @Column(name = "preco_unitario_pedido_mercadoria", nullable = false)
    private double precoUnitario;
}
