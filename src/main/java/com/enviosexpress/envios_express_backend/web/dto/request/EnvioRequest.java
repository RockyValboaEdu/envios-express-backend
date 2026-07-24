package com.enviosexpress.envios_express_backend.web.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class EnvioRequest {

    @NotNull(message = "El cliente es obligatorio")
    private Long clienteId;

    private Long rutaId;

    @NotBlank(message = "La direccion de entrega es obligatoria")
    private String direccionEntrega;

    private String destinatarioNombre;

    private String destinatarioTelefono;

    @Positive(message = "El peso debe ser positivo")
    private Double pesoKg;
}
