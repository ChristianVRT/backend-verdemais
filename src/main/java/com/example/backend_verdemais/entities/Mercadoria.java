package com.example.backend_verdemais.entities;


import jakarta.persistence.*;
        import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_mercadoria")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Mercadoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mercadoria")
    private Long id;

    @Column(name = "nome_mercadoria", nullable = false)
    private String nome;

    @Column(name = "preco_mercadoria", nullable = false)
    private double preco;

    @Column(name = "quantidade_mercadoria", nullable = false)
    private int quantidade;

    @Column(name = "habilitado_mercadoria")
    private Boolean habilitado;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Usuario usuario;

}

