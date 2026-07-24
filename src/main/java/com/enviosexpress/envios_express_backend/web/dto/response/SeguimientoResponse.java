package com.enviosexpress.envios_express_backend.web.dto.response;

import com.enviosexpress.envios_express_backend.domain.model.EstadoEnvio;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SeguimientoResponse {
    private Long envioId;
    private String codigoSeguimiento;
    private EstadoEnvio estado;
    private Double latitud;
    private Double longitud;
    private String comentario;
    private LocalDateTime timestamp;
}
