package com.enviosexpress.envios_express_backend.domain.service;

import com.enviosexpress.envios_express_backend.web.dto.request.LoginRequest;
import com.enviosexpress.envios_express_backend.web.dto.request.RegistroRequest;
import com.enviosexpress.envios_express_backend.web.dto.response.AuthResponse;

public interface AuthService {

    AuthResponse login(LoginRequest request);

    AuthResponse registrar(RegistroRequest request);
}
