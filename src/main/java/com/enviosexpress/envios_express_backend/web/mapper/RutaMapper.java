package com.enviosexpress.envios_express_backend.web.mapper;

import com.enviosexpress.envios_express_backend.domain.model.Ruta;
import com.enviosexpress.envios_express_backend.web.dto.response.RutaResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RutaMapper {

    public RutaResponse toResponse(Ruta ruta) {
        List<Long> enviosIds = ruta.getEnvios() == null
                ? List.of()
                : ruta.getEnvios().stream().map(e -> e.getId()).collect(Collectors.toList());

        return RutaResponse.builder()
                .id(ruta.getId())
                .nombre(ruta.getNombre())
                .origen(ruta.getOrigen())
                .destino(ruta.getDestino())
                .vehiculoId(ruta.getVehiculo() != null ? ruta.getVehiculo().getId() : null)
                .vehiculoPlaca(ruta.getVehiculo() != null ? ruta.getVehiculo().getPlaca() : null)
                .despachadorId(ruta.getDespachador() != null ? ruta.getDespachador().getId() : null)
                .despachadorNombre(ruta.getDespachador() != null ? ruta.getDespachador().getNombre() : null)
                .estado(ruta.getEstado())
                .fechaProgramada(ruta.getFechaProgramada())
                .enviosIds(enviosIds)
                .build();
    }
}
