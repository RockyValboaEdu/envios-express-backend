package com.enviosexpress.envios_express_backend.web.dto.response;

import com.enviosexpress.envios_express_backend.domain.model.EstadoVehiculo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehiculoResponse {
    private Long id;
    private String placa;
    private String tipo;
    private Double capacidadKg;
    private Long conductorId;
    private String conductorNombre;
    private EstadoVehiculo estado;
    private Double latitudActual;
    private Double longitudActual;
}
