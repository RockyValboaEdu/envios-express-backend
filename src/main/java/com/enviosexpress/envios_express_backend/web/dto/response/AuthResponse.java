package com.enviosexpress.envios_express_backend.web.dto.response;

import com.enviosexpress.envios_express_backend.domain.model.Rol;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    private String token;
    private Long usuarioId;
    private String nombre;
    private String email;
    private Rol rol;
}
