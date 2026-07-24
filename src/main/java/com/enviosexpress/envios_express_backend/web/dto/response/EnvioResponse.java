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
public class EnvioResponse {
    private Long id;
    private String codigoSeguimiento;
    private Long clienteId;
    private String clienteNombre;
    private Long rutaId;
    private String direccionEntrega;
    private String destinatarioNombre;
    private String destinatarioTelefono;
    private EstadoEnvio estado;
    private Double pesoKg;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaEntrega;
}
