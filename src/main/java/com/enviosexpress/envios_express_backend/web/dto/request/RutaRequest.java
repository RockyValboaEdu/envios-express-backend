package com.enviosexpress.envios_express_backend.web.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RutaRequest {

    @NotBlank(message = "El nombre de la ruta es obligatorio")
    private String nombre;

    @NotBlank(message = "El origen es obligatorio")
    private String origen;

    @NotBlank(message = "El destino es obligatorio")
    private String destino;

    private Long vehiculoId;

    @NotNull(message = "El despachador es obligatorio")
    private Long despachadorId;

    private LocalDateTime fechaProgramada;
}
