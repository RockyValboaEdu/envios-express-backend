package com.enviosexpress.envios_express_backend.domain.repository;

import com.enviosexpress.envios_express_backend.domain.model.Rol;
import com.enviosexpress.envios_express_backend.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);

    boolean existsByEmail(String email);

    List<Usuario> findByRol(Rol rol);
}
