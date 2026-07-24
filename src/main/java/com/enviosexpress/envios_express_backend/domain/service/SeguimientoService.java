package com.enviosexpress.envios_express_backend.domain.service;

import com.enviosexpress.envios_express_backend.domain.model.SeguimientoEvento;
import com.enviosexpress.envios_express_backend.web.dto.request.ActualizarSeguimientoRequest;

import java.util.List;

public interface SeguimientoService {

    /**
     * Registra un nuevo evento de seguimiento (cambio de estado y/o ubicacion)
     * y lo transmite en tiempo real a los clientes suscritos por WebSocket.
     */
    SeguimientoEvento registrarEvento(ActualizarSeguimientoRequest request);

    List<SeguimientoEvento> historialPorEnvio(Long envioId);
}
