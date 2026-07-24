package com.enviosexpress.envios_express_backend.impl;

import com.enviosexpress.envios_express_backend.domain.model.Envio;
import com.enviosexpress.envios_express_backend.domain.model.EstadoEnvio;
import com.enviosexpress.envios_express_backend.domain.model.SeguimientoEvento;
import com.enviosexpress.envios_express_backend.domain.repository.EnvioRepository;
import com.enviosexpress.envios_express_backend.domain.repository.SeguimientoEventoRepository;
import com.enviosexpress.envios_express_backend.domain.service.SeguimientoService;
import com.enviosexpress.envios_express_backend.infrastructure.exception.ResourceNotFoundException;
import com.enviosexpress.envios_express_backend.web.dto.request.ActualizarSeguimientoRequest;
import com.enviosexpress.envios_express_backend.web.dto.response.SeguimientoResponse;
import com.enviosexpress.envios_express_backend.web.mapper.EnvioMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SeguimientoServiceImpl implements SeguimientoService {

    private final SeguimientoEventoRepository seguimientoEventoRepository;
    private final EnvioRepository envioRepository;
    private final SimpMessagingTemplate messagingTemplate;
    private final EnvioMapper envioMapper;

    @Override
    public SeguimientoEvento registrarEvento(ActualizarSeguimientoRequest request) {
        Envio envio = envioRepository.findById(request.getEnvioId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Envio no encontrado con id: " + request.getEnvioId()));

        // Si viene un nuevo estado, se actualiza el envio (y su fecha de entrega si aplica)
        if (request.getEstado() != null) {
            envio.setEstado(request.getEstado());
            if (request.getEstado() == EstadoEnvio.ENTREGADO) {
                envio.setFechaEntrega(LocalDateTime.now());
            }
            envioRepository.save(envio);
        }

        SeguimientoEvento evento = SeguimientoEvento.builder()
                .envio(envio)
                .estado(request.getEstado() != null ? request.getEstado() : envio.getEstado())
                .latitud(request.getLatitud())
                .longitud(request.getLongitud())
                .comentario(request.getComentario())
                .build();

        SeguimientoEvento guardado = seguimientoEventoRepository.save(evento);

        // Difundir el evento en tiempo real a los clientes suscritos a este envio
        SeguimientoResponse mensaje = envioMapper.toSeguimientoResponse(guardado);
        messagingTemplate.convertAndSend("/topic/envios/" + envio.getId(), mensaje);

        return guardado;
    }

    @Override
    public List<SeguimientoEvento> historialPorEnvio(Long envioId) {
        return seguimientoEventoRepository.findByEnvioIdOrderByTimestampAsc(envioId);
    }
}
