package com.enviosexpress.envios_express_backend.domain.service;

import com.enviosexpress.envios_express_backend.domain.model.EstadoRuta;
import com.enviosexpress.envios_express_backend.domain.model.Ruta;
import com.enviosexpress.envios_express_backend.web.dto.request.RutaRequest;

import java.util.List;

public interface RutaService {

    Ruta crear(RutaRequest request);

    Ruta obtenerPorId(Long id);

    List<Ruta> listarTodas();

    List<Ruta> listarPorEstado(EstadoRuta estado);

    Ruta asignarVehiculo(Long rutaId, Long vehiculoId);

    Ruta cambiarEstado(Long id, EstadoRuta nuevoEstado);
}
