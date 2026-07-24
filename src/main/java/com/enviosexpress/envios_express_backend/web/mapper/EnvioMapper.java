package com.enviosexpress.envios_express_backend.web.mapper;

import com.enviosexpress.envios_express_backend.domain.model.Envio;
import com.enviosexpress.envios_express_backend.domain.model.SeguimientoEvento;
import com.enviosexpress.envios_express_backend.web.dto.response.EnvioResponse;
import com.enviosexpress.envios_express_backend.web.dto.response.SeguimientoResponse;
import org.springframework.stereotype.Component;

@Component
public class EnvioMapper {

    public EnvioResponse toResponse(Envio envio) {
        return EnvioResponse.builder()
                .id(envio.getId())
                .codigoSeguimiento(envio.getCodigoSeguimiento())
                .clienteId(envio.getCliente() != null ? envio.getCliente().getId() : null)
                .clienteNombre(envio.getCliente() != null ? envio.getCliente().getNombre() : null)
                .rutaId(envio.getRuta() != null ? envio.getRuta().getId() : null)
                .direccionEntrega(envio.getDireccionEntrega())
                .destinatarioNombre(envio.getDestinatarioNombre())
                .destinatarioTelefono(envio.getDestinatarioTelefono())
                .estado(envio.getEstado())
                .pesoKg(envio.getPesoKg())
                .fechaCreacion(envio.getFechaCreacion())
                .fechaEntrega(envio.getFechaEntrega())
                .build();
    }

    public SeguimientoResponse toSeguimientoResponse(SeguimientoEvento evento) {
        return SeguimientoResponse.builder()
                .envioId(evento.getEnvio().getId())
                .codigoSeguimiento(evento.getEnvio().getCodigoSeguimiento())
                .estado(evento.getEstado())
                .latitud(evento.getLatitud())
                .longitud(evento.getLongitud())
                .comentario(evento.getComentario())
                .timestamp(evento.getTimestamp())
                .build();
    }
}
