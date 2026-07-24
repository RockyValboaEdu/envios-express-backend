package com.enviosexpress.envios_express_backend.impl;


import com.enviosexpress.envios_express_backend.domain.model.Usuario;
import com.enviosexpress.envios_express_backend.domain.repository.UsuarioRepository;
import com.enviosexpress.envios_express_backend.domain.service.AuthService;
import com.enviosexpress.envios_express_backend.domain.service.UsuarioService;
import com.enviosexpress.envios_express_backend.infrastructure.security.JwtService;
import com.enviosexpress.envios_express_backend.infrastructure.security.UsuarioPrincipal;
import com.enviosexpress.envios_express_backend.web.dto.request.LoginRequest;
import com.enviosexpress.envios_express_backend.web.dto.request.RegistroRequest;
import com.enviosexpress.envios_express_backend.web.dto.response.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UsuarioService usuarioService;
    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;

    @Override
    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
                .orElseThrow();

        String token = jwtService.generarToken(new UsuarioPrincipal(usuario));

        return construirRespuesta(usuario, token);
    }

    @Override
    public AuthResponse registrar(RegistroRequest request) {
        Usuario usuario = usuarioService.registrar(request);
        String token = jwtService.generarToken(new UsuarioPrincipal(usuario));
        return construirRespuesta(usuario, token);
    }

    private AuthResponse construirRespuesta(Usuario usuario, String token) {
        return AuthResponse.builder()
                .token(token)
                .usuarioId(usuario.getId())
                .nombre(usuario.getNombre())
                .email(usuario.getEmail())
                .rol(usuario.getRol())
                .build();
    }
}
