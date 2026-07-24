package com.enviosexpress.envios_express_backend.web.mapper;

import com.enviosexpress.envios_express_backend.domain.model.Vehiculo;
import com.enviosexpress.envios_express_backend.web.dto.response.VehiculoResponse;
import org.springframework.stereotype.Component;

@Component
public class VehiculoMapper {

    public VehiculoResponse toResponse(Vehiculo vehiculo) {
        return VehiculoResponse.builder()
                .id(vehiculo.getId())
                .placa(vehiculo.getPlaca())
                .tipo(vehiculo.getTipo())
                .capacidadKg(vehiculo.getCapacidadKg())
                .conductorId(vehiculo.getConductor() != null ? vehiculo.getConductor().getId() : null)
                .conductorNombre(vehiculo.getConductor() != null ? vehiculo.getConductor().getNombre() : null)
                .estado(vehiculo.getEstado())
                .latitudActual(vehiculo.getLatitudActual())
                .longitudActual(vehiculo.getLongitudActual())
                .build();
    }
}
