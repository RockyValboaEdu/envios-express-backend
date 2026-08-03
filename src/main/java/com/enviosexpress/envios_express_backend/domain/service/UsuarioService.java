package com.enviosexpress.envios_express_backend.domain.service;

import com.enviosexpress.envios_express_backend.domain.model.Rol;
import com.enviosexpress.envios_express_backend.domain.model.Usuario;
import com.enviosexpress.envios_express_backend.web.dto.request.CrearUsuarioRequest;
import com.enviosexpress.envios_express_backend.web.dto.request.RegistroRequest;

import java.util.List;

public interface UsuarioService {

    /** Registro publico (sin autenticacion): siempre crea el usuario con rol CLIENTE. */
    Usuario registrar(RegistroRequest request);

    /** Solo alcanzable por un ADMIN autenticado: crea un usuario con el rol indicado. */
    Usuario crearPorAdmin(CrearUsuarioRequest request);

    Usuario obtenerPorId(Long id);

    /** Usado por el panel de administración para localizar un usuario por su correo. */
    Usuario buscarPorEmail(String email);

    /** Solo alcanzable por un ADMIN autenticado: reasigna el rol de un usuario existente. */
    Usuario cambiarRol(Long id, Rol nuevoRol);

    List<Usuario> listarPorRol(Rol rol);

    List<Usuario> listarTodos();
}
