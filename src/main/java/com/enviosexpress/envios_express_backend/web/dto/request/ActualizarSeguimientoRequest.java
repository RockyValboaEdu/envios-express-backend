package com.enviosexpress.envios_express_backend.web.dto.request;

import com.enviosexpress.envios_express_backend.domain.model.EstadoEnvio;
import lombok.Data;

@Data
public class ActualizarSeguimientoRequest {

    private Long envioId;

    private EstadoEnvio estado;

    private Double latitud;

    private Double longitud;

    private String comentario;
}
