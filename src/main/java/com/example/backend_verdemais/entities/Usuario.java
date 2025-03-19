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
    private Long idUsuario;

    @Column(name = "name_usuario", unique = true, nullable = false)
    private String name;

    @Column(name = "email_usuario", unique = true, nullable = false)
    private String email;

    @Column(name = "password_usuario", unique = true, nullable = false)
    private String password;

    @Column(name = "role_usuario", unique = true, nullable = false)
    private String role;

    @Column(name = "data_criacao_usuario")
    private Long dataCriacao;
}
