package com.enviosexpress.envios_express_backend.web.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class VehiculoRequest {

    @NotBlank(message = "La placa es obligatoria")
    private String placa;

    @NotBlank(message = "El tipo de vehiculo es obligatorio")
    private String tipo;

    @Positive(message = "La capacidad debe ser positiva")
    private Double capacidadKg;

    private Long conductorId;
}
