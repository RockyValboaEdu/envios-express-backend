package com.enviosexpress.envios_express_backend.domain.service;

import com.enviosexpress.envios_express_backend.domain.model.Envio;
import com.enviosexpress.envios_express_backend.web.dto.request.EnvioRequest;

import java.util.List;

public interface EnvioService {

    Envio crear(EnvioRequest request);

    Envio obtenerPorId(Long id);

    Envio obtenerPorCodigoSeguimiento(String codigo);

    List<Envio> listarTodos();

    List<Envio> listarPorCliente(Long clienteId);

    List<Envio> listarPorRuta(Long rutaId);
}
