package com.example.backend_verdemais.repositories;

import com.example.backend_verdemais.dto.UsuarioDTO;
import com.example.backend_verdemais.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByEmail(String email);

    Optional<Usuario> findById(Long id);

    @Query("SELECT new com.example.backend_verdemais.dto.UsuarioDTO(u.idUsuario ,u.nome, u.email, u.habilitado) FROM Usuario u")
    List<UsuarioDTO> findAllUsuariosResumidos();
}
