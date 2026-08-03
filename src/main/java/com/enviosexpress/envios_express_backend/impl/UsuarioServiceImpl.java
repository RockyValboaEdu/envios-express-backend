package com.enviosexpress.envios_express_backend.impl;


import com.enviosexpress.envios_express_backend.domain.model.Rol;
import com.enviosexpress.envios_express_backend.domain.model.Usuario;
import com.enviosexpress.envios_express_backend.domain.repository.UsuarioRepository;
import com.enviosexpress.envios_express_backend.domain.service.UsuarioService;
import com.enviosexpress.envios_express_backend.infrastructure.exception.BusinessException;
import com.enviosexpress.envios_express_backend.infrastructure.exception.ResourceNotFoundException;
import com.enviosexpress.envios_express_backend.web.dto.request.CrearUsuarioRequest;
import com.enviosexpress.envios_express_backend.web.dto.request.RegistroRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Usuario registrar(RegistroRequest request) {
        validarEmailDisponible(request.getEmail());

        Usuario usuario = Usuario.builder()
                .nombre(request.getNombre())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .telefono(request.getTelefono())
                .rol(Rol.CLIENTE)
                .activo(true)
                .build();

        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario crearPorAdmin(CrearUsuarioRequest request) {
        validarEmailDisponible(request.getEmail());

        Usuario usuario = Usuario.builder()
                .nombre(request.getNombre())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .telefono(request.getTelefono())
                .rol(request.getRol())
                .activo(true)
                .build();

        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario obtenerPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con id: " + id));
    }

    @Override
    public Usuario buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("No existe ningun usuario con el email: " + email));
    }

    @Override
    public Usuario cambiarRol(Long id, Rol nuevoRol) {
        Usuario usuario = obtenerPorId(id);
        usuario.setRol(nuevoRol);
        return usuarioRepository.save(usuario);
    }

    @Override
    public List<Usuario> listarPorRol(Rol rol) {
        return usuarioRepository.findByRol(rol);
    }

    @Override
    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    private void validarEmailDisponible(String email) {
        if (usuarioRepository.existsByEmail(email)) {
            throw new BusinessException("Ya existe un usuario registrado con ese email");
        }
    }
}
