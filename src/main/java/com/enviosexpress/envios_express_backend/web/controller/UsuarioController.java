package com.enviosexpress.envios_express_backend.web.controller;

import com.enviosexpress.envios_express_backend.domain.model.Rol;
import com.enviosexpress.envios_express_backend.domain.service.UsuarioService;
import com.enviosexpress.envios_express_backend.web.dto.response.UsuarioResponse;
import com.enviosexpress.envios_express_backend.web.mapper.UsuarioMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final UsuarioMapper usuarioMapper;

    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> listar(@RequestParam(required = false) Rol rol) {
        List<UsuarioResponse> respuesta = (rol != null
                ? usuarioService.listarPorRol(rol)
                : usuarioService.listarTodos())
                .stream()
                .map(usuarioMapper::toResponse)
                .toList();
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioMapper.toResponse(usuarioService.obtenerPorId(id)));
    }
}
