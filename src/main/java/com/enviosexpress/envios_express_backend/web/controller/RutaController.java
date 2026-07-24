package com.enviosexpress.envios_express_backend.web.controller;

import com.enviosexpress.envios_express_backend.domain.model.EstadoRuta;
import com.enviosexpress.envios_express_backend.domain.service.RutaService;
import com.enviosexpress.envios_express_backend.web.dto.request.RutaRequest;
import com.enviosexpress.envios_express_backend.web.dto.response.RutaResponse;
import com.enviosexpress.envios_express_backend.web.mapper.RutaMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rutas")
@RequiredArgsConstructor
public class RutaController {

    private final RutaService rutaService;
    private final RutaMapper rutaMapper;

    @PostMapping
    public ResponseEntity<RutaResponse> crear(@Valid @RequestBody RutaRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(rutaMapper.toResponse(rutaService.crear(request)));
    }

    @GetMapping
    public ResponseEntity<List<RutaResponse>> listar(@RequestParam(required = false) EstadoRuta estado) {
        List<RutaResponse> respuesta = (estado != null
                ? rutaService.listarPorEstado(estado)
                : rutaService.listarTodas())
                .stream()
                .map(rutaMapper::toResponse)
                .toList();
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RutaResponse> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(rutaMapper.toResponse(rutaService.obtenerPorId(id)));
    }

    @PatchMapping("/{id}/vehiculo/{vehiculoId}")
    public ResponseEntity<RutaResponse> asignarVehiculo(@PathVariable Long id, @PathVariable Long vehiculoId) {
        return ResponseEntity.ok(rutaMapper.toResponse(rutaService.asignarVehiculo(id, vehiculoId)));
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<RutaResponse> cambiarEstado(@PathVariable Long id, @RequestParam EstadoRuta estado) {
        return ResponseEntity.ok(rutaMapper.toResponse(rutaService.cambiarEstado(id, estado)));
    }
}
