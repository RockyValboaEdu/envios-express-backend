package com.enviosexpress.envios_express_backend.web.dto.response;

import com.enviosexpress.envios_express_backend.domain.model.EstadoRuta;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RutaResponse {
    private Long id;
    private String nombre;
    private String origen;
    private String destino;
    private Long vehiculoId;
    private String vehiculoPlaca;
    private Long despachadorId;
    private String despachadorNombre;
    private EstadoRuta estado;
    private LocalDateTime fechaProgramada;
    private List<Long> enviosIds;
}
