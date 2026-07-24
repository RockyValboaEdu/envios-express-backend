package com.enviosexpress.envios_express_backend.web.controller;

import com.enviosexpress.envios_express_backend.domain.service.EnvioService;
import com.enviosexpress.envios_express_backend.domain.service.SeguimientoService;
import com.enviosexpress.envios_express_backend.web.dto.request.ActualizarSeguimientoRequest;
import com.enviosexpress.envios_express_backend.web.dto.request.EnvioRequest;
import com.enviosexpress.envios_express_backend.web.dto.response.EnvioResponse;
import com.enviosexpress.envios_express_backend.web.dto.response.SeguimientoResponse;
import com.enviosexpress.envios_express_backend.web.mapper.EnvioMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/envios")
@RequiredArgsConstructor
public class EnvioController {

    private final EnvioService envioService;
    private final SeguimientoService seguimientoService;
    private final EnvioMapper envioMapper;

    @PostMapping
    public ResponseEntity<EnvioResponse> crear(@Valid @RequestBody EnvioRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(envioMapper.toResponse(envioService.crear(request)));
    }

    @GetMapping
    public ResponseEntity<List<EnvioResponse>> listar(@RequestParam(required = false) Long clienteId,
                                                       @RequestParam(required = false) Long rutaId) {
        List<EnvioResponse> respuesta;
        if (clienteId != null) {
            respuesta = envioService.listarPorCliente(clienteId).stream().map(envioMapper::toResponse).toList();
        } else if (rutaId != null) {
            respuesta = envioService.listarPorRuta(rutaId).stream().map(envioMapper::toResponse).toList();
        } else {
            respuesta = envioService.listarTodos().stream().map(envioMapper::toResponse).toList();
        }
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnvioResponse> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(envioMapper.toResponse(envioService.obtenerPorId(id)));
    }

    @GetMapping("/seguimiento/{codigo}")
    public ResponseEntity<EnvioResponse> obtenerPorCodigo(@PathVariable String codigo) {
        return ResponseEntity.ok(envioMapper.toResponse(envioService.obtenerPorCodigoSeguimiento(codigo)));
    }

    @GetMapping("/{id}/historial")
    public ResponseEntity<List<SeguimientoResponse>> historial(@PathVariable Long id) {
        List<SeguimientoResponse> historial = seguimientoService.historialPorEnvio(id).stream()
                .map(envioMapper::toSeguimientoResponse)
                .toList();
        return ResponseEntity.ok(historial);
    }

    /**
     * Endpoint REST alternativo para registrar un evento de seguimiento
     * (ademas del canal WebSocket /app/seguimiento). Util para apps moviles
     * de conductores que prefieran hacer polling/push por HTTP.
     */
    @PostMapping("/seguimiento")
    public ResponseEntity<SeguimientoResponse> registrarSeguimiento(
            @RequestBody ActualizarSeguimientoRequest request) {
        var evento = seguimientoService.registrarEvento(request);
        return ResponseEntity.ok(envioMapper.toSeguimientoResponse(evento));
    }
}
