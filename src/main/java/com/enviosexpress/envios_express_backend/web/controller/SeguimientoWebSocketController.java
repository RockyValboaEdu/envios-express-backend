package com.enviosexpress.envios_express_backend.web.controller;

import com.enviosexpress.envios_express_backend.domain.service.SeguimientoService;
import com.enviosexpress.envios_express_backend.web.dto.request.ActualizarSeguimientoRequest;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

/**
 * Canal STOMP: los conductores (o cualquier fuente de ubicacion) publican aqui.
 *
 * Cliente publica a:      /app/seguimiento
 * Clientes se suscriben a: /topic/envios/{envioId}
 *
 * El SeguimientoService se encarga de persistir el evento y de retransmitirlo
 * a los suscriptores de ese envio especifico.
 */
@Hidden
@Controller
@RequiredArgsConstructor
public class SeguimientoWebSocketController {

    private final SeguimientoService seguimientoService;

    @MessageMapping("/seguimiento")
    public void recibirActualizacion(ActualizarSeguimientoRequest request) {
        seguimientoService.registrarEvento(request);
    }
}
