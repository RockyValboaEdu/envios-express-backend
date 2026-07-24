package com.enviosexpress.envios_express_backend.web.controller;

import com.enviosexpress.envios_express_backend.domain.model.EstadoVehiculo;
import com.enviosexpress.envios_express_backend.domain.service.VehiculoService;
import com.enviosexpress.envios_express_backend.web.dto.request.VehiculoRequest;
import com.enviosexpress.envios_express_backend.web.dto.response.VehiculoResponse;
import com.enviosexpress.envios_express_backend.web.mapper.VehiculoMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehiculos")
@RequiredArgsConstructor
public class VehiculoController {

    private final VehiculoService vehiculoService;
    private final VehiculoMapper vehiculoMapper;

    @PostMapping
    public ResponseEntity<VehiculoResponse> crear(@Valid @RequestBody VehiculoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(vehiculoMapper.toResponse(vehiculoService.crear(request)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VehiculoResponse> actualizar(@PathVariable Long id,
                                                        @Valid @RequestBody VehiculoRequest request) {
        return ResponseEntity.ok(vehiculoMapper.toResponse(vehiculoService.actualizar(id, request)));
    }

    @GetMapping
    public ResponseEntity<List<VehiculoResponse>> listar(@RequestParam(required = false) EstadoVehiculo estado) {
        List<VehiculoResponse> respuesta = (estado != null
                ? vehiculoService.listarPorEstado(estado)
                : vehiculoService.listarTodos())
                .stream()
                .map(vehiculoMapper::toResponse)
                .toList();
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehiculoResponse> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(vehiculoMapper.toResponse(vehiculoService.obtenerPorId(id)));
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<VehiculoResponse> cambiarEstado(@PathVariable Long id,
                                                           @RequestParam EstadoVehiculo estado) {
        return ResponseEntity.ok(vehiculoMapper.toResponse(vehiculoService.cambiarEstado(id, estado)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        vehiculoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
