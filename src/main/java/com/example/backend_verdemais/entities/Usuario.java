package com.example.backend_verdemais.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TB_USUARIO")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long id;

    @Column(name = "nome_usuario", unique = true, nullable = false)
    private String nome;

    @Column(name = "email_usuario", unique = true, nullable = false)
    private String email;

    @Column(name = "senha_usuario", unique = true, nullable = false)
    private String password;

    @Column(name = "role_usuario", unique = true, nullable = false)
    private String role;

    @Column(name = "habilitado_usuario", unique = true, nullable = false)
    private Boolean habilitado;

    @Column(name = "data_criacao_usuario")
    private Long dataCriacao;
}
