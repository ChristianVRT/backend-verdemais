package com.example.backend_verdemais.repositories;

import com.example.backend_verdemais.dto.UsuarioResumidoDTO;
import com.example.backend_verdemais.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByEmail(String email);
    @Query("SELECT new com.example.backend_verdemais.dto.UsuarioResumidoDTO(u.name, u.email, u.habilitado) FROM Usuario u")
    List<UsuarioResumidoDTO> findAllUsuariosResumidos();
}
