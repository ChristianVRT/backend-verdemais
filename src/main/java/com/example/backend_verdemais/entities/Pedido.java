package com.example.backend_verdemais.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_pedido")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Usuario cliente;

    @Column(name = "data_pedido", nullable = false)
    private Long data_pedido;

    @Column(name = "valor_pedido", nullable = false)
    private Double valor;

    @Column(name = "status_pedido", nullable = false)
    private String status;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MercadoriasPedido> mercadoriasPedidos = new ArrayList<>();

}
