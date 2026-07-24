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
public class UsuarioResponse {
    private Long id;
    private String nombre;
    private String email;
    private String telefono;
    private Rol rol;
    private boolean activo;
}
