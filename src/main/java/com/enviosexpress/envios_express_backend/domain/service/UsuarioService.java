package com.enviosexpress.envios_express_backend.domain.service;

import com.enviosexpress.envios_express_backend.domain.model.Rol;
import com.enviosexpress.envios_express_backend.domain.model.Usuario;
import com.enviosexpress.envios_express_backend.web.dto.request.RegistroRequest;

import java.util.List;

public interface UsuarioService {

    Usuario registrar(RegistroRequest request);

    Usuario obtenerPorId(Long id);

    List<Usuario> listarPorRol(Rol rol);

    List<Usuario> listarTodos();
}
