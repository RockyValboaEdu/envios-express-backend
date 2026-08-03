package com.enviosexpress.envios_express_backend.web.controller;

import com.enviosexpress.envios_express_backend.domain.model.Rol;
import com.enviosexpress.envios_express_backend.domain.service.UsuarioService;
import com.enviosexpress.envios_express_backend.web.dto.request.CrearUsuarioRequest;
import com.enviosexpress.envios_express_backend.web.dto.request.RegistroRequest;
import com.enviosexpress.envios_express_backend.web.dto.response.UsuarioResponse;
import com.enviosexpress.envios_express_backend.web.mapper.UsuarioMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

     //para saber si el cliente ya tiene cuenta antes de registrar un envío. Usado por el Rol Recepcionista

    @GetMapping("/buscar")
    public ResponseEntity<UsuarioResponse> buscarPorEmail(@RequestParam String email) {
        return ResponseEntity.ok(usuarioMapper.toResponse(usuarioService.buscarPorEmail(email)));
    }

     // Solo ADMIN: crea un usuario con cualquier rol.

    @PostMapping
    public ResponseEntity<UsuarioResponse> crear(@Valid @RequestBody CrearUsuarioRequest request) {
        UsuarioResponse creado = usuarioMapper.toResponse(usuarioService.crearPorAdmin(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

     //ADMIN o RECEPCIONISTA: crea la cuenta del cliente
     //que está siendo atendido en el mostrador.
     
    @PostMapping("/clientes")
    public ResponseEntity<UsuarioResponse> crearCliente(@Valid @RequestBody RegistroRequest request) {
        UsuarioResponse creado = usuarioMapper.toResponse(usuarioService.registrar(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PatchMapping("/{id}/rol")
    public ResponseEntity<UsuarioResponse> cambiarRol(@PathVariable Long id, @RequestParam Rol rol) {
        return ResponseEntity.ok(usuarioMapper.toResponse(usuarioService.cambiarRol(id, rol)));
    }
}